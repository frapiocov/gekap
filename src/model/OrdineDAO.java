package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAO {

    //salva l'ordine con tutti i relativi prodotti
    public void doSave(int utente, String data,int prezzo, ArrayList<Integer> prodotti) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO ordine (utente,data,prezzo) VALUES (?,?,?)");
            ps.setInt(1, utente);
            ps.setString(2,data);
            ps.setInt(3,prezzo);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            int last = this.returnMaxOrdine();

            for (Integer p:prodotti){
                ps = con.prepareStatement("INSERT INTO ordineprodotto (ordine,prodotto) VALUES (?, ?)");
                ps.setInt(1, last);
                ps.setInt(2,p);

                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //restituisce il codice dell'ultimo ordine aggiunto
    public int returnMaxOrdine(){
        try (Connection con = ConPool.getConnection()) {
            int value = 0;
            PreparedStatement ps = con.prepareStatement("SELECT MAX(idOrdine) FROM ordine");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                value = rs.getInt(1);
            }
            return value;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //ritorna tutti gli ordini di un utente
    public  ArrayList<Ordine> doRetrieveByUtenteAll(int utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT idOrdine, utente, data, prezzo FROM ordine WHERE utente = ?");
            ps.setInt(1, utente);

            ArrayList<Ordine> ordini = new ArrayList<>();

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Ordine o = new Ordine();
                o.setId(rs.getInt(1));
                o.setUtente(rs.getInt(2));
                o.setData(rs.getString(3));
                o.setPrezzo(rs.getInt(4));

                ordini.add(o);
            }
            return ordini;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //ritorna tutti i prodotti di un ordine
    public  ArrayList<Integer> doRetrieveByOrdineAll(int ordine, int utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT ordineprodotto.prodotto FROM ordine, ordineprodotto WHERE ordine.utente = ? AND ordine.idOrdine = ? AND ordine.idOrdine = ordineprodotto.ordine");
            ps.setInt(1, utente);
            ps.setInt(2, ordine);

            ArrayList<Integer> codici = new ArrayList<>();

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int cod = rs.getInt(1);
                codici.add(cod);
            }
            return codici;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}