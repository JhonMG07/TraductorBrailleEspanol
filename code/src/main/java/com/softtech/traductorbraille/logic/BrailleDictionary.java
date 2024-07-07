/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softtech.traductorbraille.logic;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * BrailleDictionary es una clase para generar la forma espejo del texto traducido.
 *
 * @since 1.0
 * @version 2.0
 * @author SoftTech
 */
public class BrailleDictionary {
    private static final Map<Character, Character> brailleMirrorMap = new HashMap<>();
    static{
                brailleMirrorMap.put('⠁', '⠈'); 
        brailleMirrorMap.put('⠃', '⠘');  
        brailleMirrorMap.put('⠉', '⠉'); 
        brailleMirrorMap.put('⠙', '⠋');  
        brailleMirrorMap.put('⠑', '⠊'); 
        brailleMirrorMap.put('⠋', '⠙');  
        brailleMirrorMap.put('⠦', '⠴');  
        brailleMirrorMap.put('⠊', '⠑'); 
        brailleMirrorMap.put('⠚', '⠓');
        brailleMirrorMap.put('⠓', '⠚');  
        brailleMirrorMap.put('⠅', '⠨');       
        brailleMirrorMap.put('⠇','⠸');              
        brailleMirrorMap.put('⠍', '⠩'); 
        brailleMirrorMap.put('⠝', '⠫');  
        brailleMirrorMap.put('⠕', '⠪');  
        brailleMirrorMap.put('⠏', '⠹'); 
        brailleMirrorMap.put('⠟','⠻');
        brailleMirrorMap.put('⠗', '⠺'); 
        brailleMirrorMap.put('⠎', '⠱'); 
        brailleMirrorMap.put('⠞','⠳');
        brailleMirrorMap.put('⠥', '⠬'); 
        brailleMirrorMap.put('⠧', '⠼'); 
        brailleMirrorMap.put('⠺', '⠗'); 
        brailleMirrorMap.put('⠭', '⠭'); 
        brailleMirrorMap.put('⠽','⠯');  
        brailleMirrorMap.put('⠵', '⠮'); 
        brailleMirrorMap.put('⠷','⠾');
        brailleMirrorMap.put('⠾','⠷');
        brailleMirrorMap.put('⠬', '⠥');
        brailleMirrorMap.put('⠮','⠵');
        brailleMirrorMap.put('⠌','⠡');     
        brailleMirrorMap.put('⠼','⠧');
        brailleMirrorMap.put('⠂', '⠐');
        brailleMirrorMap.put('⠦', '⠴'); 
        brailleMirrorMap.put('⠖', '⠲'); 
        brailleMirrorMap.put('⠲', '⠖'); 
        brailleMirrorMap.put('⠆', '⠰');        
        brailleMirrorMap.put('⠜','⠣'); 
        brailleMirrorMap.put('⠣','⠜'); 
        brailleMirrorMap.put('⠄','⠠'); 
        brailleMirrorMap.put('⠢','⠔');  
    }
    
        /**
     * transformar texto braille en su forma expejo
     * @param brailleText
     * @return braille en espejo
     */
    public String generateBrailleMirror(String brailleText) {
        String reversedText = new StringBuilder(brailleText).reverse().toString();
        StringBuilder mirroredText = new StringBuilder();
        for (char ch : reversedText.toCharArray()) {
            mirroredText.append(brailleMirrorMap.getOrDefault(ch, ch));
        }


        return mirroredText.toString();
    }
}
