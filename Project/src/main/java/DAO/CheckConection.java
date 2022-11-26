
package DAO;
import java.sql.*;

public class CheckConection {

    
    public static void main(String[] args) {
        Conexion con = new Conexion();
        if (con.conectar() != null) {
            System.out.println("Conectado");
        } else {
            System.out.println("No conectado");
        }
    }
    
    
}
