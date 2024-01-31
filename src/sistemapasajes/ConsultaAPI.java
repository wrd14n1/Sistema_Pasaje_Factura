/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class ConsultaAPI {
        // Otros métodos de tu clase
     String json;
    public String realizarConsultaApi(String apiUrl) throws IOException {
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

    private void esperarAntesDeReintentar() {
        try {
            // Puedes ajustar el tiempo de espera según tus necesidades
            Thread.sleep(500); // Esperar 1 segundo antes de reintentar
        } catch (InterruptedException e) {
            // Manejar la excepción si es necesario
            Thread.currentThread().interrupt();
        }
    }

}
