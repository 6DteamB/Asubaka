package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountDAO {
    // データベース接続に使用する情報
    private final String JDBC_URL = "jdbc:mysql://172.16.0.178:3306/Asubaka";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";


	public Account findByLogin(Login login) {
        Account account = null;

        // MySQLのJDBCドライバをロード
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        // データベースへ接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // SELECT文を準備
            String sql = "SELECT NAME, PASS, MAIL, OBJECTIVE, REWARD, DAY, COUNT FROM ACCOUNT WHERE NAME = ? AND PASS = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, login.getName());
            pStmt.setString(2, login.getPass());

            // SELECTを実行し、結果表を取得
            ResultSet rs = pStmt.executeQuery();

            // 一致したユーザーが存在した場合
            // そのユーザーを表すAccountインスタンスを生成
            if (rs.next()) {
                // 結果表からデータを取得
                String name = rs.getString("NAME");
                String pass = rs.getString("PASS");
                String mail = rs.getString("MAIL");
                String objective = rs.getString("OBJECTIVE");
                String reward = rs.getString("REWARD");
                int day = rs.getInt("DAY");
                int count =rs.getInt("COUNT");
                account = new Account(name, pass, mail, objective, reward, day, count);
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        // 見つかったユーザーまたはnullを返す
        return null;
    } 

    public void update(Account account) {
        // MySQLのJDBCドライバをロード
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // データベースへの接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            // UPDATE文を準備
            String sql = "UPDATE ACCOUNT SET DAY = ? WHERE NAME = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, account.getDay());
            pStmt.setString(2, account.getName());

            // UPDATEを実行
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // 例外処理を行うか、エラーハンドリングを適切に行ってください
        }
    }

	public Account getAccountByName(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
