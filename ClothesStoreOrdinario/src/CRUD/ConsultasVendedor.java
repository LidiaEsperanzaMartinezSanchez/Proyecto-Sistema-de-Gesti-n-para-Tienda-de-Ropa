/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import ConexionBD.Conexion;
import Entidades.Vendedor;
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
public class ConsultasVendedor {
    public void insertarVendedor(Vendedor v) {
        String sql = "INSERT INTO vendedor(nombre, email, puesto) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getNombre());
            ps.setString(2, v.getEmail());
            ps.setString(3, v.getPuesto());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error insertar vendedor: " + e.getMessage());
        }
    }

    public List<Vendedor> consultarVendedores() {
        List<Vendedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM vendedor";

        try (Connection con = Conexion.getconnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Vendedor v = new Vendedor();
                v.setIdVendedor(rs.getInt("idVendedor"));
                v.setNombre(rs.getString("nombre"));
                v.setEmail(rs.getString("email"));
                v.setPuesto(rs.getString("puesto"));
                lista.add(v);
            }

        } catch (Exception e) {
            System.out.println("Error consultar vendedores: " + e.getMessage());
        }

        return lista;
    }

    public void actualizarVendedor(Vendedor v) {
        String sql = "UPDATE vendedor SET nombre=?, email=?, puesto=? WHERE idVendedor=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getNombre());
            ps.setString(2, v.getEmail());
            ps.setString(3, v.getPuesto());
            ps.setInt(4, v.getIdVendedor());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error actualizar vendedor: " + e.getMessage());
        }
    }

    public void eliminarVendedor(int id) {
        String sql = "DELETE FROM vendedor WHERE idVendedor=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar vendedor: " + e.getMessage());
        }
    }

    // ===== EXISTE VENDEDOR =====
    public boolean existeVendedor(int idVendedor) {
        String sql = "SELECT idVendedor FROM vendedor WHERE idVendedor=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idVendedor);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println("Error verificar vendedor: " + e.getMessage());
            return false;
        }
    }

    public List<Vendedor> buscarVendedor(String texto) {

        List<Vendedor> lista = new ArrayList<>();

        String sql = "SELECT * FROM vendedor WHERE idVendedor LIKE ? OR nombre LIKE ?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Vendedor v = new Vendedor();

                v.setIdVendedor(rs.getInt("idVendedor"));
                v.setNombre(rs.getString("nombre"));
                v.setEmail(rs.getString("email"));
                v.setPuesto(rs.getString("puesto"));

                lista.add(v);
            }

        } catch (Exception e) {
            System.out.println("Error buscar vendedor: " + e.getMessage());
        }

        return lista;
    }
}
