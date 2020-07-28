package controller;

import model.Attore;
import model.Prodotto;
import model.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/servlet_prodotto")
public class ProdottoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final ProdottoDAO dao = new ProdottoDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codice;

        try{
            codice = Integer.parseInt(request.getParameter("codice"));
        }catch(Exception e){
            throw new controller.ServletException("ID prodotto non valido");
        }

        Prodotto prodotto = dao.doRetrieveById(codice);
        if(prodotto == null){
            throw new controller.ServletException("Prodotto non trovato :(");
        }

        ArrayList<Attore> cast = dao.doRetrieveCastById(codice);

        request.setAttribute("prodotto", prodotto);         //settiamo nella richiesta il prodotto e il relativo cast
        request.setAttribute("cast", cast);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/prodotto.jsp");
        dispatcher.forward(request, response);
    }
}
