/*
 * Esta clase proporciona una interfaz gráfica de usuario para exportar traducciones a diferentes formatos de archivo, como TXT, PDF y PNG.
 */
package com.softtech.traductorbraille.GUI;

import com.softtech.traductorbraille.logic.ExportService;
import java.awt.Color;
import java.io.File;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * La clase JFExport proporciona una interfaz gráfica de usuario para exportar traducciones a diferentes formatos de archivo.
 * 
 * @since 1.0
 * @version 1.0
 * @author SoftTech
 */
public class JFExport extends javax.swing.JFrame {

    private final String text;
    private final JFileChooser fileChooser;
    private JFTranslator jFTranslator;
    private Color selectedColor;

    /**
     * Constructor de la clase JFExport.
     * 
     * @param text El texto a exportar.
     */
    public JFExport(String text) {
        this.selectedColor = Color.BLACK;
        this.text = text;
        initComponents();
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        setDefaultCloseOperation(JFExport.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    /**
     * Constructor privado de la clase JFExport.
     */
    private JFExport() {
        this.selectedColor = Color.BLACK;
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Obtiene el texto a exportar.
     * 
     * @return El texto a exportar.
     */
    public String getTexto() {
        return text;
    }

    /**
     * Inicializa los componentes de la interfaz gráfica.
     * ADVERTENCIA: Este método se genera automáticamente y no se debe modificar su contenido.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxOpciones = new javax.swing.JComboBox<>();
        SaveButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        ButtonSelectFolder = new javax.swing.JButton();
        jTextFieldNombreArchivo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxTamañoLetra = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jCheckBoxNegrita = new javax.swing.JCheckBox();
        jCheckBoxCursiva = new javax.swing.JCheckBox();
        jButtonColor = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("GUARDAR TRADUCCION");

        jComboBoxOpciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TXT", "PDF", "PNG" }));

        SaveButton.setText("GUARDAR");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        ButtonSelectFolder.setText("...");
        ButtonSelectFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSelectFolderActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre del Archivo");

        jLabel3.setText("Ruta del Archivo");

        jLabel4.setText("Formato del Archivo");

        jLabel5.setText("FORMATO DEL ARCHIVO");

        jComboBoxTamañoLetra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30" }));

        jLabel6.setText("Tamaño Fuente");

        jCheckBoxNegrita.setText("Negrita");

        jCheckBoxCursiva.setText("Cursiva");

        jButtonColor.setText("Color Texto");
        jButtonColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColorActionPerformed(evt);
            }
        });

        jButton1.setText("REGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(238, 238, 238))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonSelectFolder))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldNombreArchivo)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SaveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(79, 79, 79)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(jComboBoxTamañoLetra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBoxNegrita)
                        .addGap(27, 27, 27)
                        .addComponent(jCheckBoxCursiva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonColor)))
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxTamañoLetra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonSelectFolder)
                    .addComponent(jCheckBoxNegrita)
                    .addComponent(jCheckBoxCursiva)
                    .addComponent(jButtonColor))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveButton)
                    .addComponent(jButton1))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Maneja el evento de clic en el botón "REGRESAR".
     * 
     * @param evt El evento de acción.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Maneja el evento de clic en el botón de selección de carpeta.
     * 
     * @param evt El evento de acción.
     */
    private void ButtonSelectFolderActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BotonSeleccionarCarpetaActionPerformed
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile();
            jTextField1.setText(selectedFolder.getAbsolutePath());
        }
    }

    /**
     * Maneja el evento de clic en el botón "GUARDAR".
     * 
     * @param evt El evento de acción.
     */
    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BotonGuardarActionPerformed
        String format = (String) jComboBoxOpciones.getSelectedItem();
        String filename = jTextFieldNombreArchivo.getText();
        String filePath = jTextField1.getText();

        if (filePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una carpeta para guardar el archivo.");
            return;
        }

        if (!filename.toLowerCase().endsWith("." + format.toLowerCase())) {
            filename += "." + format.toLowerCase();
        }

        File file = new File(filePath, filename);
        if (!file.getParentFile().isDirectory()) {
            JOptionPane.showMessageDialog(this, "La ruta seleccionada no es un directorio válido.");
            return;
        }

        try {
            int fontSize = Integer.parseInt((String) jComboBoxTamañoLetra.getSelectedItem());
            boolean isBold = jCheckBoxNegrita.isSelected();
            boolean isItalic = jCheckBoxCursiva.isSelected();
            ExportService exportService = new ExportService();
            exportService.exportBraille(file, format, text, fontSize, isBold, isItalic, selectedColor);
            JOptionPane.showMessageDialog(this, "Texto guardado correctamente como " + format);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + ex.getMessage());
        }
    }

    /**
     * Maneja el evento de clic en el botón "Color Texto".
     * 
     * @param evt El evento de acción.
     */
    private void jButtonColorActionPerformed(java.awt.event.ActionEvent evt) {
        Color color = JColorChooser.showDialog(this, "Seleccionar color de texto", selectedColor);
        if (color != null) {
            selectedColor = color;
        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton ButtonSelectFolder;
    private javax.swing.JButton SaveButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonColor;
    private javax.swing.JCheckBox jCheckBoxCursiva;
    private javax.swing.JCheckBox jCheckBoxNegrita;
    private javax.swing.JComboBox<String> jComboBoxOpciones;
    private javax.swing.JComboBox<String> jComboBoxTamañoLetra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldNombreArchivo;
    // End of variables declaration      
}