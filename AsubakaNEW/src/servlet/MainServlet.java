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
	    
	    
	    
	    @Override
	    public void init() throws ServletException {
	        super.init();

	        ServletContext context = getServletContext();
	        ConfigLoader.init(context);  // Initialize for CatApi
	        
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
	    
	    
	    
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
	     // ユーザがログインしているかをチェック
	        HttpSession session = req.getSession();
	        Boolean isLogin = (Boolean) session.getAttribute("isLogin");

	        if (isLogin == null || !isLogin) {
	            // ログインしていない場合、ログインフォームを表示
	            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
	            dispatcher.forward(req, resp);
	            return;
	        }
	    	
	    	try {
	            // ランダムな名言の取得
	            String randomQuoteAndAuthor = quoteDAO.getRandomQuoteAndAuthor();
	            req.setAttribute("randomQuoteAndAuthor", randomQuoteAndAuthor);
	            
	            // cat APIのURLの取得
	            CatApi catApi = new CatApi();
	            String catImageUrl = catApi.getRandomCatImage();
	            req.setAttribute("animalImagePath", catImageUrl);
	            
	            // main.jspにフォワード
	            req.getRequestDispatcher("/main.jsp").forward(req, resp);
	        } catch (Exception e) {
	            e.printStackTrace();
	            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred while processing the request");
	        }
	    }
	    
	    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// ログインのロジック
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        Login login = new Login(name, pass);
        LoginLogic loginLogic = new LoginLogic();
        boolean isLogin = loginLogic.execute(login);
        HttpSession session = request.getSession();
        session.setAttribute("isLogin", isLogin);
        if (isLogin) {
            session.setAttribute("login", login);
        }


        // accountDAOを使用してAccountインスタンスを取得
        Account account = accountDAO.findByLogin(login);

        // Accountオブジェクトをリクエスト属性として設定
        request.setAttribute("account", account);
        
        
   
        // ランダムな名言と作者を取得
        String randomQuoteAndAuthor = quoteDAO.getRandomQuoteAndAuthor();
        request.setAttribute("randomQuoteAndAuthor", randomQuoteAndAuthor);

        // ランダムな猫の画像のURLを取得
        CatApi catApi = new CatApi();
        String catImageUrl = catApi.getRandomCatImage();
        request.setAttribute("animalImagePath", catImageUrl);
        
        

   

     // main.jspにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
        dispatcher.forward(request, response);
  
      }
    }
        
        

