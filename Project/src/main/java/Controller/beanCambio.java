/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Cambio;
import Model.CambioDB;
import java.sql.SQLException;

/**
 *
 * @author anibal
 */
public class beanCambio {

    private String nombre;
    private String newPassword;
    private String mensaje;

    public beanCambio() {
    }

    public beanCambio(String nombre, String newPassword) {
        this.nombre = nombre;
        this.newPassword = newPassword;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void changesUser() throws SNMPExceptions, SQLException {

        Cambio user = new Cambio(nombre, newPassword);
        CambioDB userDB = new CambioDB();

        try {
            userDB.ChangesUser(user);
            this.mensaje = "Actulizacion de contraseña correcta";

        } catch (Exception e) {
            this.mensaje = "Error al encontrar el usuario y/o actulizar tu contraseña";
        }

    }

}
