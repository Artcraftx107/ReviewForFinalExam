import empleados.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class GestorDepartamentosTest {
    private GestorDepartamentos gestor;

    @BeforeEach
    void setUp() {
        gestor = new GestorDepartamentos();
    }

    @Test
    void agregarEmpleado() {
        Empleado empleado1 = new Empleado(1, "John Doe", 2500.0);
        Empleado empleado2 = new Empleado(2, "Jane Smith", 3000.0);
        Empleado empleado = new Empleado(3, "Saul", 2000);

        gestor.agregarEmpleados("Ventas", empleado1);
        gestor.agregarEmpleados("Ventas", empleado);
        gestor.agregarEmpleados("TI", empleado2);

        assertEquals(2, gestor.listarEmpleadosPorDepartamento("Ventas").size());
        assertEquals(1, gestor.listarEmpleadosPorDepartamento("TI").size());
    }

    @Test
    void buscarEmpleadoExistente() {
        Empleado empleado = new Empleado(1, "John Doe", 2500.0);
        gestor.agregarEmpleados("Ventas", empleado);

        Empleado encontrado = gestor.buscarEmpleado(1);

        assertEquals(empleado, encontrado);
    }

    @Test
    void buscarEmpleadoNoExistente() {
        Empleado empleado = new Empleado(1, "John Doe", 2500.0);
        gestor.agregarEmpleados("Ventas", empleado);

        assertThrows(NoSuchElementException.class, () -> {
            gestor.buscarEmpleado(2);
        });
    }

    @Test
    void calcularSalarioTotalPorDepartamento() {
        Empleado empleado1 = new Empleado(1, "John Doe", 2500.0);
        Empleado empleado2 = new Empleado(2, "Jane Smith", 3000.0);

        gestor.agregarEmpleados("Ventas", empleado1);
        gestor.agregarEmpleados("Ventas", empleado2);

        assertEquals(5500.0, gestor.calcularSalarioTotalPorDepartamento("Ventas"));
    }

    @Test
    void testSaveAndLoadDepartamentosYEmpleadosAFichero() throws IOException {
        // Agregar empleados a diferentes departamentos
        gestor.agregarEmpleados("IT", new Empleado(1, "Alice", 5000));
        gestor.agregarEmpleados("HR", new Empleado(2, "Bob", 4500));
        gestor.agregarEmpleados("IT", new Empleado(3, "Charlie", 5500));
        gestor.agregarEmpleados("Finance", new Empleado(4, "David", 6000));

        // Guardar en fichero
        gestor.saveDepartamentosYEmpleadosAFichero("src/test/resources/departamentos_test.txt");

        // Crear un nuevo gestor y cargar desde fichero
        GestorDepartamentos gestorCargado = new GestorDepartamentos();
        gestorCargado.loadDepartamentosYEmpleadosDesdeFichero("src/test/resources/departamentos_test.txt");

        // Comprobar que los datos se cargaron correctamente
        List<Empleado> empleadosIT = gestorCargado.listarEmpleadosPorDepartamento("IT");
        List<Empleado> empleadosHR = gestorCargado.listarEmpleadosPorDepartamento("HR");
        List<Empleado> empleadosFinance = gestorCargado.listarEmpleadosPorDepartamento("Finance");

        assertEquals(2, empleadosIT.size());
        assertEquals(1, empleadosHR.size());
        assertEquals(1, empleadosFinance.size());

        // Verificar algunos empleados específicos
        assertEquals("Alice", empleadosIT.get(0).getNombre());
        assertEquals(4500, empleadosHR.get(0).getSalarioBase());
        assertEquals("David", empleadosFinance.get(0).getNombre());

        // Eliminar el archivo de prueba
        File file = new File("src/test/resources/departamentos_test.txt");
        if (file.exists()) {
            assertTrue(file.delete());
        }
    }
}