/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tiendaderopa;
import java.util.ArrayList;

/**
 *
 * @author webon
 */
public class TiendaDeRopa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // Colección ArrayList de objetos Prenda
        ArrayList<Prenda> inventario = new ArrayList<>();

        // Agregar prendas
        inventario.add(new Prenda(1, "Playera", "M", "Azul", 250.0, 20, 1));
        inventario.add(new Prenda(2, "Pantalon", "32", "Negro", 500.0, 10, 2));
        inventario.add(new Prenda(3, "Vestido", "S", "Rojo", 700.0, 5, 3));

        // Mostrar total de prendas
        System.out.println("Cantidad de prendas: " + inventario.size());

        System.out.println("\n=== INVENTARIO ===");

        // Recorrer el ArrayList
        for (Prenda p : inventario) {

            System.out.println("ID: " + p.getIdPrenda());
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Talla: " + p.getTalla());
            System.out.println("Color: " + p.getColor());
            System.out.println("Precio: $" + p.getPrecio());
            System.out.println("Stock: " + p.getStock());
            System.out.println("Categoria: " + p.getIdCategoria());

            System.out.println("----------------------");
        }

        // Buscar una prenda por ID
        int buscar = 2;

        for (Prenda p : inventario) {

            if (p.getIdPrenda() == buscar) {

                System.out.println("\nPrenda encontrada:");
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Precio: $" + p.getPrecio());

                break;
            }
        }
    }
}