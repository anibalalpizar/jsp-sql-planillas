package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAOUSUARIO extends conexion {

    public usuario identificar(usuario user) throws Exception {
        usuario usu = null;
        conexion con;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

       // String sql = "SELECT U.IdUser, C.PositionName FROM UsuarioSeguridad U INNER JOIN Cargo C ON U.IdPosition = C.IdPosition where u.Status = 1 AND U.USERNAME = '"
              //  + user.getNombreususario() + "' AND U.PASSWORD = '" + user.getClave() + "'";

        String sql = "SELECT U.IdUser, C.PositionName FROM UsuarioSeguridad U INNER JOIN Cargo C ON U.IdPosition = C.IdPosition where u.Status = 1 AND U.USERNAME = '"
                + user.getNombreususario() + "' AND  DECRYPTBYPASSPHRASE('password', U.PASSWORD) = '" + user.getClave() + "'";

        con = new conexion();

        try {

            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next() == true) {
                usu = new usuario();
                usu.setId_usuario(rs.getInt("IdUser"));
                usu.setNombreususario(user.getNombreususario());
                usu.setCargo(new cargo());
                usu.getCargo().setNombreCargo(rs.getString("PositionName"));
                usu.setEstado(true);

            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
            if (st != null && st.isClosed() == false) {
                st.close();
            }
            st = null;
            if (cn != null && cn.isClosed() == false) {
                cn.close();
            }
            cn = null;
        }
        return usu;

    }

}
