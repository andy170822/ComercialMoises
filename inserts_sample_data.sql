-- INSERT de Roles
INSERT INTO Rol (NombreRol, Descripcion, Estado)
VALUES ('ADMIN', 'Administrador del sistema', 1);
INSERT INTO Rol (NombreRol, Descripcion, Estado)
VALUES ('VENDEDOR', 'Personal de ventas', 1);
INSERT INTO Rol (NombreRol, Descripcion, Estado)
VALUES ('ALMACENERO', 'Gestión de inventario', 1);
-- INSERT de Usuarios (Password: 123456 -> BCrypt hashed)
-- Nota: En un entorno real se usaría BCrypt. Aquí pongo hashes de ejemplo consistentes con el PasswordEncoder
INSERT INTO Usuario (
        IdRol,
        Nombres,
        Apellidos,
        DNI,
        Correo,
        Username,
        PasswordHash,
        Estado,
        FechaRegistro
    )
VALUES (
        1,
        'Admin',
        'Sistemas',
        '00000001',
        'admin@moises.com',
        'admin',
        '$2a$10$8.UnVuG9UMJOm6BrEpJqdO6X9hYp.Cmxp.16U5A8d.b/71A9qU6Ry',
        1,
        GETDATE()
    );
INSERT INTO Usuario (
        IdRol,
        Nombres,
        Apellidos,
        DNI,
        Correo,
        Username,
        PasswordHash,
        Estado,
        FechaRegistro
    )
VALUES (
        2,
        'Juan',
        'Perez',
        '00000002',
        'juan@moises.com',
        'vendedor1',
        '$2a$10$8.UnVuG9UMJOm6BrEpJqdO6X9hYp.Cmxp.16U5A8d.b/71A9qU6Ry',
        1,
        GETDATE()
    );
INSERT INTO Usuario (
        IdRol,
        Nombres,
        Apellidos,
        DNI,
        Correo,
        Username,
        PasswordHash,
        Estado,
        FechaRegistro
    )
VALUES (
        3,
        'Maria',
        'Gomez',
        '00000003',
        'maria@moises.com',
        'almacen1',
        '$2a$10$8.UnVuG9UMJOm6BrEpJqdO6X9hYp.Cmxp.16U5A8d.b/71A9qU6Ry',
        1,
        GETDATE()
    );
-- INSERT de Categorías
INSERT INTO Categoria (NombreCategoria, Descripcion, Estado)
VALUES (
        'Herramientas',
        'Herramientas manuales y eléctricas',
        1
    );
INSERT INTO Categoria (NombreCategoria, Descripcion, Estado)
VALUES ('Construcción', 'Materiales de construcción', 1);
INSERT INTO Categoria (NombreCategoria, Descripcion, Estado)
VALUES ('Pinturas', 'Pinturas y acabados', 1);
-- INSERT de Productos
INSERT INTO Producto (
        IdCategoria,
        Codigo,
        NombreProducto,
        Descripcion,
        PrecioCompra,
        PrecioVenta,
        StockActual,
        StockMinimo,
        UnidadMedida,
        Estado
    )
VALUES (
        1,
        'H001',
        'Martillo',
        'Martillo de acero 20oz',
        15.00,
        25.00,
        50,
        5,
        'UNIDAD',
        1
    );
INSERT INTO Producto (
        IdCategoria,
        Codigo,
        NombreProducto,
        Descripcion,
        PrecioCompra,
        PrecioVenta,
        StockActual,
        StockMinimo,
        UnidadMedida,
        Estado
    )
VALUES (
        2,
        'C001',
        'Cemento Sol',
        'Bolsa de cemento 42.5kg',
        22.00,
        28.00,
        100,
        20,
        'BOLSA',
        1
    );
-- INSERT de Proveedores
INSERT INTO Proveedor (
        RazonSocial,
        RUC,
        Telefono,
        Email,
        Direccion,
        Estado
    )
VALUES (
        'Aceros Arequipa',
        '20100000001',
        '01-444-5555',
        'ventas@aceros.com',
        'Av. Industrial 123',
        1
    );
-- INSERT de Clientes
INSERT INTO Cliente (
        TipoDocumento,
        NumeroDocumento,
        Nombres,
        Apellidos,
        RazonSocial,
        Telefono,
        Email,
        Direccion,
        Estado
    )
VALUES (
        'DNI',
        '44556677',
        'Carlos',
        'Lopez',
        NULL,
        '999888777',
        'carlos@mail.com',
        'Calle Los Pinos 456',
        1
    );
INSERT INTO Cliente (
        TipoDocumento,
        NumeroDocumento,
        Nombres,
        Apellidos,
        RazonSocial,
        Telefono,
        Email,
        Direccion,
        Estado
    )
VALUES (
        'RUC',
        '20556677889',
        NULL,
        NULL,
        'Constructora ABC',
        '01-222-3333',
        'contacto@abc.com',
        'Av. Progreso 789',
        1
    );