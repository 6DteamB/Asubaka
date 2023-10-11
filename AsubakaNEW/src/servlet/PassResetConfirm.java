package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/resetPasswordConfirm")
public class PassResetConfirm extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resetCode = request.getParameter("code");
        String email = request.getParameter("email");
        // ここにリセットコードとメールアドレスを使用してユーザを検索し、新しいパスワードを設定するコードを実装

        // 例: ダミーコード（実際の実装は異なります）
        boolean resetSuccessful = resetPassword(email, resetCode); // パスワードリセット

        if (resetSuccessful) {
            // パスワードリセット成功時の処理
            response.sendRedirect("login.jsp"); // ログインページにリダイレクト
        } else {
            // パスワードリセット失敗時の処理
            response.sendRedirect("resetPasswordConfirm.jsp"); // リセット確認ページにリダイレクト
        }
    }

    private boolean resetPassword(String email, String resetCode) {
        // パスワードリセットのロジックを実装
        // ここではデータベース内のパスワードを更新するコードを書く必要があります
        return true; // 仮の成功フラグ
    }
}
