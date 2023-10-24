package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;



@WebServlet("/RewardServlet")

public class RewardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/reward.jsp").forward(request, response);
    }
    
    	

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // クライアントから送信された名前を取得
        String name = ""; 

        // Initialize variables to store the reward information
        String reward = "";

        // Database connection parameters
        String jdbcUrl = "jdbc:mysql://172.16.0.178:3306/Asubaka";
        String dbUser = "sa";
        String dbPassword = "";
        
        
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loggedInAccount");
        name = account.getName(); 
  
        request.setAttribute("account", account);
        request.setAttribute("reward", reward);


       
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
        request.getRequestDispatcher("/reward.jsp").forward(request, response);


//        // Set the response content type
//        response.setContentType("text/html; charset=UTF-8");

      
    }
}
