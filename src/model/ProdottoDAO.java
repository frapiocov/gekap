package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO {

    //Metodo che ritorna un arraylist con tutti i prodotti presenti nel Database
    public ArrayList<Prodotto> doRetrieveAll(int offset, int limit) {
        var prodotti = new ArrayList<Prodotto>();
;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT codice, nome, genere, trama, anno, prezzo, durata, lingua, listaImmagini, trailer, categoria FROM prodotto LIMIT ?, ?");
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                var c = new Prodotto();
                c.setCodice(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setGenere(rs.getString(3));
                c.setTrama(rs.getString(4));
                c.setAnno(rs.getInt(5));
                c.setPrezzoCent(rs.getInt(6));
                c.setDurata(rs.getInt(7));
                c.setLingua(rs.getString(8));
                c.setListaImmagini(rs.getString(9));
                c.setTrailer(rs.getString(10));
                c.setCategoria(rs.getInt(11));

                prodotti.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prodotti;
    }

    /**
     * Trova il prodotto con il dato ID.
     * Ritorna null se non trova riscontri.
     */
    public Prodotto doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT codice, nome, genere, trama, anno, prezzo, durata, lingua, listaImmagini, trailer, categoria FROM prodotto WHERE codice=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Prodotto c = new Prodotto();
                c.setCodice(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setGenere(rs.getString(3));
                c.setTrama(rs.getString(4));
                c.setAnno(rs.getInt(5));
                c.setPrezzoCent(rs.getInt(6));
                c.setDurata(rs.getInt(7));
                c.setLingua(rs.getString(8));
                c.setListaImmagini(rs.getString(9));
                c.setTrailer(rs.getString(10));
                c.setCategoria(rs.getInt(11));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Salva il prodotto da aggiungere nel database
     * **/
    public void doSave(Prodotto p) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO prodotto (nome, genere, trama, anno, prezzo, durata, lingua, listaImmagini, trailer, categoria) VALUES(?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, p.getNome());
            ps.setString(2,p.getGenere());
            ps.setString(3,p.getTrama());
            ps.setInt(4, p.getAnno());
            ps.setInt(5,p.getPrezzoCent());
            ps.setInt(6,p.getDurata());
            ps.setString(7, p.getLingua());
            ps.setString(8,p.getListaImmagini());
            ps.setString(9,p.getTrailer());
            ps.setInt(10,p.getCategoria());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Aggiorna i valori di un prodotto nel Database
     * **/
    public void doUpdate(Prodotto p) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE prodotto SET nome=?, genere=?, trama=?, anno=?, prezzo=?, durata=?, lingua=?, listaImmagini=?, trailer=?, categoria=? WHERE codice=?");

            ps.setString(1, p.getNome());
            ps.setString(2,p.getGenere());
            ps.setString(3,p.getTrama());
            ps.setInt(4, p.getAnno());
            ps.setInt(5,p.getPrezzoCent());
            ps.setInt(6,p.getDurata());
            ps.setString(7, p.getLingua());
            ps.setString(8,p.getListaImmagini());
            ps.setString(9,p.getTrailer());
            ps.setInt(10,p.getCategoria());
            ps.setInt(11,p.getCodice());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Cancella un prodotto dal database*/
    public void doDelete(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM prodotto WHERE codice=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Trova il cast del prodotto relativo.
     */
    public ArrayList<Attore> doRetrieveCastById(int id) {

        ArrayList<Attore> cast = new ArrayList<Attore>();

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select attore.nome, attore.ruolo\n" +
                    "from attore, prodotto, prodottoCast\n" +
                    "where prodotto.codice = prodottoCast.prodotto\n" +
                    "and prodottoCast.attore = attore.id\n" +
                    "and prodotto.codice = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Attore a = new Attore();
                a.setNome(rs.getString(1));
                a.setRuolo(rs.getString(2));
                cast.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cast;
    }

    /**
     * Trova il cast del prodotto relativo.
     */
    public ArrayList<Prodotto> doRetrieveByCat(int id) {
            var prodotti = new ArrayList<Prodotto>();
            ;
            try (Connection con = ConPool.getConnection()) {
                PreparedStatement ps = con.prepareStatement("SELECT prodotto.codice, prodotto.nome, prodotto.genere, " +
                        "prodotto.trama, prodotto.anno, prodotto.prezzo, prodotto.durata, prodotto.lingua, prodotto.listaImmagini, " +
                        "prodotto.trailer, prodotto.categoria FROM prodotto, categoria WHERE prodotto.categoria = categoria.idCat AND categoria.iDcat = ?");
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    var c = new Prodotto();
                    c.setCodice(rs.getInt(1));
                    c.setNome(rs.getString(2));
                    c.setGenere(rs.getString(3));
                    c.setTrama(rs.getString(4));
                    c.setAnno(rs.getInt(5));
                    c.setPrezzoCent(rs.getInt(6));
                    c.setDurata(rs.getInt(7));
                    c.setLingua(rs.getString(8));
                    c.setListaImmagini(rs.getString(9));
                    c.setTrailer(rs.getString(10));
                    c.setCategoria(rs.getInt(11));

                    prodotti.add(c);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return prodotti;
        }

        //funzione che fa la ricerca di un prodotto in base al nome e alla trama
        public ArrayList<Prodotto> doRetrieveByNomeOrDescrizione(String against, int offset, int limit) {
            try (Connection con = ConPool.getConnection()) {
                PreparedStatement ps = con.prepareStatement("SELECT codice, nome, genere, trama, anno, prezzo, durata, lingua, listaImmagini, trailer, categoria" +
                        " FROM prodotto WHERE MATCH(nome, trama) AGAINST(?) LIMIT ?, ?");
                ps.setString(1, against);
                ps.setInt(2, offset);
                ps.setInt(3, limit);
                ArrayList<Prodotto> prodotti = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Prodotto c = new Prodotto();
                    c.setCodice(rs.getInt(1));
                    c.setNome(rs.getString(2));
                    c.setGenere(rs.getString(3));
                    c.setTrama(rs.getString(4));
                    c.setAnno(rs.getInt(5));
                    c.setPrezzoCent(rs.getInt(6));
                    c.setDurata(rs.getInt(7));
                    c.setLingua(rs.getString(8));
                    c.setListaImmagini(rs.getString(9));
                    c.setTrailer(rs.getString(10));
                    c.setCategoria(rs.getInt(11));
                    prodotti.add(c);
                }
                return prodotti;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
}