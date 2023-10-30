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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import utils.DBUtility;

@WebServlet("/DayServlet")
public class DayServlet extends HttpServlet {
    private static String lastProcessedDate = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateString = dateFormat.format(currentDate);

        if (lastProcessedDate == null || !lastProcessedDate.equals(currentDateString)) {
            String url = DBUtility.JDBC_URL;
            String user = DBUtility.DB_USER;
            String password = DBUtility.DB_PASSWORD;

            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, password);

                String nameFromMainJSP = request.getParameter("name");

             // "DATE"列に現在の日付を追加するクエリ
                String addCurrentDateQuery = "UPDATE account SET DATE = CONCAT(DATE, ', ?') WHERE name = ?";
                PreparedStatement datePreparedStatement = conn.prepareStatement(addCurrentDateQuery);
                datePreparedStatement.setString(1, currentDateString);
                datePreparedStatement.setString(2, nameFromMainJSP);
                datePreparedStatement.executeUpdate();


                String updateDayQuery = "UPDATE account SET day = day - 1 WHERE name = ?";
                PreparedStatement dayPreparedStatement = conn.prepareStatement(updateDayQuery);
                dayPreparedStatement.setString(1, nameFromMainJSP);
                int dayRowsUpdated = dayPreparedStatement.executeUpdate();

                String updateCountQuery = "UPDATE account SET count = count + 1 WHERE name = ?";
                PreparedStatement countPreparedStatement = conn.prepareStatement(updateCountQuery);
                countPreparedStatement.setString(1, nameFromMainJSP);
                int countRowsUpdated = countPreparedStatement.executeUpdate();

                int remainingDays = getRemainingDays(nameFromMainJSP, conn);

                if (dayRowsUpdated > 0 && countRowsUpdated > 0) {
                    lastProcessedDate = currentDateString;

                    if (remainingDays == 0) {
                        request.getRequestDispatcher("/reward.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("MainServlet.java").forward(request, response);
                    }
                } else {
                    request.setAttribute("errorMessage", "更新に失敗しました。");
                    request.getRequestDispatcher("Error.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "データベースエラーが発生しました。");
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            } finally {
                {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            request.setAttribute("errorMessage", "本日は既に処理済みです。");
            request.getRequestDispatcher("MainServlet.java").forward(request, response);
        }
    }

    private int getRemainingDays(String name, Connection connection) throws SQLException {
        String selectRemainingDaysQuery = "SELECT day FROM account WHERE name = ?";
        PreparedStatement selectDaysStatement = connection.prepareStatement(selectRemainingDaysQuery);
        selectDaysStatement.setString(1, name);
        ResultSet resultSet = selectDaysStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("day");
        } else {
            return -1;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loggedInAccount");
        String name = account.getName();
        request.setAttribute("account", account);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DBUtility.JDBC_URL, DBUtility.DB_USER,
                    DBUtility.DB_PASSWORD);
            String sql = "SELECT reward FROM Account WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String reward = resultSet.getString("reward");
                account.setReward(reward);
                session.setAttribute("loggedInAccount", account);
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
