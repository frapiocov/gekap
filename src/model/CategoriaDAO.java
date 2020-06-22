package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    /**
     * Metodo che ritorna un arraylist con tutte le categorie presenti nel Database
     */
    public List<Categoria> doRetrieveAll() {
        List<Categoria> categorie;
        categorie = new ArrayList<Categoria>();

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT idCat, nome FROM categoria");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCat(rs.getInt(1));
                c.setNome(rs.getString(2));

                categorie.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categorie;
    }

    /**
     * Trova la categoria con il dato ID.
     * Ritorna null se non trova riscontri.
     */
    public Categoria doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT idCat, nome FROM categoria WHERE idCat=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Categoria c = new Categoria();

                c.setIdCat(rs.getInt(1));
                c.setNome(rs.getString(2));

                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
