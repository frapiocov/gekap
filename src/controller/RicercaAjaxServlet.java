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
        JSONArray prodJson = new JSONArray();
        String query = request.getParameter("query");

        if(query != null) {
            ArrayList<Prodotto> prodotti = dao.doRetrieveByNome(query + "*", 0,10);     //l'asterisco indica che ci possono essere altre lettere dopo query
            for(Prodotto p:prodotti){   //creiamo array JSON, in cui salviamo i nomi di tutti i prodotti conformi alla ricerca
                prodJson.put(p.getNome());
            }
        }
        response.setContentType("application/json");
        response.getWriter().append(prodJson.toString());
    }
}
