package controlador;

import dao.ClientesDAO;
import dao.ProductosDAO;
import vista.Interfaz;
import vista.InterfazClientes;
import vista.Utilidades;

public class Main {
    public static void main(String[] args) {
        // Crear interfaz y DAO para productos
        Interfaz interfazProductos = new Interfaz();
        ProductosDAO productoDAO = new ProductosDAO();

        // Crear interfaz y DAO para clientes
        InterfazClientes interfazClientes = new InterfazClientes();
        ClientesDAO clienteDAO = new ClientesDAO();

        int opcionPrincipal;

        do {
            // Mostrar menú principal
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Gestión de Productos");
            System.out.println("2. Gestión de Clientes");
            System.out.println("3. Salir");

            opcionPrincipal = Utilidades.pedirEntero("Elige una opción: ");

            switch (opcionPrincipal) {
                case 1:
                    gestionarProductos(interfazProductos, productoDAO);
                    break;
                case 2:
                    gestionarClientes(interfazClientes, clienteDAO);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcionPrincipal != 3);
    }
    // Mostar menú de productos
    private static void gestionarProductos(Interfaz interfaz, ProductosDAO productoDAO) {
        int opcionProducto;
        do {
            interfaz.mostrarMenu();
            opcionProducto = Utilidades.pedirEntero("Elige una opción: ");

            switch (opcionProducto) {
                case 1:
                    interfaz.obtenerTodosLosProductos(productoDAO);
                    break;
                case 2:
                    interfaz.agregarProducto(productoDAO);
                    break;
                case 3:
                    interfaz.actualizarProducto(productoDAO);
                    break;
                case 4:
                    interfaz.eliminarProducto(productoDAO);
                    break;
                case 5:
                    interfaz.obtenerProductoPorId(productoDAO);
                    break;
                case 6:
                    interfaz.buscarProductosPorNombre(productoDAO);
                    break;
                case 7:
                    interfaz.descripcionProducto(productoDAO);
                    break;
                case 8:
                    interfaz.sugerirCategoria();
                    break;
                case 9:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcionProducto != 9);
    }
    // Mostar menú de clientes
    private static void gestionarClientes(InterfazClientes interfazClientes, ClientesDAO clienteDAO) {
        int opcionCliente;
        do {
            interfazClientes.mostrarMenu_C();
            opcionCliente = Utilidades.pedirEntero("Elige una opción: ");

            switch (opcionCliente) {
                case 1:
                    interfazClientes.obtenerTodosLosClientes(clienteDAO);
                    break;
                case 2:
                    interfazClientes.agregarCliente(clienteDAO);
                    break;
                case 3:
                    interfazClientes.actualizarCliente(clienteDAO);
                    break;
                case 4:
                    interfazClientes.eliminarCliente(clienteDAO);
                    break;
                case 5:
                    interfazClientes.obtenerClientePorId(clienteDAO);
                    break;
                case 6:
                    interfazClientes.buscarPorEmail(clienteDAO);
                    break;
                case 7:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcionCliente != 7);
    }
}
