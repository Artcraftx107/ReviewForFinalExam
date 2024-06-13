package inv532;

/**
 * Clase que representa un producto en el inventario.
 */
public class Producto {
    private final int codigo;
    private final String nombre;
    private final double precio;

    /**
     * Constructor para inicializar un producto con código, nombre y precio.
     * @param codigo El código único del producto. Debe ser mayor o igual a 0.
     * @param nombre El nombre del producto. No puede estar en blanco.
     * @param precio El precio unitario del producto. Debe ser mayor que 0.
     * @throws IllegalArgumentException Si el código es negativo, el nombre está en blanco,
     * o el precio es menor o igual a 0.
     */
    public Producto(int codigo, String nombre, double precio){
        if(codigo<0||precio<=0){
            throw new IllegalArgumentException("Neither the code can be negative or the price can be equal or lower than 0");
        } else if (nombre.isBlank()) {
            throw new IllegalArgumentException("The name of the product cannot be blank");
        }
        this.codigo=codigo;
        this.nombre=nombre.trim();
        this.precio=precio;
    }

    //Getters

    /**
     * Obtiene el código del producto.
     * @return El código del producto.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el nombre del producto.
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del producto.
     * @return El precio del producto.
     */
    public double getPrecio() {
        return precio;
    }


    //Equals y Hashcode sobreescritos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return codigo == producto.codigo && nombre.equalsIgnoreCase(producto.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.toUpperCase().hashCode()+codigo;
    }
}