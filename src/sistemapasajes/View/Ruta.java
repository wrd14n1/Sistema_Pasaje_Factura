/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package sistemapasajes.View;

import java.util.List;
import java.util.Objects;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import sistemapasajes.dao.RutaDAO;
import sistemapasajes.dao.RutaDAOImpl;
import sistemapasajes.modelo.RutaModel;

/**
 *
 * @author edson
 */
public class Ruta extends javax.swing.JInternalFrame {

    String tramo;
    double precio;
    String punto1;
    String punto2;
    int filaSeleccionada;
    String idRuta;

    /**
     * Creates new form Ruta
     */
    public Ruta() {
        initComponents();
        cargarDatosRutas();
        editarFilaSeleccionada();
    }

    private void cargarDatosRutas() {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Punto 1");
        modeloTabla.addColumn("Punto 2");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Precio");

        RutaDAO rutadao = new RutaDAOImpl();
        List<RutaModel> rutas = rutadao.obtenerTodasRutas();
        for (RutaModel ruta : rutas) {
            Object[] fila = {
                ruta.getIdRuta(),
                ruta.getPunto1Ruta(),
                ruta.getPunto2Ruta(),
                ruta.getTramoRuta(),
                ruta.getPrecioRuta(),};
            modeloTabla.addRow(fila);
        }
        tabruta.setModel(modeloTabla);
    }

    private void editarFilaSeleccionada() {
        tabruta.getSelectionModel().addListSelectionListener(
                e -> {
                    if (!e.getValueIsAdjusting()) {
                        filaSeleccionada = tabruta.getSelectedRow();
                        btnguardar.setEnabled(false);
                        btnactualizar.setEnabled(true);
                        if (filaSeleccionada != -1) {
                            idRuta = tabruta.getValueAt(filaSeleccionada, 0).toString();
                            punto1 = tabruta.getValueAt(filaSeleccionada, 1).toString();
                            punto2 = tabruta.getValueAt(filaSeleccionada, 2).toString();
                            tramo = tabruta.getValueAt(filaSeleccionada, 3).toString();
                            precio = Double.parseDouble(tabruta.getValueAt(filaSeleccionada, 4).toString());

                       txtpunto1.setText(punto1);
                       txtpunto2.setText(punto2);
                       txtprecio.setText(String.valueOf(precio));
                       txttramo.setText(tramo);

                        }
                    }
                }
        );
    }

    private void limpiarCamposTexto(JTextField... camposTexto) {
        for (JTextField campo : camposTexto) {
            campo.setText("");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtpunto1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtprecio = new javax.swing.JTextField();
        btnguardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabruta = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txttramo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtpunto2 = new javax.swing.JTextField();
        btnactualizar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setClosable(true);
        setIconifiable(true);
        setTitle("Rutas de Viaje");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Punto 1:");

        txtpunto1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtpunto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpunto1KeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Costo Referencial:");

        txtprecio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnguardar.setBackground(new java.awt.Color(30, 200, 130));
        btnguardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnguardar.setForeground(new java.awt.Color(255, 255, 255));
        btnguardar.setText("Guardar");
        btnguardar.setBorder(null);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        tabruta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabruta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabruta);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Descripción de la Ruta:");

        txttramo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txttramo.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Punto 2:");

        txtpunto2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtpunto2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpunto2KeyReleased(evt);
            }
        });

        btnactualizar.setBackground(new java.awt.Color(40, 150, 180));
        btnactualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnactualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnactualizar.setText("Actualizar");
        btnactualizar.setBorder(null);
        btnactualizar.setEnabled(false);
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txttramo))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(525, 525, 525)
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(btnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 22, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(307, 307, 307))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtpunto1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtpunto2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtpunto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtpunto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttramo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        RutaDAO rutadao = new RutaDAOImpl();
        RutaModel rut = new RutaModel();
        try {
            punto1 = txtpunto1.getText();
            punto2 = txtpunto2.getText();
            tramo = txttramo.getText();
            precio = Double.parseDouble(txtprecio.getText());

            /*CARGAR dao*/
            rut.setTramoRuta(tramo);
            rut.setPrecioRuta(precio);
            rut.setPunto1Ruta(punto1);
            rut.setPunto2Ruta(punto2);
            rutadao.agregarRuta(rut);
            cargarDatosRutas();
            limpiarCamposTexto(txtpunto1, txtpunto2, txtprecio, txttramo);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }//GEN-LAST:event_btnguardarActionPerformed
    private void txtpuntoKeyReleased(java.awt.event.KeyEvent evt, JTextField textField) {
        String punto1 = txtpunto1.getText();
        String punto2 = txtpunto2.getText();
        txttramo.setText(punto1 + " - " + punto2);
    }
    private void txtpunto1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpunto1KeyReleased
        txtpuntoKeyReleased(evt, txtpunto1);
    }//GEN-LAST:event_txtpunto1KeyReleased

    private void txtpunto2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpunto2KeyReleased
        txtpuntoKeyReleased(evt, txtpunto2);
    }//GEN-LAST:event_txtpunto2KeyReleased

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnactualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabruta;
    private javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txtpunto1;
    private javax.swing.JTextField txtpunto2;
    private javax.swing.JTextField txttramo;
    // End of variables declaration//GEN-END:variables
}
