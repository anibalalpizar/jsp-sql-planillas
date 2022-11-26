package Model;

public class CategoryPayment {

    public int idCategoriaPago;
    public String descripcion;
    public float precio;

    public CategoryPayment(int idCategoriaPago, String descripcion, float precio) {
        this.setIdCategoriaPago(idCategoriaPago);
        this.setDescripcion(descripcion);
        this.setPrecio(precio);
    }

    public CategoryPayment() {
    }

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

}
