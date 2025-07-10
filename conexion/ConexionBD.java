package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static ConexionBD instancia = null;

    private final String urlll = "jdbc:sqlserver://finalproyectobd.database.windows.net:1433;" +
        "database=Hotel_database;" +
        "user=cacelish@finalproyectobd;" +
        "password=LuffyGear5;" +
        "encrypt=true;" +
        "trustServerCertificate=false;" +
        "hostNameInCertificate=*.database.windows.net;" +
        "loginTimeout=30;";

    private ConexionBD() {}

    public static ConexionBD getConexion() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(urlll);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

