package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            strSQL = "INSERT INTO Planilla(IDPlanilla, Descripcion, IDTurno, IDTipoPlanilla, FechaInico, FechaFinal, FechaPago) VALUES"
                    + "(" + "'" + pla.getIdPlanilla() + "'" + ","
                    + "'" + pla.getDescripcion() + "'" + ","
                    + "'" + pla.getIdTurno() + "'" + ","
                    + "'" + pla.getIdTipoPlanilla() + "'" + ","
                    + "'" + pla.getFechaInicio() + "'" + ","
                    + "'" + pla.getFechaFinal() + "'" + ","
                    + "'" + pla.getFechaPago() + "'" + ")";

            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }
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
                    = "SELECT IDPlanilla, Descripcion, IDTurno, IDTipoPlanilla, FechaInico, FechaFinal, FechaPago FROM Planilla";
            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while (rsPA.next()) {

                int idPlanilla = rsPA.getInt("IDPlanilla");
                String descripcion= rsPA.getString("Descripcion");
                int idTurno = rsPA.getInt("IDTurno");
                int idTipoPlanilla = rsPA.getInt("IDTipoPlanilla");
                String fechaInicio = rsPA.getString("FechaInico");
                String fechaFinal = rsPA.getString("FechaFinal");
                String fechaPago = rsPA.getString("FechaPago");
//Date fechaInicio= rsPA.getDate("FechaInico");
//Date fechaFinal= rsPA.getDate("FechaFinal");
//Date fechaPago= rsPA.getDate("FechaPago");
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

}
