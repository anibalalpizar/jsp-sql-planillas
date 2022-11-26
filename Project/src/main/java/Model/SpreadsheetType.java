package Model;

public class SpreadsheetType {

    public int idTipoPlanilla;
    public String descripcion;

    public SpreadsheetType(int idTipoPlanilla, String descripcion) {
        this.setIdTipoPlanilla(idTipoPlanilla);
        this.setDescripcion(descripcion);
    }

    public SpreadsheetType() {
    }

    public int getIdTipoPlanilla() {
        return idTipoPlanilla;
    }

    public void setIdTipoPlanilla(int idTipoPlanilla) {
        this.idTipoPlanilla = idTipoPlanilla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
