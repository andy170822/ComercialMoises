-- Schema for H2 tests
CREATE TABLE IF NOT EXISTS Rol (
    IdRol INT AUTO_INCREMENT PRIMARY KEY,
    NombreRol VARCHAR(50) NOT NULL,
    Descripcion VARCHAR(255),
    Estado BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS Usuario (
    IdUsuario INT AUTO_INCREMENT PRIMARY KEY,
    IdRol INT NOT NULL,
    Nombres VARCHAR(100) NOT NULL,
    Apellidos VARCHAR(100) NOT NULL,
    DNI VARCHAR(8),
    Correo VARCHAR(100),
    Username VARCHAR(50) NOT NULL UNIQUE,
    PasswordHash VARCHAR(255) NOT NULL DEFAULT 'password',
    Estado BOOLEAN DEFAULT TRUE,
    FechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (IdRol) REFERENCES Rol(IdRol)
);

CREATE TABLE IF NOT EXISTS Categoria (
    idCategoria INT AUTO_INCREMENT PRIMARY KEY,
    nombreCategoria VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    estado BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS Producto (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,
    IdCategoria INT NOT NULL,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    nombreProducto VARCHAR(150) NOT NULL,
    descripcion VARCHAR(250),
    precioCompra DECIMAL(10,2) NOT NULL,
    precioVenta DECIMAL(10,2) NOT NULL,
    stockActual INT DEFAULT 0,
    stockMinimo INT DEFAULT 5,
    unidadMedida VARCHAR(20),
    estado BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (IdCategoria) REFERENCES Categoria(idCategoria)
);

CREATE TABLE IF NOT EXISTS refresh_token (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(255) NOT NULL UNIQUE,
    usuario_id INT NOT NULL,
    expiryDate TIMESTAMP NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(IdUsuario)
);

CREATE TABLE IF NOT EXISTS Auditoria (
    idAuditoria INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(300),
    fechaOperacion TIMESTAMP,
    tablaAfectada VARCHAR(100),
    tipoOperacion VARCHAR(20),
    usuarioResponsable VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS auditoria_logs (
    idLog BIGINT AUTO_INCREMENT PRIMARY KEY,
    accion VARCHAR(255),
    detalle VARCHAR(255),
    fecha TIMESTAMP,
    ip VARCHAR(255),
    modulo VARCHAR(255),
    usuario VARCHAR(255)
);
