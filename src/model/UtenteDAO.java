package model;

import java.sql.*;
import java.util.ArrayList;

public class UtenteDAO {
    public void doSave(Utente u) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Utente (username,passwordhash,email,nome,cognome,dataDiNascita,sesso,via,nCivico,città,provincia,CAP,admin) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPasswordhash());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getNome());
            ps.setString(5, u.getCognome());
            ps.setString(6, u.getDataDiNascita());
            ps.setString(7, u.getSesso());
            ps.setString(8, u.getVia());
            ps.setInt(9, u.getnCivico());
            ps.setString(10, u.getCittà());
            ps.setString(11, u.getProvincia());
            ps.setString(12, u.getCAP());
            ps.setBoolean(13, u.isAdmin());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int id = rs.getInt(1);
            u.setIdUser(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByUsernamePassword(String u,String p) {
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps=con.prepareStatement("SELECT idUser,username,email,nome,cognome,dataDiNascita,sesso,via,nCivico,città,provincia,CAP,admin FROM Utente WHERE username=? AND passwordhash=SHA1(?)");

            ps.setString(1,u);
            ps.setString(2,p);

            ResultSet rs=ps.executeQuery();

            if (rs.next()) {
                Utente ut=new Utente();

                ut.setIdUser(rs.getInt(1));
                ut.setUsername(u);
                ut.setPasswordHash(p);
                ut.setEmail(rs.getString(3));
                ut.setNome(rs.getString(4));
                ut.setCognome(rs.getString(5));
                ut.setDataDiNascita(rs.getString(6));
                ut.setSesso(rs.getString(7));
                ut.setVia(rs.getString(8));
                ut.setnCivico(rs.getInt(9));
                ut.setCittà(rs.getString(10));
                ut.setProvincia(rs.getString(11));
                ut.setCAP(rs.getString(12));
                ut.setAdmin(rs.getBoolean(13));

                return ut;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Utente> doRetrieveAll() {
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT idUser,username,email,nome,cognome,dataDiNascita,sesso,via,nCivico,città,provincia,CAP,admin FROM Utente");
            ResultSet rs = ps.executeQuery();

            ArrayList<Utente> l=new ArrayList<Utente>();

            while(rs.next()) {
                Utente ut=new Utente();

                ut.setIdUser(rs.getInt(1));
                ut.setUsername(rs.getString(2));
                ut.setEmail(rs.getString(3));
                ut.setNome(rs.getString(4));
                ut.setCognome(rs.getString(5));
                ut.setDataDiNascita(rs.getString(6));
                ut.setSesso(rs.getString(7));
                ut.setVia(rs.getString(8));
                ut.setnCivico(rs.getInt(9));
                ut.setCittà(rs.getString(10));
                ut.setProvincia(rs.getString(11));
                ut.setCAP(rs.getString(12));
                ut.setAdmin(rs.getBoolean(13));

                l.add(ut);
            }
            return l;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}