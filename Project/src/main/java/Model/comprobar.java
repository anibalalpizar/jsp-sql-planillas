package Model;

public class comprobar {

    public static void main(String[] args) {
        conexion c = new conexion();
        if (c.conectar() != null) {
            System.out.println("Conectado");
        } else {
            System.out.println("No conectado");
        }
    }
    
}
