package tiendaderopa;

import java.util.Scanner;

public class TiendaDeRopa {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Consultar dao = new Consultar();

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
            System.out.println("8. Importar TXT");
            System.out.println("9. Salir");
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

                        dao.insertarCategoria(new Categoria(0, nombre, desc));
                    }

                    if (cat == 2) {
                        for (Categoria c : dao.consultarCategorias()) {
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

                        dao.actualizarCategoria(new Categoria(id, nombre, desc));
                    }

                    if (cat == 4) {
                        System.out.print("ID: ");
                        dao.eliminarCategoria(sc.nextInt());
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

                        dao.insertarPrenda(new Prenda(0, n, t, c, p, s, idCat));
                    }

                    if (op == 2) {
                        for (Prenda p : dao.consultarPrendas()) {
                            System.out.println(p.getNombre() + " - $" + p.getPrecio());
                        }
                    }

                    if (op == 3) {
                        System.out.print("ID: ");
                        int id = sc.nextInt();

                        System.out.print("Stock: ");
                        int stock = sc.nextInt();

                        dao.actualizarPrendaStock(id, stock);
                    }

                    if (op == 4) {
                        System.out.print("ID: ");
                        dao.eliminarPrenda(sc.nextInt());
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

                        dao.insertarCliente(new Cliente(0, n, e, t, d));
                    }

                    if (op == 2) {
                        for (Cliente c : dao.consultarClientes()) {
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

                        dao.actualizarCliente(new Cliente(id, n, e, t, d));
                    }

                    if (op == 4) {
                        System.out.print("ID: ");
                        dao.eliminarCliente(sc.nextInt());
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

                        dao.insertarVendedor(new Vendedor(0, n, e, p));
                    }

                    if (op == 2) {
                        for (Vendedor v : dao.consultarVendedores()) {
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

                        dao.actualizarVendedor(new Vendedor(id, n, e, p));
                    }

                    if (op == 4) {
                        System.out.print("ID: ");
                        dao.eliminarVendedor(sc.nextInt());
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

                        dao.insertarVenta(new Venta(0, f, t, m, c, v));
                    }

                    if (op == 2) {
                        for (Venta v : dao.consultarVentas()) {
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

                        dao.actualizarVenta(new Venta(id, f, t, m, c, v));
                    }

                    if (op == 4) {
                        System.out.print("ID: ");
                        dao.eliminarVenta(sc.nextInt());
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

                        dao.insertarDetalleVenta(new DetalleVenta(0, c, p, v, pr));
                    }

                    if (op == 2) {
                        for (DetalleVenta d : dao.consultarDetalleVenta()) {
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

                        dao.actualizarDetalleVenta(new DetalleVenta(id, c, p, v, pr));
                    }

                    if (op == 4) {
                        System.out.print("ID: ");
                        dao.eliminarDetalleVenta(sc.nextInt());
                    }

                    break;
                }

                case 7:
                    System.out.println("Exportando...");
                    break;

                case 8:
                    System.out.println("Importando...");
                    break;

                case 9:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 9);

        sc.close();
    }
}