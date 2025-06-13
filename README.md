# Guía de Usuario

Este proyecto es una aplicación de gestión de productos y clientes, desarrollada en Java y basada en una base de datos MySQL. A través de una interfaz de consola, permite realizar operaciones CRUD (crear, leer, actualizar y eliminar) y emplea una API externa para generar contenido con inteligencia artificial.

---

## Configuración de la Base de Datos MySQL

1. Instala y configura MySQL en tu sistema.
2. Ejecuta el siguiente script en tu gestor de base de datos para crear la estructura y datos iniciales:

```sql
CREATE DATABASE IF NOT EXISTS akihabara;
USE akihabara;

CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(100),
    precio DECIMAL(10, 2),
    stock INT
);

INSERT INTO productos (nombre, categoria, precio, stock) VALUES
('Figura Goku', 'Figuras', 25.99, 10),
('Camiseta Naruto', 'Ropa', 15.50, 20),
('Manga One Piece Vol.1', 'Manga', 9.99, 50);

CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    fecha_registro DATE DEFAULT (CURRENT_DATE)
);

INSERT INTO clientes (nombre, email, telefono, fecha_registro) VALUES 
('Laura García', 'laura03@example.com', '617852451', '2025-06-06'),
('Carlos Martín', 'carlos36@example.com', '625874125', '2024-12-25'),
('María Gómez', 'maria.gomez@example.com', '625984752', '2023-01-15');

CREATE USER IF NOT EXISTS 'akihabara_usuario'@'localhost' IDENTIFIED BY 'campusfp';
GRANT ALL PRIVILEGES ON akihabara.* TO 'akihabara_usuario'@'localhost';
FLUSH PRIVILEGES;

```

## Archivo de configuración (.properties)

Crea un archivo llamado `config.properties` en la carpeta `documentacion`


# Configuración de la base de datos MySQL
```properties
db.url= "jdbc:mysql://localhost:puerto/nombredelabasedatos"
db.user= "nombredeusuario"
db.password= "contraseña"
```

# Configuración de la API de OpenRouter
```properties
api.key= "APIKEY"
api.url= "URLAPI"
```
#Librerias y JAR Externos
```pom
	<dependencies>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.33</version>
		</dependency>
		<dependency>
			<groupId>org.json</groupId>
			<artifactId>json</artifactId>
			<version>20220924</version>
		</dependency>
		<dependency>
			<groupId>ProyectoDAO</groupId>
			<artifactId>Akihabara</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
		<dependency>
			<groupId>com.theokanning.openai-gpt3-java</groupId>
			<artifactId>service</artifactId>
			<version>0.16.0</version>
		</dependency>
		<!--https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-params -->
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-params</artifactId>
			<version>5.13.0</version>
			<scope>test</scope>
		</dependency>
	</dependencies>
```

#Compilar y ejecutar la aplicación
1. Configurar la base de datos -> Copia y ejecuta el script SQL en tu gestor de base de datos.
2. Crear el archivo `config.properties` -> Asegúrate de que el archivo contenga la configuración correcta de conexión a la base de datos.
3. Ejecutar la aplicación -> Ejectuar el archivo .bat (Windows) para iniciar la aplicación

# Resumen de Clases

Proyecto  
├── src  
│   ├── main  
│   │   ├── java  
│   │   │   ├── config  
│   │   │   │   └── ConfigLoader.java → Clase que carga propiedades desde el archivo config.properties y permite obtener valores por clave.  
│   │   │   ├── controlador  
│   │   │   │   ├── LImService.java → Clase que se conecta a la API con la IA.  
│   │   │   │   └── Main.java → Clase que sirve como un menú interactivo para gestionar productos y clientes  
│   │   │   ├── dao  
│   │   │   │   ├── ClientesDAO.java → Clase que gestiona operaciones CRUD en la base de datos, incluyendo varias funciones.  
│   │   │   │   ├── DatabaseConnection.java → Clase que mantiene una conexión a la base de datos MySQL utilizando configuración externa.  
│   │   │   │   └── ProductosDAO.java → Clase que gestiona operaciones CRUD en la base de datos, incluyendo varias funciones.  
│   │   │   ├── modelo  
│   │   │   │   ├── ClientesOtaku.java → Clase modelo que representa un cliente con atributos, constructores, getter y setter.  
│   │   │   │   └── ProductoOtaku.java → Clase modelo que representa un producto con atributos, constructores, getter y setter.  
│   │   │   └── vista  
│   │   │       ├── Interfaz.java → Clase que maneja la interacción con el usuario para gestionar productos, y varias funciones.  
│   │   │       ├── InterfazClientes.java → Clase que maneja la interacción con el usuario para gestionar clientes, y varias funciones.  
│   │   │       ├── SetupDatos.java → Clase que inicializa y agrega datos de productos de ejemplo en la base de datos o almacenamiento.  
│   │   │       └── Utilidades.java → Clase con métodos para pedir y validar entrada de usuario desde consola.  
│   ├── test  
│   │   ├── java  
│   │   │   └── test  
│   │   │       └── pruebaProductoDAO.java → Archivo de pruebas unitarias para verificar las operaciones CRUD.  
├── config.properties → Archivo de configuración que almacena las credenciales y URLs para la conexión a la base de datos y para acceder a la API  
├── pom.xml → Archivo que configura las dependencias y metadatos del proyecto Java para gestionar librerías, versiones y compilación.  
├── README.md  




## Funcionalidades

### Gestión de Productos
- **Listar todos los productos registrados:**  
  Muestra un listado completo con todos los productos almacenados en la base de datos.

- **Agregar nuevos productos a la base de datos:**  
  Permite ingresar y guardar un nuevo producto con sus detalles (nombre, categoría, precio, stock).

- **Actualizar datos de un producto existente:**  
  Modifica la información de un producto previamente registrado según el ID proporcionado.

- **Eliminar productos por ID:**  
  Borra un producto de la base de datos usando su identificador único.

- **Consultar productos por ID:**  
  Busca y muestra la información de un producto específico a partir de su ID.

- **Consultar productos por nombre:**  
  Permite buscar productos cuyo nombre coincida o contenga un texto dado.

- **Generar descripciones creativas para productos mediante IA:**  
  Usa inteligencia artificial para crear descripciones atractivas y originales para un producto.

- **Sugerir categorías para productos mediante IA:**  
  Utiliza IA para recomendar la categoría más adecuada para un producto según sus características.

### Gestión de Clientes
- **Listar todos los clientes registrados:**  
  Muestra un listado completo con todos los clientes almacenados en la base de datos.

- **Agregar nuevos clientes a la base de datos:**  
  Permite ingresar y guardar un nuevo cliente con sus detalles personales.

- **Actualizar datos de clientes existentes:**  
  Modifica la información de un cliente registrado según su ID.

- **Eliminar clientes por ID:**  
  Borra un cliente de la base de datos usando su identificador único.

- **Consultar clientes por ID:**  
  Busca y muestra la información de un cliente específico a partir de su ID.

- **Consultar clientes por email:**  
  Permite buscar un cliente utilizando su dirección de correo electrónico como criterio.