package controller;

import model.Attore;
import model.AttoreDAO;
import model.Prodotto;
import model.ProdottoDAO;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@MultipartConfig
@WebServlet("/servlet_modifica_inserimento_prodotto")
public class AdminAggiungiModificaProdottoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final ProdottoDAO pdao = new ProdottoDAO();
    private final AttoreDAO adao = new AttoreDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Prodotto p = new Prodotto();
        String operazione="";

        String codice = request.getParameter("id"); //serve solo per la modifica
        String listaImm = request.getParameter("listaIm");
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

        String op=request.getParameter("operazione");

        if (op.equalsIgnoreCase("inserimento")) { //inserimento
            operazione="Inserimento";

            String fileName=aggiuntaFoto(request);

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
            adao.doSave(cast,pdao.returnMaxCodice());

            request.setAttribute("notifica", "Prodotto inserito con successo.");
        } else {    //aggiornamento prodotto
            operazione="Modifica";

            p.setCodice(Integer.parseInt(codice));
            p.setListaImmagini(listaImm);

            pdao.doUpdate(p);
            request.setAttribute("notifica", "Prodotto modificato con successo.");
        }

        request.setAttribute("operazione",operazione);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/adminprodotto.jsp");
        requestDispatcher.forward(request, response);
    }

    private String aggiuntaFoto(HttpServletRequest request) throws IOException, ServletException {
        String CARTELLA_UPLOAD = "images";  //cartella in cui verranno salvate le immagini prese dal form

        Part filePart = request.getPart("foto");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        String destinazione = CARTELLA_UPLOAD + File.separator + fileName;
        Path pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));

        // se un file con quel nome esiste già, gli cambia nome
        for (int i = 2; Files.exists(pathDestinazione); i++) {
            destinazione = CARTELLA_UPLOAD + File.separator + i + "_" + fileName;
            pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
        }

        InputStream fileInputStream = filePart.getInputStream();
        // crea CARTELLA_UPLOAD, se non esiste
        Files.createDirectories(pathDestinazione.getParent());
        // scrive il file
        Files.copy(fileInputStream, pathDestinazione);

        return fileName;
    }
}

