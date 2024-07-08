/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softtech.traductorbraille.logic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SoftTech
 */
public class Braille extends Language {

    /**
     * convertir un texto en español a braille
     *
     * @param spanishText
     * @return cadena en braille
     */
    @Override
    public String translateFrom(String spanishText) {
        StringBuilder brailleText = new StringBuilder();
        boolean isNumber = false;
        char[] spanishTextArray = spanishText.toCharArray();

        for (char ch : spanishTextArray) {
            String brailleValue = dictionary.getBrailleValue(ch);

            if (isWhitespace(ch)) {
                brailleText.append(brailleValue);
            } else if (!brailleValue.isEmpty()) {
                List<Character> brailleChars = brailleCodeToQuadratin(brailleValue);
                isNumber = handleNumberSequence(ch, isNumber, brailleChars);
                addCharacterToText(brailleChars, brailleText);
            }
        }

        return brailleText.toString().trim();
    }

    /**
     * verifica si el caracter es espacio en blanco
     *
     * @param ch
     * @return boolean
     */
    private boolean isWhitespace(char ch) {
        return ch == ' ' || ch == '\n' || ch == '\t';
    }

    /**
     * transformar codigo braille a cuadratin
     *
     * @param dots
     * @return cuadratin
     */
    private List<Character> brailleCodeToQuadratin(String dots) {
        List<Character> brailleChars = new ArrayList<>();
        String[] segments = dots.split(" ");
        for (String segment : segments) {
            if (!segment.isEmpty()) {
                brailleChars.add(brailleCharFromSegment(segment));
            }
        }
        return brailleChars;
    }

    /**
     * Convertir cadena de puntos en braille
     *
     * @param dots puntos activos del cuadratin
     * @return caracter braille en Unicode
     */
    private char brailleCharFromSegment(String dots) {
        int baseUnicode = 0x2800;
        int brailleValue = 0;
        for (char dot : dots.toCharArray()) {
            switch (dot) {
                case '1' ->
                    brailleValue |= 0x01;
                case '2' ->
                    brailleValue |= 0x02;
                case '3' ->
                    brailleValue |= 0x04;
                case '4' ->
                    brailleValue |= 0x08;
                case '5' ->
                    brailleValue |= 0x10;
                case '6' ->
                    brailleValue |= 0x20;
                default ->
                    brailleValue = 0;
            }
        }
        return (char) (baseUnicode + brailleValue);
    }

    /**
     * identificar y ajustar caracteres numericos braille para español
     *
     * @param ch
     * @param isNumber
     * @param brailleChars
     * @return boolean
     */
    private boolean handleNumberSequence(char ch, boolean isNumber, List<Character> brailleChars) {
        if (Character.isDigit(ch) || ch == '.' || ch == ',') {
            if (isNumber && Character.isDigit(ch)) {
                brailleChars.remove(0);
            } else if (Character.isDigit(ch)) {
                isNumber = true;
            }
        } else {
            isNumber = false;
        }
        return isNumber;
    }

    /**
     * añadir caracter braille a la lista de caracteres braille
     *
     * @param characters lista de caracteres braille
     * @param text cadena en braille
     */
    private void addCharacterToText(List<Character> characters, StringBuilder text) {
        for (char element : characters) {
            text.append(element);
        }
    }

    /**
     * transformar codigo braille a cuadratin
     *
     * @param brailleCode
     * @return cuadratin
     */
    public String brailleToQuadratin(String brailleCode) {
        StringBuilder quadratsString = new StringBuilder();
        String[] words = extractBrailleText(brailleCode);
        for (String word : words) {
            String quadrats = dictionary.getSpanishValue(word);
            if (!isWhitespace(word)) {
                quadrats = brailleCharFromSegment(word) + "";
            }

            quadratsString.append(quadrats);
        }

        return quadratsString.toString();
    }

    /**
     * Verifica si el caracter es espacio en blanco
     *
     * @param spacing
     * @return boolean
     */
    private boolean isWhitespace(String spacing) {
        return spacing.equals("  ") || spacing.equals("\n") || spacing.equals("\t");
    }
}
