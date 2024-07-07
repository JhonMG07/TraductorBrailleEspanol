package com.softtech.traductorbraille.logic;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 *
 * Servicio de edicion del texto dependiendo del metodo de traduccion 
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
    
    /**
     * Establece el modo de traducción.
     *
     * @param isSpanishToBraille true para Español a Braille, false para Braille a Español.
     */
    public void setTranslationMode(boolean isSpanishToBraille) {
        this.isSpanishToBraille = isSpanishToBraille;
    }

    /**
     * Obtiene el modo de traducción actual.
     *
     * @return true si el modo es Español a Braille, false si es Braille a Español.
     */
    public boolean isSpanishToBraille() {
        return isSpanishToBraille;
    }

    /**
     * Establece si el texto de entrada debe estar en negrita.
     *
     * @param negritaEntrada true para aplicar negrita, false para no aplicar.
     */
    public void setNegritaEntrada(boolean negritaEntrada) {
        this.negritaEntrada = negritaEntrada;
    }

    /**
     * Establece si el texto de entrada debe estar en cursiva.
     *
     * @param cursivaEntrada true para aplicar cursiva, false para no aplicar.
     */
    public void setCursivaEntrada(boolean cursivaEntrada) {
        this.cursivaEntrada = cursivaEntrada;
    }

    /**
     * Establece si el texto de salida debe estar en negrita.
     *
     * @param negritaSalida true para aplicar negrita, false para no aplicar.
     */
    public void setNegritaSalida(boolean negritaSalida) {
        this.negritaSalida = negritaSalida;
    }

    /**
     * Establece si el texto de salida debe estar en cursiva.
     *
     * @param cursivaSalida true para aplicar cursiva, false para no aplicar.
     */
    public void setCursivaSalida(boolean cursivaSalida) {
        this.cursivaSalida = cursivaSalida;
    }

    /**
     * Establece el color de texto seleccionado.
     *
     * @param selectedColor el color de texto a aplicar.
     */
    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    /**
     * Obtiene el color de texto seleccionado.
     *
     * @return el color de texto actual.
     */
    public Color getSelectedColor() {
        return selectedColor;
    }

    /**
     * Establece el tamaño de fuente.
     *
     * @param fontSize el tamaño de fuente a aplicar.
     */
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * Obtiene el tamaño de fuente actual.
     *
     * @return el tamaño de fuente actual.
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * Aplica el formato condicional a los componentes JTextArea según las preferencias establecidas.
     *
     * @param jTALenEntrada el JTextArea de entrada.
     * @param jTLenSalida el JTextArea de salida.
     */
    public void applyConditionalFormatting(JTextArea jTALenEntrada, JTextArea jTLenSalida) {
        resetFormatting(jTALenEntrada);
        resetFormatting(jTLenSalida);

        JTextArea targetEntrada = isSpanishToBraille ? jTALenEntrada : jTLenSalida;
        JTextArea targetSalida = isSpanishToBraille ? jTLenSalida : jTALenEntrada;

        applyFormatting(targetSalida, isSpanishToBraille ? negritaSalida : negritaEntrada, 
                        isSpanishToBraille ? cursivaSalida : cursivaEntrada);

        applyColorAndSize(jTALenEntrada);
        applyColorAndSize(jTLenSalida);
    }

     /**
     * Aplica negrita y/o cursiva a un componente JTextArea.
     *
     * @param textArea el JTextArea al que se aplicará el formato.
     * @param negrita true para aplicar negrita, false para no aplicar.
     * @param cursiva true para aplicar cursiva, false para no aplicar.
     */
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

    /**
     * Aplica el color y tamaño de fuente a un componente JTextArea.
     *
     * @param textArea el JTextArea al que se aplicará el color y tamaño de fuente.
     */
    private void applyColorAndSize(JTextArea textArea) {
        textArea.setForeground(selectedColor);
        textArea.setFont(new Font(textArea.getFont().getName(), textArea.getFont().getStyle(), fontSize));
    }

    /**
     * Restablece el formato de un componente JTextArea a los valores por defecto.
     *
     * @param textArea el JTextArea al que se le restablecerá el formato.
     */
    private void resetFormatting(JTextArea textArea) {
        textArea.setFont(new Font(textArea.getFont().getName(), Font.PLAIN, fontSize));
        textArea.setForeground(Color.BLACK);
    }
}