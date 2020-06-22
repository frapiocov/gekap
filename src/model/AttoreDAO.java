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

    public void doSave(Attore a) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Attore (nome,ruolo) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, a.getNome());
            ps.setString(2, a.getRuolo());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int id = rs.getInt(1);
            a.setId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}