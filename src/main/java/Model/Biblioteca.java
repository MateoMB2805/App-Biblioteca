package Model;

import java.time.LocalDate;
import java.util.List;

public class Biblioteca {
    private String nombre;
    private List<Bibliotecario> bibliotecarios;
    private List<Libro> libros;
    private List<Estudiante> estudiante;
    private List<Prestamo> prestamos;
    private List<DetallesPrestamo> detallesPrestamos;
    
    



    public Biblioteca(String nombre, List<Bibliotecario> bibliotecarios, List<Libro> libros,
            List<Estudiante> estudiante, List<Prestamo> prestamos, List<DetallesPrestamo> detallesPrestamos) {
        this.nombre = nombre;
        this.bibliotecarios = bibliotecarios;
        this.libros = libros;
        this.estudiante = estudiante;
        this.prestamos = prestamos;
        this.detallesPrestamos= detallesPrestamos;
    }



    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public List<Bibliotecario> getBibliotecarios() {
        return bibliotecarios;
    }



    public void setBibliotecarios(List<Bibliotecario> bibliotecarios) {
        this.bibliotecarios = bibliotecarios;
    }



    public List<Libro> getLibros() {
        return libros;
    }



    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }



    public List<Estudiante> getEstudiante() {
        return estudiante;
    }



    public void setEstudiante(List<Estudiante> estudiante) {
        this.estudiante = estudiante;
    }

    
    public List<Prestamo> getPrestamos() {
        return prestamos;
    }



    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
    public List<DetallesPrestamo> detallesPrestamos(){
        return detallesPrestamos;
    }
        
    
//ADMINISTRAR LIBROS    

    // Crear libros

    public void crearLibro(String codigo, String isbn, String autor, String titulo, LocalDate fecha, int unidadesDisponibles){
        Libro libro = new Libro(codigo, isbn, autor, titulo, fecha, unidadesDisponibles);
        libros.add(libro);
        System.out.println("Libro" + titulo + "creado");
    }

    // consultar datos de un libro por su código

    public Libro consultarLibroPorCodigo (String codigo) {
        
        for (Libro libro : libros){
            if ( libro.getCodigo().equals(codigo)){
                return libro;
            }
        }
        return null;
    }

    // Consultar cantidad de préstamos de un libro por su titulo.
    public int cantPrestamosPorTitulo(String titulo){
        int cantidadPrestamos= 0;
        for ( DetallesPrestamo detalles : detallesPrestamos){
            if (detalles.getLibro().getTitulo().toLowerCase().equals(titulo.toLowerCase())){
                cantidadPrestamos++;
            }

        }
        return cantidadPrestamos;
    }

    // Reemplazar libro por otro

    public void reemplazarLibro(String codLibroViejo, Libro nuevoLibro){
        Libro libroViejo = consultarLibroPorCodigo(codLibroViejo); 
        if (libroViejo != null){
            int indice = libros.indexOf(libroViejo);
            libros.set(indice, nuevoLibro);
            System.out.println("El libro fue reemplazado correctamente");
        }else{
            System.out.println("El libro con el codigo "+ codLibroViejo + "no existe" );
        }
    }

// ADMINISTRAR PRÉSTAMOS    
     // Crear préstamo
    public void crearPrestamo(String tituloLibro, String codigo, String cedulaEst, String cedulaBib, int costoTotal){
        for (Libro libro : libros){
            if (libro.getTitulo().toLowerCase().equals(tituloLibro.toLowerCase()) && libro.getUnidadesDisponibles() >= 1){ //Dado un título se compara en la lista de libros para crear el préstamo de ese mismo libro si está disponible
                Prestamo prestamo = new Prestamo(codigo, null);
                DetallesPrestamo detalles = new DetallesPrestamo(codigo, null, costoTotal, cedulaEst, cedulaBib, null, null);//Se crean los detalles del prestamo
                detallesPrestamos.add(detalles);
                prestamos.add(prestamo);
                System.out.println("Nuevo préstamo creado");
                libro.restarUnidadDisponible();
                EstadoLibro estado = EstadoLibro.PRESTADO; // Se asigna el estado del libro a prestado
                System.out.println("Ahora el libro se encuentra " + estado);
            }
        }
     }
     
     //Entregar préstamo
     public void entregarPrestamo(String codigoPrestamo, LocalDate fecha){
        for (Prestamo prestamo: prestamos){
            if(prestamo.getCodigo().equals(codigoPrestamo)){
                //detallesPrestamos.setFechaEntrega();          Posible funcion para asignar fecha de entrega de la clase detallesPrestamo
                prestamo.getCostoTotal();
                EstadoLibro estado = EstadoLibro.ENTREGADO; // Se asigna el estado del libro a prestado
                System.out.println("Ahora el libro se encuentra " + estado);
                
            }
        }
     }
    

    
}
