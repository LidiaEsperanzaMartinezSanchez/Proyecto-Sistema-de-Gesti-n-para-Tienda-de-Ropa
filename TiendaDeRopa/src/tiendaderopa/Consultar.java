package tiendaderopa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Consultar {

    // ===================== CLIENTE =====================
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
            System.out.println("Error cliente: " + e.getMessage());
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

    // ===================== PRENDA =====================
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

        try (Connection con = Conexion.getconnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

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

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, stock);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error stock: " + e.getMessage());
        }
    }

    public void eliminarPrenda(int id) {

        String sql = "DELETE FROM prenda WHERE idPrenda=?";

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar prenda: " + e.getMessage());
        }
    }

    // ===================== CATEGORIA =====================
    public void insertarCategoria(Categoria c) {

        String sql = "INSERT INTO categoria(nombre, descripcion) VALUES (?, ?)";

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error categoria: " + e.getMessage());
        }
    }

    public List<Categoria> consultarCategorias() {

        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        try (Connection con = Conexion.getconnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Categoria c = new Categoria();

                c.setIdCategoria(rs.getInt("idCategoria"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));

                lista.add(c);
            }

        } catch (Exception e) {
            System.out.println("Error consultar categorias: " + e.getMessage());
        }

        return lista;
    }

    public void actualizarCategoria(Categoria c) {

        String sql = "UPDATE categoria SET nombre=?, descripcion=? WHERE idCategoria=?";

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getIdCategoria());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error actualizar categoria: " + e.getMessage());
        }
    }

    public void eliminarCategoria(int id) {

        String sql = "DELETE FROM categoria WHERE idCategoria=?";

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar categoria: " + e.getMessage());
        }
    }

    // ===== EXISTE CATEGORIA (CORREGIDO) =====
    public boolean existeCategoria(int idCategoria) {

        String sql = "SELECT idCategoria FROM categoria WHERE idCategoria=?";

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            System.out.println("Error verificar categoria: " + e.getMessage());
            return false;
        }
    }

    // ===================== VENTA =====================
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

    // ===================== VENDEDOR =====================
    public void insertarVendedor(Vendedor v) {

        String sql = "INSERT INTO vendedor(nombre, email, puesto) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getNombre());
            ps.setString(2, v.getEmail());
            ps.setString(3, v.getPuesto());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error vendedor: " + e.getMessage());
        }
    }

    public List<Vendedor> consultarVendedores() {

        List<Vendedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM vendedor";

        try (Connection con = Conexion.getconnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

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

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

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

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar vendedor: " + e.getMessage());
        }
    }

    // ===================== DETALLE VENTA =====================
    public void insertarDetalleVenta(DetalleVenta d) {

        String sql = "INSERT INTO detalleventa(cantidad,precioUnitario,idVenta,idPrenda) VALUES(?,?,?,?)";

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

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

        try (Connection con = Conexion.getconnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

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

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

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

        try (Connection con = Conexion.getconnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar detalle venta: " + e.getMessage());
        }
    }
}