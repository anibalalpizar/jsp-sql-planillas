package Model;

public class Benefit {

    public int idBeneficios;
    public String descripcion;
    public float porcentaje;

    public Benefit(int idBeneficios, String descripcion, float porcentaje) {
        this.setIdBeneficios(idBeneficios);
        this.setDescripcion(descripcion);
        this.setPorcentaje(porcentaje);
    }

    public Benefit() {

    }

    public int getIdBeneficios() {
        return idBeneficios;
    }

    public void setIdBeneficios(int idBeneficios) {
        this.idBeneficios = idBeneficios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

}
