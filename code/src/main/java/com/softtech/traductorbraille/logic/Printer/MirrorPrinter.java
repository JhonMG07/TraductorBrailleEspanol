/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softtech.traductorbraille.logic.Printer;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * MirrorPrinter es la clase que implementa la logica para la impresión de texto
 * braille en su formato espejo.
 *
 * @since 1.0
 * @version 2.0
 * @author SoftTech
 */
public class MirrorPrinter implements Printer {

    /**
     * Imprime el texto dado en el tamaño de fuente especificado.
     *
     * @param text El texto a imprimir.
     * @param fontSize El tamaño de la fuente para imprimir el texto.
     * @throws PrinterException Si ocurre un error durante la impresión.
     */
    @Override
    public void print(String text, int fontSize) throws PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(createPrintable(text, fontSize));
        if (job.printDialog()) {
            job.print();
        }
    }

    /**
     * Crea un objeto Printable para imprimir el contenido.
     *
     * @param content El contenido a imprimir.
     * @param fontSize El tamaño de la fuente para el contenido.
     * @return Un objeto Printable configurado para imprimir el contenido.
     */
    private Printable createPrintable(String content, int fontSize) {
        return (Graphics graphics, PageFormat pageFormat, int pageIndex) -> {
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            List<String> lines = calculateLines(g2d, content, pageFormat, fontSize);
            List<List<String>> pages = calculatePages(lines, g2d, pageFormat);

            if (pageIndex < pages.size()) {
                drawText(g2d, pages.get(pageIndex), pageFormat, fontSize);
                return Printable.PAGE_EXISTS;
            } else {
                return Printable.NO_SUCH_PAGE;
            }
        };
    }

    /**
     * Calcula las líneas de texto ajustadas a la anchura de la página y el
     * tamaño de fuente especificados.
     *
     * @param g2d El contexto gráfico 2D.
     * @param content El contenido a ajustar en líneas.
     * @param pageFormat El formato de la página.
     * @param fontSize El tamaño de la fuente.
     * @return Una lista de líneas de texto ajustadas.
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
     * Calcula las líneas de un párrafo ajustadas a la anchura de la página.
     *
     * @param g2d El contexto gráfico 2D.
     * @param words Las palabras del párrafo a ajustar.
     * @param pageFormat El formato de la página.
     * @return Una lista de líneas de texto ajustadas para el párrafo.
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
     * línea si es necesario. Si la palabra es demasiado larga para caber en la
     * línea, se divide y se procesa en partes.
     *
     * @param g2d El contexto gráfico 2D.
     * @param word La palabra a procesar.
     * @param pageFormat El formato de la página.
     * @param currentLine El StringBuilder que contiene la línea actual.
     * @param lines La lista de líneas procesadas.
     * @param margin El margen aplicado a cada lado de la página.
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
     * Determina si una palabra es demasiado larga para caber en la línea
     * actual, considerando los márgenes.
     *
     * @param g2d El contexto gráfico 2D.
     * @param word La palabra a verificar.
     * @param pageFormat El formato de la página.
     * @param margin El margen aplicado a cada lado de la página.
     * @return true si la palabra es demasiado larga; de lo contrario, false.
     */
    private boolean isWordTooLong(Graphics2D g2d, String word, PageFormat pageFormat, int margin) {
        int wordWidth = g2d.getFontMetrics().stringWidth(word);
        int availableWidth = (int) pageFormat.getImageableWidth() - 2 * margin;
        return wordWidth > availableWidth;
    }

    /**
     * Divide y procesa una palabra que es demasiado larga para caber en la
     * línea actual. La palabra se divide en un punto donde la primera parte
     * cabe en la línea, y el resto se devuelve para procesamiento adicional.
     *
     * @param g2d El contexto gráfico 2D.
     * @param word La palabra a dividir y procesar.
     * @param pageFormat El formato de la página.
     * @param currentLine El StringBuilder que contiene la línea actual.
     * @param lines La lista de líneas procesadas.
     * @param margin El margen aplicado a cada lado de la página.
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
     * @param currentLine El StringBuilder que contiene la línea actual.
     * @param word La palabra a verificar.
     * @param pageFormat El formato de la página.
     * @param margin El margen aplicado a cada lado de la página.
     * @return true si se debe iniciar una nueva línea; de lo contrario, false.
     */
    private boolean shouldStartNewLine(Graphics2D g2d, StringBuilder currentLine, String word, PageFormat pageFormat, int margin) {
        int lineWidth = g2d.getFontMetrics().stringWidth(currentLine + " " + word);
        return currentLine.length() > 0 && lineWidth > pageFormat.getImageableWidth() - 2 * margin;
    }

    /**
     * Agrega una palabra a la línea actual.
     *
     * @param currentLine El StringBuilder que contiene la línea actual.
     * @param word La palabra a agregar.
     */
    private void addWordToLine(StringBuilder currentLine, String word) {
        if (currentLine.length() > 0) {
            currentLine.append(" ");
        }
        currentLine.append(word);
    }

    /**
     * Finaliza la línea actual, agregándola a la lista de líneas procesadas.
     *
     * @param currentLine El StringBuilder que contiene la línea actual.
     * @param lines La lista de líneas procesadas.
     */
    private void finalizeLine(StringBuilder currentLine, List<String> lines) {
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }
    }

    /**
     * Encuentra el índice en el que una palabra debe dividirse para que la
     * primera parte quepa en la línea actual.
     *
     * @param g2d El contexto gráfico 2D.
     * @param word La palabra a dividir.
     * @param availableWidth El ancho disponible para la palabra en la línea.
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
     * Dibuja el texto ajustado en la página especificada.
     *
     * @param g2d El contexto gráfico 2D.
     * @param lines Las líneas de texto a dibujar.
     * @param pageFormat El formato de la página.
     * @param fontSize El tamaño de la fuente.
     */
    @Override
    public void drawText(Graphics2D g2d, List<String> lines, PageFormat pageFormat, int fontSize) {
        g2d.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        int lineHeight = g2d.getFontMetrics().getHeight();
        int y = calculateStartingY(lines, lineHeight);
        int margin = 50;
        for (String line : lines) {
            int x = calculateXForLine(g2d, line, pageFormat, margin);
            g2d.drawString(line, x, y);
            y -= lineHeight;
        }
    }

    /**
     * Calcula la posición inicial en Y para comenzar a dibujar el texto, basado
     * en el número de líneas y la altura de línea.
     *
     * @param lines Las líneas de texto a dibujar.
     * @param lineHeight La altura de una línea de texto.
     * @return La posición en Y para comenzar a dibujar el texto.
     */
    private int calculateStartingY(List<String> lines, int lineHeight) {
        return lineHeight * lines.size();
    }

    /**
     * Calcula la posición en X para dibujar una línea de texto, ajustando el
     * margen derecho.
     *
     * @param g2d El contexto gráfico 2D.
     * @param line La línea de texto a dibujar.
     * @param pageFormat El formato de la página.
     * @param margin El margen derecho a ajustar.
     * @return La posición en X para comenzar a dibujar la línea de texto.
     */
    private int calculateXForLine(Graphics2D g2d, String line, PageFormat pageFormat, int margin) {
        int lineWidth = g2d.getFontMetrics().stringWidth(line);
        return (int) (pageFormat.getImageableWidth() - lineWidth - margin);
    }

    /**
     * Calcula las páginas de texto basadas en el contenido, el contexto
     * gráfico, el formato de página y el tamaño de fuente.
     *
     * @param lines Las líneas de texto a organizar en páginas.
     * @param g2d El contexto gráfico 2D.
     * @param fontSize El tamaño de la fuente.
     * @return Una lista de páginas, cada una es una lista de líneas de texto.
     */
    private List<List<String>> calculatePages(List<String> lines, Graphics2D g2d, PageFormat pageFormat) {
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
