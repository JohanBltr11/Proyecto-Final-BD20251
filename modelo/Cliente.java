package modelo;

public class Cliente {
    private long cedula;
    private String correoElectronico;

    public Cliente() {
    }

    public Cliente(long cedula, String correoElectronico) {
        this.cedula = cedula;
        this.correoElectronico = correoElectronico;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cedula=" + cedula +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }
}
