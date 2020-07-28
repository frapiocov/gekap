package controller;

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
            throw new ServletException("Impossibile effettuare l'acquisto senza prodotti nel Carrello.");
        }

        String prezzo = request.getParameter("prezTot");

        if(prezzo != null) {
            int prezz = Integer.parseInt(prezzo);

            String data = Timestamp.from(Instant.now()).toString();     //in data avremo la data del momento in cui l'utente schiaccia il bottone acquista
            Collection<Carrello.ProdottoQuantita> prodotti = c.getProdotti();      //prendiamo tutti i prodotti dal carrello
            ArrayList<Integer> codP = new ArrayList<>();

            for (Carrello.ProdottoQuantita p : prodotti) {
                codP.add(p.getProdotto().getCodice());
            }

            odao.doSave(u.getIdUser(), data, prezz, codP);
            request.getSession().removeAttribute("carrello");           //svuotiamo il carrello dopo l'acquisto

            RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/results/ordine.jsp");
            disp.forward(request, response);
        } else {        //dopo aver effettuato l'accesso
            String dest = request.getHeader("referer");         //prendiamo dall'header della richiesta l'url corrente
            if(dest == null || dest.contains("/servlet_acquisto") || dest.trim().isEmpty()){
                dest = ".";     //la destinazione sarà l'homepage
            }
            response.sendRedirect(dest);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }
}
