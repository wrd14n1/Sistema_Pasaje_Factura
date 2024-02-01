/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author edson
 */
public class Funciones {
    
    /**
     *
     * @param fecha
     * @return
     */
    public static String convertirFecha(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setTimeZone(TimeZone.getTimeZone("America/Lima"));
            return sdf.format(fecha);
        } else {
            System.out.println("No se ha seleccionado ninguna fecha.");
            return null;
        }
    }
}
