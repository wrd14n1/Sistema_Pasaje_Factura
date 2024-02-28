/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JInternalFrame;
import static sistemapasajes.View.Principal.jDesktopPane1;

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

    public static void centrarInternalFrame(JInternalFrame internalFrame) {

        // Agrega el internal frame al desktop pane
        jDesktopPane1.add(internalFrame);

        // Obtén el tamaño del desktop pane
        int desktopAncho = jDesktopPane1.getWidth();
        int desktopAlto = jDesktopPane1.getHeight();

        // Obtén el tamaño del internal frame
        int frameAncho = internalFrame.getWidth();
        int frameAlto = internalFrame.getHeight();

        // Calcula las coordenadas para centrar el internal frame
        int x = (desktopAncho - frameAncho) / 2;
        int y = (desktopAlto - frameAlto) / 2;

        // Establece la posición del internal frame
        internalFrame.setLocation(x, y);

        // Hacer visible el internal frame
        internalFrame.setVisible(true);
    }

}
