package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;  // Added import
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;  // Added import
import model.User;


@WebServlet("/Main")
public class Main extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
	  
	  
	  HttpSession session = request.getSession();
      User loginUser = (User) session.getAttribute("loginUser");
      
     
      
      if (loginUser == null) {
          // ログインしていない場合、ログインページにリダイレクト
          response.sendRedirect("index.jsp");
          return;
      }

      // データベースから必要な情報を取得
      // 例: 達成度、達成日数、残り日数、達成履歴など
      int achievedDays = 0;  // Placeholder value
      int remainingDays = 66 - achievedDays;
      String animalImagePath = ""; 
      
      
//      List<History> achievementHistory = null;  // Placeholder value

      

      // データをリクエスト属性に設定
      request.setAttribute("achievedDays", achievedDays);
      request.setAttribute("remainingDays", remainingDays);
      request.setAttribute("animalImagePath", animalImagePath);
//      request.setAttribute("achievementHistory", achievementHistory);

      // JSPにフォワード
      RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
      dispatcher.forward(request, response);
  }
 {

  }
}