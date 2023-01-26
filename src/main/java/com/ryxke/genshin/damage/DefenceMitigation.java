package com.ryxke.genshin.damage;

public class DefenceMitigation {
    public double defenceMitigation(double charLevel, double mobLevel) {
        double defenceMitigation = (charLevel + 100) / (charLevel + mobLevel + 200);
        return defenceMitigation;
    }
}
