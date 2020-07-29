package controller;

import model.Categoria;
import model.CategoriaDAO;
import model.Prodotto;
import model.ProdottoDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "", name = "HomeServlet", loadOnStartup = 1)
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final ProdottoDAO prodottoDAO = new ProdottoDAO();

    public void init() throws ServletException {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorie = categoriaDAO.doRetrieveAll();
        getServletContext().setAttribute("categorie", categorie);     //settiamo le categorie del database in un attributo nel contesto dell'applicazione
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Prodotto> prodotti = prodottoDAO.doRetrieveAll(0,14);

        int max = prodottoDAO.returnMaxCodice() - 5;
        List<Prodotto> evidenza = prodottoDAO.doRetrieveAll( max, 5);

        request.setAttribute("prodotti", prodotti);                 //inseriamo nella request tutti i prodotti e i prodotti in evidenza
        request.setAttribute("prodotti_evidenza", evidenza);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/index.jsp");
        dispatcher.forward(request, response);                          //la jsp index si occuperà di scrivere e mostrare la risposta
    }
}