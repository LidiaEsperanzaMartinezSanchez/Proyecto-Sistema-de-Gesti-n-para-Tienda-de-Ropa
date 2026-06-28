/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author webon
 */
public class Vendedor extends Persona {

    private String puesto;

    public Vendedor() {
        super();
        this.puesto = "";
    }

    public Vendedor(int idVendedor, String nombre, String email, String puesto) {
        super(idVendedor, nombre, email);
        this.puesto = puesto;
    }

    // Alias que apunta al id de Persona — sin duplicar
    public int getIdVendedor() {
        return this.id;
    }

    public void setIdVendedor(int idVendedor) {
        this.id = idVendedor;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("===== VENDEDOR =====");
        System.out.println("ID: "     + this.id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: "  + email);
        System.out.println("Puesto: " + puesto);
    }
}
