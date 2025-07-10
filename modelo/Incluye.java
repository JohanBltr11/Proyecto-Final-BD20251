package modelo;

import java.sql.Timestamp;

public class Incluye {
    private int idHabitacion;
    private long cedula;
    private Timestamp fechaHoraLlegada;
    private int idServicio;

    public Incluye() {
    }

    public Incluye(int idHabitacion, long cedula, Timestamp fechaHoraLlegada, int idServicio) {
        this.idHabitacion = idHabitacion;
        this.cedula = cedula;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.idServicio = idServicio;
    }

    // Getters y setters
    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public Timestamp getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(Timestamp fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }
}
