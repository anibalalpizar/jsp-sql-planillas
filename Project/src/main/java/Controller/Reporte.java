/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.io.IOException;

/**
 *
 * @author anibal
 */
public interface Reporte {

    public abstract void generarReporte(String DES, String titulo) throws SQLException, FileNotFoundException, IOException;
}
