/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package sistemapasajes.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import sistemapasajes.ArchivosPlanos;
import sistemapasajes.Funciones;

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
import sistemapasajes.dao.RutaAsignadaDAO;
import sistemapasajes.dao.RutaAsignadaDAOImpl;
import sistemapasajes.dao.RutaDAO;
import sistemapasajes.dao.RutaDAOImpl;
import sistemapasajes.dao.SerieDAO;
import sistemapasajes.dao.SerieDAOImpl;
import sistemapasajes.modelo.AsignacionRutaModel;
import sistemapasajes.modelo.ComprobanteModel;
import sistemapasajes.modelo.ConfiguracionModel;
import sistemapasajes.modelo.DcomprobanteModel;
import sistemapasajes.modelo.EmpresaModel;
import sistemapasajes.modelo.PasajeroModel;
import sistemapasajes.modelo.RutaModel;
import sistemapasajes.modelo.SerieModel;

/**
 *
 * @author edson
 */
public class Pasajes extends javax.swing.JInternalFrame {

    List<RutaModel> listarutas;
    int numero;
    String numserie;
    String codserie;
    String codsunatserie;
    String rutasunat;
    String rucempresa;
    String tipocompsunat;
    String tipdoccli;
    ConfiguracionDAO configuraciondao = new ConfiguracionDAOImpl();
    ConfiguracionModel configuracion = new ConfiguracionModel();
    String json;
    ApiDAO apidao = new ApiDAOImpl();
      String tiposerie;

    //String rjson = null;
    /**
     * Creates new form Pasajes
     */
    public Pasajes() {
        initComponents();
        cargarFechaActual();
        inicializarComboBoxRutas();

        String rutalogo;
        //ConfiguracionDAO configdao = new ConfiguracionDAOImpl();
        configuracion = configuraciondao.obtenerConfiguracionPorId(1);
        rutalogo = configuracion.getRazonConf();
        System.out.println(rutalogo);
        //mostrarImagen(rutalogo);
        lbllogo.setText(rutalogo);
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
        rutasunat = configuracion.getRutaSunat();
        rucempresa = configuracion.getRucConf();

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
        int rutasel = cbruta.getSelectedIndex();

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
        Date fecha = jDateChooser1.getDate();
        String fechaformato = Funciones.convertirFecha(fecha);

        inicializarComboBoxVehiculos(fechaformato, rutasel);
    }

    private void cargarFechaActual() {
        // Get the current date
        Date currentDate = Calendar.getInstance().getTime();

        // Set the current date to the JDateChooser
        jDateChooser1.setDate(currentDate);
    }

