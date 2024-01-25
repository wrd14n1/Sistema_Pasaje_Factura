/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package sistemapasajes.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import sistemapasajes.ArchivosPlanos;
import sistemapasajes.dao.ApiDAO;
import sistemapasajes.dao.ApiDAOImpl;
import sistemapasajes.dao.ComprobanteDAO;
import sistemapasajes.dao.ComprobanteDAOImpl;
import sistemapasajes.dao.ConfiguracionDAO;
import sistemapasajes.dao.ConfiguracionDAOImpl;
import sistemapasajes.dao.DcomprobanteDAO;
import sistemapasajes.dao.DcomprobanteDAOImpl;
import sistemapasajes.dao.EmpresaDAO;
import sistemapasajes.dao.EmpresaDAOImpl;
import sistemapasajes.dao.PasajeroDAO;
import sistemapasajes.dao.PasajeroDAOimpl;
import sistemapasajes.dao.RutaDAO;
import sistemapasajes.dao.RutaDAOImpl;
import sistemapasajes.dao.SerieDAO;
import sistemapasajes.dao.SerieDAOImpl;
import sistemapasajes.dao.VehiculoDAO;
import sistemapasajes.dao.VehiculoDAOImpl;
import sistemapasajes.modelo.ComprobanteModel;
import sistemapasajes.modelo.DcomprobanteModel;
import sistemapasajes.modelo.EmpresaModel;
import sistemapasajes.modelo.PasajeroModel;
import sistemapasajes.modelo.RutaModel;
import sistemapasajes.modelo.SerieModel;
import sistemapasajes.modelo.VehiculoModel;

/**
 *
 * @author edson
 */
public class Pasajes extends javax.swing.JInternalFrame {

    String json;
    List<RutaModel> listarutas;
    int numero;
    String numserie;
    String codserie;
    String rutasunat;
    String rucempresa;
    String tipocompsunat;
    String tipdoccli;

    /**
     * Creates new form Pasajes
     */
    public Pasajes() {
        initComponents();
        inicializarComboBoxRutas();
        inicializarComboBoxVehiculos();

        String rutalogo;
        ConfiguracionDAO configdao = new ConfiguracionDAOImpl();
        rutalogo = configdao.obtenerConfiguracionPorId(1).getLogoConf();
        System.out.println(rutalogo);
        mostrarImagen(rutalogo);

        SerieDAO seriedao = new SerieDAOImpl();
        SerieModel serie = seriedao.obtenerTipoSerie("BOLETA", "B001");
        numero = serie.getNumSerie() + 1;
        codserie = serie.getCodSerie();
        numserie = codserie + "-" + numero;
        String tiposerie = serie.getTipoSerie();
        Date hoy = new Date();
        txtcomprobante.setText(numserie);//txtcomprobante.setText(numserie);
        txttipocomprobante.setText(tiposerie);
        //jDateChooser1.setDate(hoy);
        rutasunat = configdao.obtenerConfiguracionPorId(1).getRutaSunat();
        rucempresa = configdao.obtenerConfiguracionPorId(1).getRucConf();

    }
    // Método para cargar y mostrar la imagen en el JLabel

