/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Register;
import Model.RegisterDB;
import java.sql.SQLException;

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

}
