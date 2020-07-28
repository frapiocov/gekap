package controller;

import model.Utente;
import model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/servlet_admin_utenti")
public class AdminUtentiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final UtenteDAO dao = new UtenteDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente u = (Utente) request.getSession().getAttribute("utente");
        if (u == null || !u.isAdmin()) {     //controlliamo se l'utente esiste e se è autorizzato (ovvero se è admin)
            throw new controller.ServletException("Utente non autorizzato");
        }

        ArrayList<Utente> utenti = dao.doRetrieveAll();
        request.setAttribute("utenti", utenti);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/adminutenti.jsp");
        requestDispatcher.forward(request, response);
    }
}
