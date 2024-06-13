package empleados;

import java.util.*;

public class Departamento {
    private String nombre;
    private Map<Integer, Empleado> empleados;

    public Departamento(String nombre){
        if(nombre.isBlank()){
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        this.nombre=nombre;
        this.empleados=new TreeMap<>();
    }

    //Getters

    public String getNombre() {
        return nombre;
    }

    public List<Empleado> getEmpleados() {
        List<Empleado> empleadosList = new ArrayList<>();
        for(Empleado empleado : empleados.values()){
            empleadosList.add(empleado);
        }
        return empleadosList;
    }

    //Management
    public void anyadeEmpleado(Empleado empleado){
        if(empleados.containsKey(empleado.getId())){
            empleados.replace(empleado.getId(), empleado);
        }
        empleados.put(empleado.getId(), empleado);
    }

    public Empleado buscarEmpleado(int id){
        if(!empleados.containsKey(id)){
            throw new NoSuchElementException("No hay un empleado con la id "+id+" en el departamento "+nombre);
        }
        return empleados.get(id);
    }

    public double calcularSalarioTotalDeLosEmpleados(){
        double salarioTotal = 0;
        for(Empleado empleado : empleados.values()){
            salarioTotal+=empleado.getSalarioBase();
        }
        return salarioTotal;
    }
}