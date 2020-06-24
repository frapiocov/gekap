package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO {

    //Metodo che ritorna un arraylist con tutti i prodotti presenti nel Database
    public ArrayList<Prodotto> doRetrieveAll() {
        var prodotti = new ArrayList<Prodotto>();
        ;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT codice, nome, genere, trama, anno, prezzo, durata, lingua, listaImmagini, trailer, categoria FROM prodotto");
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
     **/
    public void doSave(Prodotto p) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO prodotto (nome, genere, trama, anno, prezzo, durata, lingua, listaImmagini, trailer, categoria) VALUES(?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, p.getNome());
            ps.setString(2, p.getGenere());
            ps.setString(3, p.getTrama());
            ps.setInt(4, p.getAnno());
            ps.setInt(5, p.getPrezzoCent());
            ps.setInt(6, p.getDurata());
            ps.setString(7, p.getLingua());
            ps.setString(8, p.getListaImmagini());
            ps.setString(9, p.getTrailer());
            ps.setInt(10, p.getCategoria());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //restituisce i prodotti in base alla categoria
    public ArrayList<Prodotto> doRetrieveByCat(int cat,int lim) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT codice, nome, genere, trama, anno, prezzo, durata, lingua, listaImmagini, trailer, categoria FROM prodotto LIMIT ?,? WHERE categoria=?");
            ps.setInt(1, cat);
            ps.setInt(2,lim);
            ps.setInt(3,1);

            ResultSet rs = ps.executeQuery();

            ArrayList<Prodotto> prod = new ArrayList<Prodotto>();

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

                prod.add(c);
            }
            return prod;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}