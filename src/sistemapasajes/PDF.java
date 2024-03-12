/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapasajes;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import sistemapasajes.modelo.ComprobanteModel;
import sistemapasajes.modelo.ConfiguracionModel;
import sistemapasajes.modelo.DcomprobanteModel;
import sistemapasajes.modelo.EmpresaModel;

public class PDF {

    public void crearPDF(ConfiguracionModel datosconfig, ComprobanteModel datoscomprobante, DcomprobanteModel datosdetcomprobante, EmpresaModel datosemp, String rutaqr) {
        ConvertirNumeroTexto convertir = new ConvertirNumeroTexto();
        float anchoMilimetros = 80; // Ancho en milímetros
        float altoMilimetros = 297;  // Alto en milímetros

        // Convierte las dimensiones a puntos (1 pulgada = 25.4 milímetros)
        float anchoPuntos = anchoMilimetros * 72 / 25.4f;
        float altoPuntos = altoMilimetros * 72 / 25.4f;

        // Márgenes en puntos
        float margenIzquierdo = 10;
        float margenDerecho = 10;
        float margenSuperior = 5;
        float margenInferior = 5;

        try {
            // Especifica la ruta y el nombre del archivo PDF
            String nombrePDF = datoscomprobante.getSerieComp() + ".pdf";
            String rutaPDF = datosconfig.getRutaArchivo() + "\\" + nombrePDF;
            String txtcomp = null;
            // Verifica si el archivo ya existe
            File archivoPDF = new File(rutaPDF);
            if (archivoPDF.exists()) {
                int opcion = JOptionPane.showConfirmDialog(null, "El archivo " + nombrePDF + " ya existe. ¿Desea sobrescribirlo?", "Confirmar", JOptionPane.YES_NO_OPTION);

                if (opcion != JOptionPane.YES_OPTION) {
                    // Si el usuario no desea sobrescribir, termina la función
                    return;
                }
            }

            if ("01".equals(datoscomprobante.getTipoComp())) {
                txtcomp = "FACTURA";
            } else if ("03".equals(datoscomprobante.getTipoComp())) {
                txtcomp = "BOLETA";
            }

            Document document = new Document(new Rectangle(anchoPuntos, altoPuntos), margenIzquierdo, margenDerecho, margenSuperior, margenInferior);
            PdfWriter.getInstance(document, new FileOutputStream(rutaPDF));
            document.open();

            // Escala la imagen al tamaño de la hoja
            Image imagen = Image.getInstance(datosconfig.getLogoConf());
            imagen.scaleToFit(anchoPuntos - margenIzquierdo - margenDerecho, altoPuntos - margenSuperior - margenInferior);

            // Agrega la imagen al PDF antes del título
            document.add(imagen);

            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
            Font fontSubtitulo = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLDITALIC);
            Font fontNegrita = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD);
            Font fontNormal = new Font(Font.FontFamily.HELVETICA, 7);
            Font fontTerminos = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD);

            // Agrega contenido al PDF
            Paragraph titulo = new Paragraph(datosconfig.getRazonConf(), fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            Paragraph direccion = new Paragraph(datosconfig.getDirecConf(), fontSubtitulo);
            direccion.setAlignment(Element.ALIGN_CENTER);
            document.add(direccion);

            Paragraph texto1 = new Paragraph(datosconfig.getTxt1Conf(), fontNegrita);
            texto1.setAlignment(Element.ALIGN_CENTER);
            document.add(texto1);

            Paragraph texto2 = new Paragraph(datosconfig.getTxt2Conf(), fontNegrita);
            texto2.setAlignment(Element.ALIGN_CENTER);
            document.add(texto2);

            document.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(1);
            tabla.setWidthPercentage(100);
            PdfPCell celda = new PdfPCell(new Paragraph(datosconfig.getRucConf() + "\n"
                    + txtcomp + " ELECTRONICA" + "\n"
                    + datoscomprobante.getSerieComp(), fontTitulo));
            celda.setBorder(Rectangle.BOX);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celda);
            document.add(tabla);

            Paragraph fecha = new Paragraph(datoscomprobante.getFechaComp() + "   " + datoscomprobante.getHoraComp(), fontNormal);
            fecha.setAlignment(Element.ALIGN_LEFT);
            document.add(fecha);

            Paragraph cliente = new Paragraph(new Paragraph("Cliente: " + datoscomprobante.getClienteComp(), fontNormal));
            cliente.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(cliente);
            if ("01".equals(datoscomprobante.getTipoComp())) {
                Paragraph dircliente = new Paragraph("Direccion: " + datosemp.getDireccionEmp(), fontNormal);
                dircliente.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(dircliente);

                Paragraph nruc = new Paragraph("RUC: " + datoscomprobante.getDocclienteComp(), fontNormal);
                nruc.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(nruc);
            } else {
                Paragraph nruc = new Paragraph("DNI: " + datoscomprobante.getDocclienteComp(), fontNormal);
                nruc.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(nruc);
            }

            document.add(new Paragraph(" "));

            PdfPTable detalle = new PdfPTable(5);
            detalle.setWidthPercentage(100);
            float[] columnWidths = {10, 10, 40, 10, 10};
            detalle.setWidths(columnWidths);

            PdfPCell celdaCantidad = new PdfPCell(new Paragraph("Cantidad", fontNegrita));
            configurarBordes(celdaCantidad, true, true, false, false);  // Bordes en la parte superior e inferior
            celdaCantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            detalle.addCell(celdaCantidad);

            PdfPCell celdaUnidad = new PdfPCell(new Paragraph("UN", fontNegrita));
            configurarBordes(celdaUnidad, true, true, false, false);
            celdaUnidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            detalle.addCell(celdaUnidad);

            PdfPCell celdaProducto = new PdfPCell(new Paragraph("Producto", fontNegrita));
            configurarBordes(celdaProducto, true, true, false, false);
            celdaProducto.setHorizontalAlignment(Element.ALIGN_CENTER);
            detalle.addCell(celdaProducto);

            PdfPCell celdaPV = new PdfPCell(new Paragraph("PV", fontNegrita));
            configurarBordes(celdaPV, true, true, false, false);
            celdaPV.setHorizontalAlignment(Element.ALIGN_CENTER);
            detalle.addCell(celdaPV);

            PdfPCell celdaTotal = new PdfPCell(new Paragraph("Total", fontNegrita));
            configurarBordes(celdaTotal, true, true, false, false);
            celdaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
            detalle.addCell(celdaTotal);

            PdfPCell celdaCantidadDetalle = new PdfPCell(new Paragraph(String.valueOf(datosdetcomprobante.getCantidadDcomp()), fontNormal));
            celdaCantidadDetalle.setHorizontalAlignment(Element.ALIGN_CENTER);
            configurarBordes(celdaCantidadDetalle, true, true, false, false);
            detalle.addCell(celdaCantidadDetalle);

            PdfPCell celdaUnidadDetalle = new PdfPCell(new Paragraph(datosdetcomprobante.getTipunidDcomp(), fontNormal));
            celdaUnidadDetalle.setHorizontalAlignment(Element.ALIGN_CENTER);
            configurarBordes(celdaUnidadDetalle, true, true, false, false);
            detalle.addCell(celdaUnidadDetalle);

            PdfPCell celdaProductoDetalle = new PdfPCell(new Paragraph(datosdetcomprobante.getDescripcionDcomp(), fontNormal));
            celdaProductoDetalle.setHorizontalAlignment(Element.ALIGN_LEFT);
            configurarBordes(celdaProductoDetalle, true, true, false, false);
            detalle.addCell(celdaProductoDetalle);

            PdfPCell celdaPVDetalle = new PdfPCell(new Paragraph(String.valueOf(datosdetcomprobante.getValorunitarioDcomp()), fontNormal));
            celdaPVDetalle.setHorizontalAlignment(Element.ALIGN_CENTER);
            configurarBordes(celdaPVDetalle, true, true, false, false);
            detalle.addCell(celdaPVDetalle);

            PdfPCell celdaTotalDetalle = new PdfPCell(new Paragraph(String.valueOf(datosdetcomprobante.getPreciounitarioDcomp()), fontNormal));
            celdaTotalDetalle.setHorizontalAlignment(Element.ALIGN_CENTER);
            configurarBordes(celdaTotalDetalle, true, true, false, false);
            detalle.addCell(celdaTotalDetalle);

            document.add(detalle);

            if ("AFECTO".equals(datoscomprobante.getAfecComp())) {
                Paragraph subtotalg = new Paragraph("Sub Total  S/  " + datoscomprobante.getTotalventgravComp(), fontNormal);
                subtotalg.setAlignment(Element.ALIGN_RIGHT);
                document.add(subtotalg);
                /*Paragraph afectog = new Paragraph("Afecto   S/  0.00",fontNormal);
            afectog.setAlignment(Element.ALIGN_RIGHT);
            document.add(afectog); */
                Paragraph igvg = new Paragraph("IGV (18%)      S/ " + datoscomprobante.getIgvComp(), fontNormal);
                igvg.setAlignment(Element.ALIGN_RIGHT);
                document.add(igvg);
                Paragraph totalg = new Paragraph("Total     S/  " + datoscomprobante.getImptotalComp(), fontNegrita);
                totalg.setAlignment(Element.ALIGN_RIGHT);
                document.add(totalg);
            } else if ("EXONERADO".equals(datoscomprobante.getAfecComp())) {
                Paragraph subtotalg = new Paragraph("Sub Total  S/  0.00", fontNormal);
                subtotalg.setAlignment(Element.ALIGN_RIGHT);
                document.add(subtotalg);
                Paragraph afectog = new Paragraph("Exonerado   S/  " + datoscomprobante.getImptotalComp(), fontNormal);
                afectog.setAlignment(Element.ALIGN_RIGHT);
                document.add(afectog);
                Paragraph igvg = new Paragraph("IGV (18%)      S/   0.00", fontNormal);
                igvg.setAlignment(Element.ALIGN_RIGHT);
                document.add(igvg);
                Paragraph totalg = new Paragraph("Total     S/  " + datoscomprobante.getImptotalComp(), fontNegrita);
                totalg.setAlignment(Element.ALIGN_RIGHT);
                document.add(totalg);
            }

            document.add(new Paragraph(" ______________________________"));
            Paragraph montotexto = new Paragraph("SON:  " + convertir.Convertir(String.valueOf(datoscomprobante.getImptotalComp()), true), fontSubtitulo);
            document.add(montotexto);

            document.add(new Paragraph("TIPO DE PAGO: CONTADO", fontNegrita));

            /*----*/
            // Obtén la instancia de la imagen QR
            Image imagenqr2 = Image.getInstance(rutaqr);

            // Calcula el nuevo ancho y alto de la imagen reducida al 50%
            float nuevoAncho2 = (anchoPuntos - margenIzquierdo - margenDerecho);
            float nuevoAlto2 = imagenqr2.getHeight() * (nuevoAncho2 / imagenqr2.getWidth());

            // Escala la imagen a las nuevas dimensiones
            imagenqr2.scaleAbsolute(nuevoAncho2, nuevoAlto2);

            // Ajusta la imagen al tamaño de la hoja
            float anchoImagenQR = anchoPuntos - margenIzquierdo - margenDerecho - 50 - 55; // Ajusta según tus necesidades

            imagenqr2.scaleToFit(anchoImagenQR, imagenqr2.getScaledHeight());

            // Centra la imagen QR horizontalmente
            imagenqr2.setAbsolutePosition((anchoPuntos - imagenqr2.getScaledWidth()) / 2, imagenqr2.getAbsoluteY());

            // Agrega la imagen al PDF antes del título
            document.add(imagenqr2);

            Paragraph thash = new Paragraph(datoscomprobante.getHashComp(), fontNormal);
            thash.setAlignment(Element.ALIGN_CENTER);
            document.add(thash);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Representación impresa de la factura electrónica generada desde el Sistema Facturador SUNAT, "
                    + "usted puede consultar con su CLAVE SOL.", fontNegrita));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Gracias por su prefencia.", fontNegrita));

            Paragraph texto3 = new Paragraph(datosconfig.getTxt3Conf(), fontTerminos);
            texto3.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(texto3);

            document.newPage();
            document.setPageSize(new Rectangle(anchoPuntos, 50));

            // Agregar contenido en la nueva página
            Paragraph nuevoContenido = new Paragraph("Contenido en la nueva página", fontNegrita);
             Paragraph titulo2 = new Paragraph(datosconfig.getRazonConf(), fontTitulo);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo2);
            Paragraph serie2 = new Paragraph(datosconfig.getRucConf() + "\n"
                    + txtcomp + " ELECTRONICA" + "\n"
                    + datoscomprobante.getSerieComp(), fontTitulo);
            serie2.setAlignment(Element.ALIGN_CENTER);
            document.add(serie2);
             Paragraph fecha2 = new Paragraph(datoscomprobante.getFechaComp() + "   " + datoscomprobante.getHoraComp(), fontNormal);
            fecha2.setAlignment(Element.ALIGN_LEFT);
            document.add(fecha2);
            
            if ("AFECTO".equals(datoscomprobante.getAfecComp())) {

            } else if ("EXONERADO".equals(datoscomprobante.getAfecComp())) {
                 Paragraph servicio = new Paragraph("SERVICIO DE TRANSPORTE DE PASAJERO", fontNegrita);
                 Paragraph origen = new Paragraph("ORIGEN:" );
                 Paragraph destino = new Paragraph("DESTINO: ");
                 Paragraph datos = new Paragraph("PASAJERO: " + datosdetcomprobante.getDescripcionDcomp(), fontNormal);
                 Paragraph documento= new Paragraph();
                 document.add(datos);
                  document.add(fecha2);
            }
            

            document.close();
            System.out.println("PDF creado exitosamente: " + rutaPDF);
            JOptionPane.showMessageDialog(null, "PDF Creado Satisfactoriamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);

            // Abrir el documento PDF con el visor predeterminado
            try {
                File pdfFile = new File(rutaPDF);
                if (pdfFile.exists()) {
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(pdfFile);
                    } else {
                        System.out.println("La apertura del archivo no es compatible con el entorno de escritorio actual.");
                    }
                } else {
                    System.out.println("El archivo PDF no se encuentra en la ruta especificada.");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al abrir el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarBordes(PdfPCell celda, boolean top, boolean bottom, boolean left, boolean right) {
        int border = 0;
        if (top) {
            border |= Rectangle.TOP;
        }
        if (bottom) {
            border |= Rectangle.BOTTOM;
        }
        if (left) {
            border |= Rectangle.LEFT;
        }
        if (right) {
            border |= Rectangle.RIGHT;
        }
        celda.setBorder(border);
    }
}