    private void inicializarComboBoxVehiculos(String fecha, int ruta) {

        cbvehiculo.removeAllItems();
        RutaAsignadaDAO rutaasignadadao = new RutaAsignadaDAOImpl();
        // Date fecha = jDateChooser1.getDate();
        // String fechaformato = Funciones.convertirFecha(fecha);

        List<AsignacionRutaModel> listavehiculos = rutaasignadadao.obtenerAsignacionRutasporFecha(fecha, ruta);
        cbvehiculo.addItem("Seleccionar");
        for (int i = 0; i < listavehiculos.size(); i++) {
            cbvehiculo.addItem(listavehiculos.get(i).getPlacavehiAsigRuta() + " - " + listavehiculos.get(i).getDescVehiculoAsigRuta());

        }
        // Agregar un ItemListener al JComboBox
        cbvehiculo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Obtener el índice seleccionado
                    int selectedIndex = cbvehiculo.getSelectedIndex();

                    // Si no es el índice "Seleccionar", obtener el id_vehi y establecerlo en el JTextField
                    if (selectedIndex > 0) {
                        int idVehiculo = listavehiculos.get(selectedIndex - 1).getVehiculoAsigRuta();
                        txtvehiculo.setText(String.valueOf(idVehiculo));
                    } else {
                        // Si es el índice "Seleccionar", limpiar el JTextField
                        txtvehiculo.setText("");
                    }
                }
            }
        });
    }

    /*VehiculoDAO vehiculodao = new VehiculoDAOImpl();
        List<VehiculoModel> listavehiculos = vehiculodao.obtenerTodosVehiculos();
        cbvehiculo.addItem("Seleccionar");
        for (int i = 0; i < listavehiculos.size(); i++) {
            cbvehiculo.addItem(listavehiculos.get(i).getPlacaVehi() + " - " + listavehiculos.get(i).getDescVehi());
        } */
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
        btnnempresa = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        btnbpersona = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtpasajero = new javax.swing.JTextField();
        btnnpasajero = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        chkbfactura = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        txtcomprobante = new javax.swing.JTextField();
        txttipocomprobante = new javax.swing.JTextField();
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
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        txtvehiculo = new javax.swing.JTextField();
        btncomprobante = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setClosable(true);
        setIconifiable(true);
        setTitle("Pasajes");

        lbllogo.setBackground(new java.awt.Color(255, 255, 255));
        lbllogo.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lbllogo.setForeground(new java.awt.Color(204, 0, 51));
        lbllogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbllogo.setMaximumSize(new java.awt.Dimension(617, 97));

        pnlfactura.setBackground(new java.awt.Color(255, 255, 255));
        pnlfactura.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Facturación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pnlfactura.setEnabled(false);
        pnlfactura.setName(""); // NOI18N

        txtruc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtruc.setEnabled(false);

        jLabel11.setText("RUC:");

        btnbempresa.setBackground(new java.awt.Color(45, 85, 195));
        btnbempresa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnbempresa.setForeground(new java.awt.Color(255, 255, 255));
        btnbempresa.setText("Buscar");
        btnbempresa.setBorder(null);
        btnbempresa.setEnabled(false);
        btnbempresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbempresaActionPerformed(evt);
            }
        });

        jLabel12.setText("Datos:");

        txtrazon.setEditable(false);
        txtrazon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtdireccion.setEditable(false);
        txtdireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel13.setText("Dirección:");

        btnnempresa.setBackground(new java.awt.Color(40, 150, 180));
        btnnempresa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnnempresa.setForeground(new java.awt.Color(255, 255, 255));
        btnnempresa.setText("Nuevo");
        btnnempresa.setBorder(null);
        btnnempresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnempresaActionPerformed(evt);
            }
        });

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
                        .addComponent(btnbempresa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtrazon, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE))
                    .addGroup(pnlfacturaLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdireccion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnnempresa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlfacturaLayout.setVerticalGroup(
            pnlfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlfacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(btnbempresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtrazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlfacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnempresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Pasajero", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("DNI:");

        txtdni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnbpersona.setBackground(new java.awt.Color(45, 85, 195));
        btnbpersona.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnbpersona.setForeground(new java.awt.Color(255, 255, 255));
        btnbpersona.setText("Buscar");
        btnbpersona.setBorder(null);
        btnbpersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbpersonaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Datos:");

        txtpasajero.setEditable(false);
        txtpasajero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnnpasajero.setBackground(new java.awt.Color(40, 150, 180));
        btnnpasajero.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnnpasajero.setForeground(new java.awt.Color(255, 255, 255));
        btnnpasajero.setText("Nuevo");
        btnnpasajero.setBorder(null);
        btnnpasajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnpasajeroActionPerformed(evt);
            }
        });

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
                .addComponent(btnbpersona, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtpasajero, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnnpasajero, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtpasajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnnpasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnbpersona, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Comprobante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        chkbfactura.setBackground(new java.awt.Color(255, 255, 255));
        chkbfactura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chkbfactura.setText("Requiere Factura");
        chkbfactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkbfacturaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("N° de Comprobante:");

        txtcomprobante.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtcomprobante.setEnabled(false);

        txttipocomprobante.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txttipocomprobante.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkbfactura)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txttipocomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(530, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtcomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttipocomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(chkbfactura))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la Ruta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Ruta:");

        cbruta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbruta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbrutaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Soles");

        txtprecio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Precio: S/");

        txtorigen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtorigen.setEnabled(false);
        txtorigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtorigenActionPerformed(evt);
            }
        });

        txtdestino.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtdestino.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Origen:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Vehiculo:");

        cbvehiculo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Destino:");

        btncambiar.setBackground(new java.awt.Color(45, 85, 195));
        btncambiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btncambiar.setForeground(new java.awt.Color(255, 255, 255));
        btncambiar.setText("Cambiar");
        btncambiar.setBorder(null);
        btncambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncambiarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Fecha:");

        jDateChooser1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jDateChooser1.setMinSelectableDate(new java.util.Date(1704088864000L));

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
                        .addComponent(jLabel2)
                        .addGap(713, 713, 713)
                        .addComponent(txtvehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 178, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbvehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtorigen, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(248, 248, 248)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtdestino, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(btncambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbruta, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cbruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtorigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(txtdestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbvehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtvehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        btncomprobante.setBackground(new java.awt.Color(30, 200, 130));
        btncomprobante.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btncomprobante.setForeground(new java.awt.Color(255, 255, 255));
        btncomprobante.setText("Generar Comprobante");
        btncomprobante.setBorder(null);
        btncomprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncomprobanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbllogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlfactura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btncomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbllogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlfactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btncomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlfactura.getAccessibleContext().setAccessibleName("Datos de Empresa");
        pnlfactura.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbpersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbpersonaActionPerformed

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
                JOptionPane.showMessageDialog(null, "Pasajero no se encuentra en la Base de Datos Interna, se realizará una consulta externa. \n Espere por favor.", "Información", JOptionPane.INFORMATION_MESSAGE);

                //CONSULTAR A API PARA PODER REGISTRARLO
                url = apidao.obtenerApiPorId(1).getUrlApi() + dni;
                try {
                    Funciones.realizarConsultaApi(url);
                    json = Funciones.realizarConsultaApi(url);
                } catch (IOException ex) {
                    Logger.getLogger(Pasajes.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    System.out.println(Funciones.realizarConsultaApi(url));

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

                    //AGREGAR PERSONA
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

      

        if (chkbfactura.isSelected()) {
            serie = seriedao.obtenerTipoSerie("FACTURA", "F001");
            numero = serie.getNumSerie() + 1;
            codserie = serie.getCodSerie();
            numserie = codserie + "-" + numero;
            codsunatserie=serie.getCodsunatSerie();
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
             codsunatserie=serie.getCodsunatSerie();
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
                JOptionPane.showMessageDialog(null, "Empresa no se encuentra en la Base de Datos Interna, se realizará una consulta externa. \n Espere por favor.", "Información", JOptionPane.INFORMATION_MESSAGE);

                //CONSULTAR A API PARA PODER REGISTRARLO
                url = apidao.obtenerApiPorId(2).getUrlApi() + ruc;
                try {
                    Funciones.realizarConsultaApi(url);
                    json=   Funciones.realizarConsultaApi(url);
                } catch (IOException ex) {
                    Logger.getLogger(Pasajes.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    System.out.println(Funciones.realizarConsultaApi(url));

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

        if (!"".equals(txtdni.getText()) && jDateChooser1.getDate() != null
                && (chkbfactura.isSelected() ? !"".equals(txtrazon.getText()) : !"".equals(txtpasajero.getText()))
                && (chkbfactura.isSelected() ? !"".equals(txtruc.getText()) : !"".equals(txtdni.getText()))
                && !"".equals(txtorigen.getText()) && !"".equals(txtdestino.getText())
                && cbruta.getSelectedIndex() != 0 && cbvehiculo.getSelectedIndex() != 0) {

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
                        + txtdestino.getText() + " PASAJERO: " + txtpasajero.getText();

                Date fecha = jDateChooser1.getDate();
                String fechaformato = Funciones.convertirFecha(fecha);

                Date hora = new Date();
                SimpleDateFormat horaFormato = new SimpleDateFormat("HH:mm:ss");

                String horatexto = horaFormato.format(hora);

                String moneda = "PEN";
                String medio = "Contado";
                Double total = Double.valueOf(txtprecio.getText());
                Double igv = total * 0.18;
                Double totalgrav = total - igv;

                String fechaformato1 = Funciones.convertirFecha(fecha);
                String fechaformato2 = Funciones.convertirFecha(fecha);

                String estado = "XML NO GENERADO";
                
                int vehiculo = Integer.parseInt(txtvehiculo.getText());
                String codtipo = null;
                if ("BOLETA".equals(tipo)) {
                    codtipo = "03";
                    
                } else if ("FACTURA".equals(tipo)) {
                      codtipo = "01";
                }
                // Crear comprobante
                ComprobanteModel comprobante = new ComprobanteModel();
                comprobante.setTipoComp(codtipo);
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
                comprobante.setAfecComp("EXONERADO");
                comprobante.setVehiculoComp(vehiculo);

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
                archivos.ArchivoPlanoCAB(configuracion, tipocompsunat, tipdoccli, obtenerComprobante);
                archivos.ArchivoPlanoDET(configuracion, tipocompsunat, obtenerDcomprobante, obtenerComprobante);
                archivos.ArchivoPlanoTRI(configuracion, tipocompsunat, obtenerComprobante);
                archivos.ArchivoPlanoLEY(configuracion, tipocompsunat, obtenerComprobante);
                archivos.ArchivoPlanoACA(configuracion, tipocompsunat, obtenerComprobante);
                archivos.ArchivoPlanoPAG(configuracion, tipocompsunat, obtenerComprobante);
                JOptionPane.showMessageDialog(null, "Archivos Planos Generados Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);

                this.dispose();

                JInternalFrame internalFrame = new Pasajes();

                Principal.jDesktopPane1.add(internalFrame);
                // Calcular la posición para centrar el JInternalFrame
                Dimension desktopSize = Principal.jDesktopPane1.getSize();
                Dimension jInternalFrameSize = internalFrame.getSize();

                int posX = (desktopSize.width - jInternalFrameSize.width) / 2;
                int posY = (desktopSize.height - jInternalFrameSize.height) / 2;

                // Establecer la ubicación centrada
                internalFrame.setLocation(posX, posY);

                internalFrame.setVisible(true);

            } catch (Exception ex) {
                gestionarExcepcion(ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Existe Campos Vacíos", "Información", JOptionPane.INFORMATION_MESSAGE);

        }

    }//GEN-LAST:event_btncomprobanteActionPerformed

    private void btnnpasajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnpasajeroActionPerformed
        JInternalFrame internalFrame = new Pasajero();

        Principal.jDesktopPane1.add(internalFrame);
        // Calcular la posición para centrar el JInternalFrame
        Dimension desktopSize = Principal.jDesktopPane1.getSize();
        Dimension jInternalFrameSize = internalFrame.getSize();

        int posX = (desktopSize.width - jInternalFrameSize.width) / 2;
        int posY = (desktopSize.height - jInternalFrameSize.height) / 2;

        // Establecer la ubicación centrada
        internalFrame.setLocation(posX, posY);

        internalFrame.setVisible(true);
    }//GEN-LAST:event_btnnpasajeroActionPerformed

    private void btnnempresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnempresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnempresaActionPerformed

    private void txtorigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtorigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtorigenActionPerformed

    private void gestionarExcepcion(Exception ex) {
        System.out.println("Error: " + ex.getMessage());
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbempresa;
    private javax.swing.JButton btnbpersona;
    private javax.swing.JButton btncambiar;
    private javax.swing.JButton btncomprobante;
    private javax.swing.JButton btnnempresa;
    private javax.swing.JButton btnnpasajero;
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
    public static javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtorigen;
    public static javax.swing.JTextField txtpasajero;
    private javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txtrazon;
    private javax.swing.JTextField txtruc;
    private javax.swing.JTextField txttipocomprobante;
    private javax.swing.JTextField txtvehiculo;
    // End of variables declaration//GEN-END:variables
}
