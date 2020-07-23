package controller;

import model.Prodotto;
import model.ProdottoDAO;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/servlet_ricerca_ajax")
public class RicercaAjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final ProdottoDAO dao = new ProdottoDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query") + "*";
        ArrayList<Prodotto> prodotti = dao.doRetrieveByNome(query, 0,0);

        JSONArray prodJson = new JSONArray();
        for(Prodotto p:prodotti){
            prodJson.put(p.getNome());
        }
        response.setContentType("application/json");
        response.getWriter().append(prodJson.toString());
    }
}
