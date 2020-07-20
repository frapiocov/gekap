package controller;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;

@MultipartConfig
@WebServlet("/servlet_admin_prodotto")
public class AdminProdottoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final ProdottoDAO pdao = new model.ProdottoDAO();
    private final AttoreDAO adao = new AttoreDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final ProdottoDAO dao = new ProdottoDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente u = (Utente) request.getSession().getAttribute("utente");    //controllo se l'utente è un admin e se è autorizzato
        if (u == null || !u.isAdmin()) {
            throw new controller.ServletException("Utente non autorizzato");
        }
            String codice = request.getParameter("id");
            if(codice != null){
                if(request.getParameter("rimuovi") != null){
                    pdao.doDelete(Integer.parseInt(codice));
                    request.setAttribute("notifica", "Prodotto rimosso con successo.");
                }
                else{   //modifica in quanto codice non nullo
                    Prodotto p = dao.doRetrieveById(Integer.parseInt(codice));
                    request.setAttribute("prodotto", p);
                }
            }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/adminprodotto.jsp");
        requestDispatcher.forward(request,response);
    }

}