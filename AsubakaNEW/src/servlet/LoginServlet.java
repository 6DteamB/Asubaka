package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームからユーザー名とパスワードを取得
        String username = request.getParameter("name");
        String password = request.getParameter("pass");

        // ここでユーザー認証のロジックを実装
        // 仮の認証ロジック: ユーザー名とパスワードが"admin"の場合に認証成功とする
        if ("admin".equals(username) && "adminpassword".equals(password)) {
            // 認証成功時の処理
            response.sendRedirect(request.getContextPath() + "/main.jsp");
        } else {
            // 認証失敗時の処理
            // 例: エラーメッセージを設定してログインページに戻る
            request.setAttribute("errorMessage", "認証に失敗しました");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}

