package DAO;

import java.sql.*;
import javax.naming.NamingException;

public class Conexion {

    static final int SQL_SERVER = 1;

    static final int OPERACION_EFECTUADA = 1;

    /**
     * Ejecuta una instrucciÃ³n SQL
     *
     * @param pvcSQL
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection conectar() {
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=DBPlanillas;user=sa;password=123456");
//            stmt = con.createStatement();
//           System.out.println("EjecutaSQL: " + pvcSQL );
//           stmt.execute(pvcSQL);

        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar ResultSet: " + e.getMessage());
        }
    }

    public static void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar PreparedStatement: " + e.getMessage());
        }
    }

    public static void close(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar Connection: " + e.getMessage());
        }
    }
}
