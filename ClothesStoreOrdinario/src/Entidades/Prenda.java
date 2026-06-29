/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author webon
 */
public class Prenda {
    private int idPrenda;
    private String nombre;
    private String talla;
    private String color;
    private double precio;
    private int stock;
    private int idCategoria;

    public Prenda() {
        this.idPrenda = 0;
        this.nombre = "";
        this.talla = "";
        this.color = "";
        this.precio = 0.0;
        this.stock = 0;
        this.idCategoria = 0;
    }

    public Prenda(int idPrenda, String nombre, String talla, String color, double precio, int stock, int idCategoria) {
        this.idPrenda = idPrenda;
        this.nombre = nombre;
        this.talla = talla;
        this.color = color;
        this.precio = precio;
        this.stock = stock;
        this.idCategoria = idCategoria;
    }

    public int getIdPrenda() { 
        return idPrenda; 
    }
    public void setIdPrenda(int idPrenda) { 
        this.idPrenda = idPrenda; 
    }

    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public String getTalla() { 
        return talla; 
    }
    public void setTalla(String talla) { 
        this.talla = talla; 
    }

    public String getColor() { 
        return color; 
    }
    public void setColor(String color) { 
        this.color = color; 
    }

    public double getPrecio() { 
        return precio; 
    }
    public void setPrecio(double precio) { 
        this.precio = precio; 
    }

    public int getStock() { 
        return stock; 
    }
    public void setStock(int stock) { 
        this.stock = stock; 
    }

    public int getIdCategoria() { 
        return idCategoria; 
    }
    public void setIdCategoria(int idCategoria) { 
        this.idCategoria = idCategoria; 
    }
}
