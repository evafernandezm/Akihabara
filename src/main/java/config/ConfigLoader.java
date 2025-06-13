package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    // Nombre del archivo de configuración
    private static final String CONFIG_FILE = "config.properties";

    // Método para obtener el valor de una propiedad según su clave
    public static String getProperty(String key) {

        Properties props = new Properties();

        // Intentamos abrir y cargar el archivo de configuración
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {

            props.load(fis);  // Cargar propiedades del archivo

            return props.getProperty(key);  // Devolver valor de la clave

        } catch (IOException e) {

            // Mostrar error si no se puede leer el archivo
            System.err.println("Error al cargar config.properties: " + e.getMessage());

            return null;
        }
    }
}