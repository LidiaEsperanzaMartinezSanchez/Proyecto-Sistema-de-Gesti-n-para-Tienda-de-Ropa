package tiendaderopa;

import java.util.Scanner;

public class TiendaDeRopa {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ConsultasCategoria    daoCategoria    = new ConsultasCategoria();
        ConsultasCliente      daoCliente      = new ConsultasCliente();
        ConsultasVendedor     daoVendedor     = new ConsultasVendedor();
        ConsultasPrenda       daoPrenda       = new ConsultasPrenda();
        ConsultasVenta        daoVenta        = new ConsultasVenta();
        ConsultasDetalleVenta daoDetalleVenta = new ConsultasDetalleVenta();
        ImportarTXT           importarTXT     = new ImportarTXT();

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

                case 1: {
                    // Mostrar categorias antes de desplegar el submenu
                    System.out.println("--- Categorias disponibles ---");
                    for (Categoria c : daoCategoria.consultarCategorias()) {
                        System.out.println(c.getIdCategoria() + " - " + c.getNombre() + " - " + c.getDescripcion());
                    }

                    System.out.println("\n1. Registrar");
                    System.out.println("2. Consultar");
                    System.out.println("3. Actualizar");
                    System.out.println("4. Eliminar");
                    System.out.println("5. Regresar");

                    int op = sc.nextInt();
                    sc.nextLine();

                    if (op == 1) {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Descripcion: ");
                        String desc = sc.nextLine();

                        daoCategoria.insertarCategoria(new Categoria(0, nombre, desc));
                        System.out.println("Categoria registrada correctamente.");
                    }

                    if (op == 2) {
                        System.out.println("(Lista mostrada arriba)");
                    }

                    if (op == 3) {
                        System.out.print("ID a actualizar: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Descripcion: ");
                        String desc = sc.nextLine();

                        daoCategoria.actualizarCategoria(new Categoria(id, nombre, desc));
                        System.out.println("Categoria actualizada correctamente.");
                    }

                    if (op == 4) {
                        System.out.print("ID a eliminar: ");
                        daoCategoria.eliminarCategoria(sc.nextInt());
                        sc.nextLine();
                        System.out.println("Categoria eliminada correctamente.");
                    }

                    if (op == 5) {
                        System.out.println("Regresando...");
                    }

                    break;
                }

                case 2: {
                    // Mostrar prendas antes de desplegar el submenu
                    System.out.println("--- Prendas disponibles ---");
                    for (Prenda p : daoPrenda.consultarPrendas()) {
                        System.out.println(p.getIdPrenda() + " - " + p.getNombre()
                                + " | Talla: " + p.getTalla()
                                + " | Color: " + p.getColor()
                                + " | $" + p.getPrecio()
                                + " | Stock: " + p.getStock());
                    }

                    System.out.println("\n1. Registrar");
                    System.out.println("2. Consultar");
                    System.out.println("3. Actualizar stock");
                    System.out.println("4. Eliminar");
                    System.out.println("5. Regresar");

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
                        sc.nextLine();

                        System.out.println("--- Categorias disponibles ---");
                        for (Categoria cat : daoCategoria.consultarCategorias()) {
                            System.out.println(cat.getIdCategoria() + " - " + cat.getNombre());
                        }
                        System.out.print("ID Categoria: ");
                        int idCat = sc.nextInt();
                        sc.nextLine();

                        // Validar que la categoria exista antes de registrar la prenda
                        if (daoCategoria.existeCategoria(idCat)) {
                            daoPrenda.insertarPrenda(new Prenda(0, n, t, c, p, s, idCat));
                            System.out.println("Prenda registrada correctamente.");
                        } else {
                            System.out.println("Categoria no existe. Prenda no registrada.");
                        }
                    }

                    if (op == 2) {
                        System.out.println("(Lista mostrada arriba)");
                    }

