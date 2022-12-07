/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author anibal
 */
public class CambioDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public CambioDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);

    }

    public void ChangesUser(Cambio pUser) throws SNMPExceptions, SQLException {

        try {

            Cambio user = new Cambio();
            user = pUser;
            String strSQL = "UPDATE UsuarioSeguridad SET PASSWORD = ENCRYPTBYPASSPHRASE('password', '" + pUser.getNewPassword() + "') WHERE USERNAME = '" + pUser.getNombre() + "'";
            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }
}
