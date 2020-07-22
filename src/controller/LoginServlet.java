package controller;

import model.Utente;
import model.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet_login")
public class LoginServlet extends HttpServlet {
    private final UtenteDAO dao = new UtenteDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String u = request.getParameter("username");
        String p = request.getParameter("password");
        Utente utente = null;

        if(u != null && p !=null){
            utente = dao.doRetrieveByUsernamePassword(u,p);
        }

        if(utente == null){
            throw new controller.ServletException("Username e/o Password non validi");
        }
        request.getSession().setAttribute("utente", utente);

        String dest = request.getHeader("referer");
        if(dest == null || dest.contains("/servlet_login") || dest.trim().isEmpty()){
           dest = ".";
        }
        response.sendRedirect(dest);
    }
}
