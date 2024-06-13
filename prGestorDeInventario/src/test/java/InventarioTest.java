import inv532.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import inv532.Inventario;

public class InventarioTest {

    private Inventario inventario;
    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    public void setUp() {
        inventario = new Inventario();
        producto1 = new Producto(1, "Producto1", 10.5);
        producto2 = new Producto(2, "Producto2", 20.0);
        inventario.agregarProducto(producto1);
        inventario.agregarProducto(producto2);
    }

    @Test
    public void testAgregarYBuscarProducto() {
        Producto encontrado1 = inventario.buscarProducto(1);
        assertEquals(producto1, encontrado1);

        Producto encontrado2 = inventario.buscarProducto(2);
        assertEquals(producto2, encontrado2);
    }

    @Test
    public void testBuscarProductoInexistente() {
        assertThrows(NoSuchElementException.class, () -> {
            inventario.buscarProducto(3);
        });
    }
}

