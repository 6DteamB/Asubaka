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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // フォームから新規登録情報を取得
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String mail = request.getParameter("mail");
        String objective = request.getParameter("objective");
        String reward = request.getParameter("reward");

        // データベースに新規登録情報を保存
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // JDBCドライバをロード
            Class.forName("com.mysql.jdbc.Driver");

            // データベースに接続
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // SQLクエリを作成（適切なテーブル名とカラム名に置き換えてください）
            String sql = "INSERT INTO account (NAME, PASS, MAIL, OBJECTIVE, REWARD) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, mail);
            preparedStatement.setString(4, objective);
            preparedStatement.setString(5, reward);

            // クエリを実行し、新規登録情報をデータベースに保存
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // 登録成功した場合、index.jsp にリダイレクト
                response.sendRedirect("/AsubakNEW/index.jsp");
            } else {
                // 登録に失敗した場合の処理を追加（例: エラーメッセージの表示）
                // ログにエラーメッセージを出力
                System.err.println("Registration failed. No rows affected.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            // 例外処理（エラーログの出力など）
            e.printStackTrace();
            // ログにエラーメッセージを出力
            System.err.println("An error occurred: " + e.getMessage());
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
            }
        }
    }
}
