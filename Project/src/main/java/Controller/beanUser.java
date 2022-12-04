package Controller;

import DAO.SNMPExceptions;
import Model.SpreadsheetType;
import Model.SpreadsheetTypeDB;
import Model.User;
import Model.UserDB;
import Model.WorkShift;
import Model.WorkShiftDB;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

public class beanUser implements Serializable {

    private int idUsuario;
    private float salario;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int telefono;
    private String mensaje;
    private LinkedList<User> listaUser = new LinkedList<User>();

    public LinkedList<User> getListaUser() {
        return listaUser;
    }

    public void setListaUser(LinkedList<User> listaUser) {
        this.listaUser = listaUser;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void insertUser() throws SNMPExceptions, SQLException {

        User user = new User(idUsuario, salario, nombre, apellido1, apellido2, telefono);
        UserDB userDB = new UserDB();

        if (userDB.consultarUser(idUsuario) == true) {
            setMensaje("Este Usuario ya fue creado");
        } else {
            userDB.InsertarUser(user);
             setMensaje("Usuario Creado Correctamente");
        }

    }

    public void changesUser() throws SNMPExceptions, SQLException {
        User user = new User(idUsuario, salario, nombre, apellido1, apellido2, telefono);
        UserDB userDB = new UserDB();

        userDB.ChangesUser(user);

    }

    public void deleteUser() throws SNMPExceptions, SQLException {

        User user = new User(idUsuario, salario, nombre, apellido1, apellido2, telefono);
        UserDB userDB = new UserDB();

        userDB.DeleteUser(user);

    }

    public LinkedList<SelectItem> getListaTur() throws SNMPExceptions, SQLException {
        String descripcion = "";
        int idTurno = 0;

        LinkedList<WorkShift> lista = new LinkedList<WorkShift>();
        WorkShiftDB wDB = new WorkShiftDB();
        lista = wDB.TurnosTodos();

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione Turno"));

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            WorkShift work = (WorkShift) iter.next();
            idTurno = work.getIdTurno();
            descripcion = work.getDescripcion();
            resultList.add(new SelectItem(idTurno, descripcion));

        }
        return resultList;

    }

    public LinkedList<SelectItem> getListaTipoPlanilla() throws SNMPExceptions, SQLException {
        int idTipoPlanilla = 0;
        String descripcion = "";

        LinkedList<SpreadsheetType> lista = new LinkedList<SpreadsheetType>();
        SpreadsheetTypeDB sDB = new SpreadsheetTypeDB();
        lista = sDB.PlanillaTodos();

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione Tipo Planilla"));

        for (Iterator iter = lista.iterator(); iter.hasNext();) {
            SpreadsheetType spre = (SpreadsheetType) iter.next();
            idTipoPlanilla = spre.getIdTipoPlanilla();
            descripcion = spre.getDescripcion();
            resultList.add(new SelectItem(idTipoPlanilla, descripcion));

        }
        return resultList;

    }

    public void mostrarLista() throws SNMPExceptions, SQLException {
        UserDB userDb = new UserDB();

        this.setListaUser(userDb.moTodo());
    }

    public void limpiarCampos() {
        this.setIdUsuario(0);
        this.setSalario(0);
        this.setNombre("");
        this.setApellido1("");
        this.setApellido2("");
        this.setTelefono(0);

    }
}
