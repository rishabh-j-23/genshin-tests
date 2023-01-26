package com.ryxke.genshin.character.multipliers;

public class DefenceMitigation {

    public static double defenceMitigation(double charLevel, double mobLevel) {
        double defenceMitigation = (charLevel + 100) / (charLevel + mobLevel + 200);
        return defenceMitigation;
    }
    
}
