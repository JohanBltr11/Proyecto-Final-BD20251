package modelo;

public class Persona {
    private long cedula;
    private String primerN;
    private String segundoN;
    private String primerA;
    private String segundoA;
    private String carrera;
    private String calle;
    private String numero;

    public Persona() {
    }

    public Persona(long cedula, String primerN, String segundoN, String primerA,
            String segundoA, String carrera, String calle, String numero) {
        this.cedula = cedula;
        this.primerN = primerN;
        this.segundoN = segundoN;
        this.primerA = primerA;
        this.segundoA = segundoA;
        this.carrera = carrera;
        this.calle = calle;
        this.numero = numero;
    }

    // Getters y setters
    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getPrimerN() {
        return primerN;
    }

    public void setPrimerN(String primerN) {
        this.primerN = primerN;
    }

    public String getSegundoN() {
        return segundoN;
    }

    public void setSegundoN(String segundoN) {
        this.segundoN = segundoN;
    }

    public String getPrimerA() {
        return primerA;
    }

    public void setPrimerA(String primerA) {
        this.primerA = primerA;
    }

    public String getSegundoA() {
        return segundoA;
    }

    public void setSegundoA(String segundoA) {
        this.segundoA = segundoA;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
