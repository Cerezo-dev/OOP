package pe.edu.upeu.asistencia.conexion;

import java.io.File;
import java.sql.*;

public class ConnDB {

    public static Connection conexion = null;

    public static Connection getConexion() {
        try {
            Class.forName("org.sqlite.JDBC");
            File dbFile = new File("data/asistenciadb.db");
            if (!dbFile.exists() && new File("Asistencia/data/asistenciadb.db").exists()) {
                dbFile = new File("Asistencia/data/asistenciadb.db");
            }
            if (dbFile.getParentFile() != null && !dbFile.getParentFile().exists()) {
                dbFile.getParentFile().mkdirs();
            }
            String url = "jdbc:sqlite:" + dbFile.getPath() + "?foreign_keys=on;";
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(url);
                try (Statement stmt = conexion.createStatement()) {
                    stmt.execute("CREATE TABLE IF NOT EXISTS participante (" +
                            "dni VARCHAR(20) PRIMARY KEY, " +
                            "nombre VARCHAR(100), " +
                            "apellidos VARCHAR(100), " +
                            "carrera VARCHAR(50), " +
                            "tipo_participante VARCHAR(50), " +
                            "estado BOOLEAN DEFAULT 1);");
                }
            }
            System.out.println("Coneccion exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return conexion;
    }

    public static void closeConexion() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
//TEST
//    public static void main(String[] args) {
//        PreparedStatement pst=null;
//        ResultSet rs=null;
//        Connection con=getConexion();
//        try {
//            pst=con.prepareStatement("SELECT * FROM participante");
//            rs=pst.executeQuery();
//            while(rs.next()){
//                String nombre=rs.getString("nombre");
//                String apellido=rs.getString("apellidos");
//                System.out.println(nombre+" "+apellido);
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}