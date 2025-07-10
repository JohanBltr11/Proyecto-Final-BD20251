package modelo;

public class Servicio {

    private int idServicio;
    private int costo;
    private String nombreDescriptivoServicio;
    private String detalleServicio;

    public Servicio() {
    }

    public Servicio(int idServicio, int costo, String nombreDescriptivoServicio, String detalleServicio) {
        this.idServicio = idServicio;
        this.costo = costo;
        this.nombreDescriptivoServicio = nombreDescriptivoServicio;
        this.detalleServicio = detalleServicio;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getNombreDescriptivoServicio() {
        return nombreDescriptivoServicio;
    }

    public void setNombreDescriptivoServicio(String nombreDescriptivoServicio) {
        this.nombreDescriptivoServicio = nombreDescriptivoServicio;
    }

    public String getDetalleServicio() {
        return detalleServicio;
    }

    public void setDetalleServicio(String detalleServicio) {
        this.detalleServicio = detalleServicio;
    }
}
