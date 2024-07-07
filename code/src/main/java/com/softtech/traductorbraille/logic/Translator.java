package com.softtech.traductorbraille.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Translator aloja la lógica de traducción y diccionario de equivalencias entre el abecedario español y las claves braille.
 * 
 * @since 1.0
 * @version 1.0
 * @author SoftTech
 */
public class Translator {

    private static final Map<String, String> brailleMap = new HashMap<>();
    private static final Map<String, String> reverseBrailleMap = new HashMap<>();

    static {
        brailleMap.put("\t", "\t");
        brailleMap.put("  ", " ");
        brailleMap.put("\n", "\n");
        brailleMap.put("1", "a");
        brailleMap.put("12", "b");
        brailleMap.put("14", "c");
        brailleMap.put("145", "d");
        brailleMap.put("15", "e");
        brailleMap.put("124", "f");
        brailleMap.put("1245", "g");
        brailleMap.put("125", "h");
        brailleMap.put("24", "i");
        brailleMap.put("245", "j");
        brailleMap.put("13", "k");
        brailleMap.put("123", "l");
        brailleMap.put("134", "m");
        brailleMap.put("1345", "n");
        brailleMap.put("135", "o");
        brailleMap.put("1234", "p");
        brailleMap.put("12345", "q");
        brailleMap.put("1235", "r");
        brailleMap.put("234", "s");
        brailleMap.put("2345", "t");
        brailleMap.put("136", "u");
        brailleMap.put("1236", "v");
        brailleMap.put("2456", "w");
        brailleMap.put("1346", "x");
        brailleMap.put("13456", "y");
        brailleMap.put("1356", "z");
        brailleMap.put("12456", "ñ");
        brailleMap.put("12356", "á");
        brailleMap.put("2346", "é");
        brailleMap.put("34", "í");
        brailleMap.put("346", "ó");
        brailleMap.put("23456", "ú");
        brailleMap.put("1256", "ü");
        brailleMap.put("3", ".");
        brailleMap.put("2", ",");
        brailleMap.put("23", ";");
        brailleMap.put("25", ":");
        brailleMap.put("36", "_");
        brailleMap.put("63", "-");
        brailleMap.put("632", "\"");
        brailleMap.put("532", "!");
        brailleMap.put("325", "¡");
        brailleMap.put("26", "?");
        brailleMap.put("62", "¿");
        brailleMap.put("126", "(");
        brailleMap.put("345", ")");
        brailleMap.put("235", "+");
        brailleMap.put("236", "*");
        brailleMap.put("2356", "=");
        brailleMap.put("256", "/");
        brailleMap.put("3456", "#");
        brailleMap.put("3456 1", "1");
        brailleMap.put("3456 12", "2");
        brailleMap.put("3456 14", "3");
        brailleMap.put("3456 145", "4");
        brailleMap.put("3456 15", "5");
        brailleMap.put("3456 124", "6");
        brailleMap.put("3456 1245", "7");
        brailleMap.put("3456 125", "8");
        brailleMap.put("3456 24", "9");
        brailleMap.put("3456 245", "0");
        
           
        createUppercaseAlphabet();
        createReverseMap();
    }
    
    /**
     * Crea un mapa de braille a español
     */
    private static void createUppercaseAlphabet() {
        Map<String, String> auxiliaryMap = new HashMap<>();
        for (Map.Entry<String, String> entry : brailleMap.entrySet()) {
            if (Character.isLetter(entry.getValue().charAt(0))) {
                String uppercaseValue = entry.getValue().toUpperCase();
                auxiliaryMap.put("46 " + entry.getKey(), uppercaseValue);
            }
        }
        brailleMap.putAll(auxiliaryMap);
    }
    /**
     * Crear un mapa de español a braille
     */
    private static void createReverseMap() {
        for (Map.Entry<String, String> entry : brailleMap.entrySet()) {
            if (!entry.getKey().isEmpty()) {
                reverseBrailleMap.put(entry.getValue(), entry.getKey());
            }
        }
    }
    
    /**
     * Convertir cadena de puntos en braille
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
     * Traduce una cadena de texto en Braille a español.
     * <p>
     * Este método convierte cada carácter Braille en su equivalente en español, manejando modos de números y mayúsculas.
     * <p>
     * Los pasos específicos incluyen:
     * <ul>
     *     <li>Detectar el modo de número utilizando la secuencia Braille "3456".</li>
     *     <li>Convertir los caracteres Braille alfabéticos y numéricos de acuerdo a su contexto (modo de número, modo de mayúsculas).</li>
     *     <li>Detectar el modo de mayúsculas utilizando la secuencia Braille "46".</li>
     * </ul>
     *
     * @param brailleText Cadena de texto en Braille a traducir.
     * @return Cadena traducida en español.
     */
    public String translateToSpanish(String brailleText) {
        StringBuilder translatedText = new StringBuilder();
        boolean isNumberMode = false;
        boolean isCapital = false;

        String[] brailleTextArray = extractBrailleText(brailleText);

        for (int i = 0; i < brailleTextArray.length; i++) {
            String translatedChar;
            String auxiliary = brailleTextArray[i];
            if (brailleTextArray[i].equals("3456")) {
                isNumberMode = true;
                continue;
            }
            if (isNumberMode) {
                auxiliary = "3456 " + brailleTextArray[i];
                if (isAlphabeticCharacter(auxiliary) && !isPeriodOrComma(brailleTextArray[i])) {
                    auxiliary = brailleTextArray[i];
                    isNumberMode = false;
                }
                if (isPeriodOrComma(brailleTextArray[i])) {
                    auxiliary = brailleTextArray[i];
                }
            }

            if (brailleTextArray[i].equals("46")) {
                isCapital = true;
                continue;
            }
            
            if(isCapital){
                auxiliary = "46 " + brailleTextArray[i];
                isCapital = false;
            }

            translatedChar = brailleMap.getOrDefault(auxiliary, "?");

            translatedText.append(translatedChar);
        }

        return translatedText.toString();
    }

