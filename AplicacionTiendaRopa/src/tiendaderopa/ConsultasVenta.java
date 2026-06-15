package tiendaderopa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConsultasVenta {
    public void insertarVenta(Venta v) {

        String sql = "INSERT INTO venta(fecha,total,metodoPago,idCliente,idVendedor) VALUES(?,?,?,?,?)";

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getFecha());
            ps.setDouble(2, v.getTotal());
            ps.setString(3, v.getMetodoPago());
            ps.setInt(4, v.getIdCliente());
            ps.setInt(5, v.getIdVendedor());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error insertar venta: " + e.getMessage());
        }
    }

    public List<Venta> consultarVentas() {

        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM venta";

        try (Connection con = Conexion.getconnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Venta v = new Venta();

                v.setIdVenta(rs.getInt("idVenta"));
                v.setFecha(rs.getString("fecha"));
                v.setTotal(rs.getDouble("total"));
                v.setMetodoPago(rs.getString("metodoPago"));
                v.setIdCliente(rs.getInt("idCliente"));
                v.setIdVendedor(rs.getInt("idVendedor"));

                lista.add(v);
            }

        } catch (Exception e) {
            System.out.println("Error consultar ventas: " + e.getMessage());
        }

        return lista;
    }

    public void actualizarVenta(Venta v) {

        String sql = "UPDATE venta SET fecha=?, total=?, metodoPago=?, idCliente=?, idVendedor=? WHERE idVenta=?";

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getFecha());
            ps.setDouble(2, v.getTotal());
            ps.setString(3, v.getMetodoPago());
            ps.setInt(4, v.getIdCliente());
            ps.setInt(5, v.getIdVendedor());
            ps.setInt(6, v.getIdVenta());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error actualizar venta: " + e.getMessage());
        }
    }

    public void eliminarVenta(int id) {

        String sql = "DELETE FROM venta WHERE idVenta=?";

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar venta: " + e.getMessage());
        }
    }

}
