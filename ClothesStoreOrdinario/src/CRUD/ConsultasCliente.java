/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import ConexionBD.Conexion;
import Entidades.Cliente;
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
public class ConsultasCliente {
    public void insertarCliente(Cliente c) {
        String sql = "INSERT INTO cliente(nombre, email, telefono, direccion) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getDireccion());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error insertar cliente: " + e.getMessage());
        }
    }

    public List<Cliente> consultarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection con = Conexion.getconnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("idCliente"));
                c.setNombre(rs.getString("nombre"));
                c.setEmail(rs.getString("email"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                lista.add(c);
            }

        } catch (Exception e) {
            System.out.println("Error consultar clientes: " + e.getMessage());
        }

        return lista;
    }

    public void actualizarCliente(Cliente c) {
        String sql = "UPDATE cliente SET nombre=?, email=?, telefono=?, direccion=? WHERE idCliente=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getDireccion());
            ps.setInt(5, c.getIdCliente());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error actualizar cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(int id) {
        String sql = "DELETE FROM cliente WHERE idCliente=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar cliente: " + e.getMessage());
        }
    }

    // ===== EXISTE CLIENTE =====
    public boolean existeCliente(int idCliente) {
        String sql = "SELECT idCliente FROM cliente WHERE idCliente=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println("Error verificar cliente: " + e.getMessage());
            return false;
        }
    }

    public List<Cliente> buscarCliente(String texto) {

        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT * FROM cliente WHERE idCliente LIKE ? OR nombre LIKE ?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Cliente c = new Cliente();

                c.setIdCliente(rs.getInt("idCliente"));
                c.setNombre(rs.getString("nombre"));
                c.setEmail(rs.getString("email"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));

                lista.add(c);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }
}