    public void mostrarImagen(String rutaImagen) {
        try {
            // Lee la imagen desde el archivo
            BufferedImage imagen = ImageIO.read(new File(rutaImagen));

            // Obtén el tamaño del JLabel
            int labelAncho = lbllogo.getWidth();
            int labelAlto = lbllogo.getHeight();

            // Si el ancho o alto es 0, establece un tamaño predeterminado
            if (labelAncho == 0) {
                labelAncho = 140; // ajusta este valor según tus necesidades
            }
            if (labelAlto == 0) {
                labelAlto = 70; // ajusta este valor según tus necesidades
            }

            // Escala la imagen para ajustarla al JLabel (opcional)
            ImageIcon icono = new ImageIcon(imagen.getScaledInstance(
                    labelAncho, labelAlto, Image.SCALE_SMOOTH));

            // Establece la imagen en el JLabel
            lbllogo.setIcon(icono);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inicializarComboBoxRutas() {
        cbruta.removeAllItems();
        RutaDAO rutadao = new RutaDAOImpl();
        listarutas = rutadao.obtenerTodasRutas();
        cbruta.addItem("Seleccionar");
        for (int i = 0; i < listarutas.size(); i++) {
            cbruta.addItem(listarutas.get(i).getTramoRuta());

        }
        // Agregar ActionListener que referencia a la función separada
        cbruta.addActionListener(this::actualizarPrecio);
    }

    // Función separada para actualizar el precio
    private void actualizarPrecio(ActionEvent e) {
        // Obtener la ruta seleccionada
        String rutaSeleccionada = (String) cbruta.getSelectedItem();

        // Buscar la ruta en la lista
        for (RutaModel ruta : listarutas) {
            if (ruta.getTramoRuta().equals(rutaSeleccionada)) {
                // Actualizar el precio en el JTextField
                txtprecio.setText(String.valueOf(ruta.getPrecioRuta()));
                txtorigen.setText(String.valueOf(ruta.getPunto1Ruta()));
                txtdestino.setText(String.valueOf(ruta.getPunto2Ruta()));
                break;  // No es necesario seguir buscando
            }
        }
    }

    private void inicializarComboBoxVehiculos() {
        cbvehiculo.removeAllItems();
        VehiculoDAO vehiculodao = new VehiculoDAOImpl();
        List<VehiculoModel> listavehiculos = vehiculodao.obtenerTodosVehiculos();
        cbvehiculo.addItem("Seleccionar");
        for (int i = 0; i < listavehiculos.size(); i++) {
            cbvehiculo.addItem(listavehiculos.get(i).getPlacaVehi() + " - " + listavehiculos.get(i).getDescVehi());
        }
    }

    // Otros métodos de tu clase
    private String realizarConsultaApi(String apiUrl) throws IOException {
        int intentosMaximos = 5;
        int intentoActual = 0;

        while (intentoActual < intentosMaximos) {
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Establecer el método de solicitud
                connection.setRequestMethod("GET");

                // Obtener la respuesta
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    json = response.toString();
                    return response.toString();
                } finally {
                    // Asegúrate de cerrar la conexión
                    connection.disconnect();
                }
            } catch (IOException ex) {
                // Manejar la excepción (puedes registrarla o imprimir el mensaje)
                System.out.println("Error en el intento " + (intentoActual + 1) + ": " + ex.getMessage());

                // Incrementar el contador de intentos y esperar antes de reintentar
                intentoActual++;
                esperarAntesDeReintentar();
            }
        }

        // Si llegamos aquí, todos los intentos han fallado
        throw new IOException("Se agotaron los intentos para realizar la consulta API");
    }

