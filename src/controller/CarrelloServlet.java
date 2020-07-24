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
import java.util.Collection;

@WebServlet("/servlet_carrello")
public class CarrelloServlet extends HttpServlet {
    private final CarrelloDAO cdao = new CarrelloDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Carrello cartS;
        Utente u = (Utente) session.getAttribute("utente");

        if (u != null) {        //utente ha effettuato accesso
            cartS = (Carrello) session.getAttribute("carrello");

            if(!cartS.isEmpty()) {
                Collection<Carrello.ProdottoQuantita> prodotti = cartS.getProdotti();

                for(Carrello.ProdottoQuantita pr : prodotti) {
                    if (!cdao.checkUtenteProdotto(u.getIdUser(), pr.getProdotto().getCodice()))
                        cdao.doSave(u.getIdUser(), pr.getProdotto().getCodice(), pr.getQuantita(), (int) pr.getTotCent());
                }
            }
        } else {
            cartS = (Carrello) session.getAttribute("carrello");
        }
        session.setAttribute("carrello", cartS);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/carrello.jsp");
        dispatcher.forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
