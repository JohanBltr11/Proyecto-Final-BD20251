package DAO;

import conexion.ConexionBD;
import modelo.Reserva;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    // INSERTAR
    public void insertar(Reserva reserva) {
        String sql = "INSERT INTO Reserva (tiempoMaxCancelacion, FechaHoraLlegada, FechaHoraSalida, ValorReserva, idHabitacion, Cedula) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cn = (Connection) ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getTiempoMaxCancelacion());
            stmt.setTimestamp(2, Timestamp.valueOf(reserva.getFechaHoraLlegada()));
            stmt.setTimestamp(3, Timestamp.valueOf(reserva.getFechaHoraSalida()));
            stmt.setInt(4, reserva.getValorReserva());
            stmt.setInt(5, reserva.getIdHabitacion());
            stmt.setLong(6, reserva.getCedula());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar reserva: " + e.getMessage());
        }
    }

    // ELIMINAR
    public void eliminar(int idHabitacion, long cedula, LocalDateTime fechaHoraLlegada) {
        String sql = "DELETE FROM Reserva WHERE idHabitacion = ? AND Cedula = ? AND FechaHoraLlegada = ?";

        try (Connection cn = (Connection)ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setInt(1, idHabitacion);
            stmt.setLong(2, cedula);
            stmt.setTimestamp(3, Timestamp.valueOf(fechaHoraLlegada));

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar reserva: " + e.getMessage());
        }
    }

    // LISTAR TODOS
    public List<Reserva> listarTodos() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM Reserva";

        try (Connection cn = (Connection)ConexionBD.getConexion();
             Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setTiempoMaxCancelacion(rs.getInt("tiempoMaxCancelacion"));
                r.setFechaHoraLlegada(rs.getTimestamp("FechaHoraLlegada").toLocalDateTime());
                r.setFechaHoraSalida(rs.getTimestamp("FechaHoraSalida").toLocalDateTime());
                r.setValorReserva(rs.getInt("ValorReserva"));
                r.setIdHabitacion(rs.getInt("idHabitacion"));
                r.setCedula(rs.getLong("Cedula"));
                reservas.add(r);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar reservas: " + e.getMessage());
        }

        return reservas;
    }

    // BUSCAR RESERVA ESPECÍFICA
    public Reserva buscar(int idHabitacion, long cedula, LocalDateTime fechaHoraLlegada) {
        String sql = "SELECT * FROM Reserva WHERE idHabitacion = ? AND Cedula = ? AND FechaHoraLlegada = ?";

        try (Connection cn = (Connection) ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setInt(1, idHabitacion);
            stmt.setLong(2, cedula);
            stmt.setTimestamp(3, Timestamp.valueOf(fechaHoraLlegada));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Reserva r = new Reserva();
                    r.setTiempoMaxCancelacion(rs.getInt("tiempoMaxCancelacion"));
                    r.setFechaHoraLlegada(rs.getTimestamp("FechaHoraLlegada").toLocalDateTime());
                    r.setFechaHoraSalida(rs.getTimestamp("FechaHoraSalida").toLocalDateTime());
                    r.setValorReserva(rs.getInt("ValorReserva"));
                    r.setIdHabitacion(rs.getInt("idHabitacion"));
                    r.setCedula(rs.getLong("Cedula"));
                    return r;
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar reserva: " + e.getMessage());
        }

        return null;
    }
}
