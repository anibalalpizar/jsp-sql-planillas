package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class PlanillaDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public PlanillaDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public void InsertarPlanilla(Planilla pPlanilla) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            Planilla pla = new Planilla();
            pla = pPlanilla;

            strSQL = "INSERT INTO Planilla(IDPlanilla, Descripcion, IDTurno, IDTipoPlanilla, FechaInico, FechaFinal, FechaPago, Borrado) VALUES"
                    + "(" + "'" + pla.getIdPlanilla() + "'" + ","
                    + "'" + pla.getDescripcion() + "'" + ","
                    + "'" + pla.getIdTurno() + "'" + ","
                    + "'" + pla.getIdTipoPlanilla() + "'" + ","
                    + "'" + pla.getFechaInicio() + "'" + ","
                    + "'" + pla.getFechaFinal() + "'" + ","
                    + "'" + pla.getFechaPago() + "'" + ","
                     + "'" + pla.borrado + "'" + ")";

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
        ArrayList<Planilla> listaDatosCompra = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "update Planilla SET Borrado = 1 where IDPlanilla = " + idOrden;

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
        ArrayList<Planilla> listaDatosCompra = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "select * from  Planilla where IDPlanilla = " + idOrden;

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
//metodo que se trae toda la lista de Cadidatos
    public LinkedList<Planilla> moTodo() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Planilla> listaPlani = new LinkedList<Planilla>();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select
                    = "SELECT IDPlanilla, Descripcion, IDTurno, IDTipoPlanilla, FechaInico, FechaFinal, FechaPago FROM Planilla where Borrado = 0";
            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while (rsPA.next()) {

                int idPlanilla = rsPA.getInt("IDPlanilla");
                String descripcion = rsPA.getString("Descripcion");
                int idTurno = rsPA.getInt("IDTurno");
                int idTipoPlanilla = rsPA.getInt("IDTipoPlanilla");
                String fechaInicio = rsPA.getString("FechaInico");
                String fechaFinal = rsPA.getString("FechaFinal");
                String fechaPago = rsPA.getString("FechaPago");

                //se construye el objeto.
                Planilla perPlanilla = new Planilla(idPlanilla, descripcion, idTurno, idTipoPlanilla, fechaInicio, fechaFinal, fechaPago);

                listaPlani.add(perPlanilla);
            }
            rsPA.close();//se cierra el ResultSeat.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return listaPlani;
    }

    public boolean consultPlanilla(int numPlanilla) throws SNMPExceptions, SQLException {

        boolean existe = false;
        String select = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "select * from Planilla where IDPlanilla=" + numPlanilla;

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

public void ChangesPlanillas(Planilla pPlani) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            Planilla plan = new Planilla();
            plan = pPlani;
            strSQL = String.format("UPDATE Planilla set Descripcion = '%s', IDTurno = %d, IDTipoPlanilla = %d, FechaInico = '%s', FechaFinal = '%s', FechaPago = '%s'  where IDPlanilla = %d",
                 pPlani.descripcion, pPlani.idTurno, pPlani.idTipoPlanilla,pPlani.fechaInicio, pPlani.fechaFinal, pPlani.fechaPago, pPlani.idPlanilla);

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }
}
