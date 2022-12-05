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
    private String Mensaje;
    private LinkedList<Benefit> listaBenefit = new LinkedList<Benefit>();

    public beanBenefit() {
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
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

        if (benDB.consultBenefit(idBeneficios) == true) {
            setMensaje("Este Beneficio ya fue creado");
        } else {
            benDB.InsertBenefit(ben);
            setMensaje("Beneficio Creado Correctamente");
        }

    }

    public void deleteBenefit() throws SNMPExceptions, SQLException {
        Benefit ben = new Benefit(idBeneficios, descripcion, porcentaje);
        BenefitDB benDB = new BenefitDB();

        benDB.DeleteBenefit(ben);

    }

    public void borradoLogico(int idOrden) throws SNMPExceptions, SQLException {
        setMensaje("");
        BenefitDB benDB = new BenefitDB();
        String resultado = benDB.validar(idOrden);
        if (resultado.equals("Existe")) {
            benDB.borradoLogico(idOrden);
            setMensaje("Borrado Correctamente");
        } else {
            if (resultado.equals("Orden borrada")) {
                setMensaje(resultado);
            } else {
                setMensaje("No existe");
            }
        }

    }

    public void changesBenefit() throws SNMPExceptions, SQLException {
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
