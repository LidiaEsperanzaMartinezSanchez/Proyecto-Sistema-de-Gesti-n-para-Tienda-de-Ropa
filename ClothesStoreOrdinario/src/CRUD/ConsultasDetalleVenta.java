/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import ConexionBD.Conexion;
import Entidades.DetalleVenta;
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
public class ConsultasDetalleVenta {
    public void insertarDetalleVenta(DetalleVenta d) {

        String sql = "INSERT INTO detalleventa(cantidad,precioUnitario,idVenta,idPrenda) VALUES(?,?,?,?)";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, d.getCantidad());
            ps.setDouble(2, d.getPrecioUnitario());
            ps.setInt(3, d.getIdVenta());
            ps.setInt(4, d.getIdPrenda());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error detalle venta: " + e.getMessage());
        }
    }

    public List<DetalleVenta> consultarDetalleVenta() {

        List<DetalleVenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalleventa";

        try (Connection con = Conexion.getconnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                DetalleVenta d = new DetalleVenta();

                d.setIdDetalle(rs.getInt("idDetalle"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setPrecioUnitario(rs.getDouble("precioUnitario"));
                d.setIdVenta(rs.getInt("idVenta"));
                d.setIdPrenda(rs.getInt("idPrenda"));

                lista.add(d);
            }

        } catch (Exception e) {
            System.out.println("Error consultar detalle venta: " + e.getMessage());
        }

        return lista;
    }

    public void actualizarDetalleVenta(DetalleVenta d) {

        String sql = "UPDATE detalleventa SET cantidad=?, precioUnitario=?, idVenta=?, idPrenda=? WHERE idDetalle=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, d.getCantidad());
            ps.setDouble(2, d.getPrecioUnitario());
            ps.setInt(3, d.getIdVenta());
            ps.setInt(4, d.getIdPrenda());
            ps.setInt(5, d.getIdDetalle());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error actualizar detalle venta: " + e.getMessage());
        }
    }

    public void eliminarDetalleVenta(int id) {

        String sql = "DELETE FROM detalleventa WHERE idDetalle=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar detalle venta: " + e.getMessage());
        }
    }

    public List<DetalleVenta> buscarDetalleVenta(String texto) {

        List<DetalleVenta> lista = new ArrayList<>();

        String sql = "SELECT * FROM detalleventa WHERE idDetalle LIKE ? OR idVenta LIKE ? OR idPrenda LIKE ?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");
            ps.setString(3, "%" + texto + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                DetalleVenta d = new DetalleVenta();

                d.setIdDetalle(rs.getInt("idDetalle"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setPrecioUnitario(rs.getDouble("precioUnitario"));
                d.setIdVenta(rs.getInt("idVenta"));
                d.setIdPrenda(rs.getInt("idPrenda"));

                lista.add(d);
            }

        } catch (Exception e) {
            System.out.println("Error buscar detalle venta: " + e.getMessage());
        }

        return lista;
    }
}
