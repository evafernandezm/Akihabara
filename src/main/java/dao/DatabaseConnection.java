package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import config.ConfigLoader;

public class DatabaseConnection {
    private Connection conn;

    // Constructor que crea la conexión a la base de datos
    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carga el driver de MySQL
            // Conecta usando los datos del archivo config.properties
            conn = DriverManager.getConnection(
                ConfigLoader.getProperty("db.url"),
                ConfigLoader.getProperty("db.user"),
                ConfigLoader.getProperty("db.password"));
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error --> no se puede conectar a la base de datos:");
            ex.printStackTrace();
        }
    }

    // Devuelve la conexión creada
    public Connection getConnection() {
        return conn;
    }
}