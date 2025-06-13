package vista;

import java.util.List;

import dao.ClientesDAO;
import modelo.ClientesOtaku;

public class InterfazClientes {
    // Muestra el menú de gestión de clientes
    public void mostrarMenu_C() {
        System.out.println("\n--- Menú de gestión de clientes ---");
        System.out.println("1. Listar clientes");
        System.out.println("2. Agregar cliente");
        System.out.println("3. Actualizar cliente");
        System.out.println("4. Eliminar cliente");
        System.out.println("5. Consultar cliente por ID");
        System.out.println("6. Consultar cliente por Email");
        System.out.println("7. Salir");
    }

    // Método privado para imprimir un cliente con formato legible
    private void imprimirCliente(ClientesOtaku c) {
        System.out.printf("ID: %d - Nombre: %s - Email: %s - Teléfono: %d - Fecha: %s%n",
                c.getId(), c.getNombre(), c.getEmail(), c.getTelefono(), c.getFecha());
    }

    // Obtiene y muestra la lista de todos los clientes registrados
    public void obtenerTodosLosClientes(ClientesDAO clienteDAO) {
        System.out.println("Lista de clientes:");
        List<ClientesOtaku> clientes = clienteDAO.obtenerTodosLosClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Imprime cada cliente de la lista
            for (ClientesOtaku c : clientes) {
                imprimirCliente(c);
            }
        }
    }

    // Solicita datos para crear un nuevo cliente y lo agrega usando el DAO
    public void agregarCliente(ClientesDAO clienteDAO) {
        System.out.println("Agregar nuevo cliente");
        // Solicita datos validados al usuario
        String nombre = Utilidades.pedirString("Nombre: ");
        String email = Utilidades.pedirEmail("Correo electrónico: ");
        int telefono = Utilidades.pedirTelefono("Teléfono: ");

        // Crea el objeto cliente con los datos ingresados
        ClientesOtaku nuevoCliente = new ClientesOtaku();
        nuevoCliente.setNombre(nombre);
        nuevoCliente.setEmail(email);
        nuevoCliente.setTelefono(telefono);

        // Guarda el nuevo cliente en la base de datos
        clienteDAO.agregarClientes(nuevoCliente);
    }

    // Permite actualizar datos de un cliente existente
    public void actualizarCliente(ClientesDAO clienteDAO) {
        System.out.println("Actualizar cliente:");
        int idActualizar = Utilidades.pedirEntero("ID del cliente a actualizar: ");

        // Obtiene el cliente actual desde la base de datos
        ClientesOtaku clienteActual = clienteDAO.obtenerClientePorId(idActualizar);
        if (clienteActual == null) {
            System.out.println("Error --> No se encontró un cliente con ese ID.");
            return; // Termina si no existe el cliente
        }

        // Ciclo para modificar uno o varios campos hasta guardar y salir
        boolean seguirActualizando = true;
        while (seguirActualizando) {
            System.out.println("\nCliente actual:");
            System.out.printf("1. Nombre: %s\n2. Email: %s\n3. Teléfono: %d\n4. Guardar y salir\n",
                    clienteActual.getNombre(), clienteActual.getEmail(), clienteActual.getTelefono());

            int opcion = Utilidades.pedirEntero("Selecciona el campo a modificar (1-4): ");

            switch (opcion) {
                case 1:
                    // Actualiza nombre
                    String nuevoNombre = Utilidades.pedirString("Nuevo nombre: ");
                    clienteActual.setNombre(nuevoNombre);
                    break;
                case 2:
                    // Actualiza email con validación
                    String nuevoEmail = Utilidades.pedirEmail("Nuevo correo electrónico: ");
                    clienteActual.setEmail(nuevoEmail);
                    break;
                case 3:
                    // Actualiza teléfono con validación
                    int nuevoTelefono = Utilidades.pedirTelefono("Nuevo teléfono: ");
                    clienteActual.setTelefono(nuevoTelefono);
                    break;
                case 4:
                    // Guarda los cambios y sale del ciclo
                    clienteDAO.actualizarClientes(clienteActual);
                    seguirActualizando = false;
                    break;
                default:
                    System.out.println("Error --> Opción no válida, intenta otra vez.");
            }
        }
    }

    // Elimina un cliente basado en el ID ingresado
    public void eliminarCliente(ClientesDAO clienteDAO) {
        System.out.println("Eliminar por ID:");
        int idEliminar = Utilidades.pedirEntero("ID del cliente a eliminar: ");
        clienteDAO.eliminarClientes(idEliminar);
    }

    // Consulta un cliente por su ID y lo muestra si existe
    public ClientesOtaku obtenerClientePorId(ClientesDAO clienteDAO) {
        System.out.println("Consultar por ID:");
        int idConsultar = Utilidades.pedirEntero("ID del cliente a consultar: ");
        ClientesOtaku clienteConsultado = clienteDAO.obtenerClientePorId(idConsultar);
        if (clienteConsultado != null) {
            System.out.println("Cliente encontrado:");
            imprimirCliente(clienteConsultado);
        } else {
            System.out.println("Cliente no encontrado.");
        }
        return clienteConsultado;
    }

    // Busca un cliente por su email y lo muestra si existe
    public ClientesOtaku buscarPorEmail(ClientesDAO clienteDAO) {
        System.out.println("Consultar por Email:");
        String emailConsultar = Utilidades.pedirString("Email del cliente a consultar: ");
        ClientesOtaku clienteConsultado = clienteDAO.buscarPorEmail(emailConsultar);
        if (clienteConsultado != null) {
            System.out.println("Cliente encontrado:");
            imprimirCliente(clienteConsultado);
        } else {
            System.out.println("Cliente no encontrado.");
        }
        return clienteConsultado;
    }
}


