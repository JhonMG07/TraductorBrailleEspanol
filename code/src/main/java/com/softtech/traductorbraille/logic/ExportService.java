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
import javax.swing.JTextArea;

/**
 *
 * Servicio de exportación para convertir texto en Braille a diferentes formatos como TXT, PDF e imágenes.
 * 
 * @since 1.0
 * @version 1.0
 * @author SoftTech
 */

public class ExportService {

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

    private void exportToTxt(File file, String content) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(file)) {
            out.println(content);
        }
    }

    private void exportToPdf(File file, JTextArea textArea) throws IOException {
        try (PdfWriter writer = new PdfWriter(file.getAbsolutePath());
             PdfDocument pdfDoc = new PdfDocument(writer)) {
            Document document = new Document(pdfDoc);

            String fontPath = "src/main/java/folder/seguisym.ttf";
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