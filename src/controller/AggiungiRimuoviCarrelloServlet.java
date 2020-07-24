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

@WebServlet("/servlet_aggiungi_rimuovi_carrello")
public class AggiungiRimuoviCarrelloServlet extends HttpServlet {
    private final CarrelloDAO dao = new CarrelloDAO();
    private final ProdottoDAO pdao = new ProdottoDAO();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Utente u = (Utente) session.getAttribute("utente");
        Carrello cart = (Carrello) session.getAttribute("carrello");

        if (cart == null) {
            if (u == null) {
                cart = new Carrello();
            } else {
                cart = dao.doRetrieveByUtente(u.getIdUser());
            }
            session.setAttribute("carrello", cart);
        }

        String codice = request.getParameter("codice_prodotto");
        String quantita = request.getParameter("quantity");

        if (codice != null) {
            int codProd = Integer.parseInt(codice);
            if (quantita != null) {      //inserimento
                int q = Integer.parseInt(quantita);

                Carrello.ProdottoQuantita prodotto = cart.get(codProd);

                if (prodotto != null) {
                    prodotto.setQuantita(prodotto.getQuantita() + q);

                    if (u != null)
                        dao.doUpdate(u.getIdUser(), codProd, q);
                } else {
                    Prodotto p = pdao.doRetrieveById(codProd);
                    cart.put(p, q);

                    if (u != null)
                        dao.doSave(u.getIdUser(), codProd, q, p.getPrezzoCent() * q);
                }
            } else {
                cart.remove(codProd);
                //dao.doDeleteById(u.getIdUser(),codProd);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/carrello.jsp");
            dispatcher.forward(request, response);
        }
    }

        public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
        }
    }
