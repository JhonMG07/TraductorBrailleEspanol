package com.softtech.traductorbraille.translator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author SoftTech
 */
public class Spanish extends Language{

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
    @Override
    public String translateFrom(String brailleText) {
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

            translatedChar = dictionary.getSpanishValue(auxiliary);

            translatedText.append(translatedChar);
        }

        return translatedText.toString();
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
     * Verifica si el caracter braille es alfabetico
     *
     * @param code
     * @return boolean
     */
    private boolean isAlphabeticCharacter(String auxiliary) {
        return !dictionary.isExistInBrailleMap(auxiliary);
    }
}
