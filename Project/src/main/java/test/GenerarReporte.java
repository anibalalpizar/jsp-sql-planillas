/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import Controller.Reporte;
import DAO.ReportePDF;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anibal
 */
public class GenerarReporte {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gene2();
    }

    public static void gene2() {
        Reporte reporte = new ReportePDF();
        try {
            reporte.generarReporte("reporte.pdf", "Reporte");
            System.out.println("good");
        } catch (SQLException ex) {
            Logger.getLogger(GenerarReporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenerarReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
