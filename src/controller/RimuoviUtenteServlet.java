package controller;

import model.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet_rimuovi_utente")
public class RimuoviUtenteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final UtenteDAO dao = new UtenteDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));      //prendiamo il codice dell'utente da rimuovere
        dao.doDelete(id);

        String dest = request.getHeader("referer");
        if(dest == null || dest.contains("/servlet_rimuovi_utente") || dest.trim().isEmpty()){
            dest = ".";
        }
        response.sendRedirect(dest);
    }
}
