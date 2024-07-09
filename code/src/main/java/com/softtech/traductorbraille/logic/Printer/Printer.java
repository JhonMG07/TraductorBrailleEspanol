/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.softtech.traductorbraille.logic.Printer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * Printer es la intefaz que ofrece los metodos de impresión al sistema.
 *
 * @since 1.0
 * @version 2.0
 * @author SoftTech
 */
public interface Printer {
    
    final int PDF_WIDTH = 816;
    final int PDF_HEIGTH = 1056;
    
    void print(String text, int fontSize, Color fontColor) throws Exception;
    void drawText(Graphics2D g2d, List<String> lines, PageFormat pageFormat,  int fontSize, Color fontColor);
    List<String> calculateLines(Graphics2D g2d, String content, PageFormat pageFormat, int fontSize);

    default JPanel createContentPanel(String content, int fontSize, Color fontColor) {
        JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                Graphics2D g2d = (Graphics2D) graphics;
                List<String> lines = calculateLines(g2d, content, new PageFormat(), fontSize); 
                drawText(g2d, lines, new PageFormat(), fontSize, fontColor); 
            }
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(PDF_WIDTH, PDF_HEIGTH);
            }
        };
        configureContentPanel(contentPanel);
        return contentPanel;
    }

    private void configureContentPanel(JPanel panel) {
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(PDF_WIDTH, PDF_HEIGTH));
    }
    
}
