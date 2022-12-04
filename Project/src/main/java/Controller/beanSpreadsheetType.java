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
    private String mensaje;
    private LinkedList<SpreadsheetType> listaSprends = new LinkedList<SpreadsheetType>();
    
    public beanSpreadsheetType() {
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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
        
        if (spreDB.consultSpreandsheetType(idTipoPlanilla) == true) {
            setMensaje("Este Tipo de Planilla ya fue creada");
        } else {
            spreDB.InsertSpreadsheet(spreads);
            setMensaje("Tipo de Planilla Creada Correctamente");
        }
        
    }
    
    public void deleteSpreadsheet() throws SNMPExceptions, SQLException {
        SpreadsheetType spreads = new SpreadsheetType(idTipoPlanilla, descripcion);
        SpreadsheetTypeDB spreDB = new SpreadsheetTypeDB();
        
        spreDB.DeleteSpreadsheet(spreads);
        
    }

    public void borradoLogico(int idOrden) throws SNMPExceptions, SQLException {
        setMensaje("");
        SpreadsheetTypeDB spreDB = new SpreadsheetTypeDB();
        String resultado = spreDB.validar(idOrden);
        if (resultado.equals("Existe")) {
            spreDB.borradoLogicoWork(idOrden);
            setMensaje("Borrado Correctamente");
        } else {
            if (resultado.equals("Orden borrada")) {
                setMensaje(resultado);
            } else {
                setMensaje("No existe");
            }
        }
        
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
    
    public void limpiarCampos() {
        this.setIdTipoPlanilla(0);
        this.setDescripcion("");
        
    }
}
