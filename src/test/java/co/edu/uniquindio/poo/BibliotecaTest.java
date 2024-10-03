/**
 * Clase para probar el funcionamiento del código
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Model.Bibliotecario;
import Model.Prestamo;
import Model.Biblioteca;
/**
 * Unit test for simple App.
 */
public class BibliotecaTest {
    private static final Logger LOG = Logger.getLogger(AppTest.class.getName());

    /**
     * Rigorous Test :-)
     */
    @Test
    public void crearPrestamo() {
    
        Biblioteca biblioteca = new Biblioteca("CRAI", null, null, null, null, null);
        biblioteca.crearPrestamo("Cien años", "001", "123321", "321123", 0);
        Assertions.assertEquals(biblioteca, biblioteca);
        LOG.info("Iniciado test shouldAnswerWithTrue");
        assertTrue(true);
        LOG.info("Finalizando test shouldAnswerWithTrue");
    }
}