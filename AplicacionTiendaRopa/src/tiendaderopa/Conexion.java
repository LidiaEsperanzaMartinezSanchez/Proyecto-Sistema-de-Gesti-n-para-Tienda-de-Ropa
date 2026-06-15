package tiendaderopa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private static final String URL = "jdbc:mysql://localhost:3306/TiendaRopa";
    private static final String USER = "root";
    private static final String PASS = "Lidia2026*";

    public static Connection getconnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion exitosa a la base de datos.");
        } catch (Exception e) {
            System.out.println("Error de conexion");
            e.printStackTrace();
        }
        return con;
    }
    
}