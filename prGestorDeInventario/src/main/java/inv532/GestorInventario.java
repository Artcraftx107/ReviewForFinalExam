package inv532;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

/**
 * Clase que gestiona un inventario de productos, permite cargar productos desde un archivo
 * y realizar operaciones como mostrar el inventario y calcular el valor total del inventario.
 */
public class GestorInventario extends Inventario {

    /**
     * Constructor que inicializa un nuevo gestor de inventario.
     */
    public GestorInventario() {
        super(); // Llama al constructor de Inventario si es necesario
    }

    /**
     * Método para cargar productos al inventario desde un archivo de texto.
     * @param nombreArchivo El nombre del archivo que contiene los datos de los productos.
     * @throws IOException Si ocurre un error de lectura o acceso al archivo.
     */
    public void cargarInventario(String nombreArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    String[] parts = linea.split(",");
                    if (parts.length < 3) {
                        throw new InvalidPropertiesFormatException("There was an error processing the line " + linea);
                    }
                    try {
                        Producto producto = new Producto(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]));
                        agregarProducto(producto); // Utiliza el método de Inventario para agregar productos
                    } catch (NumberFormatException e) {
                        System.err.println("Error processing the line " + linea);
                    }
                } catch (InvalidPropertiesFormatException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new FileNotFoundException("The file " + nombreArchivo + " was not found");
        }
    }

    /**
     * Método para mostrar todos los productos en el inventario por consola.
     */
    public void mostrarInventario() {
        for (Producto producto : listarProductos()) { // Utiliza el método de Inventario para listar productos
            System.out.println(producto.getCodigo() + " - " + producto.getNombre() + " - " + producto.getPrecio());
        }
    }

    /**
     * Método para calcular el valor total del inventario sumando el precio de todos los productos.
     * @return El valor total del inventario.
     */
    public double valorInventario() {
        double totalPrecio = 0;
        for (Producto producto : listarProductos()) { // Utiliza el método de Inventario para listar productos
            totalPrecio += producto.getPrecio();
        }
        return totalPrecio;
    }
}