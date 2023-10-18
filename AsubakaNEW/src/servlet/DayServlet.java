package servlet;

import java.io.IOException;

import dao.AccountDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
 
public class DayServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 初期のJSPページのGETリクエストを処理
        // ここにロジックやデータ取得を配置できます
        
        // JSPページにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AsubakaNEW/main.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // "achievedButton" パラメータが存在するか確認
        if (request.getParameter("achievedButton") != null) {
            // リクエストから現在の Account オブジェクトを取得
            Account account = (Account) request.getAttribute("account");

            // account.day の値を 1 減らす
            if (account != null) {
                account.setDay(account.getDay() - 1);

                // AccountDAO を使用してデータベースでアカウントを更新
                AccountDAO accountDAO = new AccountDAO();
                accountDAO.update(account);
            }
        }

        // JSPページにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AsubakaNEW/main.jsp");
        dispatcher.forward(request, response);
    }
}
