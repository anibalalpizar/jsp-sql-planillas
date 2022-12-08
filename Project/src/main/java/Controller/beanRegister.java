/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.CargoDB;
import Model.Register;
import Model.RegisterDB;
import Model.cargo;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author anibal
 */
public class beanRegister {

    private int idUser;
    private String userName;
    private String password;
    private int status;
    private int idPosition;

    public beanRegister() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(int idPosition) {
        this.idPosition = idPosition;
    }

    public void insertBenefit() throws SNMPExceptions, SQLException {

        Register ben = new Register(idUser, userName, password, status, idPosition);
        RegisterDB benDB = new RegisterDB();

        benDB.insertarUsuario(ben);

    }

    public LinkedList<SelectItem> getListaPosition() throws SNMPExceptions, SQLException {
        String PositionName = "";
        int idPosition = 0;

        LinkedList<cargo> lista = new LinkedList<cargo>();
        CargoDB cDB = new CargoDB();
        lista = cDB.moTodo();

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione el Cargo"));

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            cargo cate = (cargo) iter.next();
            idPosition = cate.getCodigo();
            PositionName = cate.getNombreCargo();
            resultList.add(new SelectItem(idPosition, PositionName));

        }
        return resultList;

    }

    public void limpiarCampos() {
        this.idPosition = 0;
        this.idUser = 0;
        this.password = "";
        this.status = 0;
        this.userName = "";
    }

}
