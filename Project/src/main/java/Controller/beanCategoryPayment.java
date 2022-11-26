package Controller;

import DAO.SNMPExceptions;
import Model.CategoryPayment;
import Model.CategoryPaymentDB;
import Model.WorkShiftDB;
import java.sql.SQLException;
import java.util.LinkedList;

public class beanCategoryPayment {

    private int idCategoriPago;
    private String descripcion;
    private float precio;
    private LinkedList<CategoryPayment> listaCategory = new LinkedList<CategoryPayment>();

    public beanCategoryPayment() {
    }

    public int getIdCategoriPago() {
        return idCategoriPago;
    }

    public void setIdCategoriPago(int idCategoriPago) {
        this.idCategoriPago = idCategoriPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public LinkedList<CategoryPayment> getListaCategory() {
        return listaCategory;
    }

    public void setListaCategory(LinkedList<CategoryPayment> listaCategory) {
        this.listaCategory = listaCategory;
    }

    public void insertCategoryPayment() throws SNMPExceptions, SQLException {

        CategoryPayment cat = new CategoryPayment(idCategoriPago, descripcion, precio);
        CategoryPaymentDB catDB = new CategoryPaymentDB();

        catDB.InsertCategoryPayment(cat);

    }

    public void deleteCategoryPayment() throws SNMPExceptions, SQLException {
        CategoryPayment cat = new CategoryPayment(idCategoriPago, descripcion, precio);
        CategoryPaymentDB catDB = new CategoryPaymentDB();

        catDB.DeleteCategoryPayment(cat);

    }

    public void changesCategoryPayment() throws SNMPExceptions, SQLException {
        CategoryPayment cat = new CategoryPayment(idCategoriPago, descripcion, precio);
        CategoryPaymentDB catDB = new CategoryPaymentDB();

        catDB.ChangesCategoryPayment(cat);

    }

    public void mostrarLista() throws SNMPExceptions, SQLException {
        CategoryPaymentDB catDB = new CategoryPaymentDB();

        this.setListaCategory(catDB.moTodo());
    }

    public void limpiarCampos() {
        this.setIdCategoriPago(0);
        this.setDescripcion("");
        this.setPrecio(0);

    }

}
