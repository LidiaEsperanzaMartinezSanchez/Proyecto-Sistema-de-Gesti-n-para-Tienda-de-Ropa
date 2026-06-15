USE TiendaRopa;

INSERT INTO Categoria (nombre, descripcion) VALUES
('Camisas',       'Camisas formales e informales para hombre y mujer'),
('Pantalones',    'Pantalones de vestir, casual y deportivo'),
('Vestidos',      'Vestidos de noche, casual y de temporada'),
('Faldas',        'Faldas largas, cortas y midi'),
('Chamarras',     'Chamarras de cuero, tela y deportivas'),
('Sudaderas',     'Sudaderas con y sin capucha'),
('Shorts',        'Shorts deportivos y casuales'),
('Ropa Interior', 'Ropa interior para hombre y mujer'),
('Calcetines',    'Calcetines deportivos, formales y de temporada'),
('Accesorios',    'Cinturones, gorras, bufandas y complementos');


INSERT INTO Prenda (nombre, talla, color, precio, stock, idCategoria) VALUES
('Camisa Oxford',         'M',   'Blanco',   349.00,  23, 1),
('Camisa Lino',           'L',   'Beige',    299.00,  15, 1),
('Camisa Cuadros',        'S',   'Azul',     279.00,   8, 1),
('Pantalon Chino',        '32',  'Gris',     499.00,  12, 2),
('Pantalon Mezclilla',    '30',  'Azul',     599.00,  30, 2),
('Pantalon Dress',        '34',  'Negro',    649.00,   7, 2),
('Vestido Floral',        'M',   'Rosa',     799.00,  10, 3),
('Vestido Noche',         'S',   'Negro',   1299.00,   5, 3),
('Falda Mini',            'XS',  'Rojo',     349.00,  18, 4),
('Falda Midi Plisada',    'M',   'Verde',    429.00,  11, 4),
('Chamarra Cuero',        'L',   'Cafe',    1599.00,   6, 5),
('Chamarra Bomber',       'M',   'Negro',    899.00,  14, 5),
('Sudadera Capucha',      'XL',  'Gris',     499.00,  25, 6),
('Sudadera Crop',         'S',   'Lila',     449.00,  20, 6),
('Short Deportivo',       'M',   'Negro',    249.00,  35, 7),
('Short Mezclilla',       'L',   'Azul',     329.00,  17, 7),
('Boxer Pack x3',         'M',   'Varios',   199.00,  40, 8),
('Calcetines Deportivos', 'U',   'Blanco',    89.00,  60, 9),
('Calcetines Termicos',   'U',   'Gris',     129.00,  45, 9),
('Cinto Piel',            'U',   'Cafe',     349.00,  22, 10);


INSERT INTO Cliente (nombre, email, telefono, direccion) VALUES
('Maria Lopez',    'maria.lopez@gmail.com',    '5512345678', 'Av. Insurgentes 123, CDMX'),
('Carlos Ramirez', 'carlos.ramirez@gmail.com', '5587654321', 'Calle Reforma 45, CDMX'),
('Ana Torres',     'ana.torres@gmail.com',     '5598765432', 'Blvd. Juarez 78, Guadalajara'),
('Luis Hernandez', 'luis.hdz@gmail.com',       '5511223344', 'Col. Narvarte 12, CDMX'),
('Sofia Mendoza',  'sofia.mza@gmail.com',      '5544332211', 'Av. Patria 56, Monterrey');


INSERT INTO Vendedor (nombre, email, puesto) VALUES
('Roberto Vega',    'roberto.vega@tienda.com',    'Gerente de Ventas'),
('Patricia Solis',  'patricia.solis@tienda.com',  'Vendedor Senior'),
('Diego Morales',   'diego.morales@tienda.com',   'Vendedor Junior');


INSERT INTO Venta (fecha, total, metodoPago, idCliente, idVendedor) VALUES
('2025-01-10 10:30:00',  648.00,  'efectivo',      1, 1),
('2025-01-11 12:00:00', 1299.00,  'tarjeta',        2, 2),
('2025-01-12 15:45:00',  778.00,  'transferencia',  3, 1),
('2025-01-13 09:15:00', 1898.00,  'tarjeta',        4, 3),
('2025-01-14 17:00:00',  438.00,  'efectivo',       5, 2);


INSERT INTO DetalleVenta (cantidad, precioUnitario, idVenta, idPrenda) VALUES
(1, 349.00, 1,  1),   -- Venta 1: Camisa Oxford
(1, 299.00, 1,  2),   -- Venta 1: Camisa Lino
(1,1299.00, 2,  8),   -- Venta 2: Vestido Noche
(1, 499.00, 3,  4),   -- Venta 3: Pantalon Chino
(1, 279.00, 3,  3),   -- Venta 3: Camisa Cuadros
(1,1599.00, 4,  11),  -- Venta 4: Chamarra Cuero
(1, 299.00, 4,  2),   -- Venta 4: Camisa Lino
(2,  89.00, 4,  18),  -- Venta 4: Calcetines x2
(1, 349.00, 5,  9),   -- Venta 5: Falda Mini
(1,  89.00, 5,  18);  -- Venta 5: Calcetines