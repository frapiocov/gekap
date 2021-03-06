package model;

import java.sql.*;
import java.util.ArrayList;

public class AttoreDAO {

    //ritorna tutti gli attori presenti nel database
    public ArrayList<Attore> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id,nome,ruolo FROM Attore");
            ResultSet rs = ps.executeQuery();

            ArrayList<Attore> l = new ArrayList<Attore>();
            while (rs.next()) {
                Attore a = new Attore();
                a.setId(rs.getInt(1));
                a.setNome(rs.getString(2));
                a.setRuolo(rs.getString(3));
                l.add(a);
            }
            return l;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //cerca un attore in base all'id
    public Attore doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id,nome,ruolo FROM Attore WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Attore a = new Attore();
                a.setId(rs.getInt(1));
                a.setNome(rs.getString(2));
                a.setRuolo(rs.getString(3));
                return a;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(ArrayList<Attore> attori, int codiceProdotto) {
        try (Connection con = ConPool.getConnection()) {

            for (Attore a: attori) {
                PreparedStatement ps = con.prepareStatement("INSERT INTO attore (nome,ruolo) VALUES (?,?)");

                ps.setString(1, a.getNome());
                ps.setString(2, a.getRuolo());

                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }

                ps = con.prepareStatement("INSERT INTO prodottocast (prodotto, attore) VALUES (?,?)");
                ps.setInt(1,codiceProdotto);
                ps.setInt(2,AttoreDAO.returnMaxCodice());

                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int returnMaxCodice(){
        try (Connection con = ConPool.getConnection()) {
            int value = 0;

            PreparedStatement ps = con.prepareStatement("SELECT MAX(id) FROM attore");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                value = rs.getInt(1);
            }
            return value;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}