package Model;

public class Estudiante extends Persona {
    private String programa;

    public Estudiante(String nombre, String cedula, String telefono, String correo, String programa) {
        super(nombre, cedula, telefono, correo);
        this.programa = programa;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    

}
