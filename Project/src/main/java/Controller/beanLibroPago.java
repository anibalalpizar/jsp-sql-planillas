package Controller;

import DAO.SNMPExceptions;
import Model.Benefit;
import Model.BenefitDB;
import Model.CategoryPayment;
import Model.CategoryPaymentDB;
import Model.LibroPago;
import Model.LibroPagoDB;
import Model.Planilla;
import Model.PlanillaDB;
import Model.User;
import Model.UserDB;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

public class beanLibroPago {

    private int idFactura;
    private int idPlanilla;
    private Benefit ben;
    private CategoryPayment pay;
    private int idBeneficio;
    private int idCategoriaPago;
    private int idUsuario;
    private float salarioBruto;
    private float salarioNeto;
    private LinkedList<LibroPago> listaPago = new LinkedList<LibroPago>();

    public beanLibroPago() {
    }

    public Benefit getBen() {
        return ben;
    }

    public void setBen(Benefit ben) {
        this.ben = ben;
    }

    public CategoryPayment getPay() {
        return pay;
    }

    public void setPay(CategoryPayment pay) {
        this.pay = pay;
    }



    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdCategoriaPago() {
        return idCategoriaPago;
    }

    public void setIdCategoriaPago(int idCategoriaPago) {
        this.idCategoriaPago = idCategoriaPago;
    }

    public int getIdPlanilla() {
        return idPlanilla;
    }

    public void setIdPlanilla(int idPlanilla) {
        this.idPlanilla = idPlanilla;
    }

    public int getIdBeneficio() {
        return idBeneficio;
    }

    public void setIdBeneficio(int idBeneficio) {
        this.idBeneficio = idBeneficio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public float getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(float salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public float getSalarioNeto() {
        return salarioNeto;
    }

    public void setSalarioNeto(float salarioNeto) {
        this.salarioNeto = salarioNeto;
    }

    public LinkedList<LibroPago> getListaPago() {
        return listaPago;
    }

    public void setListaPago(LinkedList<LibroPago> listaPago) {
        this.listaPago = listaPago;
    }

    public void mostrarLista() throws SNMPExceptions, SQLException {
        LibroPagoDB libroDb = new LibroPagoDB();

        this.setListaPago(libroDb.moTodo());
    }

    public void insertLibro() throws SNMPExceptions, SQLException {

        LibroPago libro = new LibroPago(idFactura, idPlanilla, idBeneficio, idBeneficio, idUsuario, salarioBruto, salarioNeto);
        LibroPagoDB lirboDB = new LibroPagoDB();
        
        
        lirboDB.InsertarFactura(libro);
 
       // lirboDB.ChangesPago(libro);


    }

 public void calculos() throws SNMPExceptions, SQLException {

        LibroPago libro = new LibroPago(idFactura, idPlanilla, idBeneficio, idBeneficio, idUsuario, salarioBruto, salarioNeto);
        LibroPagoDB lirboDB = new LibroPagoDB();
        
        
        lirboDB.ChangesSalarioBruto(libro);
        lirboDB.ChangesSalarioNeto(libro);


    }



    public LinkedList<SelectItem> getListaNombres() throws SNMPExceptions, SQLException {
        int idUsuario = 0;

        String nombre = "";

        LinkedList<User> lista = new LinkedList<User>();
        UserDB uDB = new UserDB();
        lista = uDB.moTodo();

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione el Nombre"));

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            User user = (User) iter.next();
            idUsuario = user.getIdUsuario();
            nombre = user.getNombre();
            resultList.add(new SelectItem(idUsuario, nombre));

        }
        return resultList;

    }

    public LinkedList<SelectItem> getListaBenefit() throws SNMPExceptions, SQLException {
        String descripcion = "";
        int idBeneficio = 0;

        LinkedList<Benefit> lista = new LinkedList<Benefit>();
        BenefitDB bDB = new BenefitDB();
        lista = bDB.moTodo();

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione deducion"));

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Benefit beni = (Benefit) iter.next();
            idBeneficio = beni.getIdBeneficios();
            descripcion = beni.getDescripcion();
            resultList.add(new SelectItem(idBeneficio, descripcion));

        }
        return resultList;

    }

    public LinkedList<SelectItem> getListaCategoryPayment() throws SNMPExceptions, SQLException {
        String descripcion = "";
        int idCategory = 0;

        LinkedList<CategoryPayment> lista = new LinkedList<CategoryPayment>();
        CategoryPaymentDB cDB = new CategoryPaymentDB();
        lista = cDB.moTodo();

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione Categoria Pago"));

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            CategoryPayment cate = (CategoryPayment) iter.next();
            idCategory = cate.getIdCategoriaPago();
            descripcion = cate.getDescripcion();
            resultList.add(new SelectItem(idCategory, descripcion));

        }
        return resultList;

    }

    public LinkedList<SelectItem> getListaPlanilla() throws SNMPExceptions, SQLException {
        String descripcion = "";
        int idPlanilla = 0;

        LinkedList<Planilla> lista = new LinkedList<Planilla>();
        PlanillaDB pDB = new PlanillaDB();
        lista = pDB.moTodo();

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione Planilla"));

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Planilla plani = (Planilla) iter.next();
            idPlanilla = plani.getIdPlanilla();
            descripcion = plani.getDescripcion();
            resultList.add(new SelectItem(idPlanilla, descripcion));

        }
        return resultList;

    }

}