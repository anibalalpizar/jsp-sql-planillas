package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            strSQL = "INSERT INTO Turno(IDTurno, Descripcion, Borrado) VALUES"
                    + "(" + "'" + wor.getIdTurno() + "'" + ","
                    + "'" + wor.getDescripcion() + "'" + ","
                     + "'" + wor.borrado + "'" + ")";

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

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }

    public void borradoLogicoWork(int idOrden) throws SNMPExceptions {

        String Query = "";
        ArrayList<WorkShift> listaDatosCompra = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "update Turno SET Borrado = 1 where IDTurno = " + idOrden;

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(Query);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }

    }

    public String validar(int idOrden) throws SNMPExceptions {
        String mensaje = "";
        String Query = "";
        ArrayList<WorkShift> listaDatosCompra = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "select * from  Turno where IDTurno = " + idOrden;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {
                int borradoLogico = rsPA.getInt("Borrado");
                if (borradoLogico == 1) {
                    mensaje = "Orden borrada";
                } else {
                    if (borradoLogico == 0) {
                        mensaje = "Existe";
                    }
                }

            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }

        return mensaje;
    }

//Metodo para traer toda la lista de los Turnos
    public LinkedList<WorkShift> TurnosTodos() throws SNMPExceptions, SQLException {

        String select = "";
        LinkedList<WorkShift> listaWorkShift = new LinkedList<WorkShift>();
        try {
            AccesoDatos accesodatos = new AccesoDatos();
            select = "Select IDTurno, Descripcion from Turno Where Borrado = 0 ";

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
    public LinkedList<WorkShift> moTodo() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<WorkShift> listaWork = new LinkedList<WorkShift>();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select
                    = "Select IDTurno, Descripcion from Turno Where Borrado = 0 ";
            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while (rsPA.next()) {

                int idTurno = rsPA.getInt("IDTurno");

                String descripcion = rsPA.getString("Descripcion");

                //se construye el objeto.
                WorkShift perWork = new WorkShift(idTurno, descripcion);

                listaWork.add(perWork);
            }
            rsPA.close();//se cierra el ResultSeat.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return listaWork;
    }

    public void ChangesWorkShift(WorkShift pWork) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            WorkShift wor = new WorkShift();
            wor = pWork;
            strSQL = String.format("UPDATE Turno set Descripcion = '%s' where IDTurno = %d", pWork.descripcion, pWork.idTurno);

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }

    public boolean consultWorkShift(int numWork) throws SNMPExceptions, SQLException {

        boolean existe = false;
        String select = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "select * from Turno where IDTurno=" + numWork;

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            if (rsPA.next()) {

                existe = true;
            }

            rsPA.close();

            return existe;

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }

    }

}
