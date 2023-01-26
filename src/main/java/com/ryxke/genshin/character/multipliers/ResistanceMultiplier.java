package com.ryxke.genshin.character.multipliers;

public class ResistanceMultiplier {
    public static double resistanceMultiplier(double resistanceShred, double mobResist) {

        double resist = 0;
        double finalResistReduction = (mobResist - resistanceShred) / 100;

        if (finalResistReduction < 0) {
            resist = 1 - (finalResistReduction) / 2;

        } else if (finalResistReduction >= 0 && finalResistReduction < 0.75) {
            resist = 1 - finalResistReduction;
        } else if (finalResistReduction > 0.75) {
            resist = 1 / (4 * finalResistReduction + 1);
        }
        return resist;
    }
}
