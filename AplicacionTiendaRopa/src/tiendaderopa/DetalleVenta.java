package tiendaderopa;

public class DetalleVenta {
    private int idDetalle;
    private int cantidad;
    private double precioUnitario;
    private int idVenta;
    private int idPrenda;

    public DetalleVenta() {
        this.idDetalle = 0;
        this.cantidad = 0;
        this.precioUnitario = 0.0;
        this.idVenta = 0;
        this.idPrenda = 0;
    }

    public DetalleVenta(int idDetalle, int cantidad, double precioUnitario,
                        int idVenta, int idPrenda) {
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.idVenta = idVenta;
        this.idPrenda = idPrenda;
    }

    public int getIdDetalle() { 
        return idDetalle; 
    }
    public void setIdDetalle(int idDetalle) { 
        this.idDetalle = idDetalle; 
    }

    public int getCantidad() { 
        return cantidad; 
    }
    public void setCantidad(int cantidad) { 
        this.cantidad = cantidad; 
    }

    public double getPrecioUnitario() { 
        return precioUnitario; 
    }
    public void setPrecioUnitario(double precioUnitario) { 
        this.precioUnitario = precioUnitario; 
    }

    public int getIdVenta() { 
        return idVenta; 
    }
    public void setIdVenta(int idVenta) { 
        this.idVenta = idVenta; 
    }

    public int getIdPrenda() { 
        return idPrenda; 
    }
    public void setIdPrenda(int idPrenda) { 
        this.idPrenda = idPrenda; 
    }
}
