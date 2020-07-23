package controller;

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

    ProdottoDAO dao = new ProdottoDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente ut = (Utente) request.getSession().getAttribute("utente");
        if(ut == null){
            throw new controller.ServletException("Utente non loggato. Per accedere alla WishList fai il login o registrati.");
        }

        String codice = request.getParameter("id");

        if(codice != null){
            int id = Integer.parseInt(codice);
            Prodotto p = dao.doRetrieveById(id);

            ArrayList<Prodotto> preferiti = new ArrayList<>();

            if(request.getSession().getAttribute("preferiti") != null){

                preferiti = (ArrayList<Prodotto>) request.getSession().getAttribute("preferiti");
                preferiti.add(p);
            }
            request.getSession().setAttribute("preferiti", preferiti);
        }

        RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/results/preferiti.jsp");
        disp.forward(request, response);
    }
}
