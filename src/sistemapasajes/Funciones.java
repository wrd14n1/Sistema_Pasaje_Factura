/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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

    public static String realizarConsultaApi(String apiUrl) throws IOException {
        String json;
        int intentosMaximos = 5;
        int intentoActual = 0;

        while (intentoActual < intentosMaximos) {
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Establecer el método de solicitud
                connection.setRequestMethod("GET");

                // Obtener la respuesta
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    json = response.toString();
                    return json;
                } finally {
                    // Asegúrate de cerrar la conexión

                    connection.disconnect();
                }
            } catch (IOException ex) {
                // Manejar la excepción (puedes registrarla o imprimir el mensaje)
                String errorMessage = "Error en el intento " + (intentoActual + 1) + ": " + ex.getMessage();
                System.out.println(errorMessage);

                // Mostrar el mensaje de error en un JOptionPane
                //JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                // Incrementar el contador de intentos y esperar antes de reintentar
                intentoActual++;
                esperarAntesDeReintentar();
            }
        }

        // Si llegamos aquí, todos los intentos han fallado
        String errorFinal = "No se encontrado el número de RUC o DNI consultado, se realizará nuevamente la consulta; Si problema persiste el número de RUC o DNI no es válido.";
        System.out.println(errorFinal);

        // Mostrar el mensaje final en un JOptionPane
        JOptionPane.showMessageDialog(null, errorFinal, "Error", JOptionPane.ERROR_MESSAGE);

        throw new IOException(errorFinal);
    }

    private static void esperarAntesDeReintentar() {
        try {
            // Puedes ajustar el tiempo de espera según tus necesidades
            Thread.sleep(500); // Esperar 1 segundo antes de reintentar
        } catch (InterruptedException e) {
            // Manejar la excepción si es necesario
            Thread.currentThread().interrupt();
        }
    }
    
   


}
