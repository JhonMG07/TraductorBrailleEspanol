package com.softtech.traductorbraille.executable;

import com.softtech.traductorbraille.GUI.JFTranslator;

/**
 * La clase principal que inicia la aplicación.
 * 
 * <p>
 * Esta clase contiene el método {@code main} que inicia la interfaz gráfica del traductor Braille.
 * Este es un nuevo comentario para el JavaDoc
 * Este el comentario #2 para el JavaDoc
 * </p>
 * 
 * @author Isma2
 */
public class MainClass {

    /**
     * El método principal que sirve como punto de entrada a la aplicación.
     * 
     * @param args los argumentos de la línea de comandos. Actualmente no se utilizan.
     */
    public static void main(String[] args) {
        JFTranslator translator = new JFTranslator();
        translator.setVisible(true);
    }
}
