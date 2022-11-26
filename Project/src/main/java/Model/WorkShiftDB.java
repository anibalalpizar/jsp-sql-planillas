package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.naming.NamingException;

public class WorkShiftDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public WorkShiftDB() {

        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);

    }

    public void InsertWorkShift(WorkShift pWork) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            WorkShift wor = new WorkShift();
            wor = pWork;
            strSQL = "INSERT INTO Turno(IDTurno, Descripcion) VALUES"
                    + "(" + "'" + wor.getIdTurno() + "'" + ","
                    + "'" + wor.getDescripcion() + "'" + ")";

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }

    public void DeleteWorkShift(WorkShift pWork) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            WorkShift wor = new WorkShift();
            wor = pWork;
            strSQL = "DELETE FROM Turno WHERE IDTurno = " + wor.getIdTurno();
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

//Metodo para traer toda la lista de los Turnos
    public LinkedList<WorkShift> TurnosTodos() throws SNMPExceptions, SQLException {

        String select = "";
        LinkedList<WorkShift> listaWorkShift = new LinkedList<WorkShift>();
        try {
            AccesoDatos accesodatos = new AccesoDatos();
            select = "Select IDTurno, Descripcion from Turno";
            ResultSet rsPA = accesodatos.ejecutaSQLRetornaRS(select);
            while (rsPA.next()) {
                int idTurno = rsPA.getInt("IDTurno");
                String descripcion = rsPA.getString("Descripcion");

                WorkShift orkShift = new WorkShift(idTurno, descripcion);

                listaWorkShift.add(orkShift);

            }

            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (SNMPExceptions | ClassNotFoundException | NamingException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return listaWorkShift;

    }

//metodo que se trae toda la lista de Cadidatos
    public LinkedList<WorkShift> moTodo() throws SNMPExceptions, SQLException{
        String select= "";
        LinkedList<WorkShift> listaWork= new LinkedList<WorkShift>();
        
        try{
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select=
                    "SELECT IDTurno, Descripcion FROM Turno";
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                int idTurno= rsPA.getInt("IDTurno");
                
                String descripcion = rsPA.getString("Descripcion");
                
                //se construye el objeto.
                WorkShift perWork= new WorkShift(idTurno, descripcion);
                
                listaWork.add(perWork);
            }
            rsPA.close();//se cierra el ResultSeat.
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaWork;
    }
    


    public void ChangesWorkShift(WorkShift pWork) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {
//UPDATE Usuario set Nombre ='Alejandro' WHERE IDUsuario = 601110222

            WorkShift wor = new WorkShift();
            wor = pWork;
             strSQL= String.format("UPDATE Turno set Descripcion = '%s' where IDTurno = %d", pWork.descripcion, pWork.idTurno);
//            strSQL = "UPDATE Turno set Descripcion="+ wor.getDescripcion()
//                     +" WHERE IDTurno=" + wor.getIdTurno();
//                    + "(" + "'" + wor.getIdTurno() + "'" + ","
//                    + "'" + wor.getDescripcion() + "'" + ")";

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }


}
