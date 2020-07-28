package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PreferitiDAO {

    public void doSaveLista(int utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO listapreferiti VALUES (?)");
            ps.setInt(1, utente);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSaveProdotto(int prodotto, int utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO listaprodotto VALUES (?,?)");
            ps.setInt(1, utente);
            ps.setInt(2, prodotto);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDeleteLista(int prodotto, int utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM listaprodotto WHERE prodotto = ? AND lista = ?");
            ps.setInt(1, prodotto);
            ps.setInt(2, utente);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDeleteAll(int utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM listaprodotto WHERE lista = ?");
            ps.setInt(1, utente);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Integer> doRetrieveByUtente(int utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT prodotto FROM listaprodotto WHERE lista=?");
            ps.setInt(1, utente);

            int codiceProdotto = 0;
            ArrayList<Integer> codici = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                codiceProdotto = (rs.getInt(1));
                codici.add(codiceProdotto);
            }
            return codici;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        }

     public int ListaExist(int utente){
         try (Connection con = ConPool.getConnection()) {
             PreparedStatement ps = con.prepareStatement("SELECT utente FROM listapreferiti WHERE utente=?");
             ps.setInt(1, utente);

             int codiceUtente = 0;
             ResultSet rs = ps.executeQuery();
             if(rs.next()) {
                 codiceUtente = (rs.getInt(1));
             }
             return codiceUtente;

         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
     }

    public int existProdotto(int utente, int prodotto){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT prodotto FROM listaprodotto WHERE lista=? AND prodotto=?");
            ps.setInt(1, utente);
            ps.setInt(2,prodotto);

            int codiceUtente = 0;
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                codiceUtente = (rs.getInt(1));
            }
            return codiceUtente;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }