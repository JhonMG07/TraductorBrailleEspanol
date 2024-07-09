/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softtech.traductorbraille.printer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * NormalPrinter es la clase implementa la lógica para imprimir un texto
 * braille. de en sentido normal.
 *
 * @since 1.0
 * @version 2.0
 * @author SoftTech
 */
public class NormalPrinter implements Printer {

    /**
     * Imprime el texto dado en el formato especificado.
     *
     * @param text El texto a imprimir.
     * @param fontSize El tamaño de la fuente a utilizar para imprimir el texto.
     * @param fontColor
     * @throws PrinterException Si ocurre un error durante la impresión.
     */
    @Override
    public void print(String text, int fontSize, Color fontColor) throws PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(createPrintable(text, fontSize, fontColor));
        if (job.printDialog()) {
            job.print();
        }
    }

    /**
     * Crea un objeto Printable para imprimir el contenido.
     *
     * @param content El contenido a imprimir.
     * @param fontSize El tamaño de la fuente a utilizar.
     * @return Un objeto Printable configurado para imprimir el contenido dado.
     */
    private Printable createPrintable(String content, int fontSize, Color fontColor) {
        return (Graphics graphics, PageFormat pageFormat, int pageIndex) -> {
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            List<String> lines = calculateLines(g2d, content, pageFormat, fontSize);
            List<List<String>> pages = calculatePages(lines, g2d, pageFormat, fontSize);

            if (pageIndex < pages.size()) {
                drawText(g2d, pages.get(pageIndex), pageFormat, fontSize, fontColor);
                return Printable.PAGE_EXISTS;
            } else {
                return Printable.NO_SUCH_PAGE;
            }
        };
    }

    /**
     * Calcula las líneas de texto a partir del contenido dado, teniendo en
     * cuenta el formato de página y el tamaño de la fuente.
     *
     * @param g2d El contexto gráfico 2D.
     * @param content El contenido a dividir en líneas.
     * @param pageFormat El formato de la página.
     * @param fontSize El tamaño de la fuente.
     * @return Una lista de líneas de texto.
     */
    @Override
    public List<String> calculateLines(Graphics2D g2d, String content, PageFormat pageFormat, int fontSize) {
        g2d.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        List<String> lines = new ArrayList<>();
        for (String paragraph : content.split("\n")) {
            String[] words = paragraph.split("\\s+");
            lines.addAll(calculateLinesForParagraph(g2d, words, pageFormat));
        }
        return lines;
    }

    /**
     * Calcula las líneas de texto para un párrafo dado, considerando el formato
     * de página.
     *
     * @param g2d El contexto gráfico 2D.
     * @param words Las palabras del párrafo a procesar.
     * @param pageFormat El formato de la página.
     * @return Una lista de líneas de texto.
     */
    public List<String> calculateLinesForParagraph(Graphics2D g2d, String[] words, PageFormat pageFormat) {
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();
        int margin = 50;

        for (String word : words) {
            processWord(g2d, word, pageFormat, currentLine, lines, margin);
        }
        finalizeLine(currentLine, lines);
        return lines;
    }

    /**
     * Procesa una palabra, agregándola a la línea actual o iniciando una nueva
     * línea si es necesario.
     *
     * @param g2d El contexto gráfico 2D.
     * @param word La palabra a procesar.
     * @param pageFormat El formato de la página.
     * @param currentLine La línea actual de texto.
     * @param lines La lista de líneas de texto.
     * @param margin El margen a considerar en el formato de la página.
     */
    private void processWord(Graphics2D g2d, String word, PageFormat pageFormat, StringBuilder currentLine, List<String> lines, int margin) {
        while (!word.isEmpty()) {
            if (shouldStartNewLine(g2d, currentLine, word, pageFormat, margin)) {
                finalizeLine(currentLine, lines);
                currentLine.setLength(0);
            }

            if (isWordTooLong(g2d, word, pageFormat, margin)) {
                word = splitAndProcessLongWord(g2d, word, pageFormat, currentLine, lines, margin);
            } else {
                addWordToLine(currentLine, word);
                word = "";
            }
        }
    }

    /**
     * Determina si una palabra es demasiado larga para ajustarse en la línea
     * actual.
     *
     * @param g2d El contexto gráfico 2D.
     * @param word La palabra a evaluar.
     * @param pageFormat El formato de la página.
     * @param margin El margen a considerar en el formato de la página.
     * @return true si la palabra no cabe en la línea actual, false en caso
     * contrario.
     */
    private boolean isWordTooLong(Graphics2D g2d, String word, PageFormat pageFormat, int margin) {
        int wordWidth = g2d.getFontMetrics().stringWidth(word);
        int availableWidth = (int) pageFormat.getImageableWidth() - 2 * margin;
        return wordWidth > availableWidth;
    }

    /**
     * Divide y procesa una palabra que es demasiado larga para ajustarse en la
     * línea actual.
     *
     * @param g2d El contexto gráfico 2D.
     * @param word La palabra a dividir.
     * @param pageFormat El formato de la página.
     * @param currentLine La línea actual de texto.
     * @param lines La lista de líneas de texto.
     * @param margin El margen a considerar en el formato de la página.
     * @return La parte restante de la palabra después de la división.
     */
    private String splitAndProcessLongWord(Graphics2D g2d, String word, PageFormat pageFormat, StringBuilder currentLine, List<String> lines, int margin) {
        int splitIndex = findSplitIndex(g2d, word, (int) pageFormat.getImageableWidth() - 2 * margin);
        String part = word.substring(0, splitIndex);
        addWordToLine(currentLine, part);
        return word.substring(splitIndex);
    }

    /**
     * Determina si se debe iniciar una nueva línea basándose en la longitud de
     * la línea actual y la palabra a agregar.
     *
     * @param g2d El contexto gráfico 2D.
     * @param currentLine La línea actual de texto.
     * @param word La palabra a evaluar.
     * @param pageFormat El formato de la página.
     * @param margin El margen a considerar en el formato de la página.
     * @return true si se debe iniciar una nueva línea, false en caso contrario.
     */
    private boolean shouldStartNewLine(Graphics2D g2d, StringBuilder currentLine, String word, PageFormat pageFormat, int margin) {
        int lineWidth = g2d.getFontMetrics().stringWidth(currentLine + " " + word);
        return currentLine.length() > 0 && lineWidth > pageFormat.getImageableWidth() - 2 * margin;
    }

    /**
     * Agrega una palabra a la línea actual de texto.
     *
     * @param currentLine La línea actual de texto.
     * @param word La palabra a agregar.
     */
    private void addWordToLine(StringBuilder currentLine, String word) {
        if (currentLine.length() > 0) {
            currentLine.append(" ");
        }
        currentLine.append(word);
    }

    /**
     * Finaliza la línea actual de texto, agregándola a la lista de líneas.
     *
     * @param currentLine La línea actual de texto.
     * @param lines La lista de líneas de texto.
     */
    private void finalizeLine(StringBuilder currentLine, List<String> lines) {
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }
    }

    /**
     * Encuentra el índice en el que se debe dividir una palabra para que ajuste
     * en la línea actual.
     *
     * @param g2d El contexto gráfico 2D.
     * @param word La palabra a dividir.
     * @param availableWidth El ancho disponible para la palabra.
     * @return El índice en el que dividir la palabra.
     */
    private int findSplitIndex(Graphics2D g2d, String word, int availableWidth) {
        int splitIndex = word.length();
        while (splitIndex > 0 && g2d.getFontMetrics().stringWidth(word.substring(0, splitIndex)) > availableWidth) {
            splitIndex--;
        }
        return Math.max(splitIndex, 1);
    }

    /**
     * Dibuja el texto en la página utilizando el formato y tamaño de fuente
     * especificados.
     *
     * @param g2d El contexto gráfico 2D.
     * @param lines Las líneas de texto a dibujar.
     * @param pageFormat El formato de la página.
     * @param fontSize El tamaño de la fuente.
     * @param fontColor color del texto
     */
    @Override
    public void drawText(Graphics2D g2d, List<String> lines, PageFormat pageFormat, int fontSize, Color fontColor) {
        g2d.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        g2d.setColor(fontColor);
        int lineHeight = g2d.getFontMetrics().getHeight();
        int y = lineHeight;
        int margin = 50;
        for (String line : lines) {
            int lineWidth = g2d.getFontMetrics().stringWidth(line);
            int x = margin;
            g2d.drawString(line, x, y);
            y += lineHeight;
        }
    }

    /**
     * Calcula las páginas basándose en las líneas de texto, el formato de
     * página y el tamaño de la fuente.
     *
     * @param lines Las líneas de texto.
     * @param g2d El contexto gráfico 2D.
     * @param pageFormat El formato de la página.
     * @param fontSize El tamaño de la fuente.
     * @return Una lista de páginas, cada una representada por una lista de
     * líneas de texto.
     */
    private List<List<String>> calculatePages(List<String> lines, Graphics2D g2d, PageFormat pageFormat, int fontSize) {
        List<List<String>> pages = new ArrayList<>();
        int lineHeight = g2d.getFontMetrics().getHeight();
        int linesPerPage = (int) (pageFormat.getImageableHeight() / lineHeight);
        int startLine = 0;
        while (startLine < lines.size()) {
            int endLine = Math.min(startLine + linesPerPage, lines.size());
            pages.add(new ArrayList<>(lines.subList(startLine, endLine)));
            startLine = endLine;
        }
        return pages;
    }

}
