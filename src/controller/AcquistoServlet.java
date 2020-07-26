package controller;

import com.mysql.cj.CacheAdapter;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet("/servlet_acquisto")
public class AcquistoServlet extends HttpServlet {

    OrdineDAO odao = new OrdineDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        Carrello c = (Carrello) request.getSession().getAttribute("carrello");
        Utente u=(Utente) request.getSession().getAttribute("utente");

        if(u == null) {
            throw new ServletException("Per effettuare l'acquisto devi prima accedere o registrarti.");
        }

        if(c == null){
            throw new ServletException("Impossibile effettuare l'acquisto senza prodotti nel Carrello");
        }

        int prezz = Integer.parseInt(request.getParameter("prezTot"));
        String data = Timestamp.from(Instant.now()).toString();
        Collection<Carrello.ProdottoQuantita> prodotti = c.getProdotti();
        ArrayList<Integer> codP = new ArrayList<>();

        for (Carrello.ProdottoQuantita p:prodotti) {
            codP.add(p.getProdotto().getCodice());
        }

        odao.doSave(u.getIdUser(), data , prezz, codP);
        request.getSession().removeAttribute("carrello");

        RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/results/ordine.jsp");
        disp.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }
}
