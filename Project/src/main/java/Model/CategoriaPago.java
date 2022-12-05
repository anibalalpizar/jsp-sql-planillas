/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author anibal
 */
public class CategoriaPago {

    public int idCategoriaPago;
    public String descripcion;
    public float precio;
    public int borrado;

    public int getIdCategoriaPago() {
        return idCategoriaPago;
    }

    public void setIdCategoriaPago(int idCategoriaPago) {
        this.idCategoriaPago = idCategoriaPago;
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

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

}
