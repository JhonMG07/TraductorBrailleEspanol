package com.softtech.traductorbraille.translator;

/**
 *
 * @author SoftTech
 */
public class TranslatorManager {
    private Language language;

    public TranslatorManager() {
        this.language = new Spanish();
    }
    
    public void setLanguage(Language language){
        this.language = language;
    }
    
    public String translate(String text){
        return language.translateFrom(text);
    }
    
}
