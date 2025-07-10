package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionBD;
import modelo.Incluye;

public class IncluyeDAO {

    public void insertar(Incluye incluye) {
        String sql = "INSERT INTO Incluye (idHabitacion, cedula, FechaHoraLlegada, idServicio) VALUES (?, ?, ?, ?)";

        try (Connection cn = (Connection) ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setInt(1, incluye.getIdHabitacion());
            stmt.setLong(2, incluye.getCedula());
            stmt.setTimestamp(3, incluye.getFechaHoraLlegada());
            stmt.setInt(4, incluye.getIdServicio());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar en Incluye: " + e.getMessage());
        }
    }

    public List<Incluye> listarTodos() {
        List<Incluye> lista = new ArrayList<>();
        String sql = "SELECT * FROM Incluye";

        try (Connection cn = (Connection) ConexionBD.getConexion();
             Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Incluye incluye = new Incluye(
                    rs.getInt("idHabitacion"),
                    rs.getLong("cedula"),
                    rs.getTimestamp("FechaHoraLlegada"),
                    rs.getInt("idServicio")
                );
                lista.add(incluye);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar Incluye: " + e.getMessage());
        }

        return lista;
    }

    public void eliminar(Incluye incluye) {
        String sql = "DELETE FROM Incluye WHERE idHabitacion = ? AND cedula = ? AND FechaHoraLlegada = ? AND idServicio = ?";

        try (Connection cn = (Connection) ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setInt(1, incluye.getIdHabitacion());
            stmt.setLong(2, incluye.getCedula());
            stmt.setTimestamp(3, incluye.getFechaHoraLlegada());
            stmt.setInt(4, incluye.getIdServicio());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar Incluye: " + e.getMessage());
        }
    }
}

