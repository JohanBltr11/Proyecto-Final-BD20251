package modelo;

public class Brinda {
    private int idServicio;
    private long cedula;

    public Brinda() {
    }

    public Brinda(int idServicio, long cedula) {
        this.idServicio = idServicio;
        this.cedula = cedula;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }
}
