package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://172.16.0.178:3306/Asubaka";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "";

    static {
        // JDBCドライバをロード
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // クラスが見つからなかった場合のエラーハンドリング
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // フォームから新規登録情報を取得
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String mail = request.getParameter("mail");
        String objective = request.getParameter("objective");
        String reward = request.getParameter("reward");
        String day = request.getParameter("day"); 
        String count = request.getParameter("count"); 

        // データベースに新規登録情報を保存
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try { 
            // データベースに接続
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // SQLクエリを作成（適切なテーブル名とカラム名に置き換えてください）
            String sql = "INSERT INTO account (NAME, PASS, MAIL, OBJECTIVE, REWARD, DAY, COUNT) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, mail);
            preparedStatement.setString(4, objective);
            preparedStatement.setString(5, reward);
            preparedStatement.setString(6, day);
            preparedStatement.setString(7, count);

            // クエリを実行し、新規登録情報をデータベースに保存
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // 登録成功した場合、index.jsp にリダイレクト
                response.sendRedirect("/AsubakaNEW/RegisterSuccses.jsp");
            } else {
                // 登録に失敗した場合の処理を追加
                // ユーザーフレンドリーなエラーメッセージを提供
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "登録に失敗しました。もう一度お試しください。");
            }
        } catch (SQLException e) {
            // 例外処理（エラーログの出力など）
            e.printStackTrace();
            // ログにエラーメッセージを出力
            System.err.println("An error occurred: " + e.getMessage());
            // ユーザーフレンドリーなエラーメッセージを提供
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "データベースエラーが発生しました。管理者に連絡してください。");
        } finally {
            // リソースのクローズ
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // ログにエラーメッセージを出力
                System.err.println("Error while closing resources: " + e.getMessage());
                // ユーザーフレンドリーなエラーメッセージを提供
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "データベースリソースのクローズ中にエラーが発生しました。管理者に連絡してください。");
            }
        }
    }
}
