package Model;

import java.util.Date;

public class Planilla {

    public int idPlanilla;
    public String descripcion;
    public int idTurno;
    public int idTipoPlanilla;
    public String fechaInicio;
    public String fechaFinal;
    public String fechaPago;

    public Planilla(int idPlanilla,String descripcion, int idTurno, int idTipoPlanilla, String fechaInicio,String  fechaFinal, String  fechaPago) {
        this.setIdPlanilla(idPlanilla);
        this.setDescripcion(descripcion);
        this.setIdTurno(idTurno);
        this.setIdTipoPlanilla(idTipoPlanilla);
        this.setFechaInicio(fechaInicio);
        this.setFechaFinal(fechaFinal);
        this.setFechaPago(fechaPago);
    }

    public Planilla() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public int getIdTipoPlanilla() {
        return idTipoPlanilla;
    }

    public void setIdTipoPlanilla(int idTipoPlanilla) {
        this.idTipoPlanilla = idTipoPlanilla;
    }



    public int getIdPlanilla() {
        return idPlanilla;
    }

    public void setIdPlanilla(int idPlanilla) {
        this.idPlanilla = idPlanilla;
    }

    public String  getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String  fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String  getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String  fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String  getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String  fechaPago) {
        this.fechaPago = fechaPago;
    }

}