                    if (op == 3) {
                        System.out.print("ID a actualizar stock: ");
                        int id = sc.nextInt();

                        System.out.print("Nuevo Stock: ");
                        int stock = sc.nextInt();
                        sc.nextLine();

                        daoPrenda.actualizarPrendaStock(id, stock);
                        System.out.println("Stock actualizado correctamente.");
                    }

                    if (op == 4) {
                        System.out.print("ID a eliminar: ");
                        daoPrenda.eliminarPrenda(sc.nextInt());
                        sc.nextLine();
                        System.out.println("Prenda eliminada correctamente.");
                    }

                    if (op == 5) {
                        System.out.println("Regresando...");
                    }

                    break;
                }

                case 3: {
                    // Mostrar clientes antes de desplegar el submenu
                    System.out.println("--- Clientes disponibles ---");
                    for (Cliente c : daoCliente.consultarClientes()) {
                        System.out.println(c.getIdCliente() + " - " + c.getNombre()
                                + " | " + c.getEmail()
                                + " | Tel: " + c.getTelefono());
                    }

                    System.out.println("\n1. Registrar");
                    System.out.println("2. Consultar");
                    System.out.println("3. Actualizar");
                    System.out.println("4. Eliminar");
                    System.out.println("5. Regresar");

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
                        System.out.println("Cliente registrado correctamente.");
                    }

                    if (op == 2) {
                        System.out.println("(Lista mostrada arriba)");
                    }

                    if (op == 3) {
                        System.out.print("ID a actualizar: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nombre: ");
                        String n = sc.nextLine();

                        System.out.print("Email: ");
                        String e = sc.nextLine();

                        System.out.print("Telefono: ");
                        String t = sc.nextLine();

                        System.out.print("Direccion: ");
                        String d = sc.nextLine();

                        daoCliente.actualizarCliente(new Cliente(id, n, e, t, d));
                        System.out.println("Cliente actualizado correctamente.");
                    }

                    if (op == 4) {
                        System.out.print("ID a eliminar: ");
                        daoCliente.eliminarCliente(sc.nextInt());
                        sc.nextLine();
                        System.out.println("Cliente eliminado correctamente.");
                    }

                    if (op == 5) {
                        System.out.println("Regresando...");
                    }

                    break;
                }

                case 4: {
                    // Mostrar vendedores antes de desplegar el submenu
                    System.out.println("--- Vendedores disponibles ---");
                    for (Vendedor v : daoVendedor.consultarVendedores()) {
                        System.out.println(v.getIdVendedor() + " - " + v.getNombre()
                                + " | " + v.getEmail()
                                + " | Puesto: " + v.getPuesto());
                    }

                    System.out.println("\n1. Registrar");
                    System.out.println("2. Consultar");
                    System.out.println("3. Actualizar");
                    System.out.println("4. Eliminar");
                    System.out.println("5. Regresar");

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
                        System.out.println("Vendedor registrado correctamente.");
                    }

                    if (op == 2) {
                        System.out.println("(Lista mostrada arriba)");
                    }

                    if (op == 3) {
                        System.out.print("ID a actualizar: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nombre: ");
                        String n = sc.nextLine();

                        System.out.print("Email: ");
                        String e = sc.nextLine();

                        System.out.print("Puesto: ");
                        String p = sc.nextLine();

                        daoVendedor.actualizarVendedor(new Vendedor(id, n, e, p));
                        System.out.println("Vendedor actualizado correctamente.");
                    }

                    if (op == 4) {
                        System.out.print("ID a eliminar: ");
                        daoVendedor.eliminarVendedor(sc.nextInt());
                        sc.nextLine();
                        System.out.println("Vendedor eliminado correctamente.");
                    }

                    if (op == 5) {
                        System.out.println("Regresando...");
                    }

                    break;
                }

                case 5: {
                    // Mostrar ventas antes de desplegar el submenu
                    System.out.println("--- Ventas disponibles ---");
                    for (Venta v : daoVenta.consultarVentas()) {
                        System.out.println(v.getIdVenta() + " - Fecha: " + v.getFecha()
                                + " | Total: $" + v.getTotal()
                                + " | Metodo: " + v.getMetodoPago()
                                + " | Cliente ID: " + v.getIdCliente()
                                + " | Vendedor ID: " + v.getIdVendedor());
                    }

                    System.out.println("\n1. Registrar");
                    System.out.println("2. Consultar");
                    System.out.println("3. Actualizar");
                    System.out.println("4. Eliminar");
                    System.out.println("5. Regresar");

                    int op = sc.nextInt();
                    sc.nextLine();

                    if (op == 1) {
                        System.out.print("Fecha (YYYY-MM-DD): ");
                        String f = sc.nextLine();

                        System.out.print("Total: ");
                        double t = sc.nextDouble();
                        sc.nextLine();

                        System.out.print("Metodo de pago: ");
                        String m = sc.nextLine();

                        System.out.println("--- Clientes disponibles ---");
                        for (Cliente cli : daoCliente.consultarClientes()) {
                            System.out.println(cli.getIdCliente() + " - " + cli.getNombre());
                        }
                        System.out.print("ID Cliente: ");
                        int c = sc.nextInt();
                        sc.nextLine();

                        System.out.println("--- Vendedores disponibles ---");
                        for (Vendedor ven : daoVendedor.consultarVendedores()) {
                            System.out.println(ven.getIdVendedor() + " - " + ven.getNombre());
                        }
                        System.out.print("ID Vendedor: ");
                        int v = sc.nextInt();
                        sc.nextLine();

                        // Validar que el cliente y vendedor existan antes de registrar la venta
                        if (!daoCliente.existeCliente(c)) {
                            System.out.println("Cliente no existe. Venta no registrada.");
                        } else if (!daoVendedor.existeVendedor(v)) {
                            System.out.println("Vendedor no existe. Venta no registrada.");
                        } else {
                            daoVenta.insertarVenta(new Venta(0, f, t, m, c, v));
                            System.out.println("Venta registrada correctamente.");
                        }
                    }

                    if (op == 2) {
                        System.out.println("(Lista mostrada arriba)");
                    }

                    if (op == 3) {
                        System.out.print("ID venta a actualizar: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Fecha (YYYY-MM-DD): ");
                        String f = sc.nextLine();

                        System.out.print("Total: ");
                        double t = sc.nextDouble();
                        sc.nextLine();

                        System.out.print("Metodo de pago: ");
                        String m = sc.nextLine();

                        System.out.println("--- Clientes disponibles ---");
                        for (Cliente cli : daoCliente.consultarClientes()) {
                            System.out.println(cli.getIdCliente() + " - " + cli.getNombre());
                        }
                        System.out.print("ID Cliente: ");
                        int c = sc.nextInt();
                        sc.nextLine();

                        System.out.println("--- Vendedores disponibles ---");
                        for (Vendedor ven : daoVendedor.consultarVendedores()) {
                            System.out.println(ven.getIdVendedor() + " - " + ven.getNombre());
                        }
                        System.out.print("ID Vendedor: ");
                        int v = sc.nextInt();
                        sc.nextLine();

                        daoVenta.actualizarVenta(new Venta(id, f, t, m, c, v));
                        System.out.println("Venta actualizada correctamente.");
                    }

                    if (op == 4) {
                        System.out.print("ID venta a eliminar: ");
                        daoVenta.eliminarVenta(sc.nextInt());
                        sc.nextLine();
                        System.out.println("Venta eliminada correctamente.");
                    }

                    if (op == 5) {
                        System.out.println("Regresando...");
                    }

                    break;
                }

                case 6: {
                    // Mostrar detalles de venta antes de desplegar el submenu
                    System.out.println("--- Detalles de venta disponibles ---");
                    for (DetalleVenta d : daoDetalleVenta.consultarDetalleVenta()) {
                        System.out.println(d.getIdDetalle() + " - Cant: " + d.getCantidad()
                                + " | Precio unit: $" + d.getPrecioUnitario()
                                + " | Venta ID: " + d.getIdVenta()
                                + " | Prenda ID: " + d.getIdPrenda());
                    }

                    System.out.println("\n1. Registrar");
                    System.out.println("2. Consultar");
                    System.out.println("3. Actualizar");
                    System.out.println("4. Eliminar");
                    System.out.println("5. Regresar");

                    int op = sc.nextInt();
                    sc.nextLine();

                    if (op == 1) {
                        System.out.print("Cantidad: ");
                        int c = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Precio Unitario: ");
                        double p = sc.nextDouble();
                        sc.nextLine();

                        System.out.println("--- Ventas disponibles ---");
                        for (Venta ven : daoVenta.consultarVentas()) {
                            System.out.println(ven.getIdVenta() + " - " + ven.getFecha() + " - $" + ven.getTotal());
                        }
                        System.out.print("ID Venta: ");
                        int v = sc.nextInt();
                        sc.nextLine();

                        System.out.println("--- Prendas disponibles ---");
                        for (Prenda pre : daoPrenda.consultarPrendas()) {
                            System.out.println(pre.getIdPrenda() + " - " + pre.getNombre() + " - $" + pre.getPrecio());
                        }
                        System.out.print("ID Prenda: ");
                        int pr = sc.nextInt();
                        sc.nextLine();

                        daoDetalleVenta.insertarDetalleVenta(new DetalleVenta(0, c, p, v, pr));
                        System.out.println("Detalle registrado correctamente.");
                    }

                    if (op == 2) {
                        System.out.println("(Lista mostrada arriba)");
                    }

                    if (op == 3) {
                        System.out.print("ID detalle a actualizar: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Cantidad: ");
                        int c = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Precio Unitario: ");
                        double p = sc.nextDouble();
                        sc.nextLine();

                        System.out.println("--- Ventas disponibles ---");
                        for (Venta ven : daoVenta.consultarVentas()) {
                            System.out.println(ven.getIdVenta() + " - " + ven.getFecha() + " - $" + ven.getTotal());
                        }
                        System.out.print("ID Venta: ");
                        int v = sc.nextInt();
                        sc.nextLine();

                        System.out.println("--- Prendas disponibles ---");
                        for (Prenda pre : daoPrenda.consultarPrendas()) {
                            System.out.println(pre.getIdPrenda() + " - " + pre.getNombre() + " - $" + pre.getPrecio());
                        }
                        System.out.print("ID Prenda: ");
                        int pr = sc.nextInt();
                        sc.nextLine();

                        daoDetalleVenta.actualizarDetalleVenta(new DetalleVenta(id, c, p, v, pr));
                        System.out.println("Detalle actualizado correctamente.");
                    }

                    if (op == 4) {
                        System.out.print("ID detalle a eliminar: ");
                        daoDetalleVenta.eliminarDetalleVenta(sc.nextInt());
                        sc.nextLine();
                        System.out.println("Detalle eliminado correctamente.");
                    }

                    if (op == 5) {
                        System.out.println("Regresando...");
                    }

                    break;
                }

                case 7: {
                    System.out.println("Exportando...");
                    ImportarTXT.exportarClientes(daoCliente.consultarClientes());
                    ImportarTXT.exportarPrendas(daoPrenda.consultarPrendas());
                    ImportarTXT.exportarVentas(daoVenta.consultarVentas());
                    ImportarTXT.exportarDetalleVenta(daoDetalleVenta.consultarDetalleVenta());
                    System.out.println("Exportacion completa.");
                    break;
                }

                case 8: {
                    System.out.println("Importando...");
                    importarTXT.importarTodo();
                    break;
                }

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