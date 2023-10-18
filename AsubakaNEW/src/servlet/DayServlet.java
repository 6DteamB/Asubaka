package servlet;

import java.io.IOException;

import dao.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
 
public class DayServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 初期のJSPページのGETリクエストを処理
        // ここにロジックやデータ取得を配置できます
        
        // MainServlet にリダイレクト
        response.sendRedirect(request.getContextPath() + "/MainServlet.java");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストから現在の Account オブジェクトを取得
        Account account = (Account) request.getAttribute("account");

        // account.day の値を 1 減らす
        if (account != null) {
            int currentDay = account.getDay();
            if (currentDay > 0) {
                account.setDay(currentDay - 1);

                // AccountDAO を使用してデータベースでアカウントを更新
                AccountDAO accountDAO = new AccountDAO();
                accountDAO.update(account);
            }
        }

        // MainServlet にリダイレクト
        response.sendRedirect(request.getContextPath() + "/MainServlet.java");
    }
}