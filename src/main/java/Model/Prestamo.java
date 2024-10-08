package Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Prestamo {
    private String codigo;
    private Double costoTotal;
    private DetallesPrestamo detallesPrestamos;
    
    public Prestamo(String codigo, Double costoTotal) {
        this.codigo = codigo;
        this.costoTotal = costoTotal;
    }
    
   public DetallesPrestamo getDetallesPrestamos(){
    return detallesPrestamos;
   }

   public String getCodigo() {
    return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public void setDetallesPrestamos(DetallesPrestamo detallesPrestamos) {
        this.detallesPrestamos = detallesPrestamos;
    }

    // calcular costo toltal del prestamo

    public Double getCostoTotal(){
        int diasPrestamo=calcularDiasPrestamo();
        Double subTotal=detallesPrestamos.getSubTotalPrestamo();
        costoTotal= subTotal * diasPrestamo;
        return costoTotal;
    }

    private int calcularDiasPrestamo(){
        LocalDate fechaPrestamo=detallesPrestamos.getFechaPrestamo();
        LocalDate fechaEntrega= detallesPrestamos.getFechaEntrega();
        return (int) ChronoUnit.DAYS.between(fechaPrestamo, fechaEntrega);
    }
}
