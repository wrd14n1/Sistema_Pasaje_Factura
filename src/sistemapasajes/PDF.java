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

import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
import sistemapasajes.modelo.ComprobanteModel;
import sistemapasajes.modelo.ConfiguracionModel;
import sistemapasajes.modelo.DcomprobanteModel;
import sistemapasajes.modelo.EmpresaModel;

public class PDF {
    public void crearPDF(ConfiguracionModel datosconfig, ComprobanteModel datoscomprobante, DcomprobanteModel datosdetcomprobante, EmpresaModel datosemp) {
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

            // Verifica si el archivo ya existe
            File archivoPDF = new File(rutaPDF);
            if (archivoPDF.exists()) {
                int opcion = JOptionPane.showConfirmDialog(null, "El archivo " + nombrePDF + " ya existe. ¿Desea sobrescribirlo?", "Confirmar", JOptionPane.YES_NO_OPTION);

                if (opcion != JOptionPane.YES_OPTION) {
                    // Si el usuario no desea sobrescribir, termina la función
                    return;
                }
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

            Paragraph texto3 = new Paragraph(datosconfig.getTxt3Conf(), fontNormal);
            texto3.setAlignment(Element.ALIGN_CENTER);
            document.add(texto3);

            document.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(1);
            PdfPCell celda = new PdfPCell(new Paragraph(datosconfig.getRucConf() + "\n"
                    + datoscomprobante.getTipoComp() + " ELECTRONICA" + "\n"
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

            Paragraph dircliente = new Paragraph("Direccion: " + datosemp.getDireccionEmp(), fontNormal);
            dircliente.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(dircliente);

            Paragraph nruc = new Paragraph("RUC: " + datoscomprobante.getDocclienteComp(), fontNormal);
            nruc.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(nruc);

            document.add(new Paragraph(" "));

            PdfPTable detalle = new PdfPTable(5);
            float anchoTabla = anchoPuntos - margenIzquierdo - margenDerecho;
            float[] columnWidths = {anchoTabla * 0.1f, anchoTabla * 0.1f, anchoTabla * 0.4f, anchoTabla * 0.1f, anchoTabla * 0.1f};
            detalle.setWidths(columnWidths);

            PdfPCell celdaCantidad = new PdfPCell(new Paragraph("Cantidad", fontNegrita));
            celdaCantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            detalle.addCell(celdaCantidad);

            PdfPCell celdaUnidad = new PdfPCell(new Paragraph("UN", fontNegrita));
            celdaUnidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            detalle.addCell(celdaUnidad);

            PdfPCell celdaProducto = new PdfPCell(new Paragraph("Producto", fontNegrita));
            celdaProducto.setHorizontalAlignment(Element.ALIGN_CENTER);
            detalle.addCell(celdaProducto);

            PdfPCell celdaPV = new PdfPCell(new Paragraph("PV", fontNegrita));
            celdaPV.setHorizontalAlignment(Element.ALIGN_CENTER);
            detalle.addCell(celdaPV);

            PdfPCell celdaTotal = new PdfPCell(new Paragraph("Total", fontNegrita));
            celdaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
            detalle.addCell(celdaTotal);

            detalle.addCell(new Paragraph(String.valueOf(datosdetcomprobante.getCantidadDcomp()), fontNormal));
            detalle.addCell(new Paragraph(datosdetcomprobante.getTipunidDcomp(), fontNormal));
            detalle.addCell(new Paragraph(datosdetcomprobante.getDescripcionDcomp(), fontNormal));
            detalle.addCell(new Paragraph(String.valueOf(datosdetcomprobante.getValorunitarioDcomp()), fontNormal));
            detalle.addCell(new Paragraph(String.valueOf(datosdetcomprobante.getPreciounitarioDcomp()), fontNormal));

            document.add(detalle);

            document.close();
            System.out.println("PDF creado exitosamente: " + rutaPDF);
            JOptionPane.showMessageDialog(null, "PDF Creado Satisfactoriamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
