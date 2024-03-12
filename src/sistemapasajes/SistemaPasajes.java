/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemapasajes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import sistemapasajes.View.Principal;

/**
 *
 * @author edson
 */
public class SistemaPasajes {

    public static void main(String[] args) {
        /* Conectar base de datos */
       
        CargarXAMPP();
         esperarXAMPP();
         // Espera a que XAMPP se inicie completamente
        Conexion conexion = new Conexion();
        try {
            Connection con = conexion.conectar();
            if (con != null) {
                System.out.println("Conexión Exitosa");
                Principal prin = new Principal();
                prin.show();
            }
        } catch (SQLException ex) {
            System.out.println("Error al establecer la conexión: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void CargarXAMPP() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("C:/xampp/xampp-control.exe");
            processBuilder.start();
        } catch (IOException e) {
            System.out.println("Error al iniciar XAMPP: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void esperarXAMPP() {
        boolean xamppIniciado = false;
        while (!xamppIniciado) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("tasklist");
                Process process = processBuilder.start();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("xampp-control.exe")) {
                            xamppIniciado = true;
                            break;
                        }
                    }
                }
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                System.out.println("Error al verificar XAMPP: " + e.getMessage());
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                System.out.println("Error al esperar: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
