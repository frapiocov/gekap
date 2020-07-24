package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarrelloDAO {
    public Carrello doRetrieveByUtente(int ut) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT prodotto,quantitaP FROM carrello WHERE utente=?");
            ps.setInt(1,ut);
            ResultSet rs = ps.executeQuery();

            Carrello cart=new Carrello();
            Prodotto p; ProdottoDAO dao=new ProdottoDAO(); int q;

            while (rs.next()) {
                p=dao.doRetrieveById(rs.getInt(1));
                q=rs.getInt(2);

                cart.put(p,q);
            }
            return cart;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(int utente,int prodotto,int q) {
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE carrello SET quantitaP=? WHERE utente=? AND prodotto=?");
            ps.setInt(1,q);
            ps.setInt(2,utente);
            ps.setInt(3,prodotto);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(int utente,int prodotto,int q,int pre) {
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO carrello (utente,prodotto,quantitaP,prezzoTot) VALUES (?,?,?,?)");
            ps.setInt(1,utente);
            ps.setInt(2,prodotto);
            ps.setInt(3,q);
            ps.setInt(4,pre);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkUtenteProdotto(int utente,int prodotto) {
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT prodotto FROM carrello WHERE utente=? AND prodotto=?");
            ps.setInt(1,utente);
            ps.setInt(2,prodotto);

            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
