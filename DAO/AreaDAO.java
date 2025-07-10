package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import conexion.ConexionBD;
import modelo.Area;

public class AreaDAO {

    // INSERTAR
    public void insertar(Area area) {
        String sql = "INSERT INTO Area (IdArea, descripcionArea, nombreArea) VALUES (?, ?, ?)";

        try (Connection cn = (Connection) ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setInt(1, area.getIdArea());
            stmt.setString(2, area.getDescripcionArea());
            stmt.setString(3, area.getNombreArea());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar área: " + e.getMessage());
        }
    }

    // ACTUALIZAR
    public void actualizar(Area area) {
        String sql = "UPDATE Area SET descripcionArea = ?, nombreArea = ? WHERE IdArea = ?";

        try (Connection cn = (Connection) ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setString(1, area.getDescripcionArea());
            stmt.setString(2, area.getNombreArea());
            stmt.setInt(3, area.getIdArea());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar área: " + e.getMessage());
        }
    }

    // ELIMINAR
    public void eliminar(int idArea) {
        String sql = "DELETE FROM Area WHERE IdArea = ?";

        try (Connection cn = (Connection) ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setInt(1, idArea);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar área: " + e.getMessage());
        }
    }

    // LISTAR TODOS
    public List<Area> listarTodos() {
        List<Area> areas = new ArrayList<>();
        String sql = "SELECT * FROM Area";

        try (Connection cn = (Connection) ConexionBD.getConexion();
             Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Area area = new Area();
                area.setIdArea(rs.getInt("IdArea"));
                area.setDescripcionArea(rs.getString("descripcionArea"));
                area.setNombreArea(rs.getString("nombreArea"));
                areas.add(area);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar áreas: " + e.getMessage());
        }

        return areas;
    }

    // BUSCAR POR ID
    public Area buscarPorId(int idArea) {
        String sql = "SELECT * FROM Area WHERE IdArea = ?";

        try (Connection cn = (Connection) ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setInt(1, idArea);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Area area = new Area();
                    area.setIdArea(rs.getInt("IdArea"));
                    area.setDescripcionArea(rs.getString("descripcionArea"));
                    area.setNombreArea(rs.getString("nombreArea"));
                    return area;
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar área: " + e.getMessage());
        }

        return null;
    }
}
