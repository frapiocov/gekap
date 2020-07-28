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
    private OrdineDAO odao = new OrdineDAO();
    private ProdottoDAO pdao = new ProdottoDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente ut = (Utente) request.getSession().getAttribute("utente");
        if(ut == null){
            throw new controller.ServletException("Utente non loggato.");
        }
        if(!ut.isAdmin()){
            throw new controller.ServletException("Pagina accessibile solo agli admin.");
        }

        int id = Integer.parseInt(request.getParameter("idUtente"));

        ArrayList<Ordine> ordiniUtente = odao.doRetrieveByUtenteAll(id);    //prendiamo tutti gli ordini dell'utente

        if(ordiniUtente != null){
            for (Ordine e: ordiniUtente) {
                ArrayList<Integer> codiciProdotti = odao.doRetrieveByOrdineAll(e.getId(),id);   //prendiamo i codici dei prodotti per ogni ordine
                ArrayList<Prodotto> prodottiOrdini = new ArrayList<>();

                for (Integer i:codiciProdotti) {    //riempiamo l'array di prodotti con i prodotti relativi ai vari codici
                    Prodotto p = pdao.doRetrieveById(i);
                    prodottiOrdini.add(p);
                }
                e.setProdotti(prodottiOrdini);     //aggiorniamo i prodotti relativi all'ordine, per ogni ordine
            }
        }

        request.setAttribute("ordini", ordiniUtente);
        request.getSession().setAttribute("utente", ut);

        RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/results/admindettagli.jsp");
        disp.forward(request, response);
    }
}
