/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softtech.traductorbraille.logic;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


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
    public void exportBraille(File file, String format, String brailleText) throws FileNotFoundException, IOException {
        switch (format.toUpperCase()) {
            case "TXT":
                exportAsTxt(file, brailleText);
                break;
            case "PDF":
                exportAsPdf(file, brailleText);
                break;
            case "PNG":
                exportAsImage(file, brailleText, format);
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
    private void exportAsPdf(File file, String content) throws IOException {
        try (PdfWriter writer = new PdfWriter(file.getAbsolutePath());
             PdfDocument pdfDoc = new PdfDocument(writer)) {
            Document document = new Document(pdfDoc);
            document.add(new Paragraph(content));
            document.close();
        }
    }

    private void exportAsImage(File file, String content, String format) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setFont(new Font("Braille", Font.PLAIN, 20));
        g2d.drawString(content, 20, 20);
        g2d.dispose();
        ImageIO.write(bufferedImage, format.toLowerCase(), file);
    }
    

}
