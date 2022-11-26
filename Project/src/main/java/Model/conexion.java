/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author anibal
 */
public class conexion {

    // sql

    private final String baseDatos = "DBPlanillas";
    private final String servidor = "jdbc:sqlserver://localhost:1433;databaseName=" + baseDatos;
    private final String usuario = "sa";
    private final String clave = "123456";

    public Connection conectar() {
        Connection cn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(servidor, usuario, clave);
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return cn;
    }
}
