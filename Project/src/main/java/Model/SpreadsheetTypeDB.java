package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.naming.NamingException;

public class SpreadsheetTypeDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public SpreadsheetTypeDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    //Insertar Tipos Planillas
    public void InsertSpreadsheet(SpreadsheetType pSpreands) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            SpreadsheetType spre = new SpreadsheetType();
            spre = pSpreands;
            strSQL = "INSERT INTO TipoPlanilla(IDTipoPlanilla, Descripcion, Borrado) VALUES"
                    + "(" + "'" + spre.getIdTipoPlanilla() + "'" + ","
                    + "'" + spre.getDescripcion() + "'" + ","
                    + "'" + spre.borrado + "'" + ")";

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }

    //Metodo que elimina los datos del tipo planilla
    public void DeleteSpreadsheet(SpreadsheetType pSpreands) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            SpreadsheetType spre = new SpreadsheetType();
            spre = pSpreands;
            strSQL = "DELETE FROM TipoPlanilla WHERE IDTipoPlanilla = " + spre.getIdTipoPlanilla();
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

    public void borradoLogico(int idOrden) throws SNMPExceptions {

        String Query = "";
        ArrayList<SpreadsheetType> listaDatosCompra = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "update TipoPlanilla SET Borrado = 1 where IDTipoPlanilla = " + idOrden;

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
        ArrayList<SpreadsheetType> listaDatosCompra = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "select * from  TipoPlanilla where IDTipoPlanilla = " + idOrden;

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

//Metodo para traer toda la lista de los tipos planilla
    public LinkedList<SpreadsheetType> PlanillaTodos() throws SNMPExceptions, SQLException {

        String select = "";
        LinkedList<SpreadsheetType> listaSpreadsheet = new LinkedList<SpreadsheetType>();
        try {

            AccesoDatos datos = new AccesoDatos();

            select = "SELECT IDTipoPlanilla, Descripcion FROM TipoPlanilla Where Borrado = 0";
            ResultSet rsPA = datos.ejecutaSQLRetornaRS(select);
            while (rsPA.next()) {
                int idTipoPlanilla = rsPA.getInt("IDTipoPlanilla");
                String descripcion = rsPA.getString("Descripcion");

                SpreadsheetType spreadsheetType = new SpreadsheetType(idTipoPlanilla, descripcion);

                listaSpreadsheet.add(spreadsheetType);

            }

            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (SNMPExceptions | ClassNotFoundException | NamingException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return listaSpreadsheet;

    }

//metodo que se trae toda la lista de Cadidatos
    public LinkedList<SpreadsheetType> moTodo() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<SpreadsheetType> listaSpreand = new LinkedList<SpreadsheetType>();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select
                    = "SELECT IDTipoPlanilla, Descripcion FROM TipoPlanilla Where Borrado = 0";
            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while (rsPA.next()) {

                int idTipoPlanilla = rsPA.getInt("IDTipoPlanilla");

                String descripcion = rsPA.getString("Descripcion");

                //se construye el objeto.
                SpreadsheetType perSpreand = new SpreadsheetType(idTipoPlanilla, descripcion);
                listaSpreand.add(perSpreand);
            }
            rsPA.close();//se cierra el ResultSeat.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return listaSpreand;
    }

//Modificar SpreandHeet
    public void ChangesSpreandsHeet(SpreadsheetType pSpeands) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            SpreadsheetType spe = new SpreadsheetType();
            spe = pSpeands;
            strSQL = String.format("UPDATE TipoPlanilla set Descripcion = '%s' where IDTipoPlanilla = %d", pSpeands.descripcion, pSpeands.idTipoPlanilla);

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }

    public boolean consultSpreandsheetType(int numIdSpreeand) throws SNMPExceptions, SQLException {

        boolean existe = false;
        String select = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "select * from TipoPlanilla where IDTipoPlanilla=" + numIdSpreeand;

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
