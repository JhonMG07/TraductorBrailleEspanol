package com.softtech.traductorbraille.GUI;

import com.softtech.traductorbraille.logic.Translator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;


/**
 *
 * @author Isma2
 */
public class JFTranslator extends javax.swing.JFrame {

    private BrailleCell currentBrailleCell;
    private BrailleCell additionalBrailleCell;
    private boolean isUpperCaseMode = false;
    private boolean isNumberMode = false;
    private Translator translator = new Translator();

    private static final int[] BRAILLE_INDEX_MAPPING = {0, 2, 4, 1, 3, 5};
    private static final int[] KEY_EVENT_MAPPING = {
        KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2, KeyEvent.VK_NUMPAD3,
        KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD6
    };

    /**
     * Creates new form JFTranslator
     */
    public JFTranslator() {
        initComponents();
        configureWindow();
        setTranslationMode(!getTranslationMode());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPEspañol = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTASpanish = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPBraille = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTBraille = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jCBMayusculas = new javax.swing.JCheckBox();
        jCBNumeros = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPCuadratin = new javax.swing.JPanel();
        braillePanel = new javax.swing.JPanel();
        jBTraducir = new javax.swing.JButton();
        jBBorrar = new javax.swing.JButton();
        jBSalir = new javax.swing.JButton();
        jLEspañolEntrada = new javax.swing.JLabel();
        jBIntercambio = new javax.swing.JButton();
        jLBrailleEntrada = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMFile = new javax.swing.JMenu();
        jMExportar = new javax.swing.JMenuItem();
        jMImprimir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Brailingo");
        setResizable(false);

        jPEspañol.setBorder(javax.swing.BorderFactory.createTitledBorder("Español"));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTASpanish.setColumns(20);
        jTASpanish.setRows(5);
        jTASpanish.setWrapStyleWord(true);
        jTASpanish.setMinimumSize(new java.awt.Dimension(235, 85));
        jTASpanish.setPreferredSize(new java.awt.Dimension(235, 85));
        jScrollPane1.setViewportView(jTASpanish);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Aquí puedes traducir tus textos de español a braille usando el ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("teclado alfanumérico.");

        javax.swing.GroupLayout jPEspañolLayout = new javax.swing.GroupLayout(jPEspañol);
        jPEspañol.setLayout(jPEspañolLayout);
        jPEspañolLayout.setHorizontalGroup(
            jPEspañolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEspañolLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPEspañolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPEspañolLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPEspañolLayout.setVerticalGroup(
            jPEspañolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPEspañolLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPBraille.setBorder(javax.swing.BorderFactory.createTitledBorder("Braille"));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTBraille.setColumns(20);
        jTBraille.setRows(5);
        jTBraille.setMinimumSize(new java.awt.Dimension(235, 85));
        jTBraille.setPreferredSize(new java.awt.Dimension(235, 85));
        jScrollPane2.setViewportView(jTBraille);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jCBMayusculas.setText("Mayúsculas(Ctrl-)");
        jCBMayusculas.setEnabled(false);
        jCBMayusculas.setOpaque(true);
        jCBMayusculas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBMayusculasItemStateChanged(evt);
            }
        });

