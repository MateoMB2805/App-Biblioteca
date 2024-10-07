package Model;

import java.time.LocalDate;
import java.util.List;

public class DetallesPrestamo {
    private String codigo;
    private Double subTotalPrestamo;
    private List<Libro> libros;
    private int unidadesPrestadas ;
    private String cedulaEstudiante;
    private String cedulaBibliotecario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaEntrega;
    private Libro libro;
    private EstadoLibro estadoLibro;
    
    public DetallesPrestamo(String codigo,Double subTotalPrestamo, int unidadesPrestadas, String cedulaEstudiante,
            String cedulaBibliotecario,LocalDate fechaPrestamo, LocalDate fechaEntrega, Libro libro) {
        this.codigo=codigo;        
        this.subTotalPrestamo = subTotalPrestamo;
        this.unidadesPrestadas = unidadesPrestadas;
        this.cedulaEstudiante = cedulaEstudiante;
        this.cedulaBibliotecario = cedulaBibliotecario;
        this.fechaEntrega = fechaEntrega;
        this.fechaPrestamo = fechaPrestamo;
        this.libro = libro;
        this.estadoLibro = EstadoLibro.PRESTADO;
    }

    public Double getSubTotalPrestamo() {
        // Si subTotalPrestamo ya tiene un valor inicial, simplemente multiplícalo por las unidades prestadas
        return subTotalPrestamo * unidadesPrestadas;
    }
    

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public int getUnidadesPrestadas() {
        return unidadesPrestadas;
    }

    public void setUnidadesPrestadas(int unidadesPrestadas) {
        this.unidadesPrestadas = unidadesPrestadas;
    }

    public String getCedulaEstudiante() {
        return cedulaEstudiante;
    }

    public void setCedulaEstudiante(String cedulaEstudiante) {
        this.cedulaEstudiante = cedulaEstudiante;
    }

    public String getCedulaBibliotecario() {
        return cedulaBibliotecario;
    }

    public void setCedulaBibliotecario(String cedulaBibliotecario) {
        this.cedulaBibliotecario = cedulaBibliotecario;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Libro getLibro(){
        return libro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Método para obtener el estado del libro
    public EstadoLibro getEstadoLibro() {
        return estadoLibro;
    }

    // Método para establecer el estado del libro
    public void setEstadoLibro(EstadoLibro estadoLibro) {
        this.estadoLibro = estadoLibro;
    }
    
}
