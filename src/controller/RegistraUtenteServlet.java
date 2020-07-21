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

@WebServlet("/servlet_registra_utente")
public class RegistraUtenteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final UtenteDAO dao = new UtenteDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("utente") != null) {
            throw new controller.ServletException("Utente loggato.");
        }
        String username = request.getParameter("username");
        if (!(username != null && username.length() >= 6 && username.matches("^[0-9a-zA-Z]+$"))) {
            throw new controller.ServletException("Username non valido.");
        }

        String password = request.getParameter("password");
        if (!(password != null && password.length() >= 8 && !password.toUpperCase().equals(password)
                && !password.toLowerCase().equals(password) && password.matches(".*[0-9].*"))) {
            throw new controller.ServletException("Password non valida.");
        }

        String passwordConferma = request.getParameter("passwordConferma");
        if (!password.equals(passwordConferma)) {
            throw new controller.ServletException("Password e conferma differenti.");
        }

        String nome = request.getParameter("nome");
        if (!(nome != null && nome.trim().length() > 0 && nome.matches("^[ a-zA-Z\u00C0-\u00ff]+$"))) {
            throw new controller.ServletException("Nome non valido.");
        }

        String cognome = request.getParameter("cognome");
        if (!(cognome != null && cognome.trim().length() > 0 && cognome.matches("^[ a-zA-Z\u00C0-\u00ff]+$"))) {
            throw new controller.ServletException("Cognome non valido.");
        }

        String email = request.getParameter("email");
        if (!(email != null && email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$"))) {
            throw new controller.ServletException("Email non valida.");
        }

        String citta = request.getParameter("citta");
        if (!(citta != null && citta.trim().length() > 0 && citta.matches("^[ a-zA-Z\u00C0-\u00ff]+$"))) {
            throw new controller.ServletException("Citta non valido.");
        }

        String via = request.getParameter("via");
        if (!(via != null && via.trim().length() > 0 && via.matches("^[ a-zA-Z\u00C0-\u00ff]+$"))) {
            throw new controller.ServletException("Via non valido.");
        }

        String prov = request.getParameter("prov");
        if (!(prov != null && prov.trim().length() > 0 && prov.matches("^[ a-zA-Z\u00C0-\u00ff]+$"))) {
            throw new controller.ServletException("Provincia non valido.");
        }

        String cap = request.getParameter("cap");
        int nciv = Integer.parseInt(request.getParameter("nc"));
        String data = request.getParameter("ddn");
        String sex = request.getParameter("sex");

        Utente utente = new Utente();
        utente.setUsername(username);
        utente.setPasswordHash(password);
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setEmail(email);
        utente.setVia(via);
        utente.setCitta(citta);
        utente.setProvincia(prov);
        utente.setCAP(cap);
        utente.setnCivico(nciv);
        utente.setDataDiNascita(data);
        utente.setSesso(sex);

        dao.doSave(utente);
        request.getSession().setAttribute("utente", utente);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/registrazioneSuccesso.jsp");
        requestDispatcher.forward(request, response);
    }
}
