package empleados;

public class Empleado {
    private int id;
    private String nombre;
    private double salarioBase;

    public Empleado(int id, String nombre, double salarioBase) {
        if (salarioBase < 1050) {
            throw new IllegalArgumentException("El salario base no puede ser menor al salario minimo (1050€)");
        } else if (id < 0 || nombre.isBlank()) {
            throw new IllegalArgumentException("Ni el id puede ser negativo ni el nombre puede estar vacio");
        }
        this.id = id;
        this.nombre = nombre;
        this.salarioBase = salarioBase;
    }

    //Getters

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void aumentarSalario(double cantidad) {
        this.salarioBase += cantidad;
    }

    public void reducirSalario(double cantidad) {
        if (salarioBase - cantidad < 1050) {
            throw new IllegalArgumentException("El salario no se puede reducir a menos del salario minimo");
        }
        this.salarioBase -= cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return id == empleado.id && this.nombre.equalsIgnoreCase(empleado.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.toUpperCase().hashCode() + id;
    }
}