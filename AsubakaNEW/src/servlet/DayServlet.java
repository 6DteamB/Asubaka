package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import utils.DBUtility;


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
            // データベース接続情報
        	String url = DBUtility.JDBC_URL;
        	String user = DBUtility.DB_USER;
        	String password = DBUtility.DB_PASSWORD;


//            String jdbcUrl = "jdbc:mysql://172.16.0.178:3306/Asubaka";
//            String dbUser = "sa";
//            String dbPassword = "";

            Connection conn = null;
            try {
                // データベースに接続
            	conn = DriverManager.getConnection(url, user, password);
//                conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

                // main.jsp から送信された name 属性の値を取得
                String nameFromMainJSP = request.getParameter("name");

                // データベース内の 'day' の値を -1 にする条件を設定（name 属性と一致する条件）
                String updateDayQuery = "UPDATE account SET day = day - 1 WHERE name = ?";
                PreparedStatement dayPreparedStatement = conn.prepareStatement(updateDayQuery);
                dayPreparedStatement.setString(1, nameFromMainJSP);
                int dayRowsUpdated = dayPreparedStatement.executeUpdate();

                // データベース内の 'count' の値を +1 にする条件を設定（name 属性と一致する条件）
                String updateCountQuery = "UPDATE account SET count = count + 1 WHERE name = ?";
                PreparedStatement countPreparedStatement = conn.prepareStatement(updateCountQuery);
                countPreparedStatement.setString(1, nameFromMainJSP);
                int countRowsUpdated = countPreparedStatement.executeUpdate();

                // Check the remaining days
                int remainingDays = getRemainingDays(nameFromMainJSP, conn);
                
                if (dayRowsUpdated > 0 && countRowsUpdated > 0) {
                    lastProcessedDate = currentDateString; // 処理が成功した場合、日付を更新
                    
                    // Redirect to reward.jsp if remaining days are 0
                    if (remainingDays == 0) {
                        request.getRequestDispatcher("/AsubakaNEW/servlet/servlet.RewardServlet").forward(request, response);
                    
                    } else {
                        request.getRequestDispatcher("MainServlet.java").forward(request, response);
                    }
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

    // Helper method to get the remaining days from the database
    private int getRemainingDays(String name, Connection connection) throws SQLException {
        String selectRemainingDaysQuery = "SELECT day FROM account WHERE name = ?";
        PreparedStatement selectDaysStatement = connection.prepareStatement(selectRemainingDaysQuery);
        selectDaysStatement.setString(1, name);
        ResultSet resultSet = selectDaysStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("day");
        } else {
            // Handle the case where the user is not found
            return -1;
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    Account account = (Account) session.getAttribute("loggedInAccount");
	    String name = account.getName(); 
	    request.setAttribute("account", account);

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(DBUtility.JDBC_URL, DBUtility.DB_USER, DBUtility.DB_PASSWORD);
        String sql = "SELECT reward FROM Account WHERE name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String reward = resultSet.getString("reward");
            request.setAttribute("reward", reward);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    }