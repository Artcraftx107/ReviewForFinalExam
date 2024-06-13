import inv532.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class GestorInventarioTest {

    private GestorInventario gestor;

    @BeforeEach
    public void setUp() {
        gestor = new GestorInventario();
    }

    @Test
    public void testCargarInventarioDesdeArchivo() {
        try {
            gestor.cargarInventario("src/test/resources/inventario_test.txt");
        } catch (IOException e) {
            fail("IOException lanzada durante la carga del inventario desde el archivo");
        }

        // Verificar que se agregaron los productos correctamente
        assertEquals(3, gestor.listarProductos().size()); // Ajustar según los productos en el archivo de prueba
    }

    @Test
    public void testValorInventario() {
        // Crear algunos productos y agregarlos manualmente al inventario
        Producto producto1 = new Producto(1, "Producto1", 10.5);
        Producto producto2 = new Producto(2, "Producto2", 20.0);
        Producto producto3 = new Producto(3, "Producto3", 15.0);

        gestor.agregarProducto(producto1);
        gestor.agregarProducto(producto2);
        gestor.agregarProducto(producto3);

        // Calcular el valor total del inventario
        double valorEsperado = 10.5 + 20.0 + 15.0;
        assertEquals(valorEsperado, gestor.valorInventario(), 0.01);
    }
}