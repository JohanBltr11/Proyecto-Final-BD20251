package control;
import java.sql.Connection;
import modelo.Cliente;
import DAO.ClienteDAO;
import conexion.*;
public class main {
    public static void main(String args[]){
        ConexionBD conexion = ConexionBD.getConexion();

        Connection conn = conexion.getConnection();
        
        if (conn != null) {
            System.out.println("✅ La conexión está activa y lista para usar.");
        } else {
            System.out.println("❌ Error: No se pudo establecer la conexión.");
        }

        ClienteDAO client= new ClienteDAO();
        System.out.println(client.obtenerClientePorCedula(1078912345).getCorreoElectronico());

    }
}
