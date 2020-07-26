package controller;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/servlet_dettagli")
public class DettagliServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente ut = (Utente) request.getSession().getAttribute("utente");
        if(ut == null){
            throw new controller.ServletException("Utente non loggato");
        }
        if(!ut.isAdmin()){
            throw new controller.ServletException("Pagina accessibile solo agli admin");
        }
        int id = Integer.parseInt(request.getParameter("idUtente"));
        OrdineDAO odao = new OrdineDAO();
        ProdottoDAO pdao = new ProdottoDAO();

        ArrayList<Ordine> ordiniUtente = odao.doRetrieveByUtenteAll(id);
        if(ordiniUtente != null){
            for (Ordine e: ordiniUtente) {
                ArrayList<Integer> codiciProdotti = new ArrayList<>();
                codiciProdotti = odao.doRetrieveByOrdineAll(e.getId(), ut.getIdUser());
                ArrayList<Prodotto> prodottiOrdini = new ArrayList<>();

                for (Integer i:codiciProdotti) {
                    Prodotto p = new Prodotto();
                    p = pdao.doRetrieveById(i);
                    prodottiOrdini.add(p);
                }
                e.setProdotti(prodottiOrdini);
            }
        }
        request.setAttribute("ordini", ordiniUtente);

        request.getSession().setAttribute("utente", ut);
        RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/results/admindettagli.jsp");
        disp.forward(request, response);
    }
}
