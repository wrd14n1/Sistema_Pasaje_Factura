/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

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

            // Leer la salida de error
            String errorOutput = readStream(errorStream);
            System.out.println("Salida de error del proceso:\n" + errorOutput);

            // Esperar a que el proceso termine
            int resultado = proceso.waitFor();
            System.out.println("El proceso ha terminado con resultado: " + resultado);
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
}