    private void esperarAntesDeReintentar() {
        try {
            // Puedes ajustar el tiempo de espera según tus necesidades
            Thread.sleep(500); // Esperar 1 segundo antes de reintentar
        } catch (InterruptedException e) {
            // Manejar la excepción si es necesario
            Thread.currentThread().interrupt();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbllogo = new javax.swing.JLabel();
        pnlfactura = new javax.swing.JPanel();
        txtruc = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnbempresa = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtrazon = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        btnbpersona = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtpasajero = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        chkbfactura = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        txtcomprobante = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txttipocomprobante = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbruta = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtprecio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtorigen = new javax.swing.JTextField();
        txtdestino = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbvehiculo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        btncambiar = new javax.swing.JButton();
        btncomprobante = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Pasajes");

        lbllogo.setBackground(new java.awt.Color(255, 255, 51));
        lbllogo.setMaximumSize(new java.awt.Dimension(617, 97));

        pnlfactura.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Facturación"));
        pnlfactura.setEnabled(false);
        pnlfactura.setName(""); // NOI18N

        txtruc.setEnabled(false);

        jLabel11.setText("RUC:");

        btnbempresa.setText("Buscar");
        btnbempresa.setEnabled(false);
        btnbempresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbempresaActionPerformed(evt);
            }
        });

        jLabel12.setText("Datos:");

        txtrazon.setEditable(false);

        txtdireccion.setEditable(false);

        jLabel13.setText("Dirección:");

        javax.swing.GroupLayout pnlfacturaLayout = new javax.swing.GroupLayout(pnlfactura);
        pnlfactura.setLayout(pnlfacturaLayout);
        pnlfacturaLayout.setHorizontalGroup(
            pnlfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlfacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlfacturaLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnbempresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtrazon, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE))
                    .addGroup(pnlfacturaLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdireccion)))
                .addContainerGap())
        );
        pnlfacturaLayout.setVerticalGroup(
            pnlfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlfacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(btnbempresa)
                    .addComponent(jLabel12)
                    .addComponent(txtrazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Pasajero"));

        jLabel3.setText("DNI:");

        btnbpersona.setText("Buscar");
        btnbpersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbpersonaActionPerformed(evt);
            }
        });

        jLabel4.setText("Datos:");

        txtpasajero.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnbpersona)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtpasajero)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnbpersona)
                        .addComponent(jLabel4)
                        .addComponent(txtpasajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Comprobante"));

        chkbfactura.setText("Requiere Factura");
        chkbfactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkbfacturaActionPerformed(evt);
            }
        });

        jLabel10.setText("N° de Comprobante:");

        txtcomprobante.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        txtcomprobante.setEnabled(false);

        jLabel5.setText("Fecha de emisión:");

        txttipocomprobante.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        txttipocomprobante.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(chkbfactura)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txttipocomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtcomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttipocomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(26, 26, 26)
                .addComponent(chkbfactura))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la Ruta"));

        jLabel1.setText("Ruta:");

        cbruta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbrutaActionPerformed(evt);
            }
        });

        jLabel6.setText("Soles");

        jLabel7.setText("Precio: S/");

        txtorigen.setEnabled(false);

        txtdestino.setEnabled(false);

        jLabel8.setText("Origen:");

        jLabel2.setText("Vehiculo:");

        jLabel9.setText("Destino:");

        btncambiar.setText("Cambiar");
        btncambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncambiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtorigen, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtdestino, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btncambiar)
                        .addGap(58, 58, 58))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbruta, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbvehiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtorigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(btncambiar)
                    .addComponent(txtdestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbvehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        btncomprobante.setText("Generar Comprobante");
        btncomprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncomprobanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlfactura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbllogo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btncomprobante)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbllogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlfactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncomprobante)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlfactura.getAccessibleContext().setAccessibleName("Datos de Empresa");
        pnlfactura.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbpersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbpersonaActionPerformed
        ApiDAO apidao = new ApiDAOImpl();
        String dni = txtdni.getText().trim();
        //CONSULTAR A API PARA PODER REGISTRARLO
        String url = null;// Obtener el texto y eliminar espacios en blanco

        if (!dni.isEmpty()) { // Verificar si el texto no está vacío
            PasajeroDAO pasajerodao = new PasajeroDAOimpl();
            PasajeroModel pasajero = pasajerodao.obtenerPasajeroPorDNI(dni);
            PasajeroModel psjemodel = new PasajeroModel();

            if (pasajero != null) {
                int pasajero_existe = pasajero.getIdPsje();
                System.out.println(pasajero_existe);
                txtpasajero.setText(pasajero.getApePsje() + " " + pasajero.getNomPsje());
            } else {
                // No se encontró el pasajero, muestra un JOptionPane
                JOptionPane.showMessageDialog(null, "Pasajero no encontrado", "Información", JOptionPane.INFORMATION_MESSAGE);

                //CONSULTAR A API PARA PODER REGISTRARLO
                url = apidao.obtenerApiPorId(1).getUrlApi() + dni;
                try {
                    realizarConsultaApi(url);
                } catch (IOException ex) {
                    Logger.getLogger(Pasajes.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    System.out.println(realizarConsultaApi(url));

                    // Crear un objeto Gson
                    Gson gson = new Gson();

                    // Analizar el JSON y convertirlo en un objeto JsonElement
                    JsonElement jsonElement = gson.fromJson(json, JsonElement.class);

                    // Obtener los valores deseados del JsonElement
                    String nombrecompleto = jsonElement.getAsJsonObject().get("nombre").getAsString();
                    String nombres = jsonElement.getAsJsonObject().get("nombres").getAsString();
                    String apellidos = jsonElement.getAsJsonObject().get("apellidoPaterno").getAsString() + " " + jsonElement.getAsJsonObject().get("apellidoMaterno").getAsString();
                    String numdoc = jsonElement.getAsJsonObject().get("numeroDocumento").getAsString();

                    txtpasajero.setText(nombrecompleto);
                    psjemodel.setNumdocPsje(numdoc);
                    psjemodel.setNomPsje(nombres);
                    psjemodel.setApePsje(apellidos);
                    pasajerodao.agregarPasajero(psjemodel);

                } catch (IOException ex) {
                    Logger.getLogger(Pasajes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            System.out.println(url);
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un DNI de pasajero válido antes de consultar.", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Ingrese un DNI de pasajero válido antes de consultar.");
        }

    }//GEN-LAST:event_btnbpersonaActionPerformed

    private void btncambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncambiarActionPerformed
        String cambio;
        cambio = txtorigen.getText();
        txtorigen.setText(txtdestino.getText());
        txtdestino.setText(cambio);
    }//GEN-LAST:event_btncambiarActionPerformed

    private void cbrutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbrutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbrutaActionPerformed

    private void chkbfacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkbfacturaActionPerformed
        SerieDAO seriedao = new SerieDAOImpl();
        SerieModel serie;

        String tiposerie;

        if (chkbfactura.isSelected()) {
            serie = seriedao.obtenerTipoSerie("FACTURA", "F001");
            numero = serie.getNumSerie() + 1;
            codserie = serie.getCodSerie();
            numserie = codserie + "-" + numero;
            tiposerie = serie.getTipoSerie();
            System.out.println("holis");
            pnlfactura.setEnabled(true);
            txtruc.setEnabled(true);
            btnbempresa.setEnabled(true);
            tipdoccli = "6";
        } else {
            serie = seriedao.obtenerTipoSerie("BOLETA", "B001");
            numero = serie.getNumSerie() + 1;
            codserie = serie.getCodSerie();
            numserie = codserie + "-" + numero;
            tiposerie = serie.getTipoSerie();
            System.out.println("adios");
            pnlfactura.setEnabled(false);
            txtruc.setEnabled(false);
            btnbempresa.setEnabled(false);
            tipdoccli = "1";
        }
        txtcomprobante.setText(numserie);
        txttipocomprobante.setText(tiposerie);

    }//GEN-LAST:event_chkbfacturaActionPerformed

    private void btnbempresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbempresaActionPerformed
        ApiDAO apidao = new ApiDAOImpl();
        String ruc = txtruc.getText().trim();
        //CONSULTAR A API PARA PODER REGISTRARLO
        String url = null;// Obtener el texto y eliminar espacios en blanco

        if (!ruc.isEmpty()) { // Verificar si el texto no está vacío
            EmpresaDAO empresadao = new EmpresaDAOImpl();
            EmpresaModel empresa = empresadao.obtenerEmpresaporRuc(ruc);
            EmpresaModel empresamodel = new EmpresaModel();

            if (empresa != null) {
                int empresa_existe = empresa.getIdEmp();
                System.out.println(empresa_existe);
                txtrazon.setText(empresa.getRazonEmp());
                txtdireccion.setText(empresa.getDireccionEmp());
            } else {
                // No se encontró el pasajero, muestra un JOptionPane
                JOptionPane.showMessageDialog(null, "Empresa no encontrada", "Información", JOptionPane.INFORMATION_MESSAGE);

                //CONSULTAR A API PARA PODER REGISTRARLO
                url = apidao.obtenerApiPorId(2).getUrlApi() + ruc;
                try {
                    realizarConsultaApi(url);
                } catch (IOException ex) {
                    Logger.getLogger(Pasajes.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    System.out.println(realizarConsultaApi(url));

                    // Crear un objeto Gson
                    Gson gson = new Gson();

                    // Analizar el JSON y convertirlo en un objeto JsonElement
                    JsonElement jsonElement = gson.fromJson(json, JsonElement.class);

                    // Obtener los valores deseados del JsonElement
                    String nombrecompleto = jsonElement.getAsJsonObject().get("nombre").getAsString();
                    String tipodocumento = jsonElement.getAsJsonObject().get("tipoDocumento").getAsString();
                    String numdoc = jsonElement.getAsJsonObject().get("numeroDocumento").getAsString();
                    String direccion = jsonElement.getAsJsonObject().get("direccion").getAsString() + " " + jsonElement.getAsJsonObject().get("distrito").getAsString()
                            + " " + jsonElement.getAsJsonObject().get("provincia").getAsString() + " " + jsonElement.getAsJsonObject().get("departamento").getAsString();
                    String ubigeo = jsonElement.getAsJsonObject().get("ubigeo").getAsString();
                    String distrito = jsonElement.getAsJsonObject().get("distrito").getAsString();
                    String provincia = jsonElement.getAsJsonObject().get("provincia").getAsString();
                    String departamento = jsonElement.getAsJsonObject().get("departamento").getAsString();

                    txtrazon.setText(nombrecompleto);
                    txtdireccion.setText(direccion);

                    empresamodel.setTipodocEmp(tipodocumento);
                    empresamodel.setNumdocEmp(numdoc);
                    empresamodel.setRazonEmp(nombrecompleto);
                    empresamodel.setDireccionEmp(direccion);
                    empresamodel.setUbigeoEmp(ubigeo);
                    empresamodel.setDistritoEmp(distrito);
                    empresamodel.setProvinciaEmp(provincia);
                    empresamodel.setDepartamentoEmp(departamento);
                    empresadao.agregarEmpresa(empresamodel);

                } catch (IOException ex) {
                    Logger.getLogger(Pasajes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            System.out.println(url);
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un RUC válido antes de consultar.", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Ingrese un DNI de pasajero válido antes de consultar.");
        }
    }//GEN-LAST:event_btnbempresaActionPerformed

    private void btncomprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncomprobanteActionPerformed
        if (!"".equals(txtdni.getText()) && jDateChooser1.getDate()!=null) {

            try {

                //ARCHIVOS PLANOS
                ArchivosPlanos archivos = new ArchivosPlanos();

                ComprobanteDAO comprobantedao = new ComprobanteDAOImpl();
                DcomprobanteDAO dcomprobantedao = new DcomprobanteDAOImpl();

                // Obtener datos del formulario
                String tipo = txttipocomprobante.getText();
                String serie = txtcomprobante.getText();
                String cliente = chkbfactura.isSelected() ? txtrazon.getText() : txtpasajero.getText();
                String docliente = chkbfactura.isSelected() ? txtruc.getText() : txtdni.getText();
                String servicio = "SERVICIO DE TRANSPORTE DE PASAJEROS Origen: " + txtorigen.getText() + " Destino: "
                        + txtdestino.getText() + "PASAJERO: " + txtpasajero.getText();

                Date fecha = jDateChooser1.getDate();
                String fechaformato = convertirFecha(fecha);

                Date hora = new Date();
                SimpleDateFormat horaFormato = new SimpleDateFormat("HH:mm:ss");

                String horatexto = horaFormato.format(hora);

                String moneda = "PEN";
                String medio = "Contado";
                Double total = Double.valueOf(txtprecio.getText());
                Double igv = total * 0.18;
                Double totalgrav = total - igv;

                String fechaformato1 = convertirFecha(fecha);
                String fechaformato2 = convertirFecha(fecha);

                String estado = "NO GENERADO";

                // Crear comprobante
                ComprobanteModel comprobante = new ComprobanteModel();
                comprobante.setTipoComp(tipo);
                comprobante.setSerieComp(serie);
                comprobante.setDocclienteComp(docliente);
                comprobante.setClienteComp(cliente);
                comprobante.setFechaComp(fechaformato);
                comprobante.setMonedaComp(moneda);
                comprobante.setMediopagoComp(medio);
                comprobante.setTotalventgravComp(totalgrav);
                comprobante.setIgvComp(igv);
                comprobante.setImptotalComp(total);
                comprobante.setFechaxmlComp(fechaformato1);
                comprobante.setFechaenvioComp(fechaformato2);
                comprobante.setEstadoComp(estado);
                comprobante.setHoraComp(horatexto);

                comprobantedao.agregarComprobante(comprobante);

                // Crear detalle del comprobante
                DcomprobanteModel dcomprobante = new DcomprobanteModel();
                dcomprobante.setNumserieDcomp(serie);
                dcomprobante.setItemDcomp(1);
                dcomprobante.setCantidadDcomp(1);
                dcomprobante.setTipunidDcomp("ZZ");
                dcomprobante.setProductoDcomp("P0001");
                dcomprobante.setDescripcionDcomp(servicio);
                dcomprobante.setValorunitarioDcomp(totalgrav);
                dcomprobante.setPreciounitarioDcomp(total);
                dcomprobantedao.agregarDcomprobante(dcomprobante);

                // Actualizar número de serie
                SerieDAO seriedao = new SerieDAOImpl();
                SerieModel seriemodel = new SerieModel();
                seriemodel.setCodSerie(codserie);
                seriemodel.setNumSerie(numero);
                seriedao.actualizarNumSerie(seriemodel);
                System.out.println("este " + numserie);
                //SE VUELVE A LLAMAR A LOS DATOS RECIEN INGRESADOS PARA GENERAR LOS ARCHIVOS PLANOS
                ComprobanteModel obtenerComprobante = new ComprobanteModel();
                DcomprobanteModel obtenerDcomprobante = new DcomprobanteModel();
                try {
                    obtenerComprobante = comprobantedao.obtenerComprobanteporNum(numserie);
                    String serieComp = obtenerComprobante.getSerieComp();
                    obtenerDcomprobante = dcomprobantedao.obtenerDcomprobanteporNum(numserie);

                    // Resto de tu código
                } catch (Exception e) {
                    // Manejar la excepción (puedes imprimir información de depuración o mostrar un mensaje de error)
                    e.printStackTrace();
                }

                tipocompsunat = seriedao.obtenerTipoSerie(tipo, codserie).getCodsunatSerie();

                //CREACIÓN ARCHIVOS PLANOS
                archivos.ArchivoPlanoCAB(rutasunat, rucempresa, tipocompsunat, tipdoccli, numserie, obtenerComprobante);
                archivos.ArchivoPlanoDET(rutasunat, rucempresa, tipocompsunat, numserie, obtenerDcomprobante);
                archivos.ArchivoPlanoTRI(rutasunat, rucempresa, tipocompsunat, numserie, obtenerComprobante);
                archivos.ArchivoPlanoLEY(rutasunat, rucempresa, tipocompsunat, numserie, obtenerComprobante);
                archivos.ArchivoPlanoACA(rutasunat, rucempresa, tipocompsunat, numserie, obtenerComprobante);
                archivos.ArchivoPlanoPAG(rutasunat, rucempresa, tipocompsunat, numserie, obtenerComprobante);
                JOptionPane.showMessageDialog(null, "Archivos Planos Generados Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                gestionarExcepcion(ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Existe Campos Vacíos", "Información", JOptionPane.INFORMATION_MESSAGE);

        }

    }//GEN-LAST:event_btncomprobanteActionPerformed

    private String convertirFecha(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setTimeZone(TimeZone.getTimeZone("America/Lima"));
            return sdf.format(fecha);
        } else {
            System.out.println("No se ha seleccionado ninguna fecha.");
            return null;
        }
    }

    private void gestionarExcepcion(Exception ex) {
        System.out.println("Error: " + ex.getMessage());
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbempresa;
    private javax.swing.JButton btnbpersona;
    private javax.swing.JButton btncambiar;
    private javax.swing.JButton btncomprobante;
    private javax.swing.JComboBox<String> cbruta;
    private javax.swing.JComboBox<String> cbvehiculo;
    private javax.swing.JCheckBox chkbfactura;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbllogo;
    private javax.swing.JPanel pnlfactura;
    private javax.swing.JTextField txtcomprobante;
    private javax.swing.JTextField txtdestino;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtorigen;
    private javax.swing.JTextField txtpasajero;
    private javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txtrazon;
    private javax.swing.JTextField txtruc;
    private javax.swing.JTextField txttipocomprobante;
    // End of variables declaration//GEN-END:variables
}
