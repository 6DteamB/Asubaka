package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuoteDAO {
    // データベース接続に使用する情報
    private final String JDBC_URL = "jdbc:mysql://172.16.0.178:3306/Asubaka";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";

    public String getRandomQuoteAndAuthor() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String query = "SELECT 名言, 偉人 FROM quotes ORDER BY RAND() LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String quote = resultSet.getString("名言");
                String author = resultSet.getString("偉人");
                return quote + " - " + author;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "名言が見つかりませんでした。";
    }
}
