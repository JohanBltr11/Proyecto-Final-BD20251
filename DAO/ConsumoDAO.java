package DAO;

import conexion.ConexionBD;
import modelo.Consumo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsumoDAO {

    // INSERTAR
    public void insertar(Consumo consumo) {
        String sql = "INSERT INTO Consumo (FechaHora, IdServicio, cedula) VALUES (?, ?, ?)";

        try (Connection cn = (Connection)ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(consumo.getFechaHora()));
            stmt.setInt(2, consumo.getIdServicio());
            stmt.setLong(3, consumo.getCedula());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar consumo: " + e.getMessage());
        }
    }

    // ELIMINAR
    public void eliminar(int idServicio, long cedula) {
        String sql = "DELETE FROM Consumo WHERE IdServicio = ? AND cedula = ?";

        try (Connection cn = (Connection)ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setInt(1, idServicio);
            stmt.setLong(2, cedula);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar consumo: " + e.getMessage());
        }
    }

    // LISTAR TODOS
    public List<Consumo> listarTodos() {
        List<Consumo> consumos = new ArrayList<>();
        String sql = "SELECT * FROM Consumo";

        try (Connection cn = (Connection)ConexionBD.getConexion();
             Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Consumo c = new Consumo();
                c.setFechaHora(rs.getTimestamp("FechaHora").toLocalDateTime());
                c.setIdServicio(rs.getInt("IdServicio"));
                c.setCedula(rs.getLong("cedula"));

                consumos.add(c);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar consumos: " + e.getMessage());
        }

        return consumos;
    }

    // BUSCAR POR SERVICIO Y CÉDULA
    public Consumo buscarPorClave(int idServicio, long cedula) {
        String sql = "SELECT * FROM Consumo WHERE IdServicio = ? AND cedula = ?";

        try (Connection cn = (Connection)ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setInt(1, idServicio);
            stmt.setLong(2, cedula);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Consumo c = new Consumo();
                    c.setFechaHora(rs.getTimestamp("FechaHora").toLocalDateTime());
                    c.setIdServicio(rs.getInt("IdServicio"));
                    c.setCedula(rs.getLong("cedula"));
                    return c;
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar consumo: " + e.getMessage());
        }

        return null;
    }
}
