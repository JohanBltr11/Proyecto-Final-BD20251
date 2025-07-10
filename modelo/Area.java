package modelo;

public class Area {
    private int idArea;
    private String descripcionArea;
    private String nombreArea;

    public Area() {
    }

    public Area(int idArea, String descripcionArea, String nombreArea) {
        this.idArea = idArea;
        this.descripcionArea = descripcionArea;
        this.nombreArea = nombreArea;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getDescripcionArea() {
        return descripcionArea;
    }

    public void setDescripcionArea(String descripcionArea) {
        this.descripcionArea = descripcionArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    @Override
    public String toString() {
        return "Area{" +
                "idArea=" + idArea +
                ", descripcionArea='" + descripcionArea + '\'' +
                ", nombreArea='" + nombreArea + '\'' +
                '}';
    }
}
