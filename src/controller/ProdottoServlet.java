package controller;

import model.Prodotto;
import model.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet_prodotto")
public class ProdottoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final ProdottoDAO dao = new ProdottoDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codice = Integer.parseInt(request.getParameter("codice"));
        Prodotto prodotto = dao.doRetrieveById(codice);

        if (prodotto == null) {
            throw new ServletException("Film non presente in catalogo :( ");
        }

        request.setAttribute("prodotto", prodotto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/prodotto.jsp");
        dispatcher.forward(request, response);
    }
}
