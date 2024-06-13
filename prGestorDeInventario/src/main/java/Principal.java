import inv532.GestorInventario;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) throws IOException {
        GestorInventario gestor = new GestorInventario();
        gestor.cargarInventario("data/productos.txt");
        gestor.mostrarInventario();

        System.out.println("\nValor total del inventario: $" + gestor.valorInventario());
    }
}