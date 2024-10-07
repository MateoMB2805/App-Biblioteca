package Model;

import java.time.LocalDate;

public class Bibliotecario extends Persona {
    
    private Double salario;
    private int cantidadPrestamos;
    private LocalDate fechaIngreso;


    public Bibliotecario(String nombre, String cedula, String telefono, String correo, Double salario, int cantidadPrestamos, LocalDate fechaIngreso){
        super(nombre, cedula, telefono, correo);
        this.salario =salario;
        this.cantidadPrestamos=cantidadPrestamos;
        this.fechaIngreso = fechaIngreso;
    }

    public Double getSalario() {
        return salario;
    }


    public void setSalario(Double salario) {
        this.salario = salario;
    }
    public int getCantidadPrestamos(){
        return cantidadPrestamos;
    }

    public void cantidadPrestamosRealizados(){
        cantidadPrestamos++;
    }

    public LocalDate getFechaIngreso(){
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso){
        this.fechaIngreso=fechaIngreso;
    }

    
    
}
