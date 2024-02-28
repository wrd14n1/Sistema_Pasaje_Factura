/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package sistemapasajes.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import sistemapasajes.dao.VehiculoDAO;
import sistemapasajes.dao.VehiculoDAOImpl;
import sistemapasajes.modelo.VehiculoModel;

/**
 *
 * @author edson
 */
public class Vehiculo extends javax.swing.JInternalFrame {

    String descripcion;
    String placa;
    int asientos;
    String conductor;
    int filaSeleccionada;

    String idVehiculo;
    String tdescripcion;

    String tplaca;
    String tasiento;
    String tconductor;

    public Vehiculo() {
        initComponents();
        cargarDatosVehiculos();
        editarFilaSeleccionada();
    }

    private void cargarDatosVehiculos() {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Placa");
        modeloTabla.addColumn("N° de Asientos");
        modeloTabla.addColumn("Conductor");

        VehiculoDAO vehidao = new VehiculoDAOImpl();
        List<VehiculoModel> vehiculos = vehidao.obtenerTodosVehiculos();
        for (VehiculoModel vehiculo : vehiculos) {
            Object[] fila = {
                vehiculo.getIdVehi(),
                vehiculo.getDescVehi(),
                vehiculo.getPlacaVehi(),
                vehiculo.getNumVehi(),
                vehiculo.getCondVehi()
            };
            modeloTabla.addRow(fila);
        }
        tabvehi.setModel(modeloTabla);
    }

    private void limpiarCamposTexto(JTextField... camposTexto) {
        for (JTextField campo : camposTexto) {
            campo.setText("");
        }
    }

    private void editarFilaSeleccionada() {
        tabvehi.getSelectionModel().addListSelectionListener(
                e -> {
                    if (!e.getValueIsAdjusting()) {
                        filaSeleccionada = tabvehi.getSelectedRow();
                        btnguardar.setEnabled(false);
                        btnactualizar.setEnabled(true);
                        if (filaSeleccionada != -1) {
                            idVehiculo = tabvehi.getValueAt(filaSeleccionada, 0).toString();
                            tdescripcion = tabvehi.getValueAt(filaSeleccionada, 1).toString();
                            tplaca = tabvehi.getValueAt(filaSeleccionada, 2).toString();
                            tasiento = tabvehi.getValueAt(filaSeleccionada, 3).toString();
                            tconductor = tabvehi.getValueAt(filaSeleccionada, 4).toString();

                            txtdescripcion.setText(tdescripcion);

                            txtplaca.setText(tplaca);
                            txtasientos.setText(tasiento);
                            txtconductor.setText(tconductor);

                        }
                    }
                }
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtdescripcion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtplaca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtasientos = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtconductor = new javax.swing.JTextField();
        btnguardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabvehi = new javax.swing.JTable();
        btnactualizar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setClosable(true);
        setIconifiable(true);
        setTitle("Vehículos");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Descripción:");

        txtdescripcion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Placa:");

        txtplaca.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("N° de Asientos");

        txtasientos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Conductor:");

        txtconductor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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

        tabvehi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabvehi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabvehi);

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtconductor)
                                    .addComponent(txtdescripcion)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtplaca, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtasientos, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtplaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txtasientos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtconductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        VehiculoDAO vehiculodao = new VehiculoDAOImpl();
        VehiculoModel vehi = new VehiculoModel();
        try {
            descripcion = txtdescripcion.getText();
            placa = txtplaca.getText();
            asientos = Integer.parseInt(txtasientos.getText());
            conductor = txtconductor.getText();

            /*Cargar DAO*/
            vehi.setDescVehi(descripcion);
            vehi.setPlacaVehi(placa);
            vehi.setNumVehi(asientos);
            vehi.setCondVehi(conductor);
            vehiculodao.agregarVehiculo(vehi);
            cargarDatosVehiculos();
            limpiarCamposTexto(txtdescripcion, txtplaca, txtasientos, txtconductor);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        if (filaSeleccionada != -1) {
            try {
                            VehiculoDAO vehiculodao = new VehiculoDAOImpl();
            VehiculoModel vehiculo = new VehiculoModel();
            vehiculo.setIdVehi(Integer.parseInt( tabvehi.getValueAt(filaSeleccionada, 0).toString()));
            vehiculo.setDescVehi(txtdescripcion.getText());
            vehiculo.setPlacaVehi(txtplaca.getText());
            vehiculo.setNumVehi(Integer.parseInt(txtasientos.getText()));
            vehiculo.setCondVehi(txtconductor.getText());
            vehiculodao.actualizarVehiculo(vehiculo);
            cargarDatosVehiculos();
               limpiarCamposTexto(txtdescripcion, txtplaca, txtasientos, txtconductor);
            } catch (Exception e) {
                 System.out.println("Error: " + e);
            }


        }else {
            // Indicar al usuario que no hay fila seleccionada para actualizar
            JOptionPane.showMessageDialog(this, "Selecciona una fila para actualizar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnactualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabvehi;
    private javax.swing.JTextField txtasientos;
    private javax.swing.JTextField txtconductor;
    private javax.swing.JTextField txtdescripcion;
    private javax.swing.JTextField txtplaca;
    // End of variables declaration//GEN-END:variables
}
