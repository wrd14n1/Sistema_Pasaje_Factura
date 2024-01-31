/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import sistemapasajes.modelo.ComprobanteModel;
import sistemapasajes.modelo.ConfiguracionModel;
import sistemapasajes.modelo.DcomprobanteModel;

/**
 *
 * @author edson
 */
public class ArchivosPlanos {

    public void ArchivoPlanoCAB(ConfiguracionModel configuracion, String tipdoc, String tipdoccli, ComprobanteModel cabecera) {
        //String ruta = rutaSUNAT + "\\sunat_archivos\\sfs\\DATA\\"+ruc+"-"+tipdoc+"-" + numserie + ".CAB";
        String ruta = configuracion.getRutaSunat() + "/sunat_archivos/sfs/DATA/" + configuracion.getRucConf() + "-" + tipdoc + "-" + cabecera.getSerieComp() + ".CAB";

        System.out.println("esta es la ruta: " + ruta);
        File apcab = new File(ruta);

        String sumatoria_tributos = null;
        String total_valor_venta = null;
        String total_precio_venta = null;

        //para almacenar datos
        BufferedWriter bufferedWriter;
        if (apcab.exists()) {
            JOptionPane.showMessageDialog(null, "El archivo: " + ruta + " ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(apcab));
                //18 valores SUNAT CAB SFS 1.3.4.4
                String tipo_operacion = "0101"; //venta interna
                String fecha_emision = cabecera.getFechaComp();//fecha en formato YYYY-MM-DD
                String hora_emision = cabecera.getHoraComp();
                String fecha_vencimiento = "-";
                String codigo_domicilio_fiscal = "0000";//domicilio fiscal
                String tipo_documento_receptor = tipdoccli;//DNI
                String numero_documento_cliente = cabecera.getDocclienteComp();//num dni
                String nombre_o_razon_social = cabecera.getClienteComp();//nombre
                String moneda = cabecera.getMonedaComp();//PEN,USD
                if ("AFECTO".equals(cabecera.getAfecComp())) {
                    sumatoria_tributos = String.valueOf(cabecera.getIgvComp());
                    total_valor_venta = String.valueOf(cabecera.getTotalventgravComp());
                    total_precio_venta = String.valueOf(cabecera.getImptotalComp());

                } else if ("EXONERADO".equals(cabecera.getAfecComp())) {
                    sumatoria_tributos = "0.00";
                    total_valor_venta = String.valueOf(cabecera.getImptotalComp());
                    total_precio_venta = String.valueOf(cabecera.getImptotalComp());
                }

                String total_descuentos = "0.00";
                String sumatoria_otros_cargos = "0.00";
                String total_anticipos = "0.00";
                String importe_total_venta = total_precio_venta;
                String version_UBL = "2.1";
                String custo_documento = "2.0";
                //se esccribe la linea en el archivo
                bufferedWriter.write(
                        tipo_operacion + "|"
                        + fecha_emision + "|"
                        + hora_emision + "|"
                        + fecha_vencimiento + "|"
                        + codigo_domicilio_fiscal + "|"
                        + tipo_documento_receptor + "|"
                        + numero_documento_cliente + "|"
                        + nombre_o_razon_social + "|"
                        + moneda + "|"
                        + sumatoria_tributos + "|"
                        + total_valor_venta + "|"
                        + total_precio_venta + "|"
                        + total_descuentos + "|"
                        + sumatoria_otros_cargos + "|"
                        + total_anticipos + "|"
                        + importe_total_venta + "|"
                        + version_UBL + "|"
                        + custo_documento
                );
                bufferedWriter.close();
            } catch (Exception e) {
                System.out.println("Error creando archivo plano " + ruta + e);
                JOptionPane.showMessageDialog(null, "Error creando archivo plano " + ruta + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void ArchivoPlanoDET(ConfiguracionModel configuracion, String tipdoc, DcomprobanteModel detalle,ComprobanteModel cabecera) {
        //se crea archivo con el nombre establecido
        //String ruta = rutaSUNAT + "\\sunat_archivos\\sfs\\DATA\\"+ruc+"-"+tipdoc+"-" + numserie + ".DET";
        String ruta = configuracion.getRutaSunat() + "/sunat_archivos/sfs/DATA/" + configuracion.getRucConf() + "-" + tipdoc + "-" + detalle.getNumserieDcomp() + ".DET";

        File apdet = new File(ruta);

        String codigo_tipo_tributo_igv = null;
        String nombre_tributo_item = null;
        String monto_igv_item = null;
        String base_imponible_igv_item = null;
        String afectacion_igv_item = null;
        String porcentaje_igv = null;
        String valor_unitario_item = null;
        String sumatoria_tributos_item = null;
        String valor_venta_item = null;

        BufferedWriter bufferedWriter;
        if (apdet.exists()) {
            JOptionPane.showMessageDialog(null, "El archivo: " + ruta + " ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(apdet));
                //recorremos la tabla detealle para capturar los datos

                //guardamos los campos en variables
                String Cantidad = String.valueOf(detalle.getCantidadDcomp());
                String PrecioUnitario = String.valueOf(detalle.getValorunitarioDcomp());
                String CodPro = detalle.getProductoDcomp();
                String PrecioTotal = String.valueOf(detalle.getPreciounitarioDcomp());
                System.out.println("prueba de precio: " + PrecioTotal);
                String Descripcion = detalle.getDescripcionDcomp();// sin salto de linea

                //36 valores SUNAT DET SFS 1.3.4.2
                String codigo_unidad_medida_item = detalle.getTipunidDcomp();//???antes EA
                String cantidad_unidades_item = Cantidad;
                //String codigo_producto = "-";
                String codigo_producto = CodPro;
                String codigo_producto_sunat = "-";
                String descripcion_detallada = Descripcion;
                //String sumatoria_tributos_item = Metodos.FormatoDecimalMostrar(String.valueOf((Double.parseDouble(PrecioTotal)*18)/82));
                if ("AFECTO".equals(cabecera.getAfecComp())) {
                    valor_unitario_item = PrecioUnitario;
                    sumatoria_tributos_item = String.valueOf(detalle.getPreciounitarioDcomp() - detalle.getValorunitarioDcomp());

                    codigo_tipo_tributo_igv = "1000";
                    nombre_tributo_item = "IGV";
                    monto_igv_item = sumatoria_tributos_item;
                    base_imponible_igv_item = PrecioUnitario;
                    afectacion_igv_item = "10";
                    porcentaje_igv = "18.00";
                    valor_venta_item = String.valueOf(detalle.getValorunitarioDcomp());

                } else if ("EXONERADO".equals(cabecera.getAfecComp())) {
                    valor_unitario_item = PrecioTotal;
                    sumatoria_tributos_item = "0.00";

                    codigo_tipo_tributo_igv = "9997";
                    nombre_tributo_item = "EXO";
                    monto_igv_item = "0.00";
                    base_imponible_igv_item = PrecioTotal;
                    afectacion_igv_item = "20";
                    porcentaje_igv = "0.00";

                    valor_venta_item = PrecioTotal;
                }
                //String codigo_tipo_tributo_igv = "1000";//hace referencia al IGV
                //String codigo_tipo_tributo_igv = "9997";//hace referencia al IGV

                //String nombre_tributo_item = "IGV";
                String codigo_tributo_item = "VAT";

                String codigo_tipo_tributo_isc = "-";
                String monto_isc_item = "0.00";
                String base_imponible_isc_item = "";
                String nombre_tributo_item_isc = "ISC";
                String codigo_tipo_tributo_item = "EXC";
                String tipo_sistema_isc = "01";
                String porcentaje_isc = "2.00";
                String codigo_tipo_tributo_otro = "-";
                String monto_tributo_otro_item = "";
                String base_imponible_tributo_otro_item = "";
                String nombre_tributo_otro_item = "";
                String codigo_tipo_tributo_otro_item = "";
                String porcentaje_tributo_otro_item = "";
                String codigo_icbper = "-";
                String monto_icbper_item = "";
                String cantidad_icbper_item = "";
                String nombre_icbper_item = "";
                String codigo_icbper_item = "";
                String monto_icbper_unidad = "";
                String precio_venta_unitario = PrecioTotal;

                String valor_referencial_gratuito = "0.00";
                //se esccribe la linea en el archivo
                bufferedWriter.write(
                        codigo_unidad_medida_item + "|"
                        + cantidad_unidades_item + "|"
                        + codigo_producto + "|"
                        + codigo_producto_sunat + "|"
                        + descripcion_detallada + "|"
                        + valor_unitario_item + "|"
                        + sumatoria_tributos_item + "|"
                        + codigo_tipo_tributo_igv + "|"
                        + monto_igv_item + "|"
                        + base_imponible_igv_item + "|"
                        + nombre_tributo_item + "|"
                        + codigo_tributo_item + "|"
                        + afectacion_igv_item + "|"
                        + porcentaje_igv + "|"
                        + codigo_tipo_tributo_isc + "|"
                        + monto_isc_item + "|"
                        + base_imponible_isc_item + "|"
                        + nombre_tributo_item_isc + "|"
                        + codigo_tipo_tributo_item + "|"
                        + tipo_sistema_isc + "|"
                        + porcentaje_isc + "|"
                        + codigo_tipo_tributo_otro + "|"
                        + monto_tributo_otro_item + "|"
                        + base_imponible_tributo_otro_item + "|"
                        + nombre_tributo_otro_item + "|"
                        + codigo_tipo_tributo_otro_item + "|"
                        + porcentaje_tributo_otro_item + "|"
                        + codigo_icbper + "|"
                        + monto_icbper_item + "|"
                        + cantidad_icbper_item + "|"
                        + nombre_icbper_item + "|"
                        + codigo_icbper_item + "|"
                        + monto_icbper_unidad + "|"
                        + precio_venta_unitario + "|"
                        + valor_venta_item + "|"
                        + valor_referencial_gratuito + "|\n");

                bufferedWriter.close();
            } catch (Exception e) {
                System.out.println("Error creando archivo plano " + ruta + e);
                JOptionPane.showMessageDialog(null, "Error creando archivo plano " + ruta + e, "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public void ArchivoPlanoTRI(ConfiguracionModel configuracion, String tipdoc, ComprobanteModel cabecera) {
        //se crea archivo con el nombre establecido
        //String ruta = rutaSUNAT + "\\sunat_archivos\\sfs\\DATA\\"+ruc+"-"+tipdoc+"-" + numserie + ".TRI";
        String ruta = configuracion.getRutaSunat() + "/sunat_archivos/sfs/DATA/" + configuracion.getRucConf() + "-" + tipdoc + "-" + cabecera.getSerieComp() + ".TRI";
        String identificador_tributo = null;
        String nombre_tributo = null;
        String codigo_tipo_tributo = null;
        String base_imponible = null;
        String monto_tributo_item = null;
        File aptri = new File(ruta);
        BufferedWriter bufferedWriter;
        if (aptri.exists()) {
            JOptionPane.showMessageDialog(null, "El archivo: " + ruta + " ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(aptri));
                if ("EXONERADO".equals(cabecera.getAfecComp())) {
                    identificador_tributo = "9997";
                    nombre_tributo = "EXO";
                    codigo_tipo_tributo = "VAT";
                    base_imponible = String.valueOf(cabecera.getImptotalComp());
                    monto_tributo_item = "0.00";

                } else if ("AFECTO".equals(cabecera.getAfecComp())) {
                    identificador_tributo = "1000";
                    nombre_tributo = "IGV";
                    codigo_tipo_tributo = "VAT";
                    base_imponible = String.valueOf(cabecera.getTotalventgravComp());
                    monto_tributo_item = String.valueOf(cabecera.getIgvComp());
                }

                //se esccribe la linea en el archivo
                bufferedWriter.write(
                        identificador_tributo + "|"
                        + nombre_tributo + "|"
                        + codigo_tipo_tributo + "|"
                        + base_imponible + "|"
                        + monto_tributo_item + "|\n");
                bufferedWriter.close();
            } catch (Exception e) {
                System.out.println("Error creando archivo plano " + ruta + e);
                JOptionPane.showMessageDialog(null, "Error creando archivo plano " + ruta + e, "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public void ArchivoPlanoLEY(ConfiguracionModel configuracion, String tipdoc, ComprobanteModel cabecera) {

        ConvertirNumeroTexto convertir = new ConvertirNumeroTexto();
        //String ruta = rutaSUNAT + "\\sunat_archivos\\sfs\\DATA\\" +ruc+"-"+tipdoc+"-" + numserie + ".LEY";
        String ruta = configuracion.getRutaSunat() + "/sunat_archivos/sfs/DATA/" + configuracion.getRucConf() + "-" + tipdoc + "-" + cabecera.getSerieComp() + ".LEY";
        String monto = String.valueOf(cabecera.getImptotalComp());
        File apley = new File(ruta);
        BufferedWriter bufferedWriter;
        if (apley.exists()) {
            JOptionPane.showMessageDialog(null, "El archivo: " + ruta + " ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(apley));
                String codigo_leyenda = "1000";//venta interna
                String descripcion_leyenda = convertir.Convertir(monto, band());
                //se esccribe la linea en el archivo
                bufferedWriter.write(
                        codigo_leyenda + "|"
                        + descripcion_leyenda
                );
                bufferedWriter.close();
            } catch (Exception e) {
                System.out.println("Error creando archivo plano " + ruta + e);
                JOptionPane.showMessageDialog(null, "Error creando archivo plano " + ruta + e, "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public void ArchivoPlanoACA(ConfiguracionModel configuracion, String tipdoc, ComprobanteModel cabecera) {
        //String ruta = rutaSUNAT + "\\sunat_archivos\\sfs\\DATA\\" +ruc+"-"+tipdoc+"-" + numserie + ".ACA";
        String ruta = configuracion.getRutaSunat() + "/sunat_archivos/sfs/DATA/" + configuracion.getRucConf() + "-" + tipdoc + "-" + cabecera.getSerieComp() + ".ACA";

//se crea archivo con el nombre establecido
        File apaca = new File(ruta);
        BufferedWriter bufferedWriter;
        if (apaca.exists()) {
            JOptionPane.showMessageDialog(null, "El archivo: " + ruta + " ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(apaca));
                String cuenta_detraccion = "";
                String codigo_producto_detraccion = "";
                String porcentaje_detraccion = "";
                String monto_detraccion = "";
                String medio_pago = "008";
                //direccion cliente
                String direccion_cliente_pais = "-";
                String direccion_cliente_ubigeo = "-";
                String direccion_cliente_detallada = "-";
                //direccion distinta a la del cliente
                String direccion_cliente_pais_distinta = "-";
                String direccion_cliente_ubigeo_distinta = "-";
                String direccion_cliente_detallada_distinta = "-";
                //se esccribe la linea en el archivo
                bufferedWriter.write(
                        cuenta_detraccion + "|"
                        + codigo_producto_detraccion + "|"
                        + porcentaje_detraccion + "|"
                        + monto_detraccion + "|"
                        + medio_pago + "|"
                        + direccion_cliente_pais + "|"
                        + direccion_cliente_ubigeo + "|"
                        + direccion_cliente_detallada + "|"
                        + direccion_cliente_pais_distinta + "|"
                        + direccion_cliente_ubigeo_distinta + "|"
                        + direccion_cliente_detallada_distinta
                );
                bufferedWriter.close();
            } catch (Exception e) {
                System.out.println("Error creando archivo plano " + ruta + e);
                JOptionPane.showMessageDialog(null, "Error creando archivo plano " + ruta + e, "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public void ArchivoPlanoPAG(ConfiguracionModel configuracion, String tipdoc, ComprobanteModel cabecera) {
        //String ruta = rutaSUNAT + "\\sunat_archivos\\sfs\\DATA\\" +ruc+"-"+tipdoc+"-" + numserie + ".PAG";
        String ruta = configuracion.getRutaSunat() + "/sunat_archivos/sfs/DATA/" + configuracion.getRucConf() + "-" + tipdoc + "-" + cabecera.getSerieComp() + ".PAG";

//se crea archivo con el nombre establecido
        File appag = new File(ruta);
        BufferedWriter bufferedWriter;
        if (appag.exists()) {
            JOptionPane.showMessageDialog(null, "El archivo: " + ruta + " ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(appag));
                String forma_pago = cabecera.getMediopagoComp();
                String monto_neto = "-";
                String Tipo_moneda = cabecera.getMonedaComp();
                bufferedWriter.write(forma_pago + "|"
                        + monto_neto + "|" + Tipo_moneda);
                bufferedWriter.close();
            } catch (Exception e) {
                System.out.println("Error creando archivo plano " + ruta + e);
                JOptionPane.showMessageDialog(null, "Error creando archivo plano " + ruta + e, "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }
    //para el numero en letras

    private static boolean band() {
        if (Math.random() > .5) {
            return true;
        } else {
            return false;
        }
    }
}
