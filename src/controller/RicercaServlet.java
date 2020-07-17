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
import java.util.ArrayList;

@WebServlet("/servlet_ricerca")
public class RicercaServlet extends HttpServlet {

    private final ProdottoDAO dao = new ProdottoDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parolaCercata = request.getParameter("query");
    if(parolaCercata == "" || parolaCercata == null){
            throw new controller.ServletException("Ehy! Non hai inserito nessuna parola...riprova");
        }

        ArrayList<Prodotto> prodotti = dao.doRetrieveByNomeOrDescrizione( parolaCercata, 0, 10);

        request.setAttribute("parolaCercata", parolaCercata);
        request.setAttribute("prodotti", prodotti);

        RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/results/ricerca.jsp");
        disp.forward(request, response);
    }
}
