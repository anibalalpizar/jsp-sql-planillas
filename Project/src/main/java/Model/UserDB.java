package Model;

import DAO.AccesoDatos;
import DAO.Conexion;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.jws.soap.SOAPBinding;

public class UserDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public UserDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);

    }

    //Insertar Nombre en la base de datos
    public void InsertarUser(User pUser) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            User user = new User();
            user = pUser;

            strSQL = "INSERT INTO Usuario(IDUsuario, Salario, Nombre, Apellido1, Apellido2, Telefono) VALUES"
                    + "(" + "'" + user.getIdUsuario() + "'" + ","
                    + "'" + user.getSalario() + "'" + ","
                    + "'" + user.getNombre() + "'" + ","
                    + "'" + user.getApellido1() + "'" + ","
                    + "'" + user.getApellido2() + "'" + ","
                    + "'" + user.getTelefono() + "'" + ")";
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }
    }

    public void DeleteUser(User pUser) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            User user = new User();
            user = pUser;
            strSQL = "DELETE FROM Usuario WHERE IDUsuario = " + user.getIdUsuario();
//                    + "(" + "'" + spre.getIdTipoPlanilla() + "'" + ","
//                    + "'" + spre.getDescripcion() + "'" + ")";

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }

//metodo que se trae toda la lista de Cadidatos
    public LinkedList<User> moTodo() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<User> listaUser = new LinkedList<User>();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select
                    = "SELECT IDUsuario, Salario, Nombre, Apellido1, Apellido2, Telefono FROM Usuario";
            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while (rsPA.next()) {

                int idUsuario = rsPA.getInt("IDUsuario");
                float salario = rsPA.getFloat("Salario");
                String nombre = rsPA.getString("Nombre");
                String apellido1 = rsPA.getString("Apellido1");
                String apellido2 = rsPA.getString("Apellido2");
                int telefono = rsPA.getInt("Telefono");
                //se construye el objeto.
                User perUser = new User(idUsuario, salario, nombre, apellido1, apellido2, telefono);

                listaUser.add(perUser);
            }
            rsPA.close();//se cierra el ResultSeat.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return listaUser;
    }

    public void ChangesUser(User pUser) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            User user = new User();
            user = pUser;
            strSQL = String.format("UPDATE Usuario set Salario = %f, Nombre = '%s', Apellido1 = '%s', Apellido2 = '%s', Telefono = %d where IDUsuario = %d",
                     pUser.salario, pUser.nombre, pUser.apellido1, pUser.apellido2, pUser.telefono, pUser.idUsuario);

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }

 public boolean consultarUser(int numUser) throws SNMPExceptions, SQLException{
           
        boolean existe = false;
        String select="";
         try{
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select="select * from Usuario where IDUsuario="+numUser;
                    
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            if(rsPA.next()){
                
                existe=true;
            }
            
            rsPA.close();
      
            return existe;
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        
    }
}
