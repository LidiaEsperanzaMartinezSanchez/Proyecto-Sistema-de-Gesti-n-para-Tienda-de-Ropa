CREATE DATABASE TiendaRopa;
USE TiendaRopa;

-- Categorias de ropa --
CREATE TABLE Categoria (
    idCategoria INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(50) NOT NULL,
    descripcion VARCHAR(200)
) ENGINE = InnoDB;

-- Prendas del inventario -- 
CREATE TABLE Prenda (
    idPrenda    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(80) NOT NULL,
    talla       VARCHAR(10) NOT NULL,
    color       VARCHAR(30) NOT NULL,
    precio      DECIMAL(10,2) NOT NULL,
    stock       INT NOT NULL DEFAULT 0,
    idCategoria INT NOT NULL,
    FOREIGN KEY (idCategoria) REFERENCES Categoria(idCategoria)
) ENGINE = InnoDB;

-- Clientes
CREATE TABLE Cliente (
    idCliente  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(80) NOT NULL,
    email      VARCHAR(100) UNIQUE,
    telefono   VARCHAR(20),
    direccion  VARCHAR(200)
) ENGINE = InnoDB;

-- Vendedores --
CREATE TABLE Vendedor (
    idVendedor INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(80) NOT NULL,
    email      VARCHAR(100) NOT NULL UNIQUE,
    puesto     VARCHAR(50)         
) ENGINE = InnoDB;

-- Cabecera de venta --
CREATE TABLE Venta (
    idVenta     INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fecha       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total       DECIMAL(10,2) NOT NULL DEFAULT 0,
    metodoPago  ENUM('efectivo','tarjeta','transferencia') NOT NULL,
    idCliente   INT,
    idVendedor  INT NOT NULL,
    FOREIGN KEY (idCliente)  REFERENCES Cliente(idCliente),
    FOREIGN KEY (idVendedor) REFERENCES Vendedor(idVendedor)
) ENGINE = InnoDB;

-- Detalle de cada venta (cuantas y que prendas) -- 
CREATE TABLE DetalleVenta (
    idDetalle      INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cantidad       INT NOT NULL,
    precioUnitario DECIMAL(10,2) NOT NULL,
    idVenta        INT NOT NULL,
    idPrenda       INT NOT NULL,
    FOREIGN KEY (idVenta)   REFERENCES Venta(idVenta),
    FOREIGN KEY (idPrenda)  REFERENCES Prenda(idPrenda)
) ENGINE = InnoDB;