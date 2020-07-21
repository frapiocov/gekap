package controller;

import model.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet_verifica_username")
public class VerificaUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final UtenteDAO dao = new UtenteDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username");
        response.setContentType("text/xml");
        if(user != null && user.length() >= 6 && user.matches("^[0-9a-zA-Z]+$") && dao.doRetrieveByUsername(user) == null)
        {
           response.getWriter().append("<ok/>");
        }else{
            response.getWriter().append("<no/>");
        }

    }
}
