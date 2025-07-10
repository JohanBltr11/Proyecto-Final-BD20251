package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionBD;
import modelo.Empleado;
import modelo.Area;

public class EmpleadoDAO {

    // INSERTAR
    public void insertar(Empleado empleado) {
        String sql = "INSERT INTO Empleado (cedula, cargo, IdArea) VALUES (?, ?, ?)";

        try (Connection cn = (Connection) ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setLong(1, empleado.getCedula());
            stmt.setString(2, empleado.getCargo());
            stmt.setInt(3, empleado.getArea().getIdArea());  // ✅ ahora usamos area

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar empleado: " + e.getMessage());
        }
    }

    // ACTUALIZAR
    public void actualizar(Empleado empleado) {
        String sql = "UPDATE Empleado SET cargo = ?, IdArea = ? WHERE cedula = ?";

        try (Connection cn = (Connection) ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setString(1, empleado.getCargo());
            stmt.setInt(2, empleado.getArea().getIdArea());  // ✅ ahora usamos area
            stmt.setLong(3, empleado.getCedula());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar empleado: " + e.getMessage());
        }
    }

    // ELIMINAR
    public void eliminar(long cedula) {
        String sql = "DELETE FROM Empleado WHERE cedula = ?";

        try (Connection cn = (Connection)ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setLong(1, cedula);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar empleado: " + e.getMessage());
        }
    }

    // LISTAR TODOS
    public List<Empleado> listarTodos() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM Empleado";

        try (Connection cn = (Connection)ConexionBD.getConexion();
             Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setCedula(rs.getLong("cedula"));
                emp.setCargo(rs.getString("cargo"));

                Area area = new Area();
                area.setIdArea(rs.getInt("IdArea"));
                emp.setArea(area);

                empleados.add(emp);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar empleados: " + e.getMessage());
        }

        return empleados;
    }

    // CONSULTAR POR CÉDULA
    public Empleado buscarPorCedula(long cedula) {
        String sql = "SELECT * FROM Empleado WHERE cedula = ?";

        try (Connection cn = (Connection) ConexionBD.getConexion();
             PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setLong(1, cedula);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Empleado emp = new Empleado();
                    emp.setCedula(rs.getLong("cedula"));
                    emp.setCargo(rs.getString("cargo"));

                    Area area = new Area();
                    area.setIdArea(rs.getInt("IdArea"));
                    emp.setArea(area);

                    return emp;
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar empleado: " + e.getMessage());
        }
        return null;
    }
}
