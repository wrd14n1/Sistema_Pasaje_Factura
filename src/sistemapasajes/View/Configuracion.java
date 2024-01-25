/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package sistemapasajes.View;

import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import sistemapasajes.dao.ConfiguracionDAO;
import sistemapasajes.dao.ConfiguracionDAOImpl;
import sistemapasajes.dao.RutaArchivoDAO;
import sistemapasajes.dao.RutaArchivoDAOImpl;
import sistemapasajes.modelo.ConfiguracionModel;
import sistemapasajes.modelo.RutaArchivoModel;

/**
 *
 * @author edson
 */
public class Configuracion extends javax.swing.JInternalFrame {

    String ruc;
    String razon;
    String nomcom;
    String direccion;
    String sunat;
    String archivo;
    String rsunat;
    String rarchivo;
    String rlogo;
    String logo;
    String txt1;
    String txt2;
    String txt3;
    int filaSeleccionada;

    /**
     * Creates new form Configuracion
     */
    public Configuracion() {
        initComponents();
        cargarDatosConfiguracion();

        // Agregar el manejador de eventos de selección de la tabla
        tabconfig.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                // Obtener el índice de la fila seleccionada
                filaSeleccionada = tabconfig.getSelectedRow();
                btnguardar.setEnabled(false);
                btnactualizar.setEnabled(true);
                // Verificar si hay una fila seleccionada
                if (filaSeleccionada != -1) {
                    // Obtener los valores de la fila seleccionada
                    String idConf = tabconfig.getValueAt(filaSeleccionada, 0).toString();
                    String rucConf = tabconfig.getValueAt(filaSeleccionada, 1).toString();
                    String razonConf = tabconfig.getValueAt(filaSeleccionada, 2).toString();
                    String nomcomConf = tabconfig.getValueAt(filaSeleccionada, 3).toString();
                    String direcConf = tabconfig.getValueAt(filaSeleccionada, 4).toString();
                    String rutaSunat = tabconfig.getValueAt(filaSeleccionada, 5).toString();
                    String rutaArchivo = tabconfig.getValueAt(filaSeleccionada, 6).toString();
                    String rutalogo = tabconfig.getValueAt(filaSeleccionada, 7).toString();
                    String text1 = tabconfig.getValueAt(filaSeleccionada, 8).toString();
                    String text2 = tabconfig.getValueAt(filaSeleccionada, 9).toString();
                    String text3 = tabconfig.getValueAt(filaSeleccionada, 10).toString();

                    // Cargar los valores en los campos de texto
                    txtruc.setText(rucConf);
                    txtrazon.setText(razonConf);
                    txtnom.setText(nomcomConf);
                    txtdirec.setText(direcConf);
                    txtsunat.setText(rutaSunat);
                    txtarchivo.setText(rutaArchivo);
                    txtlogo.setText(rutalogo);
                    txtAr1.setText(text1);
                    txtAr2.setText(text2);
                    txtAr3.setText(text3);

                    // Puedes continuar con el resto de los campos según sea necesario
                }
            }
        });
    }

    private void cargarDatosConfiguracion() {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("RUC");
        modeloTabla.addColumn("Razón Social");
        modeloTabla.addColumn("Nombre Comercial");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Ruta Sunat");
        modeloTabla.addColumn("Ruta de Archivos");
        modeloTabla.addColumn("Ruta de Logo");
        modeloTabla.addColumn("Texto 1");
        modeloTabla.addColumn("Texto 2");
        modeloTabla.addColumn("Texto 3");

        ConfiguracionDAO configdao = new ConfiguracionDAOImpl();
        List<ConfiguracionModel> configuraciones = configdao.obtenerTodasConfiguraciones();

        for (ConfiguracionModel configuracion : configuraciones) {
            Object[] fila = {
                configuracion.getIdConf(),
                configuracion.getRucConf(),
                configuracion.getRazonConf(),
                configuracion.getNomcomConf(),
                configuracion.getDirecConf(),
                configuracion.getRutaSunat(),
                configuracion.getRutaArchivo(),
                configuracion.getLogoConf(),
                configuracion.getTxt1Conf(),
                configuracion.getTxt2Conf(),
                configuracion.getTxt3Conf()
            };
            modeloTabla.addRow(fila);
        }

        tabconfig.setModel(modeloTabla);
    }

    private void limpiarCamposTexto(JTextField... camposTexto) {
        for (JTextField campo : camposTexto) {
            campo.setText("");
        }
    }

    // Sobrecargar el método para admitir JTextArea
    private void limpiarCamposTexto(JTextArea... camposTexto) {
        for (JTextArea campo : camposTexto) {
            campo.setText("");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtruc = new javax.swing.JTextField();
        txtrazon = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtnom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtdirec = new javax.swing.JTextField();
        btnguardar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtsunat = new javax.swing.JTextField();
        txtarchivo = new javax.swing.JTextField();
        btnsunat = new javax.swing.JButton();
        btnarchivo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabconfig = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtlogo = new javax.swing.JTextField();
        btnlogo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAr2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAr1 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAr3 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnactualizar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Configuración General");

        jLabel1.setText("RUC:");

        jLabel2.setText("Razón Social:");

        jLabel3.setText("Nombre Comercial:");

        jLabel4.setText("Dirección:");

        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        jLabel5.setText("Ruta Facturador:");

        jLabel6.setText("Ruta Archivos:");

        txtsunat.setEditable(false);

        txtarchivo.setEditable(false);

        btnsunat.setText("Buscar");
        btnsunat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsunatActionPerformed(evt);
            }
        });

        btnarchivo.setText("Buscar");
        btnarchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnarchivoActionPerformed(evt);
            }
        });

        tabconfig.setModel(new javax.swing.table.DefaultTableModel(
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
        tabconfig.setAutoscrolls(false);
        jScrollPane1.setViewportView(tabconfig);

        jLabel7.setText("Ruta Logo:");

        txtlogo.setEditable(false);

        btnlogo.setText("Buscar");
        btnlogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoActionPerformed(evt);
            }
        });

        txtAr2.setColumns(20);
        txtAr2.setRows(5);
        jScrollPane2.setViewportView(txtAr2);

        txtAr1.setColumns(20);
        txtAr1.setRows(5);
        jScrollPane3.setViewportView(txtAr1);

        txtAr3.setColumns(20);
        txtAr3.setRows(5);
        jScrollPane4.setViewportView(txtAr3);

        jLabel8.setText("Texto 1:");

        jLabel9.setText("Texto 2:");

        jLabel10.setText("Texto 3:");

        btnactualizar.setText("Actualizar");
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtrazon)
                            .addComponent(txtnom)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnguardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnactualizar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtdirec)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtsunat)
                                    .addComponent(txtarchivo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnsunat, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnarchivo, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtlogo)
                                .addGap(6, 6, 6)
                                .addComponent(btnlogo))
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtrazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtdirec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtsunat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsunat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtarchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnarchivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtlogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(btnlogo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardar)
                    .addComponent(btnactualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed

        ConfiguracionDAO configdao = new ConfiguracionDAOImpl();
        ConfiguracionModel config = new ConfiguracionModel();
        try {
            ruc = txtruc.getText();
            razon = txtrazon.getText();
            nomcom = txtnom.getText();
            direccion = txtdirec.getText();
            rsunat = sunat;
            rarchivo = archivo;
            rlogo = logo;
            txt1 = txtAr1.getText();
            txt2 = txtAr2.getText();
            txt3 = txtAr3.getText();

            /* Cargar DAO*/
            config.setRucConf(ruc);
            config.setRazonConf(razon);
            config.setNomcomConf(nomcom);
            config.setDirecConf(direccion);
            config.setRutaSunat(rsunat);
            config.setRutaArchivo(rarchivo);
            config.setLogoConf(logo);
            config.setTxt1Conf(txt1);
            config.setTxt2Conf(txt2);
            config.setTxt3Conf(txt3);
            configdao.agregarConfiguracion(config);
            //System.out.println("Configuración Agregada Correctacmente");
            cargarDatosConfiguracion();
            limpiarCamposTexto(txtruc, txtrazon, txtnom, txtdirec, txtsunat, txtarchivo, txtlogo);
            limpiarCamposTexto(txtAr1,txtAr2,txtAr3);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnsunatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsunatActionPerformed
        /*File chooser*/
        // Seleccionar la carpeta utilizando JFileChooser
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int seleccion = chooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File carpetaSeleccionada = chooser.getSelectedFile();
            sunat = carpetaSeleccionada.getAbsolutePath();

            // Mostrar la ruta en el campo de texto txtsunat
            txtsunat.setText(sunat);
        } else {
            // El usuario canceló la selección, puedes manejarlo según tus necesidades
            return;
        }
    }//GEN-LAST:event_btnsunatActionPerformed

    private void btnarchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnarchivoActionPerformed
        /*File chooser*/
        // Seleccionar la carpeta utilizando JFileChooser
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int seleccion = chooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File carpetaSeleccionada = chooser.getSelectedFile();
            archivo = carpetaSeleccionada.getAbsolutePath();

            // Mostrar la ruta en el campo de texto txtsunat
            txtarchivo.setText(archivo);
        } else {
            // El usuario canceló la selección, puedes manejarlo según tus necesidades
            return;
        }
    }//GEN-LAST:event_btnarchivoActionPerformed

    private void btnlogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoActionPerformed
        // Seleccionar el archivo de imagen utilizando JFileChooser
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Imagen", "jpg", "jpeg", "png", "gif");
        chooser.setFileFilter(filter);
        int seleccion = chooser.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = chooser.getSelectedFile();
            logo = archivoSeleccionado.getAbsolutePath();

            // Mostrar la ruta en el campo de texto txtarchivo
            txtlogo.setText(logo);
        } else {
            // El usuario canceló la selección, puedes manejarlo según tus necesidades
            // Por ejemplo, mostrar un mensaje o realizar alguna acción específica
        }
    }//GEN-LAST:event_btnlogoActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        // Verificar si hay una fila seleccionada
        if (filaSeleccionada != -1) {
            ConfiguracionDAO configdao = new ConfiguracionDAOImpl();
            ConfiguracionModel config = new ConfiguracionModel();
            
            RutaArchivoDAO rutaarchivodao = new RutaArchivoDAOImpl();
            RutaArchivoModel rutaarchivo = new RutaArchivoModel();
            
            try {
                // Obtener los valores de los campos de entrada
                ruc = txtruc.getText();
                razon = txtrazon.getText();
                nomcom = txtnom.getText();
                direccion = txtdirec.getText();
                rsunat = txtsunat.getText(); // Utilizar el texto directamente
                rarchivo = txtarchivo.getText(); // Utilizar el texto directamente
                rlogo = txtlogo.getText(); // Utilizar el texto directamente
                txt1 = txtAr1.getText();
                txt2 = txtAr2.getText();
                txt3 = txtAr3.getText();

                // Cargar el modelo ConfiguracionModel con los nuevos valores
                config.setIdConf(Integer.parseInt(tabconfig.getValueAt(filaSeleccionada, 0).toString()));
                config.setRucConf(ruc);
                config.setRazonConf(razon);
                config.setNomcomConf(nomcom);
                config.setDirecConf(direccion);
                config.setRutaSunat(rsunat);
                config.setRutaArchivo(rarchivo);
                config.setLogoConf(rlogo);
                config.setTxt1Conf(txt1);
                config.setTxt2Conf(txt2);
                config.setTxt3Conf(txt3);

                // Actualizar la configuración en la base de datos
                configdao.actualizarConfiguracion(config);
                
                //actualizar rutas 
                rutaarchivo.setDescRutaArchivo(rarchivo+"\\sunat_archivos\\sfs\\FIRMA\\");
                rutaarchivo.setEmpRutaArchivo(ruc);
                rutaarchivo.setTituloRutaArchivo("hash");
                rutaarchivodao.actualizarRuta(rutaarchivo);
                
                rutaarchivo.setDescRutaArchivo(rarchivo+"\\sunat_archivos\\sfs\\ORIDAT\\");
                rutaarchivo.setEmpRutaArchivo(ruc);
                rutaarchivo.setTituloRutaArchivo("qr");
                  rutaarchivodao.actualizarRuta(rutaarchivo);
                cargarDatosConfiguracion();
                
                limpiarCamposTexto(txtruc, txtrazon, txtnom, txtdirec, txtsunat, txtarchivo, txtlogo);
                limpiarCamposTexto(txtAr1,txtAr2,txtAr3);

                // Restablecer la fila seleccionada
                filaSeleccionada = -1;

            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        } else {
            // Indicar al usuario que no hay fila seleccionada para actualizar
            JOptionPane.showMessageDialog(this, "Selecciona una fila para actualizar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnactualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btnarchivo;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnlogo;
    private javax.swing.JButton btnsunat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tabconfig;
    private javax.swing.JTextArea txtAr1;
    private javax.swing.JTextArea txtAr2;
    private javax.swing.JTextArea txtAr3;
    private javax.swing.JTextField txtarchivo;
    private javax.swing.JTextField txtdirec;
    private javax.swing.JTextField txtlogo;
    private javax.swing.JTextField txtnom;
    private javax.swing.JTextField txtrazon;
    private javax.swing.JTextField txtruc;
    private javax.swing.JTextField txtsunat;
    // End of variables declaration//GEN-END:variables
}
