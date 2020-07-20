package controller;

import model.Carrello;
import model.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/servlet_carrello")
public class CarrelloServlet extends HttpServlet {
    private final ProdottoDAO dao=new ProdottoDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        Carrello cart=(Carrello) session.getAttribute("carrello");

        if(cart==null) {
            cart=new Carrello();
            session.setAttribute("carrello",cart);
        }

        String codProdStr = request.getParameter("codice_prodotto");
        if(codProdStr!=null) {                                              //inserire prodotto nel carrello
            int codice = Integer.parseInt(codProdStr);

            String numStr = request.getParameter("quantity");
            if (numStr != null) {
                int num = Integer.parseInt(numStr);

                Carrello.ProdottoQuantita prodQuant = cart.get(codice);
                if (prodQuant != null) {
                    prodQuant.setQuantita(prodQuant.getQuantita() + num);
                } else {
                    cart.put(dao.doRetrieveById(codice), num);
                }
            } else {
                cart.remove(codice);
            }
        }

        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/results/carrello.jsp");
        dispatcher.forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
}
