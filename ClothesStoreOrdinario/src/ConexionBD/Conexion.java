/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author webon
 */
public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/TiendaRopa";
    private static final String USER = "root";
    private static final String PASS = "134679";

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
