package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.ProductoOtaku;

public class ProductosDAO {
    // Agrega un producto a la base de datos
    public void agregarProducto(ProductoOtaku producto) {
        String sql = "INSERT INTO productos (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";

        DatabaseConnection db = new DatabaseConnection();

        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getCategoria());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getStock());

            stmt.executeUpdate();
            System.out.println("Producto insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error --> al insertar el producto:");
            e.printStackTrace();
        }
    }

    // Busca un producto por su ID
    public ProductoOtaku obtenerProductoPorId(int id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        DatabaseConnection db = new DatabaseConnection();

        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ProductoOtaku producto = new ProductoOtaku();

                    producto.setId(rs.getInt("id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setCategoria(rs.getString("categoria"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setStock(rs.getInt("stock"));

                    return producto;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error --> al consultar el producto:");
            e.printStackTrace();
        }
        return null;
    }

    // Obtiene todos los productos de la base de datos
    public List<ProductoOtaku> obtenerTodosLosProductos() {
        String sql = "SELECT * FROM productos";
        DatabaseConnection db = new DatabaseConnection();
        List<ProductoOtaku> listaProductos = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("=== Lista de productos ===");
            while (rs.next()) {
                ProductoOtaku producto = new ProductoOtaku();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCategoria(rs.getString("categoria"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));

                listaProductos.add(producto);
            }
        } catch (SQLException e) {
            System.err.println("Error --> al listar los productos:");
            e.printStackTrace();
        }
        return listaProductos;
    }

    // Actualiza un producto existente
    public void actualizarProducto(ProductoOtaku producto) {
        String sql = "UPDATE productos SET nombre = ?, categoria = ?, precio = ?, stock = ? WHERE id = ?";
        DatabaseConnection db = new DatabaseConnection();

        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            System.out.println("Actualizando producto: " + producto);

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getCategoria());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getStock());
            stmt.setInt(5, producto.getId());

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Producto actualizado correctamente.");
            } else {
                System.out.println("No se encontró un producto con ese ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error --> al actualizar el producto:");
            e.printStackTrace();
        }
    }

    // Elimina un producto por su ID
    public void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        DatabaseConnection db = new DatabaseConnection();

        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int filasEliminadas = stmt.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("No se encontró un producto con ese ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error --> al eliminar el producto:");
            e.printStackTrace();
        }
    }

    // Busca un producto por su nombre
    public ProductoOtaku buscarProductosPorNombre(String nombre) {
        String sql = "SELECT * FROM productos WHERE nombre = ?";
        DatabaseConnection db = new DatabaseConnection();

        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ProductoOtaku producto = new ProductoOtaku();

                    producto.setId(rs.getInt("id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setCategoria(rs.getString("categoria"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setStock(rs.getInt("stock"));

                    return producto;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error --> al consultar el producto:");
            e.printStackTrace();
        }
        return null;
    }
}

