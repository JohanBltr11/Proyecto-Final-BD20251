package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionBD;
import modelo.Empleado;
import modelo.Area;
import modelo.Persona;

public class EmpleadoDAO {
    private Connection conn;

    public EmpleadoDAO() {
        conn = ConexionBD.getConexion().getConnection();
    }

    // INSERTAR
    public void insertar(Empleado empleado) {
        String sql = "INSERT INTO Empleado (cedula, cargo, IdArea) VALUES (?, ?, ?)";

        try (Connection cn = ConexionBD.getConexion().getConnection();
                PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setLong(1, empleado.getCedula());
            stmt.setString(2, empleado.getCargo());
            stmt.setInt(3, empleado.getArea().getIdArea()); // ✅ ahora usamos area

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar empleado: " + e.getMessage());
        }
    }

    // ACTUALIZAR
    public void actualizar(Empleado empleado) {
        String sql = "UPDATE Empleado SET cargo = ?, IdArea = ? WHERE cedula = ?";

        try (Connection cn = ConexionBD.getConexion().getConnection();
                PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setString(1, empleado.getCargo());
            stmt.setInt(2, empleado.getArea().getIdArea()); // ✅ ahora usamos area
            stmt.setLong(3, empleado.getCedula());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar empleado: " + e.getMessage());
        }
    }

    // ELIMINAR
    public void eliminar(long cedula) {
        String sql = "DELETE FROM Empleado WHERE cedula = ?";

        try (Connection cn = ConexionBD.getConexion().getConnection();
                PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setLong(1, cedula);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar empleado: " + e.getMessage());
        }
    }

    public List<Empleado> listarTodos() {
        List<Empleado> empleados = new ArrayList<>();

        String sql = "SELECT e.cedula, e.cargo, a.IdArea, a.NombreArea, " +
                "p.primerN, p.segundoN, p.primerA, p.segundoA, p.Carrera, p.Calle, p.Numero " +
                "FROM Empleado e " +
                "JOIN Persona p ON e.cedula = p.cedula " +
                "JOIN Area a ON e.IdArea = a.IdArea";

        try (Connection cn = ConexionBD.getConexion().getConnection();
                PreparedStatement stmt = cn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setCedula(rs.getLong("cedula"));
                emp.setCargo(rs.getString("cargo"));

                Area area = new Area();
                area.setIdArea(rs.getInt("IdArea"));
                area.setNombreArea(rs.getString("NombreArea")); // 👈 nombre del área
                emp.setArea(area);

                Persona p = new Persona();
                p.setCedula(rs.getLong("cedula"));
                p.setPrimerN(rs.getString("primerN"));
                p.setSegundoN(rs.getString("segundoN"));
                p.setPrimerA(rs.getString("primerA"));
                p.setSegundoA(rs.getString("segundoA"));
                p.setCarrera(rs.getString("Carrera"));
                p.setCalle(rs.getString("Calle"));
                p.setNumero(rs.getString("Numero"));

                emp.setPersona(p);
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

        try (Connection cn = ConexionBD.getConexion().getConnection();
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
