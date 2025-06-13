package vista;

import java.util.List;

import controlador.LlmService;
import dao.ProductosDAO;
import modelo.ProductoOtaku;

public class Interfaz {
	// Muestra el menú de gestión de productos
    public void mostrarMenu() {
        System.out.println("\n--- Menú de gestión de productos ---");
        System.out.println("1. Listar productos");
        System.out.println("2. Agregar producto");
        System.out.println("3. Actualizar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Consultar producto por ID");
        System.out.println("6. Consultar producto por Nombre");
        System.out.println("7. Generar descripcion del producto");
        System.out.println("8. Sugerir una categoria para el producto");
        System.out.println("9. Salir");
    }

    // Listar todos los productos almacenados
    public void obtenerTodosLosProductos(ProductosDAO productoDAO) {
        System.out.println("Lista de productos:");
        List<ProductoOtaku> productos = productoDAO.obtenerTodosLosProductos();

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (ProductoOtaku p : productos) {
                System.out.printf("ID: %d - Nombre: %s - Categoría: %s - Precio: %.2f - Stock: %d%n",
                        p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio(), p.getStock());
            }
        }
    }

    // Solicitar datos y agregar un producto nuevo
    public void agregarProducto(ProductosDAO productoDAO) {
        System.out.println("Agregar nuevo producto");
        String nombre = Utilidades.pedirString("Nombre: ");
        String categoria = Utilidades.pedirText("Categoría: ");
        double precio = Utilidades.pedirDouble("Precio: ");
        int stock = Utilidades.pedirEntero("Stock: ");

        ProductoOtaku nuevoProducto = new ProductoOtaku(nombre, categoria, precio, stock);
        productoDAO.agregarProducto(nuevoProducto);
    }

    // Actualizar producto por campos uno a uno con menú interno
    public void actualizarProducto(ProductosDAO productoDAO) {
        System.out.println("Actualizar producto:");
        int idActualizar = Utilidades.pedirEntero("ID del producto a actualizar: ");

        ProductoOtaku productoActual = productoDAO.obtenerProductoPorId(idActualizar);
        if (productoActual == null) {
            System.out.println("Error --> No se encontró un producto con ese ID.");
            return;
        }

        boolean seguirActualizando = true;
        while (seguirActualizando) {
            System.out.println("\nProducto actual:");
            System.out.printf("1. Nombre: %s\n2. Categoría: %s\n3. Precio: %.2f\n4. Stock: %d\n5. Guardar y salir\n",
                    productoActual.getNombre(), productoActual.getCategoria(), productoActual.getPrecio(),
                    productoActual.getStock());

            int opcion = Utilidades.pedirEntero("Selecciona el campo a modificar (1-5): ");

            switch (opcion) {
                case 1:
                    productoActual.setNombre(Utilidades.pedirString("Nuevo nombre: "));
                    break;
                case 2:
                    productoActual.setCategoria(Utilidades.pedirString("Nueva categoría: "));
                    break;
                case 3:
                    productoActual.setPrecio(Utilidades.pedirDouble("Nuevo precio: "));
                    break;
                case 4:
                    productoActual.setStock(Utilidades.pedirEntero("Nuevo stock: "));
                    break;
                case 5:
                    productoDAO.actualizarProducto(productoActual);
                    seguirActualizando = false;
                    break;
                default:
                    System.out.println("Error --> Opción no válida, intenta otra vez.");
            }
        }
    }

    // Eliminar producto por ID
    public void eliminarProducto(ProductosDAO productoDAO) {
        System.out.println("Eliminar por ID:");
        int idEliminar = Utilidades.pedirEntero("ID del producto a eliminar: ");
        productoDAO.eliminarProducto(idEliminar);
    }

    // Consultar producto por ID y mostrar info
    public ProductoOtaku obtenerProductoPorId(ProductosDAO productoDAO) {
        System.out.println("Consultar por ID:");
        int idConsultar = Utilidades.pedirEntero("ID del producto a consultar: ");
        ProductoOtaku producto = productoDAO.obtenerProductoPorId(idConsultar);
        if (producto != null) {
            System.out.println("Producto encontrado:");
            System.out.println(producto);
        } else {
            System.out.println("Producto no encontrado.");
        }
        return producto;
    }

    // Consultar producto por nombre y mostrar info
    public ProductoOtaku buscarProductosPorNombre(ProductosDAO productoDAO) {
        System.out.println("Consultar por Nombre:");
        String nombreConsultar = Utilidades.pedirString("Nombre del producto a consultar: ");
        ProductoOtaku producto = productoDAO.buscarProductosPorNombre(nombreConsultar);
        if (producto != null) {
            System.out.println("Producto encontrado:");
            System.out.println(producto);
        } else {
            System.out.println("Producto no encontrado.");
        }
        return producto;
    }

    // Usar IA para generar descripción atractiva del producto
    public void descripcionProducto(ProductosDAO productoDAO) {
        System.out.println("\n--- Generar Descripción con IA ---");
        int id = Utilidades.pedirEntero("ID del producto: ");
        ProductoOtaku producto = productoDAO.obtenerProductoPorId(id);

        if (producto != null) {
            String prompt = "Para productos otaku, crea una descripción atractiva de máximo 10 palabras para el producto: \""
                    + producto.getNombre() + "\" (categoría: " + producto.getCategoria()
                    + "). Destaca sus características más llamativas y sé creativo.";

            System.out.println("\nGenerando descripción...");
            String descripcion = LlmService.obtenerRespuesta(prompt);

            System.out.println("\nDESCRIPCIÓN: ");
            System.out.println(descripcion);
        } else {
            System.out.println("Error: No existe producto con ID " + id);
        }
    }

    // Usar IA para sugerir categoría de producto
    public void sugerirCategoria() {
        System.out.println("\n--- Sugerir Categoría con IA ---");
        String nombre = Utilidades.pedirString("Nombre del producto: ");

        String prompt = "Para productos otaku, sugiere una sola categoría para el producto: \"" + nombre
                + "\". Solo usa estas categorías válidas: Figura, Manga, Póster, Llavero, Ropa, Videojuego, Otro. "
                + "La respuesta tiene que ser con el nombre de la categoría.";

        System.out.println("\nGenerando sugerencia...");
        String categoria = LlmService.obtenerRespuesta(prompt);

        System.out.println("\nCATEGORÍA: " + categoria);
    }
}
