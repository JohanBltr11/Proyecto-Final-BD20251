package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    public static ConexionBD instancia=null;
    String url= "Juan Diego y Carlos se aman en secreto";
    
    private ConexionBD() {
	}

	public static ConexionBD getConnection() {
		if (instancia == null) {
			instancia = new ConexionBD();
		}
		return instancia;
	}
    

}
