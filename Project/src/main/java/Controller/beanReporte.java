/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author anibal
 */
import DAO.ReportePDF;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class beanReporte {

    Reporte reporte = new ReportePDF();

    public void generar() {
        try {
            reporte.generarReporte("reporte.pdf", "Resultado");
        } catch (SQLException ex) {
            Logger.getLogger(beanReporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(beanReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
