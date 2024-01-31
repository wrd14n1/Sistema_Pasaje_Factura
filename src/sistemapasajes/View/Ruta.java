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
    /**
     * Creates new form Ruta
     */
    public Ruta() {
        initComponents();
        cargarDatosRutas(); 
    }
    private void cargarDatosRutas(){
       DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Punto 1");
        modeloTabla.addColumn("Punto 2");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Precio");
        
        RutaDAO rutadao = new RutaDAOImpl();
        List<RutaModel> rutas = rutadao.obtenerTodasRutas();
        for (RutaModel ruta: rutas) {
            Object[] fila ={
                ruta.getIdRuta(),
                ruta.getPunto1Ruta(),
                ruta.getPunto2Ruta(),
                ruta.getTramoRuta(),
                ruta.getPrecioRuta(),
                
            };
            modeloTabla.addRow(fila);
        }
        tabruta.setModel(modeloTabla);
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

        setClosable(true);
        setIconifiable(true);
        setTitle("Rutas de Viaje");

        jLabel1.setText("Punto 1:");

        txtpunto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpunto1KeyReleased(evt);
            }
        });

        jLabel2.setText("Costo Referencial:");

        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar.png"))); // NOI18N
        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

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

        jLabel3.setText("Descripción de la Ruta:");

        txttramo.setEnabled(false);

        jLabel4.setText("Punto 2:");

        txtpunto2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpunto2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtpunto1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtpunto2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttramo, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnguardar)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnguardar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txttramo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
       RutaDAO rutadao = new RutaDAOImpl();
       RutaModel rut = new RutaModel();
        try {
            punto1=txtpunto1.getText();
            punto2=txtpunto2.getText();
            tramo= txttramo.getText();
            precio = Double.parseDouble(txtprecio.getText());
            
            /*CARGAR dao*/
            rut.setTramoRuta(tramo);
            rut.setPrecioRuta(precio);
            rut.setPunto1Ruta(punto1);
            rut.setPunto2Ruta(punto2);
            rutadao.agregarRuta(rut);
            cargarDatosRutas();
            limpiarCamposTexto(txtpunto1,txtpunto2,txtprecio,txttramo);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
