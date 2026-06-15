package tiendaderopa;

import java.util.Scanner;

public class TiendaDeRopa {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        ConsultasCategoria daoCategoria = new ConsultasCategoria();
        ConsultasCliente daoCliente = new ConsultasCliente();
        ConsultasPrenda daoPrenda = new ConsultasPrenda();
        ConsultasVenta daoVenta = new ConsultasVenta();
        ConsultasVendedor daoVendedor = new ConsultasVendedor();
        ConsultasDetalleVenta daoDetalle = new ConsultasDetalleVenta();

        int opcion;

        do {

            System.out.println("\n==============================");
            System.out.println("      TIENDA DE ROPA");
            System.out.println("==============================");
            System.out.println("1. Categorias");
            System.out.println("2. Prendas");
            System.out.println("3. Clientes");
            System.out.println("4. Vendedores");
            System.out.println("5. Ventas");
            System.out.println("6. Detalle Venta");
            System.out.println("7. Exportar TXT");
            System.out.println("8. Salir"); 
            System.out.print("Opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                // ================= CATEGORIAS =================
                case 1: {
                    System.out.println("1. Registrar");
                    System.out.println("2. Consultar");
                    System.out.println("3. Actualizar");
                    System.out.println("4. Eliminar");

                    int cat = sc.nextInt();
                    sc.nextLine();

                    if (cat == 1) {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Descripcion: ");
                        String desc = sc.nextLine();

                        daoCategoria.insertarCategoria(new Categoria(0, nombre, desc));
                    }

                    if (cat == 2) {
                        for (Categoria c : daoCategoria.consultarCategorias()) {
                            System.out.println(c.getIdCategoria() + " - " + c.getNombre());
                        }
                    }

                    if (cat == 3) {
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Descripcion: ");
                        String desc = sc.nextLine();

                        
                        daoCategoria.actualizarCategoria(new Categoria(id, nombre, desc));
                    }

                    if (cat == 4) {
                        System.out.print("ID: ");
                        daoCategoria.eliminarCategoria(sc.nextInt());
                    }

                    break;
                }

                // ================= PRENDAS =================
                case 2: {
                    System.out.println("1. Registrar");
                    System.out.println("2. Consultar");
                    System.out.println("3. Actualizar stock");
                    System.out.println("4. Eliminar");

                    int op = sc.nextInt();
                    sc.nextLine();

                    if (op == 1) {
                        System.out.print("Nombre: ");
                        String n = sc.nextLine();

                        System.out.print("Talla: ");
                        String t = sc.nextLine();

                        System.out.print("Color: ");
                        String c = sc.nextLine();

                        System.out.print("Precio: ");
                        double p = sc.nextDouble();

                        System.out.print("Stock: ");
                        int s = sc.nextInt();

                        System.out.print("ID Categoria: ");
                        int idCat = sc.nextInt();

                        daoPrenda.insertarPrenda(new Prenda(0, n, t, c, p, s, idCat));
                    }

                    if (op == 2) {
                        for (Prenda p : daoPrenda.consultarPrendas()) {
                            System.out.println(p.getNombre() + " - $" + p.getPrecio());
                        }
                    }

                    if (op == 3) {
                        System.out.print("ID: ");
                        int id = sc.nextInt();

                        System.out.print("Stock: ");
                        int stock = sc.nextInt();

                        
                        daoPrenda.actualizarPrendaStock(id, stock);
                    }

                    if (op == 4) {
                        System.out.print("ID: ");
                        
                        daoPrenda.eliminarPrenda(sc.nextInt());
                    }

                    break;
                }

                // ================= CLIENTES =================
                case 3: {
                    System.out.println("1. Registrar");
                    System.out.println("2. Consultar");
                    System.out.println("3. Actualizar");
                    System.out.println("4. Eliminar");

                    int op = sc.nextInt();
                    sc.nextLine();

                    if (op == 1) {
                        System.out.print("Nombre: ");
                        String n = sc.nextLine();

                        System.out.print("Email: ");
                        String e = sc.nextLine();

                        System.out.print("Telefono: ");
                        String t = sc.nextLine();

                        System.out.print("Direccion: ");
                        String d = sc.nextLine();

                        
                        daoCliente.insertarCliente(new Cliente(0, n, e, t, d));
                    }

                    if (op == 2) {
                        
                        for (Cliente c : daoCliente.consultarClientes()) {
                            System.out.println(c.getNombre());
                        }
                    }

                    if (op == 3) {
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nombre: ");
                        String n = sc.nextLine();

                        System.out.print("Email: ");
                        String e = sc.nextLine();

                        System.out.print("Tel: ");
                        String t = sc.nextLine();

                        System.out.print("Dir: ");
                        String d = sc.nextLine();

                        daoCliente.actualizarCliente(new Cliente(id, n, e, t, d));
                    }

                    if (op == 4) {
                        System.out.print("ID: ");
                        
                        daoCliente.eliminarCliente(sc.nextInt());
                    }

                    break;
                }

                // ================= VENDEDORES =================
                case 4: {
                    System.out.println("1. Registrar");
                    System.out.println("2. Consultar");
                    System.out.println("3. Actualizar");
                    System.out.println("4. Eliminar");

                    int op = sc.nextInt();
                    sc.nextLine();

                    if (op == 1) {
                        System.out.print("Nombre: ");
                        String n = sc.nextLine();

                        System.out.print("Email: ");
                        String e = sc.nextLine();

                        System.out.print("Puesto: ");
                        String p = sc.nextLine();

                        daoVendedor.insertarVendedor(new Vendedor(0, n, e, p));
                    }

                    if (op == 2) {
                        for (Vendedor v : daoVendedor.consultarVendedores()) {
                            System.out.println(v.getNombre());
                        }
                    }

                    if (op == 3) {
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nombre: ");
                        String n = sc.nextLine();

                        System.out.print("Email: ");
                        String e = sc.nextLine();

                        System.out.print("Puesto: ");
                        String p = sc.nextLine();

                        daoVendedor.actualizarVendedor(new Vendedor(id, n, e, p));
                    }

                    if (op == 4) {
                        System.out.print("ID: ");
                        daoVendedor.eliminarVendedor(sc.nextInt());
                    }

                    break;
                }

                // ================= VENTAS =================
                case 5: {
                    System.out.println("1. Registrar");
                    System.out.println("2. Consultar");
                    System.out.println("3. Actualizar");
                    System.out.println("4. Eliminar");

                    int op = sc.nextInt();
                    sc.nextLine();

                    if (op == 1) {
                        System.out.print("Fecha: ");
                        String f = sc.nextLine();

                        System.out.print("Total: ");
                        double t = sc.nextDouble();

                        System.out.print("Metodo: ");
                        sc.nextLine();
                        String m = sc.nextLine();

                        System.out.print("Cliente: ");
                        int c = sc.nextInt();

                        System.out.print("Vendedor: ");
                        int v = sc.nextInt();

                        daoVenta.insertarVenta(new Venta(0, f, t, m, c, v));
                    }

                    if (op == 2) {
                        for (Venta v : daoVenta.consultarVentas()) {
                            System.out.println(v.getIdVenta() + " - $" + v.getTotal());
                        }
                    }

                    if (op == 3) {
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Fecha: ");
                        String f = sc.nextLine();

                        System.out.print("Total: ");
                        double t = sc.nextDouble();

                        System.out.print("Metodo: ");
                        sc.nextLine();
                        String m = sc.nextLine();

                        System.out.print("Cliente: ");
                        int c = sc.nextInt();

                        System.out.print("Vendedor: ");
                        int v = sc.nextInt();

                        daoVenta.actualizarVenta(new Venta(id, f, t, m, c, v));
                    }

                    if (op == 4) {
                        System.out.print("ID: ");
                        daoVenta.eliminarVenta(sc.nextInt());
                    }

                    break;
                }

                // ================= DETALLE VENTA =================
                case 6: {
                    System.out.println("1. Registrar");
                    System.out.println("2. Consultar");
                    System.out.println("3. Actualizar");
                    System.out.println("4. Eliminar");

                    int op = sc.nextInt();

                    if (op == 1) {
                        System.out.print("Cantidad: ");
                        int c = sc.nextInt();

                        System.out.print("Precio Unitario: ");
                        double p = sc.nextDouble();

                        System.out.print("ID Venta: ");
                        int v = sc.nextInt();

                        System.out.print("ID Prenda: ");
                        int pr = sc.nextInt();

                        
                        daoDetalle.insertarDetalleVenta(new DetalleVenta(0, c, p, v, pr));
                    }

                    if (op == 2) {
                        for (DetalleVenta d : daoDetalle.consultarDetalleVenta()) {
                            System.out.println(d.getIdDetalle() + " - Cant: " + d.getCantidad());
                        }
                    }

                    if (op == 3) {
                        System.out.print("ID: ");
                        int id = sc.nextInt();

                        System.out.print("Cantidad: ");
                        int c = sc.nextInt();

                        System.out.print("Precio: ");
                        double p = sc.nextDouble();

                        System.out.print("Venta: ");
                        int v = sc.nextInt();

                        System.out.print("Prenda: ");
                        int pr = sc.nextInt();

                        daoDetalle.actualizarDetalleVenta(new DetalleVenta(id, c, p, v, pr));
                    }

                    if (op == 4) {
                        System.out.print("ID: ");
                        daoDetalle.eliminarDetalleVenta(sc.nextInt());
                    }

                    break;
                }

                case 7: {
                    
                    ImportarTXT.exportarClientes(daoCliente.consultarClientes());
                    ImportarTXT.exportarPrendas(daoPrenda.consultarPrendas());
                    ImportarTXT.exportarVentas(daoVenta.consultarVentas());
                    ImportarTXT.exportarDetalleVenta(daoDetalle.consultarDetalleVenta());

                    System.out.println("Exportacion completada.");
                    break;
                }

                case 8:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 8);

        sc.close();
    }
}
