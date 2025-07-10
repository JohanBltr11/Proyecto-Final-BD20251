package modelo;

public class Telefono {
    private long cedula;
    private String telefono;

    public Telefono(long cedula, String telefono) {
        this.cedula = cedula;
        this.telefono = telefono;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
