package com.softtech.traductorbraille.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * JFPreview es una ventana de previsualización e impresión para texto en formato espejo.
 * Permite previsualizar y enviar el texto a imprimir.
 * 
 * @since 1.0
 * @version 1.0
 * @author SoftTech
 */
public class JFPreview extends javax.swing.JFrame {

    String texto;
    /**
     * Crea una nueva instancia de JFPreview.
     *
     * @param texto El texto a previsualizar e imprimir.
     */
    public JFPreview(String texto) {
        initComponents();
        this.texto = texto;
        previewText(texto);
    }
    
    /**
     * Muestra una vista previa del texto proporcionado en el panel de previsualización.
     *
     * @param content El contenido de texto a previsualizar.
     */
    private void previewText(String content) {
        JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.setFont(new Font("SansSerif", Font.PLAIN, 32));
                g2d.setColor(Color.BLACK);
                int lineHeight = g2d.getFontMetrics().getHeight();
                int y1 = lineHeight; 
                int margin = 50;
                for (String line : content.split("\n")) {
                    String[] words = line.split(" ");
                    String currentLine = words[0];
                    for (int i = 1; i < words.length; i++) {
                        if (g2d.getFontMetrics().stringWidth(currentLine + " " + words[i]) < getWidth() - 2 * margin) {
                            currentLine += " " + words[i];
                        } else {
                            g2d.drawString(currentLine, margin, y1);
                            currentLine = words[i];
                            y1 += lineHeight;
                        }
                    }
                    g2d.drawString(currentLine, margin, y1);
                    y1 += lineHeight;
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(470, 440);
            }
        };
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setPreferredSize(new Dimension(470, 440));
        JPPreview.removeAll();
        JPPreview.setLayout(new BorderLayout());
        JPPreview.add(new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        JPPreview.revalidate();
        JPPreview.repaint();
    }
    
    /**
     * Envía el contenido del texto a la impresora para imprimirlo.
     *
     * @param content El contenido de texto a imprimir.
     * @throws PrinterException Si ocurre un error durante la impresión.
     */
    public void printText(String content) throws PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable((Graphics graphics, PageFormat pageFormat, int pageIndex) -> {
            if (pageIndex > 0) {
                return NO_SUCH_PAGE;
            }
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 32));
            g2d.setColor(Color.BLACK);
            int lineHeight = g2d.getFontMetrics().getHeight();
            int y1 = 0;
            int margin = 50;
            for (String line : content.split("\n")) {
                String[] words = line.split(" ");
                String currentLine = words[0];
                for (int i = 1; i < words.length; i++) {
                    if (g2d.getFontMetrics().stringWidth(currentLine + " " + words[i]) < pageFormat.getImageableWidth() - 2 * margin) {
                        currentLine += " " + words[i];
                    } else {
                        y1 += lineHeight;
                        g2d.drawString(currentLine, margin, y1);
                        currentLine = words[i];
                    }
                }
                y1 += lineHeight;
                g2d.drawString(currentLine, margin, y1);
            }
            return PAGE_EXISTS;
        });

        // Mostrar el cuadro de diálogo de impresión
        if (job.printDialog()) {
            job.print();
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

        jLabel1 = new javax.swing.JLabel();
        JPPreview = new javax.swing.JPanel();
        jBImprimir = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Previsualizar impresión espejo");

        JPPreview.setPreferredSize(new java.awt.Dimension(470, 440));

        javax.swing.GroupLayout JPPreviewLayout = new javax.swing.GroupLayout(JPPreview);
        JPPreview.setLayout(JPPreviewLayout);
        JPPreviewLayout.setHorizontalGroup(
            JPPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JPPreviewLayout.setVerticalGroup(
            JPPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        jBImprimir.setText("Imprimir");
        jBImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBImprimirActionPerformed(evt);
            }
        });

        jBCancelar.setText("Cancelar");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JPPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBImprimir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBCancelar)))
                        .addGap(0, 233, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBImprimir)
                    .addComponent(jBCancelar))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void jBImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBImprimirActionPerformed
        try {
            printText(texto);
        } catch (PrinterException ex) {
            Logger.getLogger(JFPreview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBImprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPPreview;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBImprimir;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
