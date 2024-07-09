package com.softtech.traductorbraille.export;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.PdfEncodings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.JTextArea;

/**
 *
 * Servicio de exportación para convertir texto en Braille a diferentes formatos como TXT, PDF e imágenes.
 * 
 * @since 1.0
 * @version 2.0
 * @author SoftTech
 */

public class ExportService {

    /**
     * Exporta el contenido de un JTextArea a un archivo en el formato especificado.
     *
     * @param file El archivo de destino.
     * @param format El formato de exportación ("TXT", "PDF" o "PNG").
     * @param textArea El JTextArea que contiene el texto a exportar.
     * @throws Exception Si ocurre un error durante la exportación.
     */
    public void exportBraille(File file, String format, JTextArea textArea) throws Exception {
        switch (format.toUpperCase()) {
            case "TXT":
                exportToTxt(file, textArea.getText());
                break;
            case "PDF":
                exportToPdf(file, textArea);
                break;
            case "PNG":
                exportToImage(file, textArea);
                break;
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }

    /**
     * Exporta el contenido a un archivo de texto plano (TXT).
     *
     * @param file El archivo de destino.
     * @param content El contenido a exportar.
     * @throws FileNotFoundException Si no se puede encontrar el archivo.
     */
    private void exportToTxt(File file, String content) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(file)) {
            out.println(content);
        }
    }

    /**
     * Exporta el contenido a un archivo PDF.
     *
     * @param file El archivo de destino.
     * @param textArea El JTextArea que contiene el texto a exportar.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void exportToPdf(File file, JTextArea textArea) throws IOException {
        try (PdfWriter writer = new PdfWriter(file.getAbsolutePath());
             PdfDocument pdfDoc = new PdfDocument(writer)) {
            Document document = new Document(pdfDoc);

            String fontPath = "src\\main\\java\\com\\softtech\\traductorbraille\\fonts\\seguisym.ttf";
            com.itextpdf.kernel.font.PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H, true);

            Paragraph paragraph = new Paragraph(textArea.getText());
            paragraph.setFont(font);
            paragraph.setFontSize(textArea.getFont().getSize());

            Color awtColor = textArea.getForeground();
            DeviceRgb pdfColor = new DeviceRgb(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());
            paragraph.setFontColor(pdfColor);

            int fontStyle = textArea.getFont().getStyle();
            if ((fontStyle & Font.BOLD) != 0) {
                paragraph.setBold();
            }
            if ((fontStyle & Font.ITALIC) != 0) {
                paragraph.setItalic();
            }

            document.add(paragraph);
            document.close();
        }
    }

    /**
     * Exporta el contenido a un archivo de imagen (PNG).
     *
     * @param file El archivo de destino.
     * @param textArea El JTextArea que contiene el texto a exportar.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void exportToImage(File file, JTextArea textArea) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setFont(textArea.getFont());
        g2d.setColor(textArea.getForeground());
        g2d.drawString(textArea.getText(), 20, 50);
        g2d.dispose();
        ImageIO.write(bufferedImage, "png", file);
    }
}