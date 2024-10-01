package Model;



public class DetallesPrestamo {
    private Double subTotalPrestamo;
    private int unidadesPrestadas ;
    
    public DetallesPrestamo(Double subTotalPrestamo, int unidadesPrestadas) {
        this.subTotalPrestamo = subTotalPrestamo;
        this.unidadesPrestadas = unidadesPrestadas;
    }

    public Double getSubTotalPrestamo() {
        return subTotalPrestamo;
    }

    public void setSubTotalPrestamo(Double subTotalPrestamo) {
        this.subTotalPrestamo = subTotalPrestamo;
    }

    public int getUnidadesPrestadas() {
        return unidadesPrestadas;
    }

    public void setUnidadesPrestadas(int unidadesPrestadas) {
        this.unidadesPrestadas = unidadesPrestadas;
    }
    
    public void prestarLibro(){
        
    }

    
    
    
    
    
    

    
}
