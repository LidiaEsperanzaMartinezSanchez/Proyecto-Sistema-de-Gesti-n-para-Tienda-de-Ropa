package tiendaderopa;

public class Vendedor {
    private int idVendedor;
    private String nombre;
    private String email;
    private String puesto;

    public Vendedor() {
        this.idVendedor = 0;
        this.nombre = "";
        this.email = "";
        this.puesto = "";
    }

    public Vendedor(int idVendedor, String nombre, String email, String puesto) {
        this.idVendedor = idVendedor;
        this.nombre = nombre;
        this.email = email;
        this.puesto = puesto;
    }

    public int getIdVendedor() { 
        return idVendedor; 
    }
    public void setIdVendedor(int idVendedor) { 
        this.idVendedor = idVendedor; 
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

    public String getPuesto() { 
        return puesto; 
    }
    public void setPuesto(String puesto) { 
        this.puesto = puesto; 
    }
}