package modelo;

public class Habitacion {

    private int idHabitacion;
    private String tipoHabitacion;
    private int precioNoche;
    private boolean estadoMantenimiento;
    private boolean estadoDisponibilidad;

    public Habitacion() {
    }

    public Habitacion(int idHabitacion, String tipoHabitacion, int precioNoche, boolean estadoMantenimiento,
            boolean estadoDisponibilidad) {
        this.idHabitacion = idHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.precioNoche = precioNoche;
        this.estadoMantenimiento = estadoMantenimiento;
        this.estadoDisponibilidad = estadoDisponibilidad;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(int precioNoche) {
        this.precioNoche = precioNoche;
    }

    public boolean isEstadoMantenimiento() {
        return estadoMantenimiento;
    }

    public void setEstadoMantenimiento(boolean estadoMantenimiento) {
        this.estadoMantenimiento = estadoMantenimiento;
    }

    public boolean isEstadoDisponibilidad() {
        return estadoDisponibilidad;
    }

    public void setEstadoDisponibilidad(boolean estadoDisponibilidad) {
        this.estadoDisponibilidad = estadoDisponibilidad;
    }
}
