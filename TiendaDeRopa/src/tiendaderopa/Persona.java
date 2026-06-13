/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendaderopa;

/**
 *
 * @author lidia24
 */
public class Persona {
    
    protected int id_persona;
    protected String nombre;
    protected String email;

    public Persona() {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.email = email;
    }
    
    public Persona(int id, String nombre, String email) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.email = email;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_persona() {
        return id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
    }

    
}



