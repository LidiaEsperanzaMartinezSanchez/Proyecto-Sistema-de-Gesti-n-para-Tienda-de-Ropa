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
                pw.println("ID Venta: " + v.getIdVenta());
                pw.println("Fecha: " + v.getFecha());
                pw.println("Total: $" + v.getTotal());
                pw.println("Metodo Pago: " + v.getMetodoPago());
                pw.println("ID Cliente: " + v.getIdCliente());
                pw.println("ID Vendedor: " + v.getIdVendedor());
                pw.println("----------------------");
            }

            System.out.println("ventas.txt generado");

        } catch (Exception e) {
            System.out.println("Error ventas: " + e.getMessage());
        }
    }

    // ==================== IMPORTAR TODO ======================
    public void importarTodo() {
        importarClientes();
        importarPrendas();
        System.out.println("Importación completa");
    }

    // ====================== IMPORTAR CLIENTES =================
    public void importarClientes() {

        try (BufferedReader br = new BufferedReader(new FileReader("clientes.txt"))) {

            String linea;
            Cliente c = null;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                if (linea.startsWith("ID:")) {
                    c = new Cliente();
                }

                if (c != null) {

                    if (linea.startsWith("Nombre:")) {
                        c.setNombre(linea.substring(8).trim());
                    }

                    if (linea.startsWith("Email:")) {
                        c.setEmail(linea.substring(7).trim());
                    }

                    if (linea.startsWith("Telefono:")) {
                        c.setTelefono(linea.substring(10).trim());
                    }

                    if (linea.startsWith("Direccion:")) {
                        c.setDireccion(linea.substring(11).trim());
                    }

                    if (linea.startsWith("----------------------")) {
                        dao.insertarCliente(c);
                        c = null;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error clientes import: " + e.getMessage());
        }
    }

    // ================== IMPORTAR PRENDAS =========================0
    public void importarPrendas() {

        try (BufferedReader br = new BufferedReader(new FileReader("prendas.txt"))) {

            String linea;
            Prenda p = null;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                if (linea.startsWith("ID:")) {
                    p = new Prenda();
                }

                if (p != null) {

                    if (linea.startsWith("Nombre:")) {
                        p.setNombre(linea.substring(8).trim());
                    }

                    if (linea.startsWith("Talla:")) {
                        p.setTalla(linea.substring(7).trim());
                    }

                    if (linea.startsWith("Color:")) {
                        p.setColor(linea.substring(7).trim());
                    }

                    if (linea.startsWith("Precio:")) {
                        p.setPrecio(Double.parseDouble(linea.substring(8).replace("$", "").trim()));
                    }

                    if (linea.startsWith("Stock:")) {
                        p.setStock(Integer.parseInt(linea.substring(7).trim()));
                    }

                    if (linea.startsWith("ID Categoria:")) {
                        p.setIdCategoria(Integer.parseInt(linea.substring(14).trim()));
                    }

                    if (linea.startsWith("----------------------")) {

                        // ----- VALIDACIÓN FOREIGN KEY ------------------
                        if (dao.existeCategoria(p.getIdCategoria())) {
                            dao.insertarPrenda(p);
                        } else {
                            System.out.println("Categoria no existe: " + p.getIdCategoria());
                        }

                        p = null;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error prendas import: " + e.getMessage());
        }
    }
}