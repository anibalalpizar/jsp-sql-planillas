package Model;

public class WorkShift {
    
    public int idTurno;
    public String descripcion;
    
    public WorkShift(int idTurno, String descripcion) {
        this.setIdTurno(idTurno);
        this.setDescripcion(descripcion);
    }
    
    public WorkShift() {
    }
    
    public int getIdTurno() {
        return idTurno;
    }
    
    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
