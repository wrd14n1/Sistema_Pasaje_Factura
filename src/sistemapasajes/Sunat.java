/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import sistemapasajes.dao.ConfiguracionDAO;
import sistemapasajes.dao.ConfiguracionDAOImpl;
import sistemapasajes.modelo.ConfiguracionModel;

public class Sunat {

    public static void ejecutarJarExterno(String jarFile, String... args) {
        try {
            List<String> command = new java.util.ArrayList<>(Arrays.asList("java", "-jar", jarFile));
            command.addAll(Arrays.asList(args));

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.directory(new File(System.getProperty("user.dir")));
            Process proceso = processBuilder.start();

            // Obtener la salida estándar y de error del proceso
            InputStream inputStream = proceso.getInputStream();
            InputStream errorStream = proceso.getErrorStream();

            // Leer la salida estándar
            String output = readStream(inputStream);
            System.out.println("Salida estándar del proceso:\n" + output);
            JOptionPane.showMessageDialog(null, "Salida estándar del proceso:\n" + output, "Error", JOptionPane.ERROR_MESSAGE);

            // Leer la salida de error
            String errorOutput = readStream(errorStream);
            System.out.println("Salida de error del proceso:\n" + errorOutput);
            JOptionPane.showMessageDialog(null, "Salida de error del proceso:\n" + errorOutput, "Error", JOptionPane.ERROR_MESSAGE);

            // Esperar a que el proceso termine
            int resultado = proceso.waitFor();
            System.out.println("El proceso ha terminado con resultado: " + resultado);
            JOptionPane.showMessageDialog(null, "\"El proceso ha terminado con resultado: \" + resultado:" + resultado, "Error", JOptionPane.ERROR_MESSAGE);

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private static String readStream(InputStream stream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            return result.toString();
        }
    }

    public static String getHash(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        String linea5 = null;// se encuentra hash en XML
        String hash;

        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);

        long numeroLinea = 0;
        while ((cadena = br.readLine()) != null) {
            numeroLinea++;
            if (numeroLinea == 5) {
                linea5 = cadena;
            }
        }
        br.close();
        hash = linea5.substring(linea5.indexOf("<ds:DigestValue>"), linea5.indexOf("</ds:DigestValue>"));
        //eliminando los primeros 16 caracteres
        hash = hash.substring(16);
        return hash;
    }

    public static int EjecutarSFS() {
        ConfiguracionDAO configdao = new ConfiguracionDAOImpl();
        ConfiguracionModel configuracion = configdao.obtenerConfiguracionPorId(1);
         int resultado = 0;
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "start", "EjecutarSFS.bat", configuracion.getRutaSunat() + "\\");
            builder.directory(new File(configuracion.getRutaSunat()));

            Process proceso = builder.start();
            resultado= proceso.waitFor();

            // Imprimir salida y error
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

            // Mostrar mensaje con el resultado
            //JOptionPane.showMessageDialog(null, "Resultado: " + resultado, "Ejecución del Batch", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
}
