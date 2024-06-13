package empleados;

import java.io.*;
import java.util.*;

public class GestorDepartamentos {
    private Map<String, Departamento> departamentos;

    public GestorDepartamentos(){
        this.departamentos=new TreeMap<>();
    }

    public void agregarEmpleados(String nombreDepartamento, Empleado empleado){
        Departamento departamento;
        if(departamentos.containsKey(nombreDepartamento)){
            departamento = departamentos.get(nombreDepartamento);
        }else {
            departamento = new Departamento(nombreDepartamento);
            departamentos.put(nombreDepartamento, departamento);
        }
        departamento.anyadeEmpleado(empleado);
    }

    public Empleado buscarEmpleado(int id){
        Empleado empleado = null;
        for(Departamento departamento : departamentos.values()){
            try{
                empleado = departamento.buscarEmpleado(id);
            }catch (NoSuchElementException e){
                System.err.println(e.getMessage());
            }
        }
        if(empleado==null){
            throw new NoSuchElementException("No hay ningun empleado con el id "+id+" en ninguno de los departamentos");
        }
        return empleado;
    }

    public List<Empleado> listarEmpleadosPorDepartamento(String nombreDepartamento){
        List<Empleado> empleados = new ArrayList<>();
        if(departamentos.containsKey(nombreDepartamento)){
            empleados=departamentos.get(nombreDepartamento).getEmpleados();
        }
        return empleados;
    }

    public double calcularSalarioTotalPorDepartamento(String nombreDepartamento){
        double salarioTotal = 0;
        if(departamentos.containsKey(nombreDepartamento)){
            for(Empleado empleado : departamentos.get(nombreDepartamento).getEmpleados()){
                salarioTotal+= empleado.getSalarioBase();
            }
        }
        return salarioTotal;
    }

    public void mostrarDepartamentosYEmpleados() {
        for (Map.Entry<String, Departamento> entry : departamentos.entrySet()) {
            System.out.println("Departamento: " + entry.getKey());
            for (Empleado empleado : entry.getValue().getEmpleados()) {
                System.out.println("  Empleado ID: " + empleado.getId() + ", Nombre: " + empleado.getNombre() + ", Salario: " + empleado.getSalarioBase());
            }
        }
    }

    //File saving and loading code
    public void saveDepartamentosYEmpleadosAFichero(String fichero)throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))){
            for(Departamento departamento : departamentos.values()){
                for(Empleado empleado : departamento.getEmpleados()){
                    bw.write(departamento.getNombre() + ":" + empleado.getId() + "," + empleado.getNombre() + "," + empleado.getSalarioBase());
                    bw.newLine();
                }
            }
        }
    }

    public void loadDepartamentosYEmpleadosDesdeFichero(String fichero)throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(fichero))){
            String line;
            while((line= br.readLine())!=null){
                try {
                    String[] parts = line.split(":");
                    if(parts.length<2){
                        throw new InvalidPropertiesFormatException("Error procesando la linea "+line);
                    }
                    String[] partes = parts[1].split(",");
                    if(partes.length<3){
                        throw new InvalidPropertiesFormatException("Error procesando la linea "+line);
                    }
                    String nombreDepartamento = parts[0];
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    double salario = Double.parseDouble(partes[2]);

                    Empleado empleado = new Empleado(id, nombre, salario);
                    agregarEmpleados(nombreDepartamento, empleado);
                }catch (InvalidPropertiesFormatException e){
                    System.err.println(e.getMessage());
                }
            }
        }catch (IOException e){
            throw new FileNotFoundException("No se ha encontrado el fichero "+fichero);
        }
    }
}