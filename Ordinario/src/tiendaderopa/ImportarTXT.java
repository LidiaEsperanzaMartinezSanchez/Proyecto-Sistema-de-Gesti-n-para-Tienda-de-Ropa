package tiendaderopa;

import java.io.*;
import java.util.List;

public class ImportarTXT {

    Consultar dao = new Consultar();

    // ============ EXPORTAR CLIENTES =====================
    public static void exportarClientes(List<Cliente> lista) {

        try (PrintWriter pw = new PrintWriter(new FileWriter("clientes.txt"))) {

            for (Cliente c : lista) {
                pw.println("ID: " + c.getIdCliente());
                pw.println("Nombre: " + c.getNombre());
                pw.println("Email: " + c.getEmail());
                pw.println("Telefono: " + c.getTelefono());
                pw.println("Direccion: " + c.getDireccion());
                pw.println("----------------------");
            }

            System.out.println("clientes.txt generado");

        } catch (Exception e) {
            System.out.println("Error clientes: " + e.getMessage());
        }
    }

    // ===================== EXPORTAR PRENDAS =========================
    public static void exportarPrendas(List<Prenda> lista) {

        try (PrintWriter pw = new PrintWriter(new FileWriter("prendas.txt"))) {

            for (Prenda p : lista) {
                pw.println("ID: " + p.getIdPrenda());
                pw.println("Nombre: " + p.getNombre());
                pw.println("Talla: " + p.getTalla());
                pw.println("Color: " + p.getColor());
                pw.println("Precio: $" + p.getPrecio());
                pw.println("Stock: " + p.getStock());
                pw.println("ID Categoria: " + p.getIdCategoria());
                pw.println("----------------------");
            }

            System.out.println("prendas.txt generado");

        } catch (Exception e) {
            System.out.println("Error prendas: " + e.getMessage());
        }
    }

    // ================= EXPORTAR VENTAS ==============================
    public static void exportarVentas(List<Venta> lista) {

        try (PrintWriter pw = new PrintWriter(new FileWriter("ventas.txt"))) {

            for (Venta v : lista) {
                pw.println("ID: " + v.getIdVenta());
                pw.println("Fecha: " + v.getFecha());
                pw.println("Total: " + v.getTotal());
                pw.println("Metodo: " + v.getMetodoPago());
                pw.println("ID Cliente: " + v.getIdCliente());
                pw.println("ID Vendedor: " + v.getIdVendedor());
                pw.println("----------------------");
            }

            System.out.println("ventas.txt generado");

        } catch (Exception e) {
            System.out.println("Error ventas: " + e.getMessage());
        }
    }

    // ================= EXPORTAR DETALLE VENTA ======================
    public static void exportarDetalleVenta(List<DetalleVenta> lista) {

        try (PrintWriter pw = new PrintWriter(new FileWriter("detalleventa.txt"))) {

            for (DetalleVenta d : lista) {
                pw.println("ID: " + d.getIdDetalle());
                pw.println("Cantidad: " + d.getCantidad());
                pw.println("Precio Unitario: " + d.getPrecioUnitario());
                pw.println("ID Venta: " + d.getIdVenta());
                pw.println("ID Prenda: " + d.getIdPrenda());
                pw.println("----------------------");
            }

            System.out.println("detalleventa.txt generado");

        } catch (Exception e) {
            System.out.println("Error detalle venta: " + e.getMessage());
        }
    }
}