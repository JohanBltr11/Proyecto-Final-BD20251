package DAO;

import conexion.ConexionBD;
import modelo.Brinda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrindaDAO {
    private Connection conn;

    public BrindaDAO() {
        conn = ConexionBD.getConexion().getConnection();
    }

    public boolean insertarBrinda(Brinda brinda) {
        String sql = "INSERT INTO Brinda (IdServicio, cedula) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, brinda.getIdServicio());
            stmt.setLong(2, brinda.getCedula());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarBrinda(int idServicio, long cedula) {
        String sql = "DELETE FROM Brinda WHERE IdServicio = ? AND cedula = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idServicio);
            stmt.setLong(2, cedula);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Brinda> listarBrindas() {
        List<Brinda> lista = new ArrayList<>();
        String sql = "SELECT * FROM Brinda";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Brinda(
                    rs.getInt("IdServicio"),
                    rs.getLong("cedula")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Brinda> buscarPorCedula(long cedula) {
        List<Brinda> lista = new ArrayList<>();
        String sql = "SELECT * FROM Brinda WHERE cedula = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cedula);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Brinda(
                    rs.getInt("IdServicio"),
                    rs.getLong("cedula")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Brinda> buscarPorServicio(int idServicio) {
        List<Brinda> lista = new ArrayList<>();
        String sql = "SELECT * FROM Brinda WHERE IdServicio = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idServicio);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Brinda(
                    rs.getInt("IdServicio"),
                    rs.getLong("cedula")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
