package controller;

import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/servlet_rimuovi_preferiti")
public class RimuoviPreferitiServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final ProdottoDAO dao = new ProdottoDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente ut = (Utente) request.getSession().getAttribute("utente");
        if(ut == null){
            throw new controller.ServletException("Utente non loggato. Per accedere alla WishList fai il login o registrati");
        }

        String strcod = request.getParameter("id");
        int codice=0;
        Prodotto p;
        ArrayList<Prodotto> newpref;

        newpref=(ArrayList<Prodotto>) request.getSession().getAttribute("preferiti");

        if(strcod!=null) {
            codice = Integer.parseInt(strcod);
            p = dao.doRetrieveById(codice);

            newpref.remove(p);

            request.getSession().setAttribute("preferiti", newpref);
        } else {
            request.getSession().removeAttribute("preferiti");
        }

        String dest = request.getHeader("referer");
        if(dest == null || dest.contains("/servlet_rimuovi_preferiti") || dest.trim().isEmpty()){
            dest = ".";
        }
        response.sendRedirect(dest);
    }
}
