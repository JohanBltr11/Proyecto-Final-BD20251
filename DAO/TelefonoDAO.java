package DAO;

import conexion.ConexionBD;
import modelo.Telefono;
import modelo.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelefonoDAO {
    private Connection conn;

    public TelefonoDAO() {
        conn = ConexionBD.getConexion().getConnection();
    }

    public boolean insertarTelefono(Telefono telefonoObj) {
        String sql = "INSERT INTO Telefono (cedula, telefono) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, telefonoObj.getCedula());
            stmt.setString(2, telefonoObj.getTelefono());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarTelefono(long cedula, String telefono) {
        String sql = "DELETE FROM Telefono WHERE cedula = ? AND telefono = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cedula);
            stmt.setString(2, telefono);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Telefono> obtenerTelefonosPorCedula(long cedula) {
        List<Telefono> lista = new ArrayList<>();
        String sql = "SELECT * FROM Telefono WHERE cedula = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cedula);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Telefono(
                        rs.getLong("cedula"),
                        rs.getString("telefono")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Telefono> listarTodos() {
        List<Telefono> lista = new ArrayList<>();
        String sql = "SELECT * FROM Telefono";
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Telefono(
                        rs.getLong("cedula"),
                        rs.getString("telefono")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
