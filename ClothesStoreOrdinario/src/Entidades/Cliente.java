/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author webon
 */
public class Cliente extends Persona {

    private String telefono;
    private String direccion;

    public Cliente() {
        super();
        this.telefono = "";
        this.direccion = "";
    }

    public Cliente(int idCliente, String nombre, String email, String telefono, String direccion) {
        super(idCliente, nombre, email);
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Alias que apunta al id de Persona — sin duplicar
    public int getIdCliente() {
        return this.id;
    }

    public void setIdCliente(int idCliente) {
        this.id = idCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("===== CLIENTE =====");
        System.out.println("ID: "        + this.id);
        System.out.println("Nombre: "    + nombre);
        System.out.println("Email: "     + email);
        System.out.println("Telefono: "  + telefono);
        System.out.println("Direccion: " + direccion);
    }
}
