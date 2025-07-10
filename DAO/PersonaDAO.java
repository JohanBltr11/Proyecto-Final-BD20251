package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import conexion.ConexionBD;
import modelo.Persona;

public class PersonaDAO {
    private Connection cn;

    // INSERTAR
    public void insertar(Persona persona) {
        String sql = "INSERT INTO Persona (cedula, primerN, segundoN, primerA, segundoA, Carrera, Calle, Numero) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = ConexionBD.getConexion().getConnection();                PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setLong(1, persona.getCedula());
            stmt.setString(2, persona.getPrimerN());
            stmt.setString(3, persona.getSegundoN());
            stmt.setString(4, persona.getPrimerA());
            stmt.setString(5, persona.getSegundoA());
            stmt.setString(6, persona.getCarrera());
            stmt.setString(7, persona.getCalle());
            stmt.setString(8, persona.getNumero());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar persona: " + e.getMessage());
        }
    }

    // OBTENER TODOS
    public List<Persona> listarTodos() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM Persona";

        try (Connection cn = ConexionBD.getConexion().getConnection();                Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Persona p = new Persona();
                p.setCedula(rs.getLong("cedula"));
                p.setPrimerN(rs.getString("primerN"));
                p.setSegundoN(rs.getString("segundoN"));
                p.setPrimerA(rs.getString("primerA"));
                p.setSegundoA(rs.getString("segundoA"));
                p.setCarrera(rs.getString("Carrera"));
                p.setCalle(rs.getString("Calle"));
                p.setNumero(rs.getString("Numero"));

                personas.add(p);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar personas: " + e.getMessage());
        }

        return personas;
    }

    // ELIMINAR POR CÉDULA
    public void eliminar(long cedula) {
        String sql = "DELETE FROM Persona WHERE cedula = ?";

        try (Connection cn = ConexionBD.getConexion().getConnection();                PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setLong(1, cedula);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar persona: " + e.getMessage());
        }
    }

    // ACTUALIZAR
    public void actualizar(Persona persona) {
        String sql = "UPDATE Persona SET primerN=?, segundoN=?, primerA=?, segundoA=?, "
                + "Carrera=?, Calle=?, Numero=? WHERE cedula=?";

        try (Connection cn = ConexionBD.getConexion().getConnection();                PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setString(1, persona.getPrimerN());
            stmt.setString(2, persona.getSegundoN());
            stmt.setString(3, persona.getPrimerA());
            stmt.setString(4, persona.getSegundoA());
            stmt.setString(5, persona.getCarrera());
            stmt.setString(6, persona.getCalle());
            stmt.setString(7, persona.getNumero());
            stmt.setLong(8, persona.getCedula());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar persona: " + e.getMessage());
        }
    }

    // OBTENER PERSONA POR CÉDULA
    public Persona obtenerPersonaPorCedula(long cedula) {
        String sql = "SELECT * FROM Persona WHERE cedula = ?";
        Persona persona = null;

        try (Connection cn = ConexionBD.getConexion().getConnection();                PreparedStatement stmt = cn.prepareStatement(sql)) {

            stmt.setLong(1, cedula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                persona = new Persona();
                persona.setCedula(rs.getLong("cedula"));
                persona.setPrimerN(rs.getString("primerN"));
                persona.setSegundoN(rs.getString("segundoN"));
                persona.setPrimerA(rs.getString("primerA"));
                persona.setSegundoA(rs.getString("segundoA"));
                persona.setCarrera(rs.getString("Carrera"));
                persona.setCalle(rs.getString("Calle"));
                persona.setNumero(rs.getString("Numero"));
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener persona por cédula: " + e.getMessage());
        }

        return persona;
    }

}
