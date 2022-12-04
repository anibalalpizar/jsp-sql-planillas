package Controller;

import DAO.SNMPExceptions;
import Model.WorkShift;
import Model.WorkShiftDB;
import java.sql.SQLException;
import java.util.LinkedList;

public class beanWorkShift {

    private int idTurno;
    private String descripcion;
    private String mensaje;
    private LinkedList<WorkShift> listaWorkShift = new LinkedList<WorkShift>();

    public beanWorkShift() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LinkedList<WorkShift> getListaWorkShift() {
        return listaWorkShift;
    }

    public void setListaWorkShift(LinkedList<WorkShift> listaWorkShift) {
        this.listaWorkShift = listaWorkShift;
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

    public void insertWorkShift() throws SNMPExceptions, SQLException {

        WorkShift wor = new WorkShift(idTurno, descripcion);
        WorkShiftDB worDB = new WorkShiftDB();

        if (worDB.consultWorkShift(idTurno) == true) {
            setMensaje("Este Turno ya fue creado");
        } else {
            worDB.InsertWorkShift(wor);
            setMensaje("Turno Creado Correctamente");
        }

    }

    public void deleteWorkShift() throws SNMPExceptions, SQLException {
        WorkShift wor = new WorkShift(idTurno, descripcion);
        WorkShiftDB worDB = new WorkShiftDB();

        worDB.DeleteWorkShift(wor);

    }

    public void changesWorkShift() throws SNMPExceptions, SQLException {
        WorkShift wor = new WorkShift(idTurno, descripcion);
        WorkShiftDB worDB = new WorkShiftDB();

        worDB.ChangesWorkShift(wor);

    }

    public void mostrarLista() throws SNMPExceptions, SQLException {
        WorkShiftDB workDB = new WorkShiftDB();

        this.setListaWorkShift(workDB.moTodo());
    }

    public void limpiarCampos() {
        this.setIdTurno(0);
        this.setDescripcion("");

    }

}
