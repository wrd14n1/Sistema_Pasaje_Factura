/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para manejar la conexión a la base de datos MySQL.
 * 
 * @author edson
 */
public class Conexion {
    // Configuración de la conexión
    private static final String URL = "jdbc:mysql://localhost:3306/bd_pasaje";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "020320";
    
    private Connection conexion;

    /**
     * Método para establecer la conexión con la base de datos.
     * 
     * @return La conexión establecida.
     * @throws SQLException Si hay un error al establecer la conexión.
     */
    public Connection conectar() throws SQLException {
        try {
            // Cargar el controlador de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            
            // Comprobar si la conexión fue exitosa
            if (conexion != null) {
                System.out.println("Conexión exitosa a la base de datos");
            }
            
            return conexion;
        } catch (ClassNotFoundException | SQLException ex) {
            // Manejar cualquier excepción que pueda ocurrir durante la conexión
            throw new SQLException("Error al conectar a la base de datos", ex);
        }
    }

    /**
     * Método para cerrar la conexión con la base de datos.
     * 
     * @throws SQLException Si hay un error al cerrar la conexión.
     */
    public void desconectar() throws SQLException {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException ex) {
            // Manejar cualquier excepción que pueda ocurrir al cerrar la conexión
            throw new SQLException("Error al cerrar la conexión", ex);
        }
    }
}