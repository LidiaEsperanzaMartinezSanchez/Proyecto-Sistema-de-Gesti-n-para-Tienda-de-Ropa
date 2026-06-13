/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tiendaderopa;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author webon
 */
public class TiendaDeRopa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Prenda> inventario = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Categoria> categorias = new ArrayList<>();
        ArrayList<Vendedor> vendedores = new ArrayList<>();

        int opcion = 0;

        while (opcion != 6) {

            System.out.println("\n===== TIENDA DE ROPA =====");
            System.out.println("1. Registrar Prenda");
            System.out.println("2. Registrar Cliente");
            System.out.println("3. Registrar Categoria");
            System.out.println("4. Registrar Vendedor");
            System.out.println("5. Mostrar Registros");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {

                System.out.println("\n=== REGISTRO DE PRENDA ===");

                System.out.print("ID Prenda: ");
                int idPrenda = sc.nextInt();
                sc.nextLine();

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
                int idCategoria = sc.nextInt();
                sc.nextLine();

                Prenda prenda = new Prenda(idPrenda, nombre, talla, color, precio, stock, idCategoria);

                inventario.add(prenda);

                System.out.println("Prenda registrada correctamente.");

                
            } else if (opcion == 2) {

                System.out.println("\n=== REGISTRO DE CLIENTE ===");

                System.out.print("ID Cliente: ");
                int idCliente = sc.nextInt();
                sc.nextLine();

                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Telefono: ");
                String telefono = sc.nextLine();

                System.out.print("Direccion: ");
                String direccion = sc.nextLine();

                Cliente cliente = new Cliente(idCliente, nombre, email, telefono, direccion);

                clientes.add(cliente);

                System.out.println("Cliente registrado correctamente.");
                

            } else if (opcion == 3) {

                System.out.println("\n=== REGISTRO DE CATEGORIA ===");

                System.out.print("ID Categoria: ");
                int idCategoria = sc.nextInt();
                sc.nextLine();

                System.out.print("Nombre Categoria: ");
                String nombreCategoria = sc.nextLine();

                System.out.print("Descripcion: ");
                String descripcion = sc.nextLine();

                Categoria categoria = new Categoria(idCategoria, nombreCategoria,descripcion);

                categorias.add(categoria);

                System.out.println("Categoria registrada correctamente.");
                

            } else if (opcion == 4) {

                System.out.println("\n=== REGISTRO DE VENDEDOR ===");

                System.out.print("ID Vendedor: ");
                int idVendedor = sc.nextInt();
                sc.nextLine();

                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Puesto: ");
                String puesto = sc.nextLine();

                Vendedor vendedor = new Vendedor(idVendedor, nombre, email, puesto);

                vendedores.add(vendedor);

                System.out.println("Vendedor registrado correctamente.");
                

            } else if (opcion == 5) {

                System.out.println("\n===== PRENDAS =====");

                for (Prenda p : inventario) {
                    System.out.println("ID: " + p.getIdPrenda());
                    System.out.println("Nombre: " + p.getNombre());
                    System.out.println("Talla: " + p.getTalla());
                    System.out.println("Color: " + p.getColor());
                    System.out.println("Precio: $" + p.getPrecio());
                    System.out.println("Stock: " + p.getStock());
                    System.out.println("ID Categoria: " + p.getIdCategoria());
                    System.out.println("----------------------");
                }

                System.out.println("\n===== CLIENTES =====");

                for (Cliente c : clientes) {
                    c.mostrarDatos();
                    System.out.println("----------------------");
                }

                System.out.println("\n===== CATEGORIAS =====");

                for (Categoria c : categorias) {
                    System.out.println("ID: " + c.getIdCategoria());
                    System.out.println("Nombre: " + c.getNombre());
                    System.out.println("Descripcion: " + c.getDescripcion());
                    System.out.println("----------------------");
                }

                System.out.println("\n===== VENDEDORES =====");

                for (Vendedor v : vendedores) {
                    v.mostrarDatos();
                    System.out.println("----------------------");
                }

            } else if (opcion == 6) {

                System.out.println("Saliendo del sistema...");

            } else {

                System.out.println("Opcion no valida.");

            }
        }

        sc.close();
    }
}