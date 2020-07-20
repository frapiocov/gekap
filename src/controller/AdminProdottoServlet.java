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
    private final ProdottoDAO dao = new model.ProdottoDAO();
    private final AttoreDAO adao = new AttoreDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente u = (Utente) request.getSession().getAttribute("utente");    //controllo se l'utente è un admin e se è autorizzato
        if (u == null || !u.isAdmin()) {
            throw new controller.ServletException("Utente non autorizzato");
        }

        String idstr = request.getParameter("id");
        if (idstr != null) {
            if (request.getParameter("rimuovi") != null) {
                dao.doDelete(Integer.parseInt(idstr));
                request.setAttribute("notifica", "Prodotto rimosso con successo.");
            } else {
                Prodotto p;
                String nome = request.getParameter("nome");
                String genere = request.getParameter("genere");
                String trama = request.getParameter("trama");
                int anno = Integer.parseInt(request.getParameter("anno"));
                int prezzo = Integer.parseInt(request.getParameter("prezzo"));
                int durata = Integer.parseInt(request.getParameter("durata"));
                String lingua = request.getParameter("lingua");
                String trailer = request.getParameter("trailer");
                int cat = Integer.parseInt(request.getParameter("cat"));


                if (nome != null && genere != null && trama != null && lingua != null && trailer != null) {
                    p = new Prodotto();
                    p.setNome(nome);
                    p.setGenere(genere);
                    p.setTrama(trama);
                    p.setAnno(anno);
                    p.setPrezzoCent(prezzo);
                    p.setDurata(durata);
                    p.setLingua(lingua);
                    p.setTrailer(trailer);
                    p.setCategoria(cat);


                    if (idstr.isEmpty()) { // aggiunta nuovo prodotto

                        Attore a1, a2, a3, a4, a5;
                        String r1,r2,r3,r4,r5,n1,n2,n3,n4,n5;
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

                        a1= new Attore();a2= new Attore();a3= new Attore();
                        a4= new Attore();a5= new Attore();
                        a1.setRuolo(r1);a1.setNome(n1);
                        a2.setRuolo(r2);a2.setNome(n2);
                        a3.setRuolo(r3);a3.setNome(n3);
                        a4.setRuolo(r4);a4.setNome(n4);
                        a5.setRuolo(r5);a5.setNome(n5);

                        ArrayList<Attore> cast = new ArrayList<>();
                        cast.add(a1);cast.add(a2);cast.add(a3);cast.add(a4);cast.add(a5);

                        Part part = request.getPart("foto");    //prendo l'immagine
                        String fileName = part.getSubmittedFileName();  //prendo il nome del file
                        String path = "C:\\Users\\Francesco Pio\\Desktop\\progetto_gekap\\web\\images" + File.separator + fileName;
                        InputStream is = part.getInputStream();
                        uploadFoto(is, path);
                        p.setListaImmagini(fileName);

                        dao.doSave(p);
                        adao.doSave(cast, Integer.parseInt(idstr));
                        request.setAttribute("notifica", "Prodotto aggiunto con successo.");
                    } else { // modifica prodotto esistente
                        p.setCodice(Integer.parseInt(idstr));
                        dao.doUpdate(p);
                        request.setAttribute("notifica", "Prodotto modificato con successo.");
                    }
                } else {
                    int id = Integer.parseInt(idstr);
                    p = dao.doRetrieveById(id);
                }
                request.setAttribute("prodotto", p);
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/adminprodotto.jsp");
        requestDispatcher.forward(request, response);
    }


    public void uploadFoto (InputStream is, String path) throws controller.ServletException {
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