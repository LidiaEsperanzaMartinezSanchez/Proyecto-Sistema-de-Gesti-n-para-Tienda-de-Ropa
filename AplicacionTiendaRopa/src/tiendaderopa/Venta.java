package tiendaderopa;

public class Venta {
    private int idVenta;
    private String fecha;       // Viene como String desde el ResultSet
    private double total;
    private String metodoPago;
    private int idCliente;
    private int idVendedor;

    public Venta() {
        this.idVenta = 0;
        this.fecha = "";
        this.total = 0.0;
        this.metodoPago = "";
        this.idCliente = 0;
        this.idVendedor = 0;
    }

    public Venta(int idVenta, String fecha, double total, String metodoPago, int idCliente, int idVendedor) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.total = total;
        this.metodoPago = metodoPago;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
    }

    public int getIdVenta() { 
        return idVenta; 
    }
    public void setIdVenta(int idVenta) { 
        this.idVenta = idVenta; 
    }

    public String getFecha() { 
        return fecha; 
    }
    public void setFecha(String fecha) { 
        this.fecha = fecha; 
    }

    public double getTotal() { 
        return total; 
    }
    public void setTotal(double total) { 
        this.total = total; 
    }

    public String getMetodoPago() { 
        return metodoPago; 
    }
    public void setMetodoPago(String metodoPago) { 
        this.metodoPago = metodoPago; 
    }

    public int getIdCliente() { 
        return idCliente; 
    }
    public void setIdCliente(int idCliente) { 
        this.idCliente = idCliente; 
    }

    public int getIdVendedor() { 
        return idVendedor; 
    }
    public void setIdVendedor(int idVendedor) { 
        this.idVendedor = idVendedor; 
    }
}
