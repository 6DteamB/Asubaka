package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DayServlet extends HttpServlet {
    // クライアントが同じ日に複数回実行できないように、日付をトラッキングするための変数を追加
    private static String lastProcessedDate = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 現在の日付を取得
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateString = dateFormat.format(currentDate);

        // クライアントが同じ日に複数回実行できないように日付をチェック
        if (lastProcessedDate == null || !lastProcessedDate.equals(currentDateString)) {
            // データベース接続情報（安全に保管するべき）
            String jdbcUrl = "jdbc:mysql://172.16.0.178:3306/Asubaka";
            String dbUser = "sa";
            String dbPassword = "";

            Connection conn = null;
            try {
                // データベースに接続
                conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

                // main.jsp から送信された name 属性の値を取得
                String nameFromMainJSP = request.getParameter("name");

                // データベース内の 'day' の値を -1 にする条件を設定（name 属性と一致する条件）
                String updateQuery = "UPDATE account SET day = day - 1 WHERE name = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                preparedStatement.setString(1, nameFromMainJSP);
                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    lastProcessedDate = currentDateString; // 処理が成功した場合、日付を更新
                    request.getRequestDispatcher("MainServlet.java").forward(request, response);
                } else {
                    // If no rows were updated, set an error message and forward to an error page
                    request.setAttribute("errorMessage", "更新に失敗しました。");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Set an error message for database connection error
                request.setAttribute("errorMessage", "データベースエラーが発生しました。");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            // 同じ日に2回目以降の実行を制限し、エラーメッセージを表示
            request.setAttribute("errorMessage", "本日は既に処理済みです。");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
    }
}
