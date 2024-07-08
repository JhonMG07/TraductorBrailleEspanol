package com.softtech.traductorbraille.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author SoftTech
 */
public abstract class Language {
    
    static Dictionary dictionary = Dictionary.getInstance();
    
    public abstract String translateFrom(String text);
    
    /**
     * Crear un lista de: números, saltos de linea, tabulaciones, espaciado
     * @param brailleText
     * @return lista de coincidencias.
     */
    String[] extractBrailleText(String brailleText) {
        List<String> brailleTextList = new ArrayList<>();
        String regex = "\\d+|\\n|\\t|\\s{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(brailleText);

        while (matcher.find()) {
            brailleTextList.add(matcher.group());
        }

        return brailleTextList.toArray(new String[0]);
    }
}
