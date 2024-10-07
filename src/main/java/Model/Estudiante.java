package Model;

public class Estudiante extends Persona {
    private String programa;
    private int cantPrestamosSolicitados;

    public Estudiante(String nombre, String cedula, String telefono, String correo, String programa, int cantPrestamosSolicitados) {
        super(nombre, cedula, telefono, correo);
        this.programa = programa;
        this.cantPrestamosSolicitados= cantPrestamosSolicitados;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }
    public int getCantPrestamosSolicitados(){
        return cantPrestamosSolicitados;
    }

    public void sumarCantPrestamosEstudiante(){
        cantPrestamosSolicitados++;
    }

}
