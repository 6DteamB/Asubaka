package servlet;

import java.io.IOException;

import dao.AccountDAO;
import dao.QuoteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.CatApi;
import model.ConfigLoader;
import model.Login;
import model.LoginLogic;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
    private QuoteDAO quoteDAO;
    private AccountDAO accountDAO;

    // サーブレットの初期化処理
    @Override
    public void init() throws ServletException {
        super.init();

        ServletContext context = getServletContext();
        ConfigLoader.init(context); // CatApiの初期化

        // データベース接続情報を設定
        String jdbcUrl = "jdbc:mysql://172.16.0.178:3306/Asubaka";
        String jdbcUsername = "sa";
        String jdbcPassword = "";

        try {
            quoteDAO = new QuoteDAO(jdbcUrl, jdbcUsername, jdbcPassword);
            accountDAO = new AccountDAO();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("データベース接続エラーが発生しました", e);
        }
    }

    // POSTリクエストの処理
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ログインのロジック   
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        Login login = new Login(name, pass);
        LoginLogic loginLogic = new LoginLogic();
        boolean isLogin = loginLogic.execute(login);
        HttpSession session = request.getSession();

        if (isLogin) {
            session.setAttribute("isLogin", true);
            session.setAttribute("login", login);
            // AccountDAOを使用してAccountインスタンスを取得
            Account account = accountDAO.findByLogin(login);

            // Accountオブジェクトをリクエスト属性として設定
            request.setAttribute("account", account);
            session.setAttribute("loggedInAccount", account);

            // ランダムな名言と作者を取得
            String randomQuoteAndAuthor = quoteDAO.getRandomQuoteAndAuthor();
            request.setAttribute("randomQuoteAndAuthor", randomQuoteAndAuthor);
            // ランダムな猫の画像のURLを取得
            CatApi catApi = new CatApi();
            String catImageUrl = catApi.getRandomCatImage();
            request.setAttribute("animalImagePath", catImageUrl);

            // AccountDAOからJavaScript配列形式の日付データを取得
            String userName = account.getName();
            String datesJavaScriptArrayFromRequest = accountDAO.getDatesAsJavaScriptArray(userName);
            request.setAttribute("datesJavaScriptArray", datesJavaScriptArrayFromRequest);

            // main.jspにフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
            dispatcher.forward(request, response);
        } else {
            // ログインに失敗した場合、エラーメッセージを設定し、ログインページにリダイレクトします
            request.setAttribute("errorMessage", "ユーザー名またはパスワードが正しくありません。もう一度試してください.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}