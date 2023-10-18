package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CatApi;
import model.ConfigLoader;

@WebServlet("/main")
public class CatApiServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        ConfigLoader.init(context);  // <-- ここで初期化
        CatApi catApi = new CatApi();
        String catImageUrl = catApi.getRandomCatImage();

        request.setAttribute("animalImagePath", catImageUrl);

        // main.jspにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
        dispatcher.forward(request, response);
    }
}
