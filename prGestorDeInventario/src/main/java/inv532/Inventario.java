package inv532;

import java.util.*;

/**
 * Clase que representa un inventario de productos utilizando un TreeMap para almacenar los productos por su código.
 */
public class Inventario {
    private final Map<Integer, Producto> productoMap;

    /**
     * Constructor que inicializa el inventario vacío utilizando un TreeMap para mantener los productos ordenados por código.
     */
    public Inventario(){
        this.productoMap=new TreeMap<>();
    }

    /**
     * Método para agregar un producto al inventario. Si el producto ya existe (según su código), se actualiza; de lo contrario, se agrega al inventario.
     * @param producto El producto a agregar o actualizar en el inventario.
     */
    public void agregarProducto(Producto producto){
        if(productoMap.containsValue(producto)){
            // Si el producto ya existe (según equals en Producto), se actualiza en el mapa
            productoMap.replace(producto.getCodigo(), producto);
        }else{
            // Si el producto no existe, se agrega al mapa
            productoMap.put(producto.getCodigo(), producto);
        }
    }

    /**
     * Método para buscar un producto en el inventario por su código.
     * @param codigo El código del producto a buscar.
     * @return El producto encontrado.
     * @throws NoSuchElementException Si no se encuentra ningún producto con el código especificado.
     */
    public Producto buscarProducto(int codigo){
        if(!productoMap.containsKey(codigo)){
            // Si no se encuentra ningún producto con el código especificado, se lanza una excepción
            throw new NoSuchElementException("There is no product with the code "+codigo);
        }
        // Devuelve el producto encontrado
        return productoMap.get(codigo);
    }

    /**
     * Método para listar todos los productos únicos en el inventario.
     * @return Una lista de productos únicos en el inventario.
     */
    public List<Producto> listarProductos() {
        // Crea una nueva lista para almacenar los productos únicos
        List<Producto> productos = new ArrayList<>();

        // Itera sobre cada producto en el mapa de productos
        for (Producto producto : productoMap.values()) {
            // Verifica si la lista aún no contiene el producto actual
            if (!productos.contains(producto)) {
                // Si el producto no está en la lista, lo agrega
                productos.add(producto);
            }
        }

        // Devuelve la lista de productos únicos
        return productos;
    }
}