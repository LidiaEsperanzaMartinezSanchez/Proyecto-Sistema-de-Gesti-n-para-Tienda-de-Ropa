package tiendaderopa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private static final String URL = "jdbc:mysql://localhost:3306/TiendaRopa";
    private static final String USER1 = "root";
    private static final String USER2 = "root";
    private static final String PASS1 = "134679";
    private static final String PASS2 = "Lidia2026";

    public static Connection getconnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER1, PASS1);
            con = DriverManager.getConnection(URL, USER2, PASS2);
            System.out.println("Conexion exitosa a la base de datos.");
        } catch (Exception e) {
            System.out.println("Error de conexion");
            e.printStackTrace();
        }
        return con;
    }
    
}