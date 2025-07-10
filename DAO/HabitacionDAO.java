package DAO;

import conexion.ConexionBD;
import modelo.Habitacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {
    private Connection conn;

    public HabitacionDAO() {
        conn = ConexionBD.getConexion().getConnection();
    }

    public boolean insertarHabitacion(Habitacion habitacion) {
        String sql = "INSERT INTO Habitacion (IdHabitacion, tipoHabitacion, PrecioNoche, EstadoMantenimiento, EstadoDisponibilidad) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, habitacion.getIdHabitacion());
            stmt.setString(2, habitacion.getTipoHabitacion());
            stmt.setInt(3, habitacion.getPrecioNoche());
            stmt.setBoolean(4, habitacion.isEstadoMantenimiento());
            stmt.setBoolean(5, habitacion.isEstadoDisponibilidad());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Habitacion obtenerHabitacionPorId(int id) {
        String sql = "SELECT * FROM Habitacion WHERE IdHabitacion = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Habitacion(
                        rs.getInt("IdHabitacion"),
                        rs.getString("tipoHabitacion"),
                        rs.getInt("PrecioNoche"),
                        rs.getBoolean("EstadoMantenimiento"),
                        rs.getBoolean("EstadoDisponibilidad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Habitacion> listarHabitaciones() {
        List<Habitacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Habitacion";
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Habitacion(
                        rs.getInt("IdHabitacion"),
                        rs.getString("tipoHabitacion"),
                        rs.getInt("PrecioNoche"),
                        rs.getBoolean("EstadoMantenimiento"),
                        rs.getBoolean("EstadoDisponibilidad")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizarHabitacion(Habitacion habitacion) {
        String sql = "UPDATE Habitacion SET tipoHabitacion = ?, PrecioNoche = ?, EstadoMantenimiento = ?, EstadoDisponibilidad = ? WHERE IdHabitacion = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, habitacion.getTipoHabitacion());
            stmt.setInt(2, habitacion.getPrecioNoche());
            stmt.setBoolean(3, habitacion.isEstadoMantenimiento());
            stmt.setBoolean(4, habitacion.isEstadoDisponibilidad());
            stmt.setInt(5, habitacion.getIdHabitacion());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Habitacion> obtenerHabitacionesEnMantenimiento() {
        List<Habitacion> mantenimiento = new ArrayList<>();
        String sql = "SELECT * FROM Habitacion WHERE estadoMantenimiento = 1";

        try (Connection cn = ConexionBD.getConexion().getConnection();
                Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Habitacion h = new Habitacion();
                h.setIdHabitacion(rs.getInt("idHabitacion"));
                h.setTipoHabitacion(rs.getString("tipoHabitacion"));
                h.setPrecioNoche(rs.getInt("precioNoche"));
                h.setEstadoMantenimiento(rs.getBoolean("estadoMantenimiento"));
                h.setEstadoDisponibilidad(rs.getBoolean("estadoDisponibilidad"));
                mantenimiento.add(h);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener habitaciones en mantenimiento: " + e.getMessage());
        }

        return mantenimiento;
    }

    public List<Habitacion> obtenerHabitacionesLibres() {
        List<Habitacion> habitacionesLibres = new ArrayList<>();
        String sql = "SELECT * FROM Habitacion WHERE estadoDisponibilidad = 1 AND estadoMantenimiento = 0";

        try (Connection cn = ConexionBD.getConexion().getConnection();
                PreparedStatement stmt = cn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Habitacion h = new Habitacion();
                h.setIdHabitacion(rs.getInt("idHabitacion"));
                h.setTipoHabitacion(rs.getString("tipoHabitacion"));
                h.setPrecioNoche(rs.getInt("precioNoche"));
                h.setEstadoDisponibilidad(rs.getBoolean("estadoDisponibilidad"));
                h.setEstadoMantenimiento(rs.getBoolean("estadoMantenimiento"));
                habitacionesLibres.add(h);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener habitaciones libres: " + e.getMessage());
        }

        return habitacionesLibres;
    }

    public List<Habitacion> obtenerHabitacionesOcupadas() {
        List<Habitacion> ocupadas = new ArrayList<>();
        String sql = "SELECT * FROM Habitacion WHERE estadoDisponibilidad = 0 AND estadoMantenimiento = 0";
        try (Connection cn = ConexionBD.getConexion().getConnection();
                Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Habitacion h = new Habitacion();
                h.setIdHabitacion(rs.getInt("idHabitacion"));
                h.setTipoHabitacion(rs.getString("tipoHabitacion"));
                h.setPrecioNoche(rs.getInt("precioNoche"));
                h.setEstadoMantenimiento(rs.getBoolean("estadoMantenimiento"));
                h.setEstadoDisponibilidad(rs.getBoolean("estadoDisponibilidad"));
                ocupadas.add(h);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener habitaciones ocupadas: " + e.getMessage());
        }

        return ocupadas;
    }

    public boolean eliminarHabitacion(int id) {
        String sql = "DELETE FROM Habitacion WHERE IdHabitacion = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Habitacion> listarTodas() {
    List<Habitacion> lista = new ArrayList<>();
    String sql = "SELECT * FROM Habitacion";

    try (PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Habitacion h = new Habitacion();
            h.setIdHabitacion(rs.getInt("IdHabitacion"));
            h.setTipoHabitacion(rs.getString("TipoHabitacion"));
            h.setPrecioNoche(rs.getInt("PrecioNoche"));
            h.setEstadoMantenimiento(rs.getBoolean("EstadoMantenimiento"));
            h.setEstadoDisponibilidad(rs.getBoolean("EstadoDisponibilidad"));
            lista.add(h);
        }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
