package modelo;

import java.time.LocalDateTime;

public class Reserva {

    private int tiempoMaxCancelacion;
    private LocalDateTime fechaHoraLlegada;
    private LocalDateTime fechaHoraSalida;
    private int valorReserva;

    private int idHabitacion;
    private long cedula;
    public Reserva() {
    }

    public Reserva(int tiempoMaxCancelacion, LocalDateTime fechaHoraLlegada, LocalDateTime fechaHoraSalida, int valorReserva, int idHabitacion, long cedula) {
        this.tiempoMaxCancelacion = tiempoMaxCancelacion;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.fechaHoraSalida = fechaHoraSalida;
        this.valorReserva = valorReserva;
        this.idHabitacion = idHabitacion;
        this.cedula = cedula;
    }

    public int getTiempoMaxCancelacion() {
        return tiempoMaxCancelacion;
    }

    public void setTiempoMaxCancelacion(int tiempoMaxCancelacion) {
        this.tiempoMaxCancelacion = tiempoMaxCancelacion;
    }

    public LocalDateTime getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public int getValorReserva() {
        return valorReserva;
    }

    public void setValorReserva(int valorReserva) {
        this.valorReserva = valorReserva;
    }


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

    @Override
    public String toString() {
        return "Reserva{" +
                "tiempoMaxCancelacion=" + tiempoMaxCancelacion +
                ", fechaHoraLlegada=" + fechaHoraLlegada +
                ", fechaHoraSalida=" + fechaHoraSalida +
                ", valorReserva=" + valorReserva +
                ", idHabitacion=" + idHabitacion +
                ", cedula=" + cedula +
                '}';
    }
}

