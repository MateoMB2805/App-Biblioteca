package Model;

public class Bibliotecario extends Persona {
    
    private Double salario;


    public Bibliotecario(String nombre, String cedula, String telefono, String correo, Double salario){
        super(nombre, cedula, telefono, correo);
        this.salario =salario;
    }


    

    public Double getSalario() {
        return salario;
    }


    public void setSalario(Double salario) {
        this.salario = salario;
    }

    
}
