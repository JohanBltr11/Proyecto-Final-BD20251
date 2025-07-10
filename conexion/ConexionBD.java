package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static ConexionBD instancia = null;
    private Connection conn = null;

    /*private final String url = "jdbc:sqlserver://finalproyectobd.database.windows.net:1433;database=Hotel_database";
    private final String user = "cacelish"; 
    private final String password = "LuffyGear5";*/

	String urlll="jdbc:sqlserver://finalproyectobd.database.windows.net:1433;"+
	"database=Hotel_database;"+
	"user=cacelish@finalproyectobd;"+
	"password=LuffyGear5;"+
	"encrypt=true;"+
	"trustServerCertificate=false;"+
	"hostNameInCertificate=*.database.windows.net;"+
	"loginTimeout=30;";

    private ConexionBD() {
        try {
            conn = DriverManager.getConnection(urlll);
            System.out.println("Conexión exitosa a Azure SQL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConexionBD getConexion() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    public Connection getConnection() {
        return conn;
    }
}
