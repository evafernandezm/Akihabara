-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS akihabara;

-- Usar la base de datos creada
USE akihabara;

-- Crear tabla productos si no existe
CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(100),
    precio DECIMAL(10, 2),
    stock INT
);

-- Insertar datos en la tabla productos
INSERT INTO productos (nombre, categoria, precio, stock) VALUES
('Figura Goku', 'Figuras', 25.99, 10),
('Camiseta Naruto', 'Ropa', 15.50, 20),
('Manga One Piece Vol.1', 'Manga', 9.99, 50);

-- Crear usuario para la base de datos si no existe
CREATE USER IF NOT EXISTS 'akihabara_usuario'@'localhost' IDENTIFIED BY 'campusfp';

-- Dar todos los permisos al usuario sobre la base de datos akihabara
GRANT ALL PRIVILEGES ON akihabara.* TO 'akihabara_usuario'@'localhost';

-- Aplicar los cambios de permisos
FLUSH PRIVILEGES;

-- Crear tabla clientes si no existe
CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    fecha_registro DATE DEFAULT (CURRENT_DATE)
);

-- Insertar datos en la tabla clientes
INSERT INTO clientes (nombre, email, telefono, fecha_registro) VALUES 
('Laura García', 'laura03@example.com', '617852451', '2025-06-06'),
('Carlos Martín', 'carlos36@example.com', '625874125', '2024-12-25'),
('María Gómez', 'maria.gomez@example.com', '625984752', '2023-01-15');