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
public class RegisterDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public RegisterDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public RegisterDB() {
        super();
    }

    public void insertarUsuario(Register pRegister) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            Register ben = new Register();
            ben = pRegister;
            strSQL = "insert into UsuarioSeguridad(UserName, Password, Status, IdPosition) "
                    + "values('" + ben.getUserName()
                    + "', ENCRYPTBYPASSPHRASE('password', '" + ben.getPassword()+ "')"
                    + ", '" + ben.getStatus() + "', '" + ben.getIdPosition() + "')";

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }

}