        jCBNumeros.setText("Números (Ctrl+)");
        jCBNumeros.setEnabled(false);
        jCBNumeros.setOpaque(true);
        jCBNumeros.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBNumerosItemStateChanged(evt);
            }
        });

        jLabel3.setText("Atajos");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setText("Cuadratín");
        jLabel4.setToolTipText("");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        braillePanel.setPreferredSize(new java.awt.Dimension(54, 88));
        braillePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                braillePanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout braillePanelLayout = new javax.swing.GroupLayout(braillePanel);
        braillePanel.setLayout(braillePanelLayout);
        braillePanelLayout.setHorizontalGroup(
            braillePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );
        braillePanelLayout.setVerticalGroup(
            braillePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 88, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPCuadratinLayout = new javax.swing.GroupLayout(jPCuadratin);
        jPCuadratin.setLayout(jPCuadratinLayout);
        jPCuadratinLayout.setHorizontalGroup(
            jPCuadratinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCuadratinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(braillePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPCuadratinLayout.setVerticalGroup(
            jPCuadratinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(braillePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPBrailleLayout = new javax.swing.GroupLayout(jPBraille);
        jPBraille.setLayout(jPBrailleLayout);
        jPBrailleLayout.setHorizontalGroup(
            jPBrailleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBrailleLayout.createSequentialGroup()
                .addGroup(jPBrailleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPBrailleLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPBrailleLayout.createSequentialGroup()
                        .addGroup(jPBrailleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPBrailleLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPBrailleLayout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(jPCuadratin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPBrailleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPBrailleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jCBMayusculas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCBNumeros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPBrailleLayout.setVerticalGroup(
            jPBrailleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPBrailleLayout.createSequentialGroup()
                .addGroup(jPBrailleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPBrailleLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jPCuadratin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPBrailleLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jCBMayusculas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPBrailleLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1)))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jBTraducir.setText("Traducir");
        jBTraducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTraducirActionPerformed(evt);
            }
        });

        jBBorrar.setText("Borrar");
        jBBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBorrarActionPerformed(evt);
            }
        });

        jBSalir.setText("Salir");
        jBSalir.setPreferredSize(new java.awt.Dimension(73, 23));
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jLEspañolEntrada.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLEspañolEntrada.setText("Español");
        jLEspañolEntrada.setPreferredSize(new java.awt.Dimension(62, 22));

        jBIntercambio.setBackground(new java.awt.Color(242, 242, 242));
        jBIntercambio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/to32.png"))); // NOI18N
        jBIntercambio.setBorder(null);
        jBIntercambio.setBorderPainted(false);
        jBIntercambio.setContentAreaFilled(false);
        jBIntercambio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBIntercambio.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/switch32.png"))); // NOI18N
        jBIntercambio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBIntercambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIntercambioActionPerformed(evt);
            }
        });

        jLBrailleEntrada.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLBrailleEntrada.setText("Braille");

        jMFile.setText("Archivo");

        jMExportar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMExportar.setText("Exportar");
        jMExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMExportarActionPerformed(evt);
            }
        });
        jMFile.add(jMExportar);

        jMImprimir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMImprimir.setText("Imprimir");
        jMImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMImprimirActionPerformed(evt);
            }
        });
        jMFile.add(jMImprimir);

        jMenuBar1.add(jMFile);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLEspañolEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBIntercambio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLBrailleEntrada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBTraducir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBBorrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPEspañol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPBraille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jBBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBTraducir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLBrailleEntrada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLEspañolEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBIntercambio)
                    .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPEspañol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPBraille, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configureWindow() {
        // Configuración inicial de la ventana
        this.setLocationRelativeTo(null);
        this.jTBraille.setLineWrap(true);
        this.jTASpanish.setLineWrap(true);
        this.jTBraille.setEditable(false);

        // Manejador de eventos de teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyInput(e.getKeyCode());
            }
        });

        // Panel de celdas Braille
        currentBrailleCell = new BrailleCell();
        braillePanel.add(currentBrailleCell);
    }

    private void handleKeyInput(int keyCode) {
        // Manejo de entrada de teclado para puntos Braille
        int index = findIndexForKeyCode(keyCode);
        if (index != -1) {
            boolean currentState = currentBrailleCell.getPoint(index);
            currentBrailleCell.setPoint(index, !currentState);
            braillePanel.repaint();
        } else if (keyCode == KeyEvent.VK_ENTER) {
            // Traducción al presionar Enter
            translateText();
        }
    }

    private int findIndexForKeyCode(int keyCode) {
        // Busca el índice correspondiente al código de tecla presionado
        for (int i = 0; i < KEY_EVENT_MAPPING.length; i++) {
            if (keyCode == KEY_EVENT_MAPPING[i]) {
                return BRAILLE_INDEX_MAPPING[i];
            }
        }
        return -1;
    }

    private boolean canTranslate() {
        // Verifica si se puede realizar la traducción
        return !(isUpperCaseMode || isNumberMode) || !isBrailleCellEmpty(currentBrailleCell);
    }

    private boolean isBrailleCellEmpty(BrailleCell cell) {
        // Verifica si la celda Braille está vacía
        for (int i = 0; i < 6; i++) {
            if (cell.getPoint(i)) {
                return false;
            }
        }
        return true;
    }

    private void translateCurrentBrailleCell() {
        // Realiza la traducción de la celda Braille actual
        StringBuilder cellText = new StringBuilder();
        StringBuilder targetCellText = new StringBuilder();

        for (int i = 0; i < BRAILLE_INDEX_MAPPING.length; i++) {
            if (currentBrailleCell.getPoint(BRAILLE_INDEX_MAPPING[i])) {
                cellText.append(i + 1);
            }
        }

        if (isUpperCaseMode || isNumberMode) {
            targetCellText.append(getTargetCellText(additionalBrailleCell));
            targetCellText.append(" ");
        }

        String combinedText = targetCellText.toString() + cellText.toString();
        String translatedText = translator.translateToSpanish(combinedText);

        if (translatedText.equals("?")) {
            JOptionPane.showMessageDialog(this,
                    "La traducción para la combinación ingresada no existe en el diccionario.",
                    "Error de Traducción", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.jTASpanish.append(translatedText);
        String brailleUnicode = isUpperCaseMode || isNumberMode
                ? translator.brailleToUnicode(combinedText)
                : translator.brailleToUnicode(cellText.toString());
        this.jTBraille.append(brailleUnicode);
    }

    private String getTargetCellText(BrailleCell cell) {
        // Obtiene el texto de la celda objetivo (mayúsculas o números)
        StringBuilder targetText = new StringBuilder();
        for (int i = 0; i < BRAILLE_INDEX_MAPPING.length; i++) {
            if (cell.getPoint(BRAILLE_INDEX_MAPPING[i])) {
                targetText.append(i + 1);
            }
        }
        return targetText.toString();
    }

    private void updateBraillePanelLayout() {
        // Actualiza el diseño del panel de Braille
        int columns = isUpperCaseMode || isNumberMode ? 2 : 1;
        int panelWidth = isUpperCaseMode || isNumberMode ? 108 : 54;

        braillePanel.setLayout(new GridLayout(1, columns));

        if (columns == 1 && braillePanel.getComponentCount() > 1) {
            braillePanel.remove(0);
            additionalBrailleCell = null;
        } else if (columns == 2 && braillePanel.getComponentCount() == 1) {
            additionalBrailleCell = new BrailleCell();
            braillePanel.add(additionalBrailleCell, 0);
        }

        braillePanel.setPreferredSize(new Dimension(panelWidth, braillePanel.getHeight()));
        braillePanel.revalidate();

        configureAdditionalBrailleCell();
    }

    private void configureAdditionalBrailleCell() {
        // Configura la celda Braille adicional según el modo activo
        if (additionalBrailleCell != null) {
            if (isUpperCaseMode) {
                setAdditionalCellPoints(true, true, false, false);
            } else if (isNumberMode) {
                setAdditionalCellPoints(true, true, true, true);
            }
        }
    }

    private void setAdditionalCellPoints(boolean p1, boolean p2, boolean p3, boolean p4) {
        // Configura los puntos de la celda Braille adicional
        additionalBrailleCell.setPoint(1, p1);
        additionalBrailleCell.setPoint(5, p2);
        additionalBrailleCell.setPoint(4, p3);
        additionalBrailleCell.setPoint(3, p4);
    }

    private void upperCaseSelect() {
        // Activa/desactiva el modo mayúsculas
        isUpperCaseMode = !isUpperCaseMode;
        isNumberMode = false;
        updateBraillePanelLayout();
        requestFocusInWindow();
    }

    private void numberCaseSelect() {
        // Activa/desactiva el modo números
        isNumberMode = !isNumberMode;
        isUpperCaseMode = false;
        updateBraillePanelLayout();
        requestFocusInWindow();
    }

    private void clearTextFields() {
        this.jTASpanish.setText("");
        this.jTBraille.setText("");
    }

    private boolean getTranslationMode() {
        return this.jLEspañolEntrada.getText().equalsIgnoreCase("Español")
                && this.jLBrailleEntrada.getText().equalsIgnoreCase("Braille");
    }

    private void switchTranslationMode() {
        setTranslationMode(getTranslationMode());
        clearTextFields();
    }

    private void setTranslationMode(boolean isSpanishToBraille) {
        if (isSpanishToBraille) {
            this.setTitle("Brailingo - Traductor: Braille -> Español");
            this.jLBrailleEntrada.setText("Español");
            this.jLEspañolEntrada.setText("Braille");
            requestFocusInWindow();
        } else {
            this.setTitle("Brailingo - Traductor: Español -> Braille");
            this.jLBrailleEntrada.setText("Braille");
            this.jLEspañolEntrada.setText("Español");
        }
        this.jTASpanish.setEditable(!isSpanishToBraille);
        this.jCBMayusculas.setEnabled(isSpanishToBraille);
        this.jCBNumeros.setEnabled(isSpanishToBraille);
        this.jPCuadratin.setEnabled(isSpanishToBraille);
    }

    private void translateText() {
        if (getTranslationMode()) {
            String spanishText = this.jTASpanish.getText();
            this.jTBraille.setText(translator.translateToBraille(spanishText));
        } else {
            if (canTranslate()) {
                translateCurrentBrailleCell();
                currentBrailleCell.clearPoints();
                if (additionalBrailleCell != null) {
                    additionalBrailleCell.clearPoints();
                }
                updateBraillePanelLayout();
            } else {
                JOptionPane.showMessageDialog(this,
                        "El modo de mayúscula o número está activado, pero la celda de braille actual está vacía.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void jBBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBorrarActionPerformed
        clearTextFields();
    }//GEN-LAST:event_jBBorrarActionPerformed

    private void jBTraducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTraducirActionPerformed
        translateText();
    }//GEN-LAST:event_jBTraducirActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        clearTextFields();
        System.exit(0);
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jBIntercambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIntercambioActionPerformed
        switchTranslationMode();
    }//GEN-LAST:event_jBIntercambioActionPerformed

    private void jCBMayusculasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBMayusculasItemStateChanged
        if (jCBMayusculas.isSelected()) {
            upperCaseSelect();
            this.jCBNumeros.setSelected(false);
        } else {
            isUpperCaseMode = false;
            updateBraillePanelLayout();
        }
    }//GEN-LAST:event_jCBMayusculasItemStateChanged

    private void jCBNumerosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBNumerosItemStateChanged
        if (jCBNumeros.isSelected()) {
            numberCaseSelect();
            this.jCBMayusculas.setSelected(false);
        } else {
            isNumberMode = false;
            updateBraillePanelLayout();
        }
    }//GEN-LAST:event_jCBNumerosItemStateChanged

    private void braillePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_braillePanelMouseClicked
        requestFocusInWindow();
    }//GEN-LAST:event_braillePanelMouseClicked

    private void jMExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMExportarActionPerformed
        String texto = jTBraille.getText(); // Obtener el texto del JTextArea
        JFExport exportFrame = new JFExport(texto); // Crear una instancia de JFExport
        exportFrame.setVisible(true); // Hacer visible la ventana de exportación
    }//GEN-LAST:event_jMExportarActionPerformed

    private void jMImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMImprimirActionPerformed
        try {
            // TODO add your handling code here:
            printText(translator.generateBrailleMirror(jTBraille.getText()));
        } catch (PrinterException ex) {
            System.out.println("Error: "+ ex.getMessage());
        }
    }//GEN-LAST:event_jMImprimirActionPerformed

    /**
     * method to print text traduction
     * @param content
     * @throws PrinterException 
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
            for (String line : content.split("\n")) {
                y1 += lineHeight;
                g2d.drawString(line, 50, y1);
            }
            return PAGE_EXISTS;
        });
        // Mostrar el cuadro de diálogo de impresión
        if (job.printDialog()) {
            job.print();
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel braillePanel;
    private javax.swing.JButton jBBorrar;
    private javax.swing.JButton jBIntercambio;
    private javax.swing.JButton jBSalir;
    private javax.swing.JButton jBTraducir;
    private javax.swing.JCheckBox jCBMayusculas;
    private javax.swing.JCheckBox jCBNumeros;
    private javax.swing.JLabel jLBrailleEntrada;
    private javax.swing.JLabel jLEspañolEntrada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuItem jMExportar;
    private javax.swing.JMenu jMFile;
    private javax.swing.JMenuItem jMImprimir;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPBraille;
    private javax.swing.JPanel jPCuadratin;
    private javax.swing.JPanel jPEspañol;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTASpanish;
    private javax.swing.JTextArea jTBraille;
    // End of variables declaration//GEN-END:variables
}
