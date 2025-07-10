package DAO;

import conexion.ConexionBD;
import modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO() {
        conn = ConexionBD.getConexion().getConnection();
    }

    public boolean insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (cedula, correoElectronico) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cliente.getCedula());
            stmt.setString(2, cliente.getCorreoElectronico());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cliente obtenerClientePorCedula(long cedula) {
        String sql = "SELECT * FROM Cliente WHERE cedula = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cedula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getLong("cedula"),
                        rs.getString("correoElectronico"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Cliente(
                        rs.getLong("cedula"),
                        rs.getString("correoElectronico")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void listarClientesConNombreYApellido() {
        String sql = "SELECT c.cedula, c.correoElectronico, p.nombre, p.apellido " +
                "FROM Cliente c JOIN Persona p ON c.cedula = p.cedula";

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("---- Lista de Clientes ----");
            while (rs.next()) {
                long cedula = rs.getLong("cedula");
                String correo = rs.getString("correoElectronico");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                System.out.println("Cédula: " + cedula +
                        ", Nombre: " + nombre +
                        ", Apellido: " + apellido +
                        ", Correo: " + correo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> listarClientesParaTabla() {
        List<Object[]> datos = new ArrayList<>();
        String sql = "SELECT c.cedula, p.primerN, p.primerA, c.correoElectronico " +
                "FROM Cliente c JOIN Persona p ON c.cedula = p.cedula";

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getLong("cedula");
                fila[1] = rs.getString("primerN");
                fila[2] = rs.getString("primerA");
                fila[3] = rs.getString("correoElectronico");
                datos.add(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }

    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET correoElectronico = ? WHERE cedula = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getCorreoElectronico());
            stmt.setLong(2, cliente.getCedula());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarCliente(long cedula) {
        String sql = "DELETE FROM Cliente WHERE cedula = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cedula);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void eliminarClienteTotal(long cedula) {
        try (Connection cn = ConexionBD.getConexion().getConnection()) {

            // Borrar Incluye
            try (PreparedStatement ps = cn.prepareStatement("DELETE FROM Incluye WHERE cedula = ?")) {
                ps.setLong(1, cedula);
                ps.executeUpdate();
            }

            // Borrar Reserva
            try (PreparedStatement ps = cn.prepareStatement("DELETE FROM Reserva WHERE Cedula = ?")) {
                ps.setLong(1, cedula);
                ps.executeUpdate();
            }

            // Borrar Consumo
            try (PreparedStatement ps = cn.prepareStatement("DELETE FROM Consumo WHERE cedula = ?")) {
                ps.setLong(1, cedula);
                ps.executeUpdate();
            }

            // Borrar Teléfono
            try (PreparedStatement ps = cn.prepareStatement("DELETE FROM Telefono WHERE cedula = ?")) {
                ps.setLong(1, cedula);
                ps.executeUpdate();
            }

            // Borrar Cliente
            try (PreparedStatement ps = cn.prepareStatement("DELETE FROM Cliente WHERE cedula = ?")) {
                ps.setLong(1, cedula);
                ps.executeUpdate();
            }

            // Borrar Persona (si no es empleado)
            try (PreparedStatement ps = cn.prepareStatement("DELETE FROM Persona WHERE cedula = ?")) {
                ps.setLong(1, cedula);
                ps.executeUpdate();
            }

            System.out.println("✅ Cliente eliminado exitosamente.");

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar cliente: " + e.getMessage());
        }
    }

}
