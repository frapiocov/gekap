package controller;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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
        String op = "";

        Utente u = (Utente) request.getSession().getAttribute("utente");    //controlliamo se l'utente è un admin e se è autorizzato
        if (u == null || !u.isAdmin()) {
            throw new controller.ServletException("Utente non autorizzato.");
        }

        String codice = request.getParameter("id");     //prendiamo dalla richiesta il codice del prodotto
        if (codice != null) {   //controllo se l'operazione è una modifica oppure una rimozione
            if (request.getParameter("rimuovi") != null) {  //si tratta di una rimozione
                pdao.doDelete(Integer.parseInt(codice));

                op = "Rimozione";
                request.setAttribute("notifica", "Prodotto rimosso con successo.");
            } else {   //si tratta di una modifica
                Prodotto p = dao.doRetrieveById(Integer.parseInt(codice));

                op = "Modifica";
                request.setAttribute("prodotto", p);
            }
        } else {    //il codice è nullo, quindi si tratta di un inserimento
            op = "Inserimento";
        }

        request.setAttribute("operazione", op);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/adminprodotto.jsp");
        requestDispatcher.forward(request, response);
    }

}