    /**
     * Traduce una cadena de texto en Braille a números en español.
     * <p>
     * Este método convierte cada carácter Braille a su equivalente numérico en español, manejando el modo de número.
     * <p>
     * Los pasos específicos incluyen:
     * <ul>
     *     <li>Detectar el modo de número utilizando la secuencia Braille "3456".</li>
     *     <li>Convertir los caracteres Braille a números y puntos/comas en su contexto de modo de número.</li>
     *     <li>Ignorar cualquier carácter que no sea un número, punto o coma durante la traducción.</li>
     * </ul>
     *
     * @param brailleText Cadena de texto en Braille a traducir.
     * @return Cadena traducida a números en español.
     */
    public String translateToSpanishNumbersOnly(String brailleText) {
        StringBuilder translatedText = new StringBuilder();
        boolean isNumberMode = false;

        String[] brailleTextArray = extractBrailleText(brailleText);

        for (int i = 0; i < brailleTextArray.length; i++) {
            String translatedChar;
            String auxiliary = brailleTextArray[i];
            if (brailleTextArray[i].equals("3456")) {
                isNumberMode = true;
                continue;
            }
            if (isNumberMode) {
                auxiliary = "3456 " + brailleTextArray[i];
                if (!isPeriodOrComma(brailleTextArray[i])) {
                    isNumberMode = false;
                }
            }

            translatedChar = brailleMap.getOrDefault(auxiliary, "?");

            // Asegurarse de que solo se traduzcan números
            if (!Character.isDigit(translatedChar.charAt(0)) && !isPeriodOrComma(brailleTextArray[i])) {
                translatedChar = "?";  // Ignorar letras
            }

            translatedText.append(translatedChar);
        }

        return translatedText.toString();
    }
    
    /**
     * Crear un lista de: números, saltos de linea, tabulaciones, espaciado
     * @param brailleText
     * @return lista de coincidencias.
     */
    private String[] extractBrailleText(String brailleText) {
        List<String> brailleTextList = new ArrayList<>();
        String regex = "\\d+|\\n|\\t|\\s{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(brailleText);

        while (matcher.find()) {
            brailleTextList.add(matcher.group());
        }

        return brailleTextList.toArray(new String[0]);
    }

    /**
     * Verifica si el caracter braille es alfabetico
     * @param code
     * @return boolean 
     */
    private boolean isAlphabeticCharacter(String code) {
        return brailleMap.getOrDefault(code, "?").equals("?");
    }
    
    /**
     * transformar codigo braille a cuadratin
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
     * convertir un texto en español a braille
     * @param spanishText
     * @return cadena en braille
     */
    public String translateToBraille(String spanishText) {
        StringBuilder brailleText = new StringBuilder();
        boolean isNumber = false;
        char[] spanishTextArray = spanishText.toCharArray();

        for (char ch : spanishTextArray) {
            String brailleValue = getBrailleValue(ch);

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
     * obtener el caracter en braille
     * @param ch
     * @return braille caracter
     */
    private String getBrailleValue(char ch) {
        return reverseBrailleMap.getOrDefault(String.valueOf(ch), "");
    }
    
    /**
     * verifica si el caracter es espacio en blanco
     * @param ch
     * @return boolean
     */
    private boolean isWhitespace(char ch) {
        return ch == ' ' || ch == '\n' || ch == '\t';
    }
    /**
     * identificar y ajustar caracteres numericos braille para español
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
     * @param characters lista de caracteres braille
     * @param text cadena en braille
     */
    private void addCharacterToText(List<Character> characters, StringBuilder text) {
        for (char element : characters) {
            text.append(element);
        }
    }
    
    
    /**
     * convertir texto braille a cuadratin Unicode
     * @param brailleText
     * @return cuadratin Unicode
     */
    public String brailleToUnicode(String brailleText) {
        StringBuilder quadratsString = new StringBuilder();
        String[] words = extractBrailleText(brailleText);
        for (String word : words) {
            String quadrats = brailleMap.getOrDefault(word, "?");
            if (!isWhitespace(word)) {
                quadrats = brailleCharFromSegment(word) + "";
            }

            quadratsString.append(quadrats);
        }

        return quadratsString.toString();
    }
    

    /**
     * Verifica si el caracter braille es punto o coma.
     * @param brailleText
     * @return boolena
     */
    private boolean isPeriodOrComma(String brailleText) {
        return brailleText.equals("3") || brailleText.equals("2");
    }
    /**
     * Verifica si el caracter es espacio en blanco
     * @param spacing
     * @return boolean
     */
    private boolean isWhitespace(String spacing) {
        return spacing.equals("  ") || spacing.equals("\n") || spacing.equals("\t");
    }

}
