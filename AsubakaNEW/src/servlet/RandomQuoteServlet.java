package servlet;

import java.io.IOException;

import dao.QuoteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RandomQuoteServlet extends HttpServlet {
    private QuoteDAO quoteDAO;

    @Override
    public void init() throws ServletException {
        // データベース接続情報を設定
        String jdbcUrl = "jdbc:mysql://172.16.0.178:3306/Asubaka";
        String jdbcUsername = "sa";
        String jdbcPassword = "";

        try {
            quoteDAO = new QuoteDAO(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            // エラーハンドリング：データベース接続エラー
            e.printStackTrace(); // エラー情報をログに記録するなど、適切な処理を行う
            throw new ServletException("データベース接続エラーが発生しました", e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // ランダムな名言と偉人を取得
            String randomQuoteAndAuthor = quoteDAO.getRandomQuoteAndAuthor();

            // 名言と偉人をJSPに渡す
            req.setAttribute("randomQuoteAndAuthor", randomQuoteAndAuthor);

            // JSPにフォワード
            req.getRequestDispatcher("/main.jsp").forward(req, resp);
        } catch (Exception e) {
            // エラーハンドリング：名言の取得エラー
            e.printStackTrace(); // エラー情報をログに記録するなど、適切な処理を行う
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "名言の取得中にエラーが発生しました");
        }
    }
}
