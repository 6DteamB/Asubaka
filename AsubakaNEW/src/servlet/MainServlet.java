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
 
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームからユーザー名とパスワードの値を取得
        String username = request.getParameter("name");
        String password = request.getParameter("pass");

        // ユーザー名とパスワードを使用してLoginオブジェクトを作成
        Login login = new Login(username, password);

        // AccountDAOを使用してAccountインスタンスを取得
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.findByLogin(login);

        // Accountオブジェクトをリクエスト属性として設定
        request.setAttribute("account", account);

        // リクエストをJSPにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("//main.jsp");
        dispatcher.forward(request, response);
    }
}
