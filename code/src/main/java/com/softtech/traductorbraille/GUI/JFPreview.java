package com.softtech.traductorbraille.GUI;

import com.softtech.traductorbraille.translator.Dictionary;
import com.softtech.traductorbraille.printer.MirrorPrinter;
import com.softtech.traductorbraille.printer.NormalPrinter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.print.PrinterException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * JFPreview es la interfaz gráfica que permite al usuario realizar
 * ver la vista previa de su documento a imprimir
 *
 * @since 1.0
 * @version 2.0
 * @author SoftTech
 */
public class JFPreview extends javax.swing.JFrame {

    private String text;
    private String mirrorText;
    private int fontSize;
    private final NormalPrinter normal;
    private final MirrorPrinter mirror;
    private final Dictionary mirrorBraille;
    private Color fontColor;

    /**
     * Crea una nueva instancia de JFPreview.
     *
     * @param texto El texto a previsualizar e imprimir.
     * @param size
     * @param selectedColor
     * @param isBraille
     */
    public JFPreview(String texto, int size, Color selectedColor, boolean isBraille) {
        initComponents();
        this.text = texto;
        this.fontSize = size;
        normal = new NormalPrinter();
        mirror = new MirrorPrinter();
        if (!isBraille){
            cmbImprimir.setSelectedIndex(1);
            cmbImprimir.setEnabled(isBraille);
        } 
        mirrorBraille = new Dictionary();
        fontColor = selectedColor;
        mirrorText = mirrorBraille.generateBrailleMirror(texto);
        previewText();
        setLocationRelativeTo(null);
    }

    private void previewText() {
        JPanel contentPanel;
        if (cmbImprimir.getSelectedIndex() == 0) {
            contentPanel = mirror.createContentPanel(mirrorText, fontSize, fontColor);
        } else {
            contentPanel = normal.createContentPanel(text, fontSize, fontColor);
        }
        addToMainContainer(contentPanel);
    }

    private void addToMainContainer(JPanel contentPanel) {
        JPPreview.removeAll();
        JPPreview.setLayout(new BorderLayout());
        JPPreview.add(new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        JPPreview.revalidate();
        JPPreview.repaint();
    }

    /**
     * Este método es llamado desde el constructor para inicializar el
     * formulario. ADVERTENCIA: No modifique este código. El contenido de este
     * método es siempre regenerado por el Editor de Formularios.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPPreview = new javax.swing.JPanel();
        jBImprimir = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbImprimir = new javax.swing.JComboBox<>();
        lb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Brailling");
        setBackground(new java.awt.Color(153, 153, 153));
        setResizable(false);

        JPPreview.setPreferredSize(new java.awt.Dimension(470, 440));

        javax.swing.GroupLayout JPPreviewLayout = new javax.swing.GroupLayout(JPPreview);
        JPPreview.setLayout(JPPreviewLayout);
        JPPreviewLayout.setHorizontalGroup(
            JPPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );
        JPPreviewLayout.setVerticalGroup(
            JPPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        jBImprimir.setBackground(new java.awt.Color(204, 204, 204));
        jBImprimir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBImprimir.setText("Imprimir");
        jBImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBImprimirActionPerformed(evt);
            }
        });

        jBCancelar.setBackground(new java.awt.Color(204, 204, 204));
        jBCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBCancelar.setText("Cancelar");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Braillingo - Vista Previa");

        cmbImprimir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "espejo", "normal" }));
        cmbImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbImprimirActionPerformed(evt);
            }
        });

        lb.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb.setForeground(new java.awt.Color(153, 153, 153));
        lb.setText("Tipo de Impresión :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(lb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jBCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBImprimir)
                .addGap(150, 150, 150))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(JPPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JPPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCancelar)
                    .addComponent(jBImprimir))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBImprimirActionPerformed
        try {
            if (cmbImprimir.getSelectedIndex() == 0) {
                mirror.print(mirrorText, fontSize, fontColor);
            } else {
                normal.print(text, fontSize, fontColor);
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, "Error al imprimir: " + ex.getMessage(), "Error de Impresión", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBImprimirActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void cmbImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbImprimirActionPerformed
        // TODO add your handling code here:
        previewText();
    }//GEN-LAST:event_cmbImprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPPreview;
    private javax.swing.JComboBox<String> cmbImprimir;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb;
    // End of variables declaration//GEN-END:variables
}
