package controller;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/servlet_carrello")
public class CarrelloServlet extends HttpServlet {
    private final ProdottoDAO pdao = new ProdottoDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codice = request.getParameter("codice_prodotto");
        String quantita = request.getParameter("quantity");
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");

        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }

        if (codice != null) {   //inserimento o rimozione
            int prodId = Integer.parseInt(codice);

            if (quantita != null) { //inserisco
                int addNum = Integer.parseInt(quantita);

                Carrello.ProdottoQuantita prodQuant = carrello.get(prodId);
                if (prodQuant != null) { //controllo se esiste già il prodotto nel carrello
                    prodQuant.setQuantita(prodQuant.getQuantita() + addNum);
                } else {
                    carrello.put(pdao.doRetrieveById(prodId), addNum);
                }
            } else { //rimuovo in quanto non ho la quantita
                Carrello.ProdottoQuantita prodQuant = carrello.get(prodId);

                if (prodQuant.getQuantita() > 1) {
                    prodQuant.setQuantita(prodQuant.getQuantita() - 1);
                } else {
                    carrello.remove(prodId);
                }
            }
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/carrello.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
