package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class CargoDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public CargoDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public LinkedList<cargo> moTodo() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<cargo> listaCategory = new LinkedList<cargo>();

        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select
                    = "SELECT IdPosition, PositionName FROM Cargo where Status = 1";
            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while (rsPA.next()) {

                int idPosition = rsPA.getInt("IdPosition");
                String PositionName = rsPA.getString("PositionName");
               
                //se construye el objeto.
                cargo perCat = new cargo(idPosition, PositionName);
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

}
