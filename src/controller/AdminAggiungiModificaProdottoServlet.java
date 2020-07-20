package controller;

import model.Attore;
import model.AttoreDAO;
import model.Prodotto;
import model.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@WebServlet("/servlet_modifica_inserimento_prodotto")
public class AdminAggiungiModificaProdottoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final ProdottoDAO pdao = new ProdottoDAO();
    private final AttoreDAO adao = new AttoreDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Prodotto p = new Prodotto();

        String codice = request.getParameter("id");
        String nome = request.getParameter("nome");
        String genere = request.getParameter("genere");
        String trama = request.getParameter("trama");
        String anno = request.getParameter("anno");
        String prezzo = request.getParameter("prezzo");
        String durata = request.getParameter("durata");
        String lingua = request.getParameter("lingua");
        String trailer = request.getParameter("trailer");
        String cat = request.getParameter("cat");

        p.setNome(nome);
        p.setGenere(genere);
        p.setTrama(trama);
        p.setAnno(Integer.parseInt(anno));
        p.setPrezzoCent(Integer.parseInt(prezzo));
        p.setDurata(Integer.parseInt(durata));
        p.setLingua(lingua);
        p.setTrailer(trailer);
        p.setCategoria(Integer.parseInt(cat));


        if (codice == null) { //inserimento

            Part part = request.getPart("foto");    //prendo l'immagine
            String fileName = part.getSubmittedFileName();  //prendo il nome del file
            String path = "C:\\Users\\Francesco Pio\\Desktop\\progetto_gekap\\web\\images" + File.separator + fileName;
            InputStream is = part.getInputStream();
            uploadFoto(is, path);
            p.setListaImmagini(fileName);

            Attore a1, a2, a3, a4, a5;
            String r1, r2, r3, r4, r5, n1, n2, n3, n4, n5;
            r1 = request.getParameter("r1");
            r2 = request.getParameter("r2");
            r3 = request.getParameter("r3");
            r4 = request.getParameter("r4");
            r5 = request.getParameter("r5");
            n1 = request.getParameter("n1");
            n2 = request.getParameter("n2");
            n3 = request.getParameter("n3");
            n4 = request.getParameter("n4");
            n5 = request.getParameter("n5");

            a1 = new Attore();
            a2 = new Attore();
            a3 = new Attore();
            a4 = new Attore();
            a5 = new Attore();
            a1.setRuolo(r1);
            a1.setNome(n1);
            a2.setRuolo(r2);
            a2.setNome(n2);
            a3.setRuolo(r3);
            a3.setNome(n3);
            a4.setRuolo(r4);
            a4.setNome(n4);
            a5.setRuolo(r5);
            a5.setNome(n5);

            ArrayList<Attore> cast = new ArrayList<>();
            cast.add(a1);
            cast.add(a2);
            cast.add(a3);
            cast.add(a4);
            cast.add(a5);

            pdao.doSave(p);
            adao.doSave(cast, pdao.returnMaxCodice());
            request.setAttribute("notifica", "Prodotto aggiunto con successo.");
        } else {//aggiornamento prodotto
            if(true){
                throw new controller.ServletException(codice);
            }
            p.setCodice(Integer.parseInt(codice));

            pdao.doUpdate(p);
            request.setAttribute("notifica", "Prodotto modificato con successo.");
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/adminprodotto.jsp");
        requestDispatcher.forward(request, response);
    }


    public void uploadFoto(InputStream is, String path) throws controller.ServletException {
        try {
            byte[] bytes = new byte[is.available()];
            is.read();
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            throw new controller.ServletException("Upload immagine fallito");
        }
    }
}
