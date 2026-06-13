package tiendaderopa;

public class Vendedor extends Persona {

    private int idVendedor;
    private String puesto;

    public Vendedor() {
        super();
        this.idVendedor = 0;
        this.puesto = "";
    }

    public Vendedor(int idVendedor, String nombre,
                     String email, String puesto) {

        super(idVendedor, nombre, email);

        this.idVendedor = idVendedor;
        this.puesto = puesto;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
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
        System.out.println("ID: " + idVendedor);
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
        System.out.println("Puesto: " + puesto);
    }
}
