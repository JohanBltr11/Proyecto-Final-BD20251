package modelo;

public class Empleado {
    private long cedula;
    private String cargo;
    private int IdArea;
    private Area area;
    private Persona persona;

    public Empleado() {
    }

    public Empleado(long cedula, String cargo, Area area) {
        this.cedula = cedula;
        this.cargo = cargo;
        this.area = area;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public int getIdArea() {
        return area != null ? area.getIdArea() : 0;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "cedula=" + cedula +
                ", cargo='" + cargo + '\'' +
                ", area=" + (area != null ? area.getNombreArea() : "Sin asignar") +
                '}';
    }
}
