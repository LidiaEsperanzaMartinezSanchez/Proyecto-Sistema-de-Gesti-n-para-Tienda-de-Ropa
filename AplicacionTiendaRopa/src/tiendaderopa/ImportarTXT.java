package tiendaderopa;

import java.io.*;
import java.util.List;

public class ImportarTXT {
    private final ConsultasCliente      daoCliente      = new ConsultasCliente();
    private final ConsultasPrenda       daoPrenda       = new ConsultasPrenda();
    private final ConsultasVenta        daoVenta        = new ConsultasVenta();
    private final ConsultasDetalleVenta daoDetalleVenta = new ConsultasDetalleVenta();
    private final ConsultasCategoria    daoCategoria    = new ConsultasCategoria();
    private final ConsultasVendedor     daoVendedor     = new ConsultasVendedor();

    // EXPORTAR
    public static void exportarClientes(List<Cliente> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("clientes.txt"))) {
            for (Cliente c : lista) {
                pw.println("ID: "         + c.getIdCliente());
                pw.println("Nombre: "     + c.getNombre());
                pw.println("Email: "      + c.getEmail());
                pw.println("Telefono: "   + c.getTelefono());
                pw.println("Direccion: "  + c.getDireccion());
                pw.println("----------------------");
            }
            System.out.println("clientes.txt generado");
        } catch (Exception e) {
            System.out.println("Error exportar clientes: " + e.getMessage());
        }
    }

    public static void exportarPrendas(List<Prenda> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("prendas.txt"))) {
            for (Prenda p : lista) {
                pw.println("ID: "           + p.getIdPrenda());
                pw.println("Nombre: "       + p.getNombre());
                pw.println("Talla: "        + p.getTalla());
                pw.println("Color: "        + p.getColor());
                pw.println("Precio: $"      + p.getPrecio());
                pw.println("Stock: "        + p.getStock());
                pw.println("ID Categoria: " + p.getIdCategoria());
                pw.println("----------------------");
            }
            System.out.println("prendas.txt generado");
        } catch (Exception e) {
            System.out.println("Error exportar prendas: " + e.getMessage());
        }
    }

    public static void exportarVentas(List<Venta> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("ventas.txt"))) {
            for (Venta v : lista) {
                pw.println("ID: "          + v.getIdVenta());
                pw.println("Fecha: "       + v.getFecha());
                pw.println("Total: "       + v.getTotal());
                pw.println("Metodo: "      + v.getMetodoPago());
                pw.println("ID Cliente: "  + v.getIdCliente());
                pw.println("ID Vendedor: " + v.getIdVendedor());
                pw.println("----------------------");
            }
            System.out.println("ventas.txt generado");
        } catch (Exception e) {
            System.out.println("Error exportar ventas: " + e.getMessage());
        }
    }

    public static void exportarDetalleVenta(List<DetalleVenta> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("detalleventa.txt"))) {
            for (DetalleVenta d : lista) {
                pw.println("ID: "              + d.getIdDetalle());
                pw.println("Cantidad: "        + d.getCantidad());
                pw.println("Precio Unitario: " + d.getPrecioUnitario());
                pw.println("ID Venta: "        + d.getIdVenta());
                pw.println("ID Prenda: "       + d.getIdPrenda());
                pw.println("----------------------");
            }
            System.out.println("detalleventa.txt generado");
        } catch (Exception e) {
            System.out.println("Error exportar detalle venta: " + e.getMessage());
        }
    }

    
    // IMPORTAR
    public void importarTodo() {
        importarClientes();
        importarPrendas();
        importarVentas();
        importarDetalleVenta();
        System.out.println("Importación completa");
    }

    // Clientes 
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
                    if (linea.startsWith("Nombre:"))    c.setNombre(valor(linea, "Nombre:"));
                    if (linea.startsWith("Email:"))     c.setEmail(valor(linea, "Email:"));
                    if (linea.startsWith("Telefono:"))  c.setTelefono(valor(linea, "Telefono:"));
                    if (linea.startsWith("Direccion:")) c.setDireccion(valor(linea, "Direccion:"));

                    if (linea.startsWith("------")) {
                        daoCliente.insertarCliente(c);
                        c = null;
                    }
                }
            }
            System.out.println("Clientes importados");
        } catch (Exception e) {
            System.out.println("Error importar clientes: " + e.getMessage());
        }
    }

    // Prendas 
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
                    if (linea.startsWith("Nombre:"))      p.setNombre(valor(linea, "Nombre:"));
                    if (linea.startsWith("Talla:"))       p.setTalla(valor(linea, "Talla:"));
                    if (linea.startsWith("Color:"))       p.setColor(valor(linea, "Color:"));
                    if (linea.startsWith("Precio:"))      p.setPrecio(Double.parseDouble(
                                                              valor(linea, "Precio:").replace("$", "")));
                    if (linea.startsWith("Stock:"))       p.setStock(Integer.parseInt(valor(linea, "Stock:")));
                    if (linea.startsWith("ID Categoria:")) p.setIdCategoria(Integer.parseInt(
                                                              valor(linea, "ID Categoria:")));

                    if (linea.startsWith("------")) {
                        if (daoCategoria.existeCategoria(p.getIdCategoria())) {
                            daoPrenda.insertarPrenda(p);
                        } else {
                            System.out.println("Categoria " + p.getIdCategoria()
                                + " no existe. Prenda '" + p.getNombre() + "' omitida.");
                        }
                        p = null;
                    }
                }
            }
            System.out.println("Prendas importadas");
        } catch (Exception e) {
            System.out.println("Error importar prendas: " + e.getMessage());
        }
    }

    // Ventas
    public void importarVentas() {
        try (BufferedReader br = new BufferedReader(new FileReader("ventas.txt"))) {
            String linea;
            Venta v = null;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.startsWith("ID:")) {
                    v = new Venta();
                }

                if (v != null) {
                    if (linea.startsWith("Fecha:"))       v.setFecha(valor(linea, "Fecha:"));
                    if (linea.startsWith("Total:"))       v.setTotal(Double.parseDouble(valor(linea, "Total:")));
                    if (linea.startsWith("Metodo:"))      v.setMetodoPago(valor(linea, "Metodo:"));
                    if (linea.startsWith("ID Cliente:"))  v.setIdCliente(Integer.parseInt(valor(linea, "ID Cliente:")));
                    if (linea.startsWith("ID Vendedor:")) v.setIdVendedor(Integer.parseInt(valor(linea, "ID Vendedor:")));

                    if (linea.startsWith("------")) {
                        if (!daoCliente.existeCliente(v.getIdCliente())) {
                            System.out.println("Cliente " + v.getIdCliente()
                                + " no existe. Venta omitida.");
                        } else if (!daoVendedor.existeVendedor(v.getIdVendedor())) {
                            System.out.println("Vendedor " + v.getIdVendedor()
                                + " no existe. Venta omitida.");
                        } else {
                            daoVenta.insertarVenta(v);
                        }
                        v = null;
                    }
                }
            }
            System.out.println("Ventas importadas");
        } catch (Exception e) {
            System.out.println("Error importar ventas: " + e.getMessage());
        }
    }

    // Detalle Venta
    public void importarDetalleVenta() {
        try (BufferedReader br = new BufferedReader(new FileReader("detalleventa.txt"))) {
            String linea;
            DetalleVenta d = null;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.startsWith("ID:")) {
                    d = new DetalleVenta();
                }

                if (d != null) {
                    if (linea.startsWith("Cantidad:"))        d.setCantidad(Integer.parseInt(valor(linea, "Cantidad:")));
                    if (linea.startsWith("Precio Unitario:")) d.setPrecioUnitario(Double.parseDouble(valor(linea, "Precio Unitario:")));
                    if (linea.startsWith("ID Venta:"))        d.setIdVenta(Integer.parseInt(valor(linea, "ID Venta:")));
                    if (linea.startsWith("ID Prenda:"))       d.setIdPrenda(Integer.parseInt(valor(linea, "ID Prenda:")));

                    if (linea.startsWith("------")) {
                        daoDetalleVenta.insertarDetalleVenta(d);
                        d = null;
                    }
                }
            }
            System.out.println("Detalle venta importado");
        } catch (Exception e) {
            System.out.println("Error importar detalle venta: " + e.getMessage());
        }
    }

    // Utilidad para extraer valor después de etiqueta
    private String valor(String linea, String etiqueta) {
        return linea.substring(etiqueta.length()).trim();
    }
}