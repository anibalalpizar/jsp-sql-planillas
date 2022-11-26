package Controller;

import DAO.SNMPExceptions;
import Model.Benefit;
import Model.BenefitDB;
import Model.WorkShiftDB;
import java.sql.SQLException;
import java.util.LinkedList;

public class beanBenefit {

    private int idBeneficios;
    private String descripcion;
    private float porcentaje;
    private LinkedList<Benefit> listaBenefit = new LinkedList<Benefit>();

    public beanBenefit() {
    }

    public int getIdBeneficios() {
        return idBeneficios;
    }

    public void setIdBeneficios(int idBeneficios) {
        this.idBeneficios = idBeneficios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public LinkedList<Benefit> getListaBenefit() {
        return listaBenefit;
    }

    public void setListaBenefit(LinkedList<Benefit> listaBenefit) {
        this.listaBenefit = listaBenefit;
    }

    public void insertBenefit() throws SNMPExceptions, SQLException {

        Benefit ben = new Benefit(idBeneficios, descripcion, porcentaje);
        BenefitDB benDB = new BenefitDB();

        benDB.InsertBenefit(ben);

    }

    public void deleteWorkShift() throws SNMPExceptions, SQLException {
        Benefit ben = new Benefit(idBeneficios, descripcion, porcentaje);
        BenefitDB benDB = new BenefitDB();

        benDB.DeleteBenefit(ben);

    }

    public void changesWorkShift() throws SNMPExceptions, SQLException {
        Benefit ben = new Benefit(idBeneficios, descripcion, porcentaje);
        BenefitDB benDB = new BenefitDB();

        benDB.ChangesBenefit(ben);

    }

 public void mostrarLista() throws SNMPExceptions, SQLException {
        BenefitDB benDB = new BenefitDB();

        this.setListaBenefit(benDB.moTodo());
    }

public void limpiarCampos() {
this.setIdBeneficios(0);
this.setDescripcion("");
this.setPorcentaje(0);


}

}
