package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class LibroPagoDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public LibroPagoDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    //Insertar Nombre en la base de datos
    public void InsertarFactura(LibroPago pLibro) throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {
            LibroPago libro = new LibroPago();
            libro = pLibro;

            strSQL = "INSERT INTO LibroPago(IDPlanilla, IDCategoriaPago, IDBeneficio, IDUsuario, CantHoras) VALUES"
                  //  + "(" + "'" + libro.getIdFactura() + "'" + ","
                    +"(" + "'" + libro.getIdPlanilla() + "'" + ","
                    + "'" + libro.getCategoriaPago() + "'" + ","
                    + "'" + libro.getIdBeneficio() + "'" + ","
                    + "'" + libro.getIdUsuario() + "'" + ","
                    + "'" + libro.getCantHoras() + "'" + ")";

            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }
    }

    public void ChangesSalarioBruto(LibroPago pLibro) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            LibroPago libro = new LibroPago();
            libro = pLibro;
            strSQL = String.format("update LibroPago set SalarioBruto = u.Salario + (c.Precio * l.CantHoras)\n"
                    + "from Usuario u , CategoriaPago c, LibroPago L where L.IDUsuario = u.IDUsuario AND L.IDCategoriaPago = C.IDCategoriaPago\n"
                    + "AND L.IDFactura =" + pLibro.getIdFactura());

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }

    public void ChangesSalarioNeto(LibroPago pLibro) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {
            LibroPago libro = new LibroPago();
            libro = pLibro;
            strSQL = String.format("update LibroPago set SalarioNeto = L.SalarioBruto - B.Porcentaje\n"
                    + "from Beneficio B ,  LibroPago L where L.IDBeneficio = B.IDBeneficios AND  L.IDFactura =" + pLibro.getIdFactura());

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }

    public LinkedList<LibroPago> moTodo() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<LibroPago> listaPago = new LinkedList<LibroPago>();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select
                    = "SELECT C.IDFactura, C.IDPlanilla,u.Nombre, P.Descripcion, O.DescripcionBene, c.SalarioBruto, c.SalarioNeto\n"
                    + "FROM LibroPago C, Beneficio O, CategoriaPago P, Usuario u\n"
                    + "WHERE C.IDCategoriaPago = P.IDCategoriaPago and C.IDBeneficio = O.IDBeneficios and c.IDUsuario = u.IDUsuario";
            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while (rsPA.next()) {

                int idFactura = rsPA.getInt("IDFactura");
                int idPlanilla = rsPA.getInt("IDPlanilla");
                String nombre = rsPA.getString("Nombre");
                String descCate = rsPA.getString("Descripcion");
                String descBene = rsPA.getString("DescripcionBene");

                float ben = rsPA.getFloat("SalarioBruto");
                float pay = rsPA.getFloat("SalarioNeto");

                //se construye el objeto.
                LibroPago perLirbo = new LibroPago(idFactura, idPlanilla, nombre, descCate, descBene, ben, pay);

                listaPago.add(perLirbo);
            }
            rsPA.close();//se cierra el ResultSeat.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return listaPago;
    }

    public boolean consultLibroPago(int numPago) throws SNMPExceptions, SQLException {

        boolean existe = false;
        String select = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "select * from LibroPago where IDFactura=" + numPago;

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
