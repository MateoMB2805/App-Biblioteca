package co.edu.uniquindio.poo;


import Model.Biblioteca;
import Model.Bibliotecario;
import Model.Estudiante;
import Model.Prestamo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static Biblioteca biblioteca;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        biblioteca = new Biblioteca("Biblioteca Central", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Agregar algunos datos iniciales para probar la aplicación
        inicializarDatos();

        boolean running = true;
        while (running) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    crearLibro();
                    break;
                case 2:
                    realizarPrestamo();
                    break;
                case 3:
                    entregarPrestamo();
                    break;
                case 4:
                    consultarPrestamo();
                    break;
                case 5:
                    mostrarEstudianteConMasPrestamos();
                    break;
                case 6:
                    System.out.println("Saliendo de la aplicación...");
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú de la Biblioteca ---");
        System.out.println("1. Crear Libro");
        System.out.println("2. Realizar Préstamo");
        System.out.println("3. Entregar Préstamo");
        System.out.println("4. Consultar Préstamo por Código");
        System.out.println("5. Mostrar Estudiante con Más Préstamos");
        System.out.println("6. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private static void crearLibro() {
        System.out.print("Ingresa el código del libro: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingresa el ISBN del libro: ");
        String isbn = scanner.nextLine();
        System.out.print("Ingresa el autor del libro: ");
        String autor = scanner.nextLine();
        System.out.print("Ingresa el título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingresa la fecha de publicación (YYYY-MM-DD): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());
        System.out.print("Ingresa la cantidad de unidades disponibles: ");
        int unidadesDisponibles = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        biblioteca.crearLibro(codigo, isbn, autor, titulo, fecha, unidadesDisponibles);
    }

    private static void realizarPrestamo() {
        System.out.print("Ingresa el título del libro: ");
        String tituloLibro = scanner.nextLine();
        System.out.print("Ingresa el código del préstamo: ");
        String codigoPrestamo = scanner.nextLine();
        System.out.print("Ingresa la cédula del estudiante: ");
        String cedulaEstudiante = scanner.nextLine();
        System.out.print("Ingresa la cédula del bibliotecario: ");
        String cedulaBibliotecario = scanner.nextLine();
        System.out.print("Ingresa la cantidad de unidades prestadas: ");
        int unidadesPrestadas = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        try {
            biblioteca.crearPrestamo(tituloLibro, codigoPrestamo, cedulaEstudiante, cedulaBibliotecario, unidadesPrestadas);
            System.out.println("Préstamo realizado con éxito.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void entregarPrestamo() {
        System.out.print("Ingresa el código del préstamo: ");
        String codigoPrestamo = scanner.nextLine();
        System.out.print("Ingresa la fecha de entrega (YYYY-MM-DD): ");
        LocalDate fechaEntrega = LocalDate.parse(scanner.nextLine());

        biblioteca.entregarPrestamo(codigoPrestamo, fechaEntrega);
    }

    private static void consultarPrestamo() {
        System.out.print("Ingresa el código del préstamo: ");
        String codigo = scanner.nextLine();
        Prestamo prestamo = biblioteca.consultarPrestamoPorCodigo(codigo);
        if (prestamo != null) {
            System.out.println("Préstamo encontrado: " + prestamo.getCodigo());
        } else {
            System.out.println("Préstamo no encontrado.");
        }
    }

    private static void mostrarEstudianteConMasPrestamos() {
        Estudiante estudiante = biblioteca.mostrarEstudianteConMasPrestamos();
        if (estudiante != null) {
            System.out.println("Estudiante con más préstamos: " + estudiante.getNombre() + " (Préstamos: " + estudiante.getCantPrestamosSolicitados() + ")");
        } else {
            System.out.println("No hay estudiantes registrados.");
        }
    }

    private static void inicializarDatos() {
        // Agregar bibliotecarios y estudiantes para pruebas
        biblioteca.getBibliotecarios().add(new Bibliotecario("Juan Pérez", "123456", "3001234567", "juan@example.com", 2000.0, 0, LocalDate.now()));
        biblioteca.getEstudiantes().add(new Estudiante("Ana Gómez", "789012", "3009876543", "ana@example.com", "Ingeniería", 0));
        biblioteca.getEstudiantes().add(new Estudiante("Luis Torres", "345678", "3005432167", "luis@example.com", "Derecho", 0));
        biblioteca.crearLibro("LIB001", "978-3-16-148410-0", "Autor Ejemplo", "Título Ejemplo", LocalDate.now(), 5);
    }
}
