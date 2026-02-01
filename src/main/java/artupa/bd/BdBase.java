package artupa.bd;

import java.sql.Connection;
import java.sql.DriverManager;
// IMPORTANTE: Importamos esto para que reconozca el objeto que le pasa el Servlet
import artupa.config.Configuracion; 

public class BdBase {

    // --- DATOS DE CONEXIÓN FIJOS (Para que funcione seguro) ---
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    

    private static final String URL = "jdbc:mysql://localhost:3306/salsashop?useSSL=false&allowPublicKeyRetrieval=true";
    
    private static final String USER = "root";
    private static final String PASSWORD = "admin"; 
    
    protected Connection conexion;


    public static void inicializarParametrosConexion(Configuracion configuracion) {
        System.out.println("SrvValidarEntrada llamó a inicializar, pero usaremos los datos fijos de BdBase.");
    }
    // -------------------------------------------------------------

    protected BdBase() {
        super();
    }

    public boolean abrirConexion() {
        boolean correcto = true;
        try {
            System.out.println("Intentando conectar a: " + URL);
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("¡Conexión Éxitosa!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FALLO EN CONEXIÓN: " + e.getMessage());
            correcto = false;
        }
        return correcto;
    }

    public boolean cerrarConexion() {
        boolean correcto = true;
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            correcto = false;
        }
        return correcto;
    }

    public boolean abrirTransaccion() {
        boolean correcto = true;
        try {
            if (conexion != null) conexion.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            correcto = false;
        }
        return correcto;
    }

    public boolean hacerCommit() {
        boolean correcto = true;
        try {
            if (conexion != null) conexion.commit();
        } catch (Exception e) {
            e.printStackTrace();
            correcto = false;
        }
        return correcto;
    }

    public boolean hacerRollback() {
        boolean correcto = true;
        try {
            if (conexion != null) conexion.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            correcto = false;
        }
        return correcto;
    }
}