package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import dao.ProductosDAO;
import modelo.ProductoOtaku;

// Clase de pruebas unitarias para las operaciones CRUD de ProductosDAO
class pruebaProductoDAO {

    // Prueba que verifica la inserción de un producto y su correcta recuperación por nombre
    @Test
    void testAgregarProducto() throws Exception {
        ProductosDAO dao = new ProductosDAO();
        ProductoOtaku p = new ProductoOtaku("Figura Goku", "Figuras", 25.99, 10);
        dao.agregarProducto(p);

        ProductoOtaku resultado = dao.buscarProductosPorNombre("Figura Goku");
        assertNotNull(resultado);
        assertEquals("Figura Goku", resultado.getNombre());
        assertEquals("Figuras", resultado.getCategoria());
        assertEquals(25.99, resultado.getPrecio());
        assertEquals(10, resultado.getStock());
    }

    // Prueba que valida la obtención de un producto mediante su ID
    @Test
    void testObtenerProductoPorId() throws Exception {
        ProductosDAO dao = new ProductosDAO();
        ProductoOtaku p = new ProductoOtaku("Camiseta Naruto", "Ropa", 15.50, 20);
        dao.agregarProducto(p);

        ProductoOtaku guardado = dao.buscarProductosPorNombre("Camiseta Naruto");
        ProductoOtaku resultado = dao.obtenerProductoPorId(guardado.getId());

        assertNotNull(resultado);
        assertEquals("Camiseta Naruto", resultado.getNombre());
    }

    // Prueba que asegura que la lista de productos obtenida no sea nula y puede estar vacía o contener productos
    @Test
    void testObtenerTodosLosProductos() {
        ProductosDAO dao = new ProductosDAO();
        List<ProductoOtaku> lista = dao.obtenerTodosLosProductos();
        assertNotNull(lista);
        assertTrue(lista.size() >= 0);
    }

    // Prueba para verificar la actualización de un producto y la persistencia de los cambios
    @Test
    void testActualizarProducto() throws Exception {
        ProductosDAO dao = new ProductosDAO();
        ProductoOtaku p = new ProductoOtaku("Manga One Piece Vol.1", "Manga", 9.99, 50);
        dao.agregarProducto(p);

        ProductoOtaku producto = dao.buscarProductosPorNombre("Manga One Piece Vol.1");

        producto.setPrecio(12.99);
        producto.setStock(45);

        dao.actualizarProducto(producto);

        ProductoOtaku actualizado = dao.obtenerProductoPorId(producto.getId());
        assertNotNull(actualizado);
        assertEquals("Manga One Piece Vol.1", actualizado.getNombre());
        assertEquals("Manga", actualizado.getCategoria());
        assertEquals(12.99, actualizado.getPrecio());
        assertEquals(45, actualizado.getStock());
    }

    // Prueba que verifica la eliminación correcta de un producto y que ya no se pueda encontrar
    @Test
    void testEliminarProducto() throws Exception {
        ProductosDAO dao = new ProductosDAO();
        ProductoOtaku p = new ProductoOtaku("Figura Goku", "Figuras", 25.99, 10);
        dao.agregarProducto(p);

        ProductoOtaku producto = dao.buscarProductosPorNombre("Figura Goku");
        dao.eliminarProducto(producto.getId());

        ProductoOtaku eliminado = dao.obtenerProductoPorId(producto.getId());
        assertNull(eliminado);
    }

    // Prueba que confirma que se puede buscar un producto correctamente por su nombre
    @Test
    void testBuscarProductosPorNombre() throws Exception {
        ProductosDAO dao = new ProductosDAO();
        ProductoOtaku p = new ProductoOtaku("Camiseta Naruto", "Ropa", 15.50, 20);
        dao.agregarProducto(p);

        ProductoOtaku resultado = dao.buscarProductosPorNombre("Camiseta Naruto");
        assertNotNull(resultado);
        assertEquals("Camiseta Naruto", resultado.getNombre());
    }
}

