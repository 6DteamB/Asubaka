package servlet;

import java.io.IOException;

import dao.AccountDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.Login;

public class RewardServlet extends HttpServlet {
	// RewardServlet.java の doGet メソッド内
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // セッションからログイン情報を取得
	    Login login = (Login) request.getSession().getAttribute("loggedInUser");
	    
	    if (login != null) {
	        // AccountDAOを使用してログインユーザーのアカウント情報を取得
	        AccountDAO accountDAO = new AccountDAO();
	        Account account = accountDAO.findByLogin(login);
	        
	        if (account != null) {
	            // アカウント情報をリクエスト属性に設定
	            request.setAttribute("account", account);
	        } else {
	            // アカウントが見つからなかった場合の処理
	            // 例: エラーメッセージを設定する
	            request.setAttribute("error", "アカウント情報が見つかりません。");
	        }
	    } else {
	        // ログイン情報がセッションに見つからない場合の処理
	        // 例: エラーメッセージを設定する
	        request.setAttribute("error", "ログインしていません。");
	    }
	    
	    // JSPファイルにフォワード
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/reward.jsp");
	    dispatcher.forward(request, response);
	}
}