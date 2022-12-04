package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class CategoryPaymentDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public CategoryPaymentDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public void InsertCategoryPayment(CategoryPayment pCat) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            CategoryPayment cat = new CategoryPayment();
            cat = pCat;
            strSQL = "INSERT INTO CategoriaPago(IDCategoriaPago, Descripcion, Precio) VALUES"
                    + "(" + "'" + cat.getIdCategoriaPago() + "'" + ","
                    + "'" + cat.getDescripcion() + "'" + ","
                    + "'" + cat.getPrecio() + "'" + ")";

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }

    public void DeleteCategoryPayment(CategoryPayment pCat) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            CategoryPayment cat = new CategoryPayment();
            cat = pCat;
            strSQL = "DELETE FROM CatgoriaPago WHERE IDCategoriaPago = " + cat.getIdCategoriaPago();

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }

    public void ChangesCategoryPayment(CategoryPayment pCat) throws SNMPExceptions, SQLException {

        String strSQL = "";

        try {

            CategoryPayment cat = new CategoryPayment();
            cat = pCat;
            strSQL = String.format("UPDATE CategoriaPago set Descripcion = '%s', Precio = %f where IDCategoriaPago = %d", pCat.descripcion, pCat.precio, pCat.idCategoriaPago);

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }

    }
    public LinkedList<CategoryPayment> moTodo() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<CategoryPayment> listaCategory = new LinkedList<CategoryPayment>();

        try {
            
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select
                    = "SELECT IDCategoriaPago, Descripcion, Precio FROM CategoriaPago";
            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while (rsPA.next()) {

                int idCategoriaPago = rsPA.getInt("IDCategoriaPago");
                String descripcion = rsPA.getString("Descripcion");
                float precio = rsPA.getFloat("Precio");
                //se construye el objeto.
                CategoryPayment perCat = new CategoryPayment(idCategoriaPago, descripcion, precio);
                listaCategory.add(perCat);
            }
            rsPA.close();//se cierra el ResultSeat.

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return listaCategory;
    }

    public boolean consultCategoryPayment(int numCategory) throws SNMPExceptions, SQLException {

        boolean existe = false;
        String select = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "select * from CategoriaPago where IDCategoriaPago=" + numCategory;

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
