package vista;

import dao.ProductosDAO;
import modelo.ProductoOtaku;

public class SetupDatos {

    public static void main(String[] args) {
        // Crear instancia del DAO para interactuar con la base de datos de objetos
        ProductosDAO productosDAO = new ProductosDAO();

        // Crear primer objeto con sus atributos
        ProductoOtaku p1 = new ProductoOtaku();
        p1.setNombre("Figura de Anya Forger");
        p1.setCategoria("Figura");
        p1.setPrecio(59.95);
        p1.setStock(8);

        // Crear segundo objeto con sus atributos
        ProductoOtaku p2 = new ProductoOtaku();
        p2.setNombre("Manga Chainsaw Man Vol.1");
        p2.setCategoria("Manga");
        p2.setPrecio(9.99);
        p2.setStock(20);

        // Crear tercer objeto con sus atributos
        ProductoOtaku p3 = new ProductoOtaku();
        p3.setNombre("Póster Studio Ghibli Colección");
        p3.setCategoria("Póster");
        p3.setPrecio(15.50);
        p3.setStock(15);

        // Agregar los objetos creados a la base de datos mediante el DAO
        productosDAO.agregarProducto(p1);
        productosDAO.agregarProducto(p2);
        productosDAO.agregarProducto(p3);
    }
}
