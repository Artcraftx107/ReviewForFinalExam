import java.io.IOException;
import java.util.List;
import empleados.*;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        GestorDepartamentos gestor = new GestorDepartamentos();

        // Cargar empleados desde el archivo
        try {
            gestor.loadDepartamentosYEmpleadosDesdeFichero("data/departamentos.txt");
            System.out.println("Empleados cargados desde el archivo:");
            gestor.mostrarDepartamentosYEmpleados();
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }

        // Buscar un empleado
        try {
            Empleado empleado = gestor.buscarEmpleado(1);
            System.out.println("Empleado encontrado: " + empleado.getNombre() + " con salario " + empleado.getSalarioBase());
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }

        // Listar empleados por departamento
        String departamento = "IT";
        List<Empleado> empleados = gestor.listarEmpleadosPorDepartamento(departamento);
        System.out.println("Empleados en el departamento " + departamento + ":");
        for (Empleado emp : empleados) {
            System.out.println(emp.getNombre() + " con salario " + emp.getSalarioBase());
        }

        // Calcular salario total por departamento
        double salarioTotal = gestor.calcularSalarioTotalPorDepartamento(departamento);
        System.out.println("Salario total en el departamento " + departamento + ": " + salarioTotal);

        // Guardar empleados a un archivo
        try {
            gestor.saveDepartamentosYEmpleadosAFichero("data/departamentos_output.txt");
            System.out.println("Empleados guardados en el archivo data/departamentos_output.txt");
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}