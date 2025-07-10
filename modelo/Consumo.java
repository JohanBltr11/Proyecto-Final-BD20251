package modelo;

import java.time.LocalDateTime;

public class Consumo {

    private LocalDateTime fechaHora;
    private int Idservicio;
    private long cedula;

    public Consumo() {
    }

    public Consumo(LocalDateTime fechaHora, int idservicio, long cedula) {
        this.fechaHora = fechaHora;
        this.Idservicio = idservicio;
        this.cedula = cedula;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getIdServicio() {
        return Idservicio;
    }

    public void setIdServicio(int Idservicio) {
        this.Idservicio = Idservicio;
    } 

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }
    public long getCedula() {
        return cedula;
    }
}   




