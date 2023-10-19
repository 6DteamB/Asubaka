package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

public class RewardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームが送信されたときに実行されるPOSTメソッド内で処理を追加
        // この部分で報酬を処理するコードを実装
        // accountオブジェクトから名前と報酬を取得して処理
        Account account = new Account(request.getParameter("name"), "", "", "", "", 0, 0);
        String name = account.getName();
        String reward = account.getReward();

        // ここで報酬の処理を行う（例：報酬を付与、データベースを更新など）

        // 処理が完了したら、再度JSPファイルにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/reward.jsp");
        dispatcher.forward(request, response);
    }
}
