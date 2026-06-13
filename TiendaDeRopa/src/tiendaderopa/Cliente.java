package tiendaderopa;

public class Cliente extends Persona {

    private int idCliente;
    private String telefono;
    private String direccion;

    public Cliente() {
        super();
        this.idCliente = 0;
        this.telefono = "";
        this.direccion = "";
    }

    public Cliente(int idCliente, String nombre, String email, String telefono, String direccion) {

        super(idCliente, nombre, email);

        this.idCliente = idCliente;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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
        System.out.println("ID: " + idCliente);
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
        System.out.println("Telefono: " + telefono);
        System.out.println("Direccion: " + direccion);
    }
}