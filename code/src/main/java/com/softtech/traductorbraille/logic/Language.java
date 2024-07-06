package com.softtech.traductorbraille.logic;

/**
 *
 * @author SoftTech
 */
public abstract class Language {
    
    static Dictionary dictionary = Dictionary.getInstance();
    
    public abstract String translateTo(String language);
}
