package controller;

import model.LoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/servlet_logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final LoginDAO loginDAO = new LoginDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("utente");

        if(request.getSession().getAttribute("carrello") != null){  //se l'utente che ha appena effettuato il logout aveva un carrello, lo rimuoviamo
            request.getSession().removeAttribute("carrello");
        }

        Cookie cookies[] = request.getCookies();    //prendiamo i cookie dalla request

        if (cookies != null) {
            // cookie con nome 'login' o null se non esiste (non è mai stato eseguito l'accesso)
            Cookie cookie = Arrays.stream(cookies).filter(c -> c.getName().equals("login")).findAny().orElse(null); //se trova il cookie con nome 'login' tra tutti i cookie lo restituisce, altrimenti restituisce null
            if (cookie != null) {
                cookie.setMaxAge(0); //cancella cookie
                response.addCookie(cookie); //aggiorniamo il cookie nella risposta
                String id = cookie.getValue().split("_")[0];    //prendiamo l'id del cookie
                loginDAO.doDelete(id);
            }
        }

        String dest = request.getHeader("referer");
        if(dest == null || dest.contains("/servlet_logout") || dest.trim().isEmpty()){
            dest = ".";
        }

        response.sendRedirect(dest);
    }
}
