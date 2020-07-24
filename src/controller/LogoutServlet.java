package controller;

import model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet_logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente u=(Utente) request.getSession().getAttribute("utente");

        String dest = request.getHeader("referer");
        request.getSession().removeAttribute("utente");

        if(dest == null || dest.contains("/servlet_logout") || dest.trim().isEmpty()){
            dest = ".";
        }

        response.sendRedirect(dest);
    }
}
