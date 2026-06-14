package tiendaderopa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

        try (Connection con = Conexion.getconnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

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

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

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

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar cliente: " + e.getMessage());
        }
    }

    // ===== EXISTE CLIENTE =====
    public boolean existeCliente(int idCliente) {
        String sql = "SELECT idCliente FROM cliente WHERE idCliente=?";

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println("Error verificar cliente: " + e.getMessage());
            return false;
        }
    }
}