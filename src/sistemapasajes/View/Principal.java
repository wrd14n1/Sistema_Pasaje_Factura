/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemapasajes.View;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.fast.FastLookAndFeel;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import sistemapasajes.Sunat;
import sistemapasajes.dao.ConfiguracionDAO;
import sistemapasajes.dao.ConfiguracionDAOImpl;
import sistemapasajes.modelo.ConfiguracionModel;

/**
 *
 * @author edson
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        // Configura el look and feel de JTattoo
        try {
            // Puedes cambiar "Acryl" por el nombre de otro tema de JTattoo si lo prefieres
            UIManager.setLookAndFeel(new FastLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuitemingresar = new javax.swing.JMenuItem();
        menuconf = new javax.swing.JMenu();
        opconfig = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        menupasaje = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        menusunat = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        jMenuItem4.setText("jMenuItem4");

        jMenuItem9.setText("jMenuItem9");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Pasajes");
        setLocationByPlatform(true);
        setName("Ventana Principal"); // NOI18N

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        jMenuBar1.setEnabled(false);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ingresar_menu.png"))); // NOI18N
        jMenu1.setText("Ingresar");

        menuitemingresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ingresar_menu.png"))); // NOI18N
        menuitemingresar.setText("Ingresar");
        menuitemingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemingresarActionPerformed(evt);
            }
        });
        jMenu1.add(menuitemingresar);

        jMenuBar1.add(jMenu1);

        menuconf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/conf_menu.png"))); // NOI18N
        menuconf.setText("Configuración");
        menuconf.setEnabled(false);

        opconfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/conf_gen_menu.png"))); // NOI18N
        opconfig.setText("General");
        opconfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opconfigActionPerformed(evt);
            }
        });
        menuconf.add(opconfig);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/carros.png"))); // NOI18N
        jMenuItem2.setText("Vehiculos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuconf.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ruta_menu.png"))); // NOI18N
        jMenuItem3.setText("Rutas");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuconf.add(jMenuItem3);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/api_menu.png"))); // NOI18N
        jMenuItem6.setText("API");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menuconf.add(jMenuItem6);

        jMenuBar1.add(menuconf);

        menupasaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/pasaje_menu.png"))); // NOI18N
        menupasaje.setText("Pasajes");
        menupasaje.setEnabled(false);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/pasaje_menu.png"))); // NOI18N
        jMenuItem1.setText("Generar Pasajes");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menupasaje.add(jMenuItem1);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/pasaje-aereo.png"))); // NOI18N
        jMenuItem5.setText("Lista de Pasajes");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menupasaje.add(jMenuItem5);

        jMenuBar1.add(menupasaje);

        menusunat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/sunat_menu.png"))); // NOI18N
        menusunat.setText("Facturador");
        menusunat.setEnabled(false);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/archivo-ejecutable.png"))); // NOI18N
        jMenuItem7.setText("Ejecutar Servidor SFS");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menusunat.add(jMenuItem7);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/navegador.png"))); // NOI18N
        jMenuItem8.setText("Abrir Facturador");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menusunat.add(jMenuItem8);

        jMenuBar1.add(menusunat);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opconfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opconfigActionPerformed
        Configuracion vconf = new Configuracion();

        centrarInternalFrame(vconf);
    }//GEN-LAST:event_opconfigActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Vehiculo vvehi = new Vehiculo();
        centrarInternalFrame(vvehi);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Ruta vruta = new Ruta();
        centrarInternalFrame(vruta);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Pasajes vpasaje = new Pasajes();
        centrarInternalFrame(vpasaje);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        Api vapi = new Api();
        centrarInternalFrame(vapi);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed

   try {
        // Ruta completa del archivo batch
        ConfiguracionDAO configdao = new ConfiguracionDAOImpl();
        ConfiguracionModel configuracion = configdao.obtenerConfiguracionPorId(1);
        String rutaBatch = configuracion.getRutaSunat()+"\\EjecutarSFS.bat";

        // Crear el proceso para ejecutar el batch
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", rutaBatch);

        // Redirigir la salida estándar y de error al System.out
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

        // Iniciar el proceso
        Process proceso = processBuilder.start();

        // Esperar a que el proceso termine
        int resultado = proceso.waitFor();

        // Imprimir el resultado (por ejemplo, el código de salida)
        System.out.println("Resultado: " + resultado);

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, "Proceso completado exitosamente " + rutaBatch, "Éxito", JOptionPane.INFORMATION_MESSAGE);

    } catch (IOException | InterruptedException ex) {
        ex.printStackTrace();
        // Mostrar mensaje de error
        JOptionPane.showMessageDialog(this, "Error al ejecutar el proceso: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        Navegador vnav;
        try {
            vnav = new Navegador();
            centrarInternalFrame(vnav);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Lpasajes vlpje = new Lpasajes();

        centrarInternalFrame(vlpje);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void menuitemingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemingresarActionPerformed
        Login vlog = new Login(this);
        centrarInternalFrame(vlog);
    }//GEN-LAST:event_menuitemingresarActionPerformed

    private void centrarInternalFrame(JInternalFrame internalFrame) {

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

        // Ajusta las coordenadas si la ventana principal está maximizada (considerando una pantalla 16:9)
        if (getExtendedState() == javax.swing.JFrame.MAXIMIZED_BOTH) {
            x = (desktopAncho - frameAncho) / 2;
            y = (desktopAlto - frameAlto) / 2;
        }

        // Establece la posición del internal frame
        internalFrame.setLocation(x, y);

        // Hacer visible el internal frame
        internalFrame.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            UIManager.setLookAndFeel(new FastLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    public javax.swing.JMenu menuconf;
    private javax.swing.JMenuItem menuitemingresar;
    public javax.swing.JMenu menupasaje;
    public javax.swing.JMenu menusunat;
    private javax.swing.JMenuItem opconfig;
    // End of variables declaration//GEN-END:variables
}
