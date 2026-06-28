package CRUD;

import ConexionBD.Conexion;
import Entidades.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConsultasCategoria {
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

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar categoria: " + e.getMessage());
        }
    }

    // ===== EXISTE CATEGORIA  =====
    public boolean existeCategoria(int idCategoria) {

        String sql = "SELECT idCategoria FROM categoria WHERE idCategoria=?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            System.out.println("Error verificar categoria: " + e.getMessage());
            return false;
        }
    }

    public List<Categoria> buscarCategoria(String texto) {

        List<Categoria> lista = new ArrayList<>();

        String sql = "SELECT * FROM categoria WHERE idCategoria LIKE ? OR nombre LIKE ?";

        try (Connection con = Conexion.getconnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Categoria c = new Categoria();

                c.setIdCategoria(rs.getInt("idCategoria"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));

                lista.add(c);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }
}
