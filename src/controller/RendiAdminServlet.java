package controller;

import model.Utente;
import model.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet_rendi_admin")
public class RendiAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codice = Integer.parseInt(request.getParameter("id"));
        UtenteDAO dao = new UtenteDAO();
        Utente u = dao.doRetrieveByUseId(codice);

        if(u != null){
            if(u.isAdmin()){
                dao.updateAdmin(codice, false);
            }else {
                dao.updateAdmin(codice, true);
            }
        }

        String dest = request.getHeader("referer");
        if(dest == null || dest.contains("/servlet_rendi_admin") || dest.trim().isEmpty()){
            dest = ".";
        }
        response.sendRedirect(dest);

    }
}
