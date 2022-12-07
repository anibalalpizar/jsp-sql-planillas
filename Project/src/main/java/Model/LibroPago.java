package Model;

public class LibroPago {

    public int idFactura;
    public int idPlanilla;
    public int idBeneficio;
    public int categoriaPago;
    public int idUsuario;
    public String usu;
    public String ben;
    public String pay;
    public float salarioBruto;
    public float salarioNeto;
    public int cantHoras;

    public LibroPago(int idFactura, int idPlanilla, int idBeneficio, int categoriaPago, int idUsuario, float salarioBruto, float salarioNeto, int cantHoras) {
        this.setIdFactura(idFactura);
        this.setIdPlanilla(idPlanilla);
        this.setIdBeneficio(idBeneficio);
        this.setCategoriaPago(categoriaPago);
        this.setIdUsuario(idUsuario);
        this.setSalarioBruto(salarioBruto);
        this.setSalarioNeto(salarioNeto);
        this.setCantHoras(cantHoras);
    }

    public LibroPago(int idPlanilla, int categoriaPago, int idBeneficio, int idUsuario, float salarioBruto, float salarioNeto, int cantHoras) {

        this.setIdPlanilla(idPlanilla);

        this.setCategoriaPago(categoriaPago);
        this.setIdBeneficio(idBeneficio);
        this.setIdUsuario(idUsuario);
        this.setSalarioBruto(salarioBruto);
        this.setSalarioNeto(salarioNeto);
        this.setCantHoras(cantHoras);
    }

    public LibroPago(int idFactura, int idPlanilla, String usu, String ben, String pay, float salarioBruto, float salarioNeto) {
        this.setIdFactura(idFactura);
        this.setIdPlanilla(idPlanilla);
        this.setUsu(usu);
        this.setBen(ben);
        this.setPay(pay);
        this.setSalarioBruto(salarioBruto);
        this.setSalarioNeto(salarioNeto);
    }

    public int getCantHoras() {
        return cantHoras;
    }

    public void setCantHoras(int cantHoras) {
        this.cantHoras = cantHoras;
    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getBen() {
        return ben;
    }

    public void setBen(String ben) {
        this.ben = ben;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public LibroPago() {
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
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

    public int getCategoriaPago() {
        return categoriaPago;
    }

    public void setCategoriaPago(int categoriaPago) {
        this.categoriaPago = categoriaPago;
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

}
