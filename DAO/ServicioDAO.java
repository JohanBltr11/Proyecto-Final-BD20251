package DAO;

import conexion.ConexionBD;
import modelo.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {

    // INSERTAR
    public void insertar(Servicio servicio) {
        String sql = "INSERT INTO Servicio (IdServicio, Costo, nombreDescriptivoServicio, DetalleServicio) VALUES (?, ?, ?, ?)";

        try (Connection cn = ConexionBD.getConexion().getConnection();             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setInt(1, servicio.getIdServicio());
            stmt.setInt(2, servicio.getCosto());
            stmt.setString(3, servicio.getNombreDescriptivoServicio());
            stmt.setString(4, servicio.getDetalleServicio());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar servicio: " + e.getMessage());
        }
    }

    // ACTUALIZAR
    public void actualizar(Servicio servicio) {
        String sql = "UPDATE Servicio SET Costo = ?, nombreDescriptivoServicio = ?, DetalleServicio = ? WHERE IdServicio = ?";

        try (Connection cn = ConexionBD.getConexion().getConnection();             
            PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setInt(1, servicio.getCosto());
            stmt.setString(2, servicio.getNombreDescriptivoServicio());
            stmt.setString(3, servicio.getDetalleServicio());
            stmt.setInt(4, servicio.getIdServicio());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar servicio: " + e.getMessage());
        }
    }

    // ELIMINAR
    public void eliminar(int idServicio) {
        String sql = "DELETE FROM Servicio WHERE IdServicio = ?";

        try (Connection cn = ConexionBD.getConexion().getConnection();             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setInt(1, idServicio);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar servicio: " + e.getMessage());
        }
    }

    // LISTAR TODOS
    public List<Servicio> listarTodos() {
        List<Servicio> servicios = new ArrayList<>();
        String sql = "SELECT * FROM Servicio";

        try (Connection cn = ConexionBD.getConexion().getConnection();             Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Servicio s = new Servicio();
                s.setIdServicio(rs.getInt("IdServicio"));
                s.setCosto(rs.getInt("Costo"));
                s.setNombreDescriptivoServicio(rs.getString("nombreDescriptivoServicio"));
                s.setDetalleServicio(rs.getString("DetalleServicio"));

                servicios.add(s);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar servicios: " + e.getMessage());
        }

        return servicios;
    }

    // CONSULTAR POR ID
    public Servicio buscarPorId(int idServicio) {
        String sql = "SELECT * FROM Servicio WHERE IdServicio = ?";

        try (Connection cn = ConexionBD.getConexion().getConnection();             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setInt(1, idServicio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Servicio s = new Servicio();
                    s.setIdServicio(rs.getInt("IdServicio"));
                    s.setCosto(rs.getInt("Costo"));
                    s.setNombreDescriptivoServicio(rs.getString("nombreDescriptivoServicio"));
                    s.setDetalleServicio(rs.getString("DetalleServicio"));
                    return s;
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar servicio: " + e.getMessage());
        }

        return null;
    }
}
