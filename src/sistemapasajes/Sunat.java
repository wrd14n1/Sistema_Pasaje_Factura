/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author edson
 */
public class Sunat {
    public static void ejecutarJarExterno(String jarFile, String... args) {
        try {
            List<String> command = new java.util.ArrayList<>(Arrays.asList("java", "-jar", jarFile));
            command.addAll(Arrays.asList(args));

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.directory(new File(System.getProperty("user.dir")));
            Process proceso = processBuilder.start();

            // Puedes agregar código adicional aquí si lo necesitas.

            // Esperar a que el proceso termine
            int resultado = proceso.waitFor();
            System.out.println("El proceso ha terminado con resultado: " + resultado);
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
