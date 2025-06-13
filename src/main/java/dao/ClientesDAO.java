package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.ClientesOtaku;

public class ClientesDAO {

	 //Inserta un nuevo cliente en la base de datos, asignandole ID y guardando la fecha de registro en la base de datos y en el objeto. 
	public void agregarClientes(ClientesOtaku cliente) {
	    String sql = "INSERT INTO clientes (nombre, email, telefono, fecha_registro) VALUES (?, ?, ?, ?)";
	    DatabaseConnection db = new DatabaseConnection();

	    try (
	        Connection conn = db.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
	    ) {
	        stmt.setString(1, cliente.getNombre());
	        stmt.setString(2, cliente.getEmail());
	        stmt.setInt(3, cliente.getTelefono());
	        
	        java.sql.Date fechaActual = java.sql.Date.valueOf(java.time.LocalDate.now());
	        stmt.setDate(4, fechaActual);
	        
	        cliente.setFecha(fechaActual.toLocalDate());

	        stmt.executeUpdate();

	        ResultSet rs = stmt.getGeneratedKeys();
	        if (rs.next()) {
	            cliente.setId(rs.getInt(1)); 
	        }

	    } catch (SQLException e) {
	        System.err.println("Error --> al insertar el cliente:");
	        e.printStackTrace();
	    }
	}

    // Obtiene un cliente por su ID.
    public ClientesOtaku obtenerClientePorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        DatabaseConnection db = new DatabaseConnection();

        try (
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ClientesOtaku cliente = new ClientesOtaku();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getInt("telefono"));

                // Convertir fecha SQL a LocalDate
                java.sql.Date fechaSQL = rs.getDate("fecha_registro");
                if (fechaSQL != null) {
                    cliente.setFecha(fechaSQL.toLocalDate());
                }

                return cliente;
            }

        } catch (SQLException e) {
            System.err.println("Error --> al consultar el cliente:");
            e.printStackTrace();
        }

        return null;
    }


	// Devuelve una lista con todos los clientes de la base de datos.
    public List<ClientesOtaku> obtenerTodosLosClientes() {
        String sql = "SELECT * FROM clientes";
        DatabaseConnection db = new DatabaseConnection();
        List<ClientesOtaku> listaClientes = new ArrayList<>();

        try (
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                ClientesOtaku cliente = new ClientesOtaku();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getInt("telefono"));

                java.sql.Date fechaSQL = rs.getDate("fecha_registro");
                if (fechaSQL != null) {
                    cliente.setFecha(fechaSQL.toLocalDate());
                }

                listaClientes.add(cliente);
            }

        } catch (SQLException e) {
            System.err.println("Error --> al listar los clientes:");
            e.printStackTrace();
        }

        return listaClientes;
    }

    //Actualiza los datos de un cliente existente.
    public void actualizarClientes(ClientesOtaku cliente) {
        String sql = "UPDATE clientes SET nombre = ?, email = ?, telefono = ? WHERE id = ?";
        DatabaseConnection db = new DatabaseConnection();

        try (
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getEmail());
            stmt.setInt(3, cliente.getTelefono());
            stmt.setInt(4, cliente.getId());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Cliente actualizado correctamente.");
            } else {
                System.out.println("No se encontró un cliente con ese ID.");
            }

        } catch (SQLException e) {
            System.err.println("Error --> al actualizar el cliente:");
            e.printStackTrace();
        }
    }

    //Elimina un cliente por su ID.
    public void eliminarClientes(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        DatabaseConnection db = new DatabaseConnection();

        try (
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Cliente eliminado correctamente.");
            } else {
                System.out.println("No se encontró un cliente con ese ID.");
            }

        } catch (SQLException e) {
            System.err.println("Error --> al eliminar el cliente:");
            e.printStackTrace();
        }
    }

     // Busca un cliente por su email.
    public ClientesOtaku buscarPorEmail(String email) {
        String sql = "SELECT * FROM clientes WHERE email = ?";
        DatabaseConnection db = new DatabaseConnection();

        try (
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ClientesOtaku cliente = new ClientesOtaku();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getInt("telefono"));

                java.sql.Date fechaSQL = rs.getDate("fecha_registro");
                if (fechaSQL != null) {
                    cliente.setFecha(fechaSQL.toLocalDate());
                }

                return cliente;
            }

        } catch (SQLException e) {
            System.err.println("Error --> al consultar el cliente:");
            e.printStackTrace();
        }

        return null;
    }
}
