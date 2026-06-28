/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import ConexionBD.Conexion;
import Entidades.Prenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author webon
 */
public class ConsultasPrenda {
    public void insertarPrenda(Prenda p) {

        String sql = "INSERT INTO prenda(nombre, talla, color, precio, stock, idCategoria) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTalla());
            ps.setString(3, p.getColor());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getIdCategoria());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error prenda: " + e.getMessage());
        }
    }

    public List<Prenda> consultarPrendas() {

        List<Prenda> lista = new ArrayList<>();
        String sql = "SELECT * FROM prenda";

        try (Connection con = Conexion.getconnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Prenda p = new Prenda();

                p.setIdPrenda(rs.getInt("idPrenda"));
                p.setNombre(rs.getString("nombre"));
                p.setTalla(rs.getString("talla"));
                p.setColor(rs.getString("color"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setIdCategoria(rs.getInt("idCategoria"));

                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error consultar prendas: " + e.getMessage());
        }

        return lista;
    }

    public void actualizarPrendaStock(int id, int stock) {

        String sql = "UPDATE prenda SET stock=? WHERE idPrenda=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, stock);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error stock: " + e.getMessage());
        }
    }

    public void eliminarPrenda(int id) {

        String sql = "DELETE FROM prenda WHERE idPrenda=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar prenda: " + e.getMessage());
        }
    }

    public List<Prenda> buscarPrenda(String texto) {

        List<Prenda> lista = new ArrayList<>();

        String sql = "SELECT * FROM prenda WHERE idPrenda LIKE ? OR nombre LIKE ?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Prenda p = new Prenda();

                p.setIdPrenda(rs.getInt("idPrenda"));
                p.setNombre(rs.getString("nombre"));
                p.setTalla(rs.getString("talla"));
                p.setColor(rs.getString("color"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setIdCategoria(rs.getInt("idCategoria"));

                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error buscar prenda: " + e.getMessage());
        }

        return lista;
    }
}
