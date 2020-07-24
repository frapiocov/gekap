package controller;

import model.PreferitiDAO;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/servlet_preferiti")
public class PreferitiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final ProdottoDAO dao = new ProdottoDAO();
    private final PreferitiDAO prefdao = new PreferitiDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente ut = (Utente) request.getSession().getAttribute("utente");
        if(ut == null){
            throw new controller.ServletException("Utente non loggato. Per accedere alla WishList fai il login o registrati");
        }

        String codProdotto = request.getParameter("id");
        ArrayList<Prodotto> preferiti = new ArrayList<>();
        int idUtente = ut.getIdUser();

        if(codProdotto != null) {
            int idProdotto = Integer.parseInt(codProdotto);

            if(prefdao.existProdotto(idUtente, idProdotto) == 0){
                if (prefdao.ListaExist(idUtente) == 0) {
                    prefdao.doSaveLista(idUtente);
                }
                prefdao.doSaveProdotto(idProdotto, idUtente);
            }
        }
        ArrayList<Integer> lista = prefdao.doRetrieveByUtente(idUtente);

        for (Integer i:lista) {
            Prodotto p = dao.doRetrieveById(i);
            preferiti.add(p);
        }
        request.setAttribute("preferiti", preferiti);
        RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/results/preferiti.jsp");
        disp.forward(request, response);
    }
}
