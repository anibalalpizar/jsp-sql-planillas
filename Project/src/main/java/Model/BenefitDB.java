package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class BenefitDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public BenefitDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);

    }

    public void InsertBenefit(Benefit pBen) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            Benefit ben = new Benefit();
            ben = pBen;
            strSQL = "INSERT INTO Beneficio(IDBeneficios, DescripcionBene, Porcentaje, Borrado) VALUES"
                    + "(" + "'" + ben.getIdBeneficios() + "'" + ","
                    + "'" + ben.getDescripcion() + "'" + ","
                    + "'" + ben.getPorcentaje() + "'" + ","
                    + "'" + ben.borrado + "'" + ")";

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }

    public void DeleteBenefit(Benefit pBen) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            Benefit ben = new Benefit();
            ben = pBen;
            strSQL = "DELETE FROM Beneficio WHERE IDBeneficios = " + ben.getIdBeneficios();

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
        ArrayList<Benefit> listaDatosCompra = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "update Beneficio SET Borrado = 1 where IDBeneficios = " + idOrden;

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
        ArrayList<Benefit> listaDatosCompra = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "select * from  Beneficio where IDBeneficios = " + idOrden;

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

    public void ChangesBenefit(Benefit pBen) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            Benefit ben = new Benefit();
            ben = pBen;
            strSQL = String.format("UPDATE Beneficio set DescripcionBene = '%s', Porcentaje = %f where IDBeneficios = %d", pBen.descripcion, pBen.porcentaje, pBen.idBeneficios);

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }

//metodo que se trae toda la lista de Cadidatos
    public LinkedList<Benefit> moTodo() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Benefit> listaBenefit = new LinkedList<Benefit>();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select
                    = "SELECT IDBeneficios, DescripcionBene, Porcentaje FROM Beneficio Where Borrado = 0";
            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while (rsPA.next()) {

                int idBeneficio = rsPA.getInt("IDBeneficios");
                String descripcion = rsPA.getString("DescripcionBene");
                float precio = rsPA.getFloat("Porcentaje");
                //se construye el objeto.
                Benefit perBen = new Benefit(idBeneficio, descripcion, precio);

                listaBenefit.add(perBen);
            }
            rsPA.close();//se cierra el ResultSeat.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return listaBenefit;
    }

    public boolean consultBenefit(int numIdBenefit) throws SNMPExceptions, SQLException {

        boolean existe = false;
        String select = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "select * from Beneficio where IDBeneficios=" + numIdBenefit;

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
