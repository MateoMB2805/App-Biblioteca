/**
 * Clase para probar el funcionamiento del código
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Bibliotecario;
import Model.Prestamo;
import Model.Biblioteca;
import Model.Estudiante;
import Model.DetallesPrestamo;
import Model.EstadoLibro;
import Model.Libro;
/**
 * Unit test for simple App.
 */
class BibliotecaTest {

    private Biblioteca biblioteca;
    private List<Libro> libros;
    private List<Bibliotecario> bibliotecarios;
    private List<Estudiante> estudiantes;
    private List<Prestamo> prestamos;
    private List<DetallesPrestamo> detallesPrestamos;

    @BeforeEach
    void setUp() {
        libros = new ArrayList<>();
        bibliotecarios = new ArrayList<>();
        estudiantes = new ArrayList<>();
        prestamos = new ArrayList<>();
        detallesPrestamos = new ArrayList<>();

        biblioteca = new Biblioteca("Biblioteca CRAI", bibliotecarios, libros, estudiantes, prestamos, detallesPrestamos);
    }

    @Test
    void testCrearLibro() {
        biblioteca.crearLibro("001", "123-456-789", "Autor A", "Libro A", LocalDate.now(), 5);
        assertEquals(1, biblioteca.getLibros().size());
        assertEquals("Libro A", biblioteca.getLibros().get(0).getTitulo());
    }

    @Test
    void testConsultarLibroPorCodigo() {
        Libro libro = new Libro("001", "123-456-789", "Autor A", "Libro A", LocalDate.now(), 5);
        libros.add(libro);

        Libro resultado = biblioteca.consultarLibroPorCodigo("001");
        assertNotNull(resultado);
        assertEquals("Libro A", resultado.getTitulo());
    }

    @Test
    void testConsultarLibroInexistente() {
        Libro resultado = biblioteca.consultarLibroPorCodigo("999");
        assertNull(resultado);
    }

    @Test
    void testReemplazarLibro() {
        Libro libroViejo = new Libro("001", "123-456-789", "Autor A", "Libro A", LocalDate.now(), 5);
        libros.add(libroViejo);

        Libro nuevoLibro = new Libro("002", "987-654-321", "Autor B", "Libro B", LocalDate.now(), 3);
        biblioteca.reemplazarLibro("001", nuevoLibro);

        assertEquals(1, biblioteca.getLibros().size());
        assertEquals("Libro B", biblioteca.getLibros().get(0).getTitulo());
    }

    @Test
    void testCantPrestamosPorTitulo() {
        Libro libro = new Libro("001", "123-456-789", "Autor A", "Libro A", LocalDate.now(), 5);
        libros.add(libro);
    
        DetallesPrestamo detalles = new DetallesPrestamo("001", 2000.0, 1, "123", "456", LocalDate.now(), null, libro);
        detallesPrestamos.add(detalles);

        int cantidad = biblioteca.cantPrestamosPorTitulo("Libro A");
        assertEquals(1, cantidad);
    }

