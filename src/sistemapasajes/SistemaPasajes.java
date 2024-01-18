/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemapasajes;

import java.sql.Connection;

import sistemapasajes.View.Principal;

/**
 *
 * @author edson
 */
public class SistemaPasajes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Conectar base datos*/
        Conexion conexion = new Conexion();
        try {
            Connection con = (Connection) conexion.conectar();
            if (con != null) {
                //System.out.println("Conexión Existosa");
                Principal prin = new Principal();
                prin.show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
             System.out.println("Error al establecer la conexión: " + ex.getMessage());
        }
        
    }
    
}
