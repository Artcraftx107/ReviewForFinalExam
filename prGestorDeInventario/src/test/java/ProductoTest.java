import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import inv532.Producto;

public class ProductoTest {

    @Test
    public void crearProductoCorrecto() {
        Producto producto = new Producto(1, "Producto1", 10.5);
        assertEquals(1, producto.getCodigo());
        assertEquals("Producto1", producto.getNombre());
        assertEquals(10.5, producto.getPrecio(), 0.01);
    }

    @Test
    public void crearProductoConDatosInvalidos() {
        // Código negativo
        assertThrows(IllegalArgumentException.class, () -> {
            Producto producto = new Producto(-1, "Producto2", 20.0);
        });

        // Nombre vacío
        assertThrows(IllegalArgumentException.class, () -> {
            Producto producto = new Producto(2, "", 15.0);
        });

        // Precio negativo
        assertThrows(IllegalArgumentException.class, () -> {
            Producto producto = new Producto(3, "Producto3", -5.0);
        });
    }
}