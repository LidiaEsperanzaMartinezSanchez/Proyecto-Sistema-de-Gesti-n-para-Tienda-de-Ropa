/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// ---------------------- Clase padre --------------------
package tiendaderopa;

/**
 *
 * @author lidia24
 */

public class Persona {

    protected int id;
    protected String nombre;
    protected String email;

    public Persona() {
        this.id = 0;
        this.nombre = "";
        this.email = "";
    }

    public Persona(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ------------ Polimorfismo ---------------
    
    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
    }
}