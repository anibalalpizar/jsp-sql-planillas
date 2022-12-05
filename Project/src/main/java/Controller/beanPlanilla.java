package Controller;

import DAO.SNMPExceptions;
import Model.Benefit;
import Model.BenefitDB;
import Model.CategoryPayment;
import Model.CategoryPaymentDB;
import Model.Planilla;
import Model.PlanillaDB;
import Model.SpreadsheetType;
import Model.SpreadsheetTypeDB;
import Model.User;
import Model.UserDB;
import Model.WorkShift;
import Model.WorkShiftDB;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import javax.faces.model.SelectItem;

public class beanPlanilla {

    private int idPlanilla;
    private String descripcion;
    private int idTurno;
    private int idTipoPlanilla;
    private String fechaInicio;
    private String FechaFinal;
    private String fechaPago;
    private String mensaje;
    private LinkedList<Planilla> listaPlanilla = new LinkedList<Planilla>();

    public beanPlanilla() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LinkedList<Planilla> getListaPlanilla() {
        return listaPlanilla;
    }

    public void setListaPlanilla(LinkedList<Planilla> listaPlanilla) {
        this.listaPlanilla = listaPlanilla;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public int getIdTipoPlanilla() {
        return idTipoPlanilla;
    }

    public void setIdTipoPlanilla(int idTipoPlanilla) {
        this.idTipoPlanilla = idTipoPlanilla;
    }

    public int getIdPlanilla() {
        return idPlanilla;
    }

    public void setIdPlanilla(int idPlanilla) {
        this.idPlanilla = idPlanilla;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return FechaFinal;
    }

    public void setFechaFinal(String FechaFinal) {
        this.FechaFinal = FechaFinal;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public void insertPlanilla() throws SNMPExceptions, SQLException {

        Planilla pla = new Planilla(idPlanilla, descripcion, idTurno, idTipoPlanilla, fechaInicio, FechaFinal, fechaPago);
        PlanillaDB planillaDB = new PlanillaDB();

        if (planillaDB.consultPlanilla(idPlanilla) == true) {
            setMensaje("Esta planilla ya fue creada");
        } else {
            planillaDB.InsertarPlanilla(pla);
            setMensaje("Planilla Creada Correctamente");
        }

    }

    public void borradoLogico(int idOrden) throws SNMPExceptions, SQLException {
        setMensaje("");
        PlanillaDB plaDB = new PlanillaDB();
        String resultado = plaDB.validar(idOrden);
        if (resultado.equals("Existe")) {
            plaDB.borradoLogicoWork(idOrden);
            setMensaje("Borrado Correctamente");
        } else {
            if (resultado.equals("Orden borrada")) {
                setMensaje(resultado);
            } else {
                setMensaje("No existe");
            }
        }

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

    public LinkedList<SelectItem> getListaTipoPlanilla() throws SNMPExceptions, SQLException {
        int idTipoPlanilla = 0;
        String descripcion = "";

        LinkedList<SpreadsheetType> lista = new LinkedList<SpreadsheetType>();
        SpreadsheetTypeDB sDB = new SpreadsheetTypeDB();
        lista = sDB.PlanillaTodos();

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione Tipo Planilla"));

        for (Iterator iter = lista.iterator(); iter.hasNext();) {
            SpreadsheetType spre = (SpreadsheetType) iter.next();
            idTipoPlanilla = spre.getIdTipoPlanilla();
            descripcion = spre.getDescripcion();
            resultList.add(new SelectItem(idTipoPlanilla, descripcion));

        }
        return resultList;

    }

    public LinkedList<SelectItem> getListaTur() throws SNMPExceptions, SQLException {
        String descripcion = "";
        int idTurno = 0;

        LinkedList<WorkShift> lista = new LinkedList<WorkShift>();
        WorkShiftDB wDB = new WorkShiftDB();
        lista = wDB.TurnosTodos();

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione Turno"));

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            WorkShift work = (WorkShift) iter.next();
            idTurno = work.getIdTurno();
            descripcion = work.getDescripcion();
            resultList.add(new SelectItem(idTurno, descripcion));

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

    public void mostrarLista() throws SNMPExceptions, SQLException {
        PlanillaDB planiDb = new PlanillaDB();

        this.setListaPlanilla(planiDb.moTodo());
    }
}
