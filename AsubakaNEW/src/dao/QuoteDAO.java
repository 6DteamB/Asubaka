package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuoteDAO {
	private String jdbcUrl;
	private String jdbcUsername;
	private String jdbcPassword;

	    public QuoteDAO(String jdbcUrl, String jdbcUsername, String jdbcPassword) {
	        this.jdbcUrl =jdbcUrl;
	        this.jdbcUsername = jdbcUsername;
	        this.jdbcPassword = jdbcPassword;
	    }
	    
    public String getRandomQuoteAndAuthor() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
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