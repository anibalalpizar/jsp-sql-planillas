package Controller;

import DAO.SNMPExceptions;
import Model.SpreadsheetType;
import Model.SpreadsheetTypeDB;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;

public class beanSpreadsheetType implements Serializable {

    private int idTipoPlanilla;
    private String descripcion;
    private LinkedList<SpreadsheetType> listaSprends = new LinkedList<SpreadsheetType>();

    public beanSpreadsheetType() {
    }

    public LinkedList<SpreadsheetType> getListaSprends() {
        return listaSprends;
    }

    public void setListaSprends(LinkedList<SpreadsheetType> listaSprends) {
        this.listaSprends = listaSprends;
    }

    public int getIdTipoPlanilla() {
        return idTipoPlanilla;
    }

    public void setIdTipoPlanilla(int idTipoPlanilla) {
        this.idTipoPlanilla = idTipoPlanilla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void insertSpreadsheet() throws SNMPExceptions, SQLException {

        SpreadsheetType spreads = new SpreadsheetType(idTipoPlanilla, descripcion);
        SpreadsheetTypeDB spreDB = new SpreadsheetTypeDB();

        spreDB.InsertSpreadsheet(spreads);

    }

    public void deleteSpreadsheet() throws SNMPExceptions, SQLException {
        SpreadsheetType spreads = new SpreadsheetType(idTipoPlanilla, descripcion);
        SpreadsheetTypeDB spreDB = new SpreadsheetTypeDB();

        spreDB.DeleteSpreadsheet(spreads);

    }

 public void changesSpreandsHeet() throws SNMPExceptions, SQLException {
        SpreadsheetType spe = new SpreadsheetType(idTipoPlanilla, descripcion);
        SpreadsheetTypeDB spreDB = new SpreadsheetTypeDB();
        
        
        spreDB.ChangesSpreandsHeet(spe);
        
    }
    

    public void mostrarLista() throws SNMPExceptions, SQLException {
        SpreadsheetTypeDB spreandsDB = new SpreadsheetTypeDB();

        this.setListaSprends(spreandsDB.moTodo());
    }

public void limpiarCampos(){
this.setIdTipoPlanilla(0);
this.setDescripcion("");

}
}
