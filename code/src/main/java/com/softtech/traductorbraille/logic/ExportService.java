/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softtech.traductorbraille.logic;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.constants.StandardFonts;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


/**
 *
 * @author USUARIO
 */
public class ExportService {
    
    /**
     *
     * @param file
     * @param format
     * @param brailleText
     * @throws Exception
     */
    /**public void exportBraille(File file, String format, String brailleText) throws Exception {
        switch (format.toUpperCase()) {
            case "TXT" -> exportAsTxt(file, brailleText);
            case "PDF" -> exportAsPdf(file, brailleText);
            case "PNG" -> exportAsImage(file, brailleText, format);
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }*/
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
    
    private void exportAsTxt(File file, String content) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(file)) {
            out.println(content);
        }
    }

    /**private void exportAsPdf(File file, String content) throws IOException {
        try (PdfWriter writer = new PdfWriter(file.getAbsolutePath());
             PdfDocument pdfDoc = new PdfDocument(writer); Document document = new Document(pdfDoc)) {
            document.add(new Paragraph(content));
        }
    }*/
    private void exportAsPdf(File file, String content, int fontSize, boolean isBold, boolean isItalic, java.awt.Color color) throws IOException {
        try (PdfWriter writer = new PdfWriter(file.getAbsolutePath());
             PdfDocument pdfDoc = new PdfDocument(writer)) {
            Document document = new Document(pdfDoc);

            com.itextpdf.layout.element.Paragraph paragraph = new Paragraph(content);
            paragraph.setFontSize(fontSize);
            paragraph.setFontColor(new DeviceRgb(color.getRed(), color.getGreen(), color.getBlue()));

            if (isBold && isItalic) {
                paragraph.setFont(com.itextpdf.kernel.font.PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.TIMES_BOLDITALIC));
            } else if (isBold) {
                paragraph.setFont(com.itextpdf.kernel.font.PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.TIMES_BOLD));
            } else if (isItalic) {
                paragraph.setFont(com.itextpdf.kernel.font.PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.TIMES_ITALIC));
            } else {
                paragraph.setFont(com.itextpdf.kernel.font.PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.TIMES_ROMAN));
            }

            document.add(paragraph);
            document.close();
        }
    }

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
        g2d.drawString(content, 20, 50); // Ajusta la posición del texto según sea necesario
        g2d.dispose();
        ImageIO.write(bufferedImage, format.toLowerCase(), file);
    }    
}
