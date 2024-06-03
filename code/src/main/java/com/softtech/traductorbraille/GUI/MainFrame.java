/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.softtech.traductorbraille.GUI;

import com.softtech.traductorbraille.logic.Translator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Jodual
 */
public class MainFrame extends javax.swing.JFrame {

    private final BrailleCell currentBrailleCell;
    private BrailleCell additionalBrailleCell;  // Nueva celda adicional
    private boolean isUpperCaseMode = false;
    private boolean isNumberMode = false;
    private Translator translator = new Translator();

    public MainFrame() {
        initComponents();
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyInput(e.getKeyCode());
            }
        });

        currentBrailleCell = new BrailleCell();
        braillePanel.add(currentBrailleCell);
    }

    private void handleKeyInput(int keyCode) {
    int index = -1;
    switch (keyCode) {
        case KeyEvent.VK_NUMPAD1, KeyEvent.VK_1 -> index = 0;
        case KeyEvent.VK_NUMPAD2, KeyEvent.VK_2 -> index = 2;
        case KeyEvent.VK_NUMPAD3, KeyEvent.VK_3 -> index = 4;
        case KeyEvent.VK_NUMPAD4, KeyEvent.VK_4 -> index = 1;
        case KeyEvent.VK_NUMPAD5, KeyEvent.VK_5 -> index = 3;
        case KeyEvent.VK_NUMPAD6, KeyEvent.VK_6 -> index = 5;
        case KeyEvent.VK_ENTER -> {
            if (canTranslate()) {
                translateCurrentBrailleCell();
                currentBrailleCell.clearPoints();
                if (additionalBrailleCell != null) {
                    additionalBrailleCell.clearPoints();
                }
                updateBraillePanelLayout();
            } else {
                JOptionPane.showMessageDialog(this, "El modo de mayúscula o número está activado, pero la celda de braille actual está vacía.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
    }
    if (index != -1) {
        boolean currentState = currentBrailleCell.getPoint(index);
        currentBrailleCell.setPoint(index, !currentState);
        braillePanel.repaint();
    }
}

private boolean isBrailleCellEmpty(BrailleCell cell) {
    for (int i = 0; i < 6; i++) {
        if (cell.getPoint(i)) {
            return false;
        }
    }
    return true;
}

private boolean canTranslate() {
    if ((isUpperCaseMode || isNumberMode) && isBrailleCellEmpty(currentBrailleCell)) {
        return false;
    }
    return true;
}

private void translateCurrentBrailleCell() {
    StringBuilder cellText = new StringBuilder();
    StringBuilder targetCellText = new StringBuilder();

    if (currentBrailleCell.getPoint(0)) cellText.append('1');
    if (currentBrailleCell.getPoint(2)) cellText.append('2');
    if (currentBrailleCell.getPoint(4)) cellText.append('3');
    if (currentBrailleCell.getPoint(1)) cellText.append('4');
    if (currentBrailleCell.getPoint(3)) cellText.append('5');
    if (currentBrailleCell.getPoint(5)) cellText.append('6');

    if (isUpperCaseMode || isNumberMode) {
        if (additionalBrailleCell.getPoint(0)) targetCellText.append('1');
        if (additionalBrailleCell.getPoint(2)) targetCellText.append('2');
        if (additionalBrailleCell.getPoint(4)) targetCellText.append('3');
        if (additionalBrailleCell.getPoint(1)) targetCellText.append('4');
        if (additionalBrailleCell.getPoint(3)) targetCellText.append('5');
        if (additionalBrailleCell.getPoint(5)) targetCellText.append('6');
        targetCellText.append(" ");
    }

    String combinedText = targetCellText.toString() + cellText.toString();
    String translatedText = translator.translateToSpanish(combinedText);

    if (translatedText.equals("?")) {
        JOptionPane.showMessageDialog(this, "La traducción para la combinación ingresada no existe en el diccionario.", "Error de Traducción", JOptionPane.ERROR_MESSAGE);
        return;
    }

    textOutput.append(translatedText);

    String brailleUnicode;
    if (isUpperCaseMode || isNumberMode) {
        brailleUnicode = translator.brailleToUnicode(combinedText);
    } else {
        brailleUnicode = translator.brailleToUnicode(cellText.toString());
    }

    brailleInputArea.append(brailleUnicode);
}


    private void updateBraillePanelLayout() {
        int columns;
        int panelWidth;
        if (isUpperCaseMode || isNumberMode) {
            columns = 2;
            panelWidth = 200;
        } else {
            columns = 1;
            panelWidth = 100;
        }

        braillePanel.setLayout(new GridLayout(1, columns));

        if (columns == 1) {
            if (braillePanel.getComponentCount() > 1) {
                braillePanel.remove(0);
                additionalBrailleCell = null; // Asegúrate de que la celda adicional se elimine
            }
        } else {
            if (braillePanel.getComponentCount() == 1) {
                additionalBrailleCell = new BrailleCell();
                braillePanel.add(additionalBrailleCell, 0);
            }
        }

        braillePanel.setPreferredSize(new Dimension(panelWidth, braillePanel.getHeight()));
        braillePanel.revalidate();

        if (additionalBrailleCell != null) {
            if (isUpperCaseMode) {
                additionalBrailleCell.setPoint(1, true);
                additionalBrailleCell.setPoint(5, true);
                additionalBrailleCell.setPoint(4, false);
                additionalBrailleCell.setPoint(3, false);
            } else if (isNumberMode) {
                additionalBrailleCell.setPoint(4, true);
                additionalBrailleCell.setPoint(1, true);
                additionalBrailleCell.setPoint(3, true);
                additionalBrailleCell.setPoint(5, true);
            }
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        braillePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textOutput = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        brailleInputArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        uppercaseButton = new javax.swing.JButton();
        numberButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        braillePanel.setPreferredSize(new java.awt.Dimension(100, 150));

        javax.swing.GroupLayout braillePanelLayout = new javax.swing.GroupLayout(braillePanel);
        braillePanel.setLayout(braillePanelLayout);
        braillePanelLayout.setHorizontalGroup(
            braillePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        braillePanelLayout.setVerticalGroup(
            braillePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        textOutput.setEditable(false);
        textOutput.setColumns(20);
        textOutput.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        textOutput.setLineWrap(true);
        textOutput.setRows(5);
        textOutput.setWrapStyleWord(true);
        jScrollPane1.setViewportView(textOutput);

        brailleInputArea.setEditable(false);
        brailleInputArea.setColumns(20);
        brailleInputArea.setFont(new Font("SimBraille", Font.PLAIN, 18));
        brailleInputArea.setLineWrap(true);
        brailleInputArea.setRows(5);
        brailleInputArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(brailleInputArea);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("1");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("2");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("3");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("4");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("5");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("6");

        uppercaseButton.setText("Mayúscula");
        uppercaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uppercaseButtonActionPerformed(evt);
            }
        });

        numberButton.setText("Número");
        numberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(braillePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uppercaseButton)
                    .addComponent(numberButton))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(braillePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel5)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(uppercaseButton)
                                .addGap(48, 48, 48)
                                .addComponent(numberButton)))))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uppercaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uppercaseButtonActionPerformed
        isUpperCaseMode = !isUpperCaseMode;
        isNumberMode = false;
        updateBraillePanelLayout();
        requestFocusInWindow();

    }//GEN-LAST:event_uppercaseButtonActionPerformed

    private void numberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberButtonActionPerformed
        isNumberMode = !isNumberMode;
        isUpperCaseMode = false;
        updateBraillePanelLayout();
        requestFocusInWindow();
    }//GEN-LAST:event_numberButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea brailleInputArea;
    private javax.swing.JPanel braillePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton numberButton;
    private javax.swing.JTextArea textOutput;
    private javax.swing.JButton uppercaseButton;
    // End of variables declaration//GEN-END:variables
}
