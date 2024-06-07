package com.softtech.traductorbraille.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * La clase BrailleCell proporciona un cuadratín de forma gráfica para el usuario.
 * 
 * @since 1.0
 * @version 1.0
 * @author SoftTech
 */
public class BrailleCell extends javax.swing.JPanel {

    private boolean[] points = new boolean[6];

    /**
     * Constructor de la clase BrailleCell.
     * Establece el tamaño y el borde del panel.
     */
    public BrailleCell() {
        setSize(54, 88);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Obtiene el estado de un punto Braille específico.
     * @param index El índice del punto Braille.
     * @return true si el punto está activo, false si está inactivo.
     */
    public boolean getPoint(int index) {
        return points[index];
    }

    /**
     * Establece el estado de un punto Braille específico.
     * @param index El índice del punto Braille.
     * @param value El valor que indica si el punto debe activarse o desactivarse.
     */
    public void setPoint(int index, boolean value) {
        points[index] = value;
        repaint();
    }

    /**
     * Limpia todos los puntos Braille de la celda.
     */
    public void clearPoints() {
        for (int i = 0; i < points.length; i++) {
            points[i] = false;
        }
        repaint();
    }

    /**
     * Método para pintar los puntos Braille en la celda.
     * @param g El contexto gráfico en el que se dibujarán los puntos.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        int cellWidth = getWidth() / 2; 
        int cellHeight = getHeight() / 3; 

        String[] numbers = {"1", "4", "2", "5", "3", "6"};

        for (int i = 0; i < 6; i++) {
            int x = (i % 2) * cellWidth + cellWidth / 4;
            int y = (i / 2) * cellHeight + cellHeight / 4; 

            if (points[i]) {
                g.fillOval(x, y, cellWidth / 2, cellHeight / 2);
            } else {
                g.drawOval(x, y, cellWidth / 2, cellHeight / 2);
            }

            FontMetrics fm = g.getFontMetrics();
            int stringWidth = fm.stringWidth(numbers[i]);
            int stringHeight = fm.getAscent();
            g.drawString(numbers[i], x + (cellWidth / 4) - (stringWidth / 2), y + (cellHeight / 4) + (stringHeight / 2));
        }
    }

    /**
     * Este método es llamado desde el constructor para inicializar el formulario.
     * ADVERTENCIA: No modifique este código. El contenido de este método es siempre
     * regenerado por el Editor de Formularios.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
