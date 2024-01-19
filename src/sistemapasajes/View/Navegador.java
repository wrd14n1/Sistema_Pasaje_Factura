/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package sistemapasajes.View;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class Navegador extends javax.swing.JInternalFrame {

    /**
     * Creates new form Navegador
     */
   
public Navegador() throws PropertyVetoException {
     initComponents();
     setMaximum(true);
    try {
        NativeInterface.open(); // Asegúrate de que esto se llame antes de cualquier componente relacionado con SWT
        JWebBrowser navegador = new JWebBrowser();
        this.WebPanel.setLayout(new BorderLayout());
        navegador.navigate("http://localhost:9000/#");
        this.WebPanel.add(navegador);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WebPanel = new javax.swing.JPanel();

        setClosable(true);
        setTitle("SFS SUNAT");

        javax.swing.GroupLayout WebPanelLayout = new javax.swing.GroupLayout(WebPanel);
        WebPanel.setLayout(WebPanelLayout);
        WebPanelLayout.setHorizontalGroup(
            WebPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 905, Short.MAX_VALUE)
        );
        WebPanelLayout.setVerticalGroup(
            WebPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(WebPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(WebPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel WebPanel;
    // End of variables declaration//GEN-END:variables
}
