package servlet;

import java.io.IOException;

import dao.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DayServlet")
public class DayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ここでAccountDao.javaのday値を1減らす処理を行います
        AccountDAO accountDao = new AccountDAO();
        int currentDay = accountDao.getDay(); // AccountDaoからday値を取得
        if (currentDay > 0) {
            accountDao.setDay(currentDay - 1); // day値を1減らす
        }

        // MainServlet.javaにフォワード
        request.getRequestDispatcher("MainServlet").forward(request, response);
    }
}