    @Test
    void testCrearPrestamo() {
        Libro libro = new Libro("001", "123-456-789", "Autor A", "Libro A", LocalDate.now(), 5);
        libros.add(libro);

        biblioteca.crearPrestamo("Libro A", "P001", "123456", "654321", 2000);
        assertEquals(1, biblioteca.getPrestamos().size());
        assertEquals(4, libro.getUnidadesDisponibles());
    }
    @Test
    void testCrearPrestamoLibroInexistente() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            biblioteca.crearPrestamo("Libro Inexistente", "P001", "123456", "654321", 2000);
        });
    }

    @Test
    public void testMostrarEstudianteConMasPrestamos() {
        // Crear estudiantes de prueba
        Estudiante estudiante1 = new Estudiante("Juan", "123", "3123456789", "juan@mail.com", "Ingeniería", 5);
        Estudiante estudiante2 = new Estudiante("Ana", "456", "3223456789", "ana@mail.com", "Medicina", 10);
        
        // Crear biblioteca con los estudiantes
        Biblioteca biblioteca = new Biblioteca("Mi Biblioteca", null, null, Arrays.asList(estudiante1, estudiante2), null, null);
        
        // Ejecutar la función y verificar
        Estudiante estudianteConMasPrestamos = biblioteca.mostrarEstudianteConMasPrestamos();
        assertEquals(estudiante2, estudianteConMasPrestamos, "El estudiante con más préstamos debería ser Ana.");
    }

    @Test
    public void testTotalDineroRecaudado() {
        // Crear detalles de préstamos de prueba con diferentes subtotales y fechas
        DetallesPrestamo detalles1 = new DetallesPrestamo("001", 2000.0, 1, "123", "789", LocalDate.now().minusDays(5), LocalDate.now(), new Libro("001", "111", "Autor1", "Titulo1", LocalDate.now(), 2));
        DetallesPrestamo detalles2 = new DetallesPrestamo("002", 3000.0, 1, "456", "789", LocalDate.now().minusDays(7), LocalDate.now(), new Libro("002", "222", "Autor2", "Titulo2", LocalDate.now(), 3));

        // Crear préstamos con sus detalles
        Prestamo prestamo1 = new Prestamo("001", detalles1.getSubTotalPrestamo());
        prestamo1.setDetallesPrestamos(detalles1);

        Prestamo prestamo2 = new Prestamo("002", detalles2.getSubTotalPrestamo());
        prestamo2.setDetallesPrestamos(detalles2);

        // Crear biblioteca con los préstamos
        Biblioteca biblioteca = new Biblioteca("Mi Biblioteca", null, null, null, Arrays.asList(prestamo1, prestamo2), Arrays.asList(detalles1, detalles2));

        // Ejecutar la función y verificar el resultado
        double totalRecaudado = biblioteca.totalDineroRecaudado();

        // Cálculo esperado:
        // Prestamo 1: 2000.0 * 5 días = 10,000
        // Prestamo 2: 3000.0 * 7 días = 21,000
        // Total esperado: 10,000 + 21,000 = 31,000
        assertEquals(31000.0, totalRecaudado, "El total de dinero recaudado debería ser 31,000.");
    }


    @Test
    public void testTotalPagarBibliotecarios() {
        // Crear bibliotecarios con fechas de ingreso
        Bibliotecario bibliotecario1 = new Bibliotecario("Juan", "001", "3211234567", "juan@mail.com", 0.0, 0, LocalDate.of(2019, 10, 1)); // 5 años de antigüedad (suponiendo que estamos en 2024)
        Bibliotecario bibliotecario2 = new Bibliotecario("Maria", "002", "3219876543", "maria@mail.com", 0.0, 0, LocalDate.of(2014, 10, 1)); // 10 años de antigüedad
    
        // Crear detalles de préstamos y asociarlos a los bibliotecarios
        DetallesPrestamo detalles1 = new DetallesPrestamo("001", 2000.0, 1, "123", "001", LocalDate.now().minusDays(5), LocalDate.now(), new Libro("001", "111", "Autor1", "Titulo1", LocalDate.now(), 2));
        DetallesPrestamo detalles2 = new DetallesPrestamo("002", 3000.0, 1, "456", "002", LocalDate.now().minusDays(7), LocalDate.now(), new Libro("002", "222", "Autor2", "Titulo2", LocalDate.now(), 3));
    
        // Crear préstamos y asociar los detalles
        Prestamo prestamo1 = new Prestamo("001", detalles1.getSubTotalPrestamo());
        prestamo1.setDetallesPrestamos(detalles1);
    
        Prestamo prestamo2 = new Prestamo("002", detalles2.getSubTotalPrestamo());
        prestamo2.setDetallesPrestamos(detalles2);
    
        // Crear biblioteca con los préstamos y bibliotecarios
        Biblioteca biblioteca = new Biblioteca("Mi Biblioteca", Arrays.asList(bibliotecario1, bibliotecario2), null, null, Arrays.asList(prestamo1, prestamo2), Arrays.asList(detalles1, detalles2));
    
        // Ejecutar la función y obtener el resultado
        double totalPagar = biblioteca.calcularTotalAPagarBibliotecarios();
    
        // Cálculo esperado:
        // Bibliotecario 1 (5 años de antigüedad):
        // - Prestamo 1: 2000.0 * 5 días = 10,000, 20% de 10,000 = 2,000, más bonificación (2% por cada año de antigüedad, 5 años): 2,000 * 1.10 = 2,200
        // Bibliotecario 2 (10 años de antigüedad):
        // - Prestamo 2: 3000.0 * 7 días = 21,000, 20% de 21,000 = 4,200, más bonificación (2% por cada año de antigüedad, 10 años): 4,200 * 1.20 = 5,040
        // Total esperado: 2,200 + 5,040 = 7,240
    
        assertEquals(7240.0, totalPagar, "El total a pagar a los bibliotecarios debería ser 7,240.");
    }

    @Test
    public void testEntregarPrestamo() {
        // Configuración inicial
        Libro libro = new Libro("L001", "ISBN1234", "Autor 1", "Libro 1", LocalDate.now().minusYears(1), 3);
        DetallesPrestamo detalles = new DetallesPrestamo("P001", 6000.0, 1, "E001", "B001", LocalDate.now().minusDays(7), null, libro);
        Prestamo prestamo = new Prestamo("P001", 6000.0);
        prestamo.setDetallesPrestamos(detalles);

        Biblioteca biblioteca = new Biblioteca("Biblioteca Central", List.of(), List.of(libro), List.of(), List.of(prestamo), List.of(detalles));

        // Simulación de entrega de préstamo
        biblioteca.entregarPrestamo("P001", LocalDate.now());

        // Verificaciones
        assertEquals(EstadoLibro.ENTREGADO, detalles.getEstadoLibro(), "El estado del libro debería ser ENTREGADO.");
        assertEquals(LocalDate.now(), detalles.getFechaEntrega(), "La fecha de entrega debería coincidir con la asignada.");
    }

}