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
//	private static String lastProcessedDate = null;

	// HTTP POSTリクエストを処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    String lastProcessedDate = (String) session.getAttribute("lastProcessedDate");

	    Date currentDate = new Date();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String currentDateString = dateFormat.format(currentDate);
	    
	    
		// 前回の処理日付と比較して、処理を続行するかどうかを判断
	    if (lastProcessedDate == null || !lastProcessedDate.equals(currentDateString)) {
			
//			Connection conn = null;
			 // データベース接続と処理
	        try (Connection conn = DriverManager.getConnection(DBUtility.JDBC_URL, DBUtility.DB_USER, DBUtility.DB_PASSWORD)) {

				// ユーザーからの入力を取得
				String nameFromMainJSP = request.getParameter("name");
				String passFromMainJSP = request.getParameter("pass");

				// ユーザーアカウントの日数を更新するSQLクエリ
				String updateDayQuery = "UPDATE account SET day = day - 1, ";

				// 既存のDATEカラムが値を持っているかを確認し、次の空いているDATEnカラムに値を挿入
				for (int i = 66; i >= 2; i--) {
					String currentColumn = "DATE" + i;
					String previousColumn = "DATE" + (i - 1);
					updateDayQuery += currentColumn + " = IF(" + previousColumn + " IS NOT NULL, " + previousColumn
							+ ", " + currentColumn + "), ";
				}

				// 最後に新しい日付をDATE1のカラムに挿入
				updateDayQuery += "DATE1 = CURDATE()";

				updateDayQuery += " WHERE name = ? AND pass = ?";
				PreparedStatement dayPreparedStatement = conn.prepareStatement(updateDayQuery);
				dayPreparedStatement.setString(1, nameFromMainJSP);
				dayPreparedStatement.setString(2, passFromMainJSP); // プログラム内で定義されたパスワード

				int dayRowsUpdated = dayPreparedStatement.executeUpdate();

				// ユーザーアカウントのカウントを更新するSQLクエリ
				String updateCountQuery = "UPDATE account SET count = count + 1 WHERE name = ?";
				PreparedStatement countPreparedStatement = conn.prepareStatement(updateCountQuery);
				countPreparedStatement.setString(1, nameFromMainJSP);
				int countRowsUpdated = countPreparedStatement.executeUpdate();
				
				

				// 残りの日数を取得
				int remainingDays = getRemainingDays(nameFromMainJSP, conn);

				  if (dayRowsUpdated > 0 && countRowsUpdated > 0) {
	                    // 処理日をセッションに保存
	                    session.setAttribute("lastProcessedDate", currentDateString);

	                    if (remainingDays == 0) {
	                        // 報酬ページにリダイレクト
	                        request.getRequestDispatcher("/reward.jsp").forward(request, response);
	                    } else {
	                        // メインページにリダイレクト
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
	            }
	        } else {
	            request.setAttribute("errorMessage", "本日は既に処理済みです。");
	            request.getRequestDispatcher("MainServlet.java").forward(request, response);
	        }
	    }

	    // 指定したユーザーの残りの日数を取得する
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

	    // HTTP GETリクエストを処理
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        Account account = (Account) session.getAttribute("loggedInAccount");
	        String name = account.getName();
	        request.setAttribute("account", account);

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            try (Connection connection = DriverManager.getConnection(DBUtility.JDBC_URL, DBUtility.DB_USER, DBUtility.DB_PASSWORD)) {
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
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}