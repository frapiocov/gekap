package controller;

import model.LoginDAO;
import model.Utente;

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

        if(request.getSession().getAttribute("carrello") != null){
            request.getSession().removeAttribute("carrello");
        }

        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            // cookie con nome 'login' o null se non esiste
            Cookie cookie = Arrays.stream(cookies).filter(c -> c.getName().equals("login")).findAny().orElse(null);
            if (cookie != null) {
                cookie.setMaxAge(0); // rimuove cookie
                response.addCookie(cookie);
                String id = cookie.getValue().split("_")[0];
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
