package com.softtech.traductorbraille.logic;

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


/**
 *
 * Servicio de exportación para convertir texto en Braille a diferentes formatos como TXT, PDF e imágenes.
 * 
 * @since 1.0
 * @version 1.0
 * @author SoftTech
 */

public class ExportService {
    
    /**
     * Exporta el texto en Braille al archivo especificado en el formato deseado.
     *
     * @param file El archivo de destino donde se exportará el contenido.
     * @param format El formato de exportación (TXT, PDF, PNG).
     * @param brailleText El texto en Braille que se va a exportar.
     * @param fontSize El tamaño de la fuente para el contenido exportado.
     * @param isBold Indica si el texto debe estar en negrita.
     * @param isItalic Indica si el texto debe estar en cursiva.
     * @param color El color del texto.
     * @throws Exception Si ocurre un error durante la exportación.
     */
    
    public void exportBraille(File file, String format, String brailleText, int fontSize, boolean isBold, boolean isItalic, java.awt.Color color) throws Exception {
        switch (format.toUpperCase()) {
            case "TXT":
                exportAsTxt(file, brailleText);
                break;
            case "PDF":
                exportAsPdf(file, brailleText, fontSize, isBold, isItalic, color);
                break;
            case "PNG":
                exportAsImage(file, brailleText, fontSize, isBold, isItalic, color, format);
                break;
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
    
    /**
     * Exporta el contenido como archivo de texto.
     *
     * @param file El archivo de destino.
     * @param content El contenido a exportar.
     * @throws FileNotFoundException Si el archivo no puede ser creado o abierto.
     */
    
    private void exportAsTxt(File file, String content) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(file)) {
            out.println(content);
        }
    }

    /**
     * Exporta el contenido como archivo PDF.
     *
     * @param file El archivo de destino.
     * @param content El contenido a exportar.
     * @param fontSize El tamaño de la fuente.
     * @param isBold Indica si el texto debe estar en negrita.
     * @param isItalic Indica si el texto debe estar en cursiva.
     * @param color El color del texto.
     * @throws IOException Si ocurre un error durante la creación del PDF.
     */
    
    private void exportAsPdf(File file, String content, int fontSize, boolean isBold, boolean isItalic, java.awt.Color color) throws IOException {
        try (PdfWriter writer = new PdfWriter(file.getAbsolutePath());
             PdfDocument pdfDoc = new PdfDocument(writer)) {
            Document document = new Document(pdfDoc);

            String fontPath = "src/main/java/folder/seguisym.ttf";
            com.itextpdf.kernel.font.PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H, true);

            com.itextpdf.layout.element.Paragraph paragraph = new Paragraph(content);
            paragraph.setFontSize(fontSize);
            paragraph.setFontColor(new DeviceRgb(color.getRed(), color.getGreen(), color.getBlue()));
            paragraph.setFont(font);
            
            if (isBold && isItalic) {
                paragraph.setBold().setItalic();
            } else if (isBold) {
                paragraph.setBold();
            } else if (isItalic) {
                paragraph.setItalic();
            }

            document.add(paragraph);
            document.close();
        }
    }
    

    /**
     * Exporta el contenido como una imagen.
     *
     * @param file El archivo de destino.
     * @param content El contenido a exportar.
     * @param fontSize El tamaño de la fuente.
     * @param isBold Indica si el texto debe estar en negrita.
     * @param isItalic Indica si el texto debe estar en cursiva.
     * @param color El color del texto.
     * @param format El formato de la imagen (PNG).
     * @throws IOException Si ocurre un error durante la creación de la imagen.
     */
    private void exportAsImage(File file, String content, int fontSize, boolean isBold, boolean isItalic, java.awt.Color color, String format) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        int fontStyle = Font.PLAIN;
        if (isBold && isItalic) {
            fontStyle = Font.BOLD | Font.ITALIC;
        } else if (isBold) {
            fontStyle = Font.BOLD;
        } else if (isItalic) {
            fontStyle = Font.ITALIC;
        }

        g2d.setFont(new Font("Braille", fontStyle, fontSize));
        g2d.setColor(color);
        g2d.drawString(content, 20, 50);
        g2d.dispose();
        ImageIO.write(bufferedImage, format.toLowerCase(), file);
    }    
}
