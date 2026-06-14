package tiendaderopa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Consultar {

    // ===================== CLIENTE =====================
    
    //---------------------- Insertar cliente ------------------------
    
    public void insertarCliente(Cliente c) {

        String sql = "INSERT INTO cliente(nombre, email, telefono, direccion) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getDireccion());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error cliente: " + e.getMessage());
        }
    }

    //---------------------- Listar clientes ------------------------------------
    
    public List<Cliente> listarClientes() {

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
            System.out.println("Error listar clientes: " + e.getMessage());
        }

        return lista;
    }

    //----------------------- Actualizar cliente --------------------------------
    
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

    //----------------------------- Eliminar cliente --------------------------
    
    public void eliminarCliente(int id) {

        String sql = "DELETE FROM cliente WHERE idCliente=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar cliente: " + e.getMessage());
        }
    }

    // ============================= PRENDA ================================
    
    // --------------------- Insertar prenda ---------------------------------
    
    public void insertarPrenda(Prenda p) {

        String sql = "INSERT INTO prenda(nombre, talla, color, precio, stock, idCategoria) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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

    //--------------------- Listar prendas -----------------------------------
    public List<Prenda> listarPrendas() {

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
            System.out.println("Error listar prendas: " + e.getMessage());
        }

        return lista;
    }

    //------------------- Actualizar Prenda -----------------------------
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

    //------------------------ Eliminar prenda -------------------------------
    public void eliminarPrenda(int id) {

        String sql = "DELETE FROM prenda WHERE idPrenda=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar prenda: " + e.getMessage());
        }
    }

    // ===================== CATEGORIA =====================
    
    //--------------------- Insertar categoria --------------------------
    
    public void insertarCategoria(Categoria c) {

        String sql = "INSERT INTO categoria(nombre, descripcion) VALUES (?, ?)";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error categoria: " + e.getMessage());
        }
    }

    //------------------- listar categorias ---------------------------------
  
    public List<Categoria> listarCategorias() {

        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        try (Connection con = Conexion.getconnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Categoria c = new Categoria();

                c.setIdCategoria(rs.getInt("idCategoria"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));

                lista.add(c);
            }

        } catch (Exception e) {
            System.out.println("Error categorias: " + e.getMessage());
        }

        return lista;
    }

    // ---------------- existe categoria -------------------------------------
    
    public boolean existeCategoria(int id) {

        String sql = "SELECT idCategoria FROM categoria WHERE idCategoria=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            return false;
        }
    }

    // --------------------------- Actualizar categoria ------------------------
    
    public void actualizarCategoria(Categoria c) {

        String sql = "UPDATE categoria SET nombre=?, descripcion=? WHERE idCategoria=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getIdCategoria());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error actualizar categoria: " + e.getMessage());
        }
    }

   // ----------------------------- Eliminar categoria -------------------------
    
    public void eliminarCategoria(int id) {

        String sql = "DELETE FROM categoria WHERE idCategoria=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar categoria: " + e.getMessage());
        }
    }

    // ===================== VENTA =====================
    
    //--------------------- Insertar venta -----------------------------------
    
    public void insertarVenta(Venta v) {

        String sql = "INSERT INTO venta(fecha,total,metodoPago,idCliente,idVendedor) VALUES(?,?,?,?,?)";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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

    // -------------------Listar venta ----------------------------
    public List<Venta> listarVentas() {

        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM venta";

        try (Connection con = Conexion.getconnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

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
            System.out.println("Error ventas: " + e.getMessage());
        }

        return lista;
    }

    //-------------------------- Actualizar venta ----------------------------------
    
    public void actualizarVenta(Venta v) {

        String sql = "UPDATE venta SET fecha=?, total=?, metodoPago=?, idCliente=?, idVendedor=? WHERE idVenta=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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
    
    // ------------------------------- Eliminar venta --------------------------

    public void eliminarVenta(int id) {

        String sql = "DELETE FROM venta WHERE idVenta=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar venta: " + e.getMessage());
        }
    }

    // ===================== VENDEDOR =====================
    
    //----------------------- Insertar vendedor ----------------------
    
    public void insertarVendedor(Vendedor v) {

        String sql = "INSERT INTO vendedor(nombre, email, puesto) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getNombre());
            ps.setString(2, v.getEmail());
            ps.setString(3, v.getPuesto());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error vendedor: " + e.getMessage());
        }
    }

    // -------------------- Listar vendedor ----------------------
    
    public List<Vendedor> listarVendedores() {

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
            System.out.println("Error vendedores: " + e.getMessage());
        }

        return lista;
    }

    // ---------------------- Actualizar vendedor -------------------------
    
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

    // ------------------------ Eliminar vendedor -----------------------
    
    public void eliminarVendedor(int id) {

        String sql = "DELETE FROM vendedor WHERE idVendedor=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar vendedor: " + e.getMessage());
        }
    }
}
