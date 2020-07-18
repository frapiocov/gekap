package controller;

import model.Categoria;
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

@WebServlet("/servlet_categoria")
public class CategoriaServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        @SuppressWarnings("unchecked")
        ArrayList<Categoria> categorie=(ArrayList<Categoria>) getServletContext().getAttribute("categorie");
        int cat;
        try{
            cat = Integer.parseInt(request.getParameter("categoria"));
        }catch(Exception e){
            throw new controller.ServletException("ID categoria non valido");
        }

        Categoria c=new Categoria();
        for (Categoria x : categorie) {
            if (x.getIdCat() == cat)
                c = x;
        }
        request.setAttribute("categoriaScelta",c);

        ArrayList<Prodotto> prodotti=prodottoDAO.doRetrieveByCat(cat);
        request.setAttribute("prodottiCategoria",prodotti);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/results/categoria.jsp");
        dispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
        doGet(request,response);
    }

    private final static ProdottoDAO prodottoDAO=new ProdottoDAO();
}
