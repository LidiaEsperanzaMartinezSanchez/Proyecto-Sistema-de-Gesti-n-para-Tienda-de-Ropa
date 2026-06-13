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
            System.out.println("1. Clientes");
            System.out.println("2. Prendas");
            System.out.println("3. Categorias");
            System.out.println("4. Vendedores");
            System.out.println("5. Ventas");
            System.out.println("6. Exportar TXT");
            System.out.println("7. Importar TXT");
            System.out.println("8. Salir");
            System.out.print("Opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                // ================= CLIENTES =================
                case 1:

                    System.out.println("\n--- CLIENTES ---");
                    System.out.println("1. Registrar");
                    System.out.println("2. Listar");
                    System.out.println("3. Actualizar");
                    System.out.println("4. Eliminar");

                    int c1 = sc.nextInt();
                    sc.nextLine();

                    if (c1 == 1) {

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        System.out.print("Telefono: ");
                        String tel = sc.nextLine();

                        System.out.print("Direccion: ");
                        String dir = sc.nextLine();

                        Cliente c = new Cliente(0, nombre, email, tel, dir);
                        dao.insertarCliente(c);

                    } else if (c1 == 2) {

                        for (Cliente cl : dao.listarClientes()) {
                            cl.mostrarDatos();
                            System.out.println("-------------------");
                        }

                    } else if (c1 == 3) {

                        System.out.print("ID Cliente: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nuevo nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Nuevo email: ");
                        String email = sc.nextLine();

                        System.out.print("Nuevo telefono: ");
                        String tel = sc.nextLine();

                        System.out.print("Nueva direccion: ");
                        String dir = sc.nextLine();

                        Cliente c = new Cliente(id, nombre, email, tel, dir);
                        dao.actualizarCliente(c);

                    } else if (c1 == 4) {

                        System.out.print("ID a eliminar: ");
                        int id = sc.nextInt();
                        dao.eliminarCliente(id);
                    }

                    break;

                // ================= PRENDAS =================
                case 2:

                    System.out.println("\n--- PRENDAS ---");
                    System.out.println("1. Registrar");
                    System.out.println("2. Listar");
                    System.out.println("3. Actualizar stock");
                    System.out.println("4. Eliminar");

                    int p1 = sc.nextInt();
                    sc.nextLine();

                    if (p1 == 1) {

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Talla: ");
                        String talla = sc.nextLine();

                        System.out.print("Color: ");
                        String color = sc.nextLine();

                        System.out.print("Precio: ");
                        double precio = sc.nextDouble();

                        System.out.print("Stock: ");
                        int stock = sc.nextInt();

                        System.out.print("ID Categoria: ");
                        int idCat = sc.nextInt();
                        sc.nextLine();

                        if (precio > 0 && stock >= 0) {

                            if (dao.existeCategoria(idCat)) {

                                Prenda p = new Prenda(0, nombre, talla, color, precio, stock, idCat);
                                dao.insertarPrenda(p);

                                System.out.println("✔ Prenda registrada correctamente");

                            } else {
                                System.out.println("❌ Categoria no existe");
                            }

                        } else {
                            System.out.println("❌ Datos invalidos");
                        }

                    } else if (p1 == 2) {

                        for (Prenda pr : dao.listarPrendas()) {
                            System.out.println(pr.getNombre() + " - $" + pr.getPrecio());
                        }

                    } else if (p1 == 3) {

                        System.out.print("ID prenda: ");
                        int id = sc.nextInt();

                        System.out.print("Nuevo stock: ");
                        int stock = sc.nextInt();

                        dao.actualizarPrendaStock(id, stock);

                    } else if (p1 == 4) {

                        System.out.print("ID a eliminar: ");
                        int id = sc.nextInt();

                        dao.eliminarPrenda(id);
                    }

                    break;

                // ================= CATEGORIAS =================
                case 3:

                    System.out.println("\n--- CATEGORIAS ---");
                    System.out.println("1. Registrar");
                    System.out.println("2. Listar");

                    int cat1 = sc.nextInt();
                    sc.nextLine();

                    if (cat1 == 1) {

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Descripcion: ");
                        String desc = sc.nextLine();

                        Categoria c = new Categoria(0, nombre, desc);
                        dao.insertarCategoria(c);

                    } else if (cat1 == 2) {

                        for (Categoria ca : dao.listarCategorias()) {
                            System.out.println(ca.getNombre());
                        }
                    }

                    break;

                // ================= VENDEDORES =================
                case 4:

                    System.out.println("\n--- VENDEDORES ---");
                    System.out.println("1. Registrar");
                    System.out.println("2. Listar");

                    int v1 = sc.nextInt();
                    sc.nextLine();

                    if (v1 == 1) {

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        System.out.print("Puesto: ");
                        String puesto = sc.nextLine();

                        Vendedor v = new Vendedor(0, nombre, email, puesto);
                        dao.insertarVendedor(v);

                    } else if (v1 == 2) {

                        for (Vendedor ve : dao.listarVendedores()) {
                            ve.mostrarDatos();
                        }
                    }

                    break;

                // ================= VENTAS =================
                case 5:

                    for (Venta ve : dao.listarVentas()) {
                        System.out.println("Venta #" + ve.getIdVenta());
                        System.out.println("Total: $" + ve.getTotal());
                    }

                    break;

                // ================= EXPORTAR =================
                case 6:

                    ImportarTXT.exportarClientes(dao.listarClientes());
                    ImportarTXT.exportarPrendas(dao.listarPrendas());
                    ImportarTXT.exportarVentas(dao.listarVentas());

                    System.out.println("✔ Archivos exportados");
                    break;

                // ================= IMPORTAR =================
                case 7:

                    ImportarTXT imp = new ImportarTXT();
                    imp.importarTodo();

                    break;

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