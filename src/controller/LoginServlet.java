package controller;

import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

@WebServlet("/servlet_login")
public class LoginServlet extends HttpServlet {
    private final UtenteDAO dao = new UtenteDAO();
    private final LoginDAO ldao = new LoginDAO();

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
            throw new controller.ServletException("Username e/o Password non validi.");
        }

        request.getSession().setAttribute("utente", utente);
 
        Login login = new Login();
        login.setIdutente(utente.getIdUser());
        login.setToken(UUID.randomUUID().toString()); //settiamo il token in modo univoco e random associato all'utente che ha eseguito il login
        login.setTime(Timestamp.from(Instant.now())); //settiamo la data attuale del momento in cui ho eseguito il login

        ldao.doSave(login);

        Cookie cookie = new Cookie("login", login.getId() + "_" + login.getToken()); //creiamo cookie relativo al login
        cookie.setMaxAge(30 * 24 * 60 * 60); //cookie cancellato dopo 30 giorni
        response.addCookie(cookie);

        String dest = request.getHeader("referer");
        if(dest == null || dest.contains("/servlet_login") || dest.trim().isEmpty()){
           dest = ".";
        }
        response.sendRedirect(dest);
    }
}
