package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RewardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // クライアントから送信された名前を取得
        String name = "せり"; // 固定の名前

        // Initialize variables to store the reward information
        String reward = "";

        // Database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/your_database";
        String dbUser = "your_username";
        String dbPassword = "your_password";

        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Prepare a SQL statement to retrieve the reward based on the name
            String sql = "SELECT reward FROM Account WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a record was found
            if (resultSet.next()) {
                reward = resultSet.getString("reward");
            }

            // Close the database connections
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions here
        }

        // Set the response content type
        response.setContentType("text/html; charset=UTF-8");

        // Generate the response in HTML format
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>報酬ページ</title></head><body>");
        out.println("<h1>" + name + "さんの報酬</h1>");
        out.println("<p>報酬: " + reward + "</p>");
        out.println("</body></html>");
    }
}
