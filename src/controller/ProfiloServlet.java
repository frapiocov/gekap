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

@WebServlet("/servlet_profilo")
public class ProfiloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final UtenteDAO dao = new UtenteDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente ut = (Utente) request.getSession().getAttribute("utente");
        if(ut == null){
            throw new controller.ServletException("Utente non loggato");
        }

        request.getSession().setAttribute("utente", ut);
        RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/results/profiloutente.jsp");
        disp.forward(request, response);
    }
}
