package Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Biblioteca {
    private String nombre;
    private List<Bibliotecario> bibliotecarios;
    private List<Libro> libros;
    private List<Estudiante> estudiantes;
    private List<Prestamo> prestamos;
    private List<DetallesPrestamo> detallesPrestamos;
    
    



    public Biblioteca(String nombre, List<Bibliotecario> bibliotecarios, List<Libro> libros,
            List<Estudiante> estudiantes, List<Prestamo> prestamos, List<DetallesPrestamo> detallesPrestamos) {
        this.nombre = nombre;
        this.bibliotecarios = bibliotecarios;
        this.libros = libros;
        this.estudiantes = estudiantes;
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



    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }



    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
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

    public void crearLibro(String codigo, String isbn, String autor, String titulo, LocalDate fecha, int unidadesDisponibles) {
        if (codigo == null || titulo == null || autor == null || unidadesDisponibles < 0) {
            throw new IllegalArgumentException("Datos del libro no pueden ser nulos o inválidos.");
        }
    
        Libro libro = new Libro(codigo, isbn, autor, titulo, fecha, unidadesDisponibles);
        libros.add(libro);
        System.out.println("Libro " + titulo + " creado.");
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
        return (int) detallesPrestamos.stream()
        .filter(detalle -> detalle.getLibro().getTitulo().equalsIgnoreCase(titulo))
        .count();
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
    public void crearPrestamo(String tituloLibro, String codigoPrestamo, String cedulaEstudiante, String cedulaBibliotecario, int unidadesPrestadas) {
        Libro libro = consultarLibroPorTitulo(tituloLibro);
        if (libro == null || libro.getUnidadesDisponibles() <= 0) {
            throw new IllegalArgumentException("El libro no existe o no hay unidades disponibles.");
        }

    // Si llegamos aquí, el libro existe y hay unidades disponibles
        DetallesPrestamo detalles = new DetallesPrestamo(codigoPrestamo, 2000.0, unidadesPrestadas, cedulaEstudiante, cedulaBibliotecario, LocalDate.now(), LocalDate.now().plusDays(7), libro);

        Prestamo prestamo = new Prestamo(codigoPrestamo, detalles.getSubTotalPrestamo());
        prestamo.setDetallesPrestamos(detalles);

    // Agregar el préstamo a la lista de préstamos
        prestamos.add(prestamo);

    // Restar una unidad del libro prestado
        libro.restarUnidadDisponible();
        
    // Sumar cantidad de préstamos realizados a bibliotecario
        for (Bibliotecario bibliotecario: bibliotecarios){
            if (bibliotecario.getCedula().equals(cedulaBibliotecario)){
                bibliotecario.cantidadPrestamosRealizados();
            }
        }

    // Sumar cantidad de préstamos que tiene el estudiante
        for (Estudiante estudiante : estudiantes){
            if(estudiante.getCedula().equals(cedulaEstudiante)){
                estudiante.sumarCantPrestamosEstudiante();
            }
        }
    }


    // Método para consultar datos del libro por su código
    public Libro consultarLibroPorTitulo(String titulo) {
    for (Libro libro : libros) {
        if (libro.getTitulo().equalsIgnoreCase(titulo)) {
            return libro;
        }
    }
    return null; // No se encontró el libro
    }

     
     //Entregar préstamo
     public void entregarPrestamo(String codigoPrestamo, LocalDate fechaEntrega) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getCodigo().equals(codigoPrestamo)) {
                DetallesPrestamo detalles = prestamo.getDetallesPrestamos();
                if (detalles != null) {
                    // Actualiza la fecha de entrega
                    detalles.setFechaEntrega(fechaEntrega);
                    // Cambia el estado del libro a ENTREGADO
                    detalles.setEstadoLibro(EstadoLibro.ENTREGADO);
                    System.out.println("El préstamo con código " + codigoPrestamo + " ha sido entregado. Estado del libro: " + EstadoLibro.ENTREGADO);
                } else {
                    System.out.println("No se encontraron detalles del préstamo.");
                }
                return; // Sale del método después de encontrar y procesar el préstamo
            }
        }
        System.out.println("Préstamo con código " + codigoPrestamo + " no encontrado.");
    }
    
     //Consultar datos de un préstamo por su código 
     public Prestamo consultarPrestamoPorCodigo (String codigo) {
        for (Prestamo prestamo : prestamos){
            if ( prestamo!=null && prestamo.getCodigo().equals(codigo)){
                return prestamo;
            }
        }
        return null;
    }

    // Mostrar la cantidad de préstamos realizados por cada empleado
    public int cantidadPrestamosBibliotecario(){
        int cantidadPrestamosBibliotecario=0;
        for (Bibliotecario bibliotecario: bibliotecarios){
            cantidadPrestamosBibliotecario = bibliotecario.getCantidadPrestamos();

        }
        return cantidadPrestamosBibliotecario;
    }


    //Mostrar datos del estudiante con más préstamos realizados

    public Estudiante mostrarEstudianteConMasPrestamos(){
        Estudiante estudianteMax = null; // Se inicializa la variable para guaradar el estudiante con más préstamos solicitados
        int maxPrestamos=-1; //Contador del de préstamos máximos iniciado en -1

        //Se recorre la lista de estudiantes
        for (Estudiante estudiante: estudiantes){
            // Se compara el numero de prestamos solicitados de cada estudiante con maxPrestamos
            if( estudiante.getCantPrestamosSolicitados()>maxPrestamos){
                maxPrestamos= estudiante.getCantPrestamosSolicitados();
                estudianteMax= estudiante;  
            }
        }
        return estudianteMax;
    }


    //TOTAL DINERO RECAUDADO 
    public double totalDineroRecaudado() {
        double totalRecaudado = 0.0; // Inicializamos la variable para el total recaudado
    
        // Recorremos la lista de préstamos
        for (Prestamo prestamo : prestamos) {
            // Sumamos el costo total de cada préstamo al total recaudado
            totalRecaudado += prestamo.getCostoTotal();
        }
    
        // Retornamos el total recaudado
        return totalRecaudado;
    }


    //Calcular salario bibliotecario
    public double calcularTotalAPagarBibliotecarios() {
        double totalPago = 0.0;

        for (Bibliotecario bibliotecario : bibliotecarios) {
            double salarioBibliotecario = 0.0;

        // Calcular el salario del bibliotecario por el 20% de cada préstamo que realizó
            for (DetallesPrestamo detallesPrestamo : detallesPrestamos) {
                if (detallesPrestamo.getCedulaBibliotecario().equals(bibliotecario.getCedula())) {
                    Prestamo prestamo = consultarPrestamoPorCodigo(detallesPrestamo.getCodigo());
                    if (prestamo != null) {
                        salarioBibliotecario += prestamo.getCostoTotal() * 0.2;
                    }
                }
            }   

            // Calcular la antigüedad del bibliotecario
            int añosAntiguedad = (int) ChronoUnit.YEARS.between(bibliotecario.getFechaIngreso(), LocalDate.now());

            // Calcular la bonificación adicional del 2% por cada año de antigüedad
            double bonificacion = salarioBibliotecario * (0.02 * añosAntiguedad);

            // Sumar bonificación al salario del bibliotecario
            salarioBibliotecario += bonificacion;

            // Sumar al total general
            totalPago += salarioBibliotecario;
        }

        return totalPago;
    }
    
}
