package com.softtech.traductorbraille.logic;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 *
 * Servicio de cambio de formato para el texto Traducido y a Traducir
 * 
 * @since 1.0
 * @version 2.0
 * @author SoftTech
 */

public class TextFormatter {
    private boolean isSpanishToBraille;
    private boolean negritaEntrada;
    private boolean cursivaEntrada;
    private boolean negritaSalida;
    private boolean cursivaSalida;
    private Color selectedColor;
    private int fontSize;

    public TextFormatter() {
        this.isSpanishToBraille = true;
        this.negritaEntrada = false;
        this.cursivaEntrada = false;
        this.negritaSalida = false;
        this.cursivaSalida = false;
        this.selectedColor = Color.BLACK;
        this.fontSize = 12;
    }

    public void setTranslationMode(boolean isSpanishToBraille) {
        this.isSpanishToBraille = isSpanishToBraille;
    }

    public boolean isSpanishToBraille() {
        return isSpanishToBraille;
    }

    public void setNegritaEntrada(boolean negritaEntrada) {
        this.negritaEntrada = negritaEntrada;
    }

    public void setCursivaEntrada(boolean cursivaEntrada) {
        this.cursivaEntrada = cursivaEntrada;
    }

    public void setNegritaSalida(boolean negritaSalida) {
        this.negritaSalida = negritaSalida;
    }

    public void setCursivaSalida(boolean cursivaSalida) {
        this.cursivaSalida = cursivaSalida;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void applyConditionalFormatting(JTextArea jTALenEntrada, JTextArea jTLenSalida) {
        JTextArea targetEntrada = isSpanishToBraille ? jTALenEntrada : jTLenSalida;
        JTextArea targetSalida = isSpanishToBraille ? jTLenSalida : jTALenEntrada;

        // Formatear jTALenEntrada o jTLenSalida según el modo de traducción
        applyFormatting(targetSalida, isSpanishToBraille ? negritaSalida : negritaEntrada, 
                        isSpanishToBraille ? cursivaSalida : cursivaEntrada);

        // Aplicar cambios de color y tamaño a ambos JTextArea
        applyColorAndSize(jTALenEntrada);
        applyColorAndSize(jTLenSalida);
    }

    private void applyFormatting(JTextArea textArea, boolean negrita, boolean cursiva) {
        int style = Font.PLAIN;
        if (negrita) {
            style |= Font.BOLD;
        }
        if (cursiva) {
            style |= Font.ITALIC;
        }
        textArea.setFont(new Font(textArea.getFont().getName(), style, fontSize));
    }

    private void applyColorAndSize(JTextArea textArea) {
        textArea.setForeground(selectedColor);
        textArea.setFont(new Font(textArea.getFont().getName(), textArea.getFont().getStyle(), fontSize));
    }
}