package com.ryxke.genshin.substats;

import com.ryxke.genshin.character.Character;

public class SubStatsAllotter {
    double atk;
    double def;
    double hp;
    double em;
    double er;
    double cd;
    double cr;
    double eleDmgBonus;
    double atkPercent;
    double hpPercent;
    double defPercent;

    Character ch;

    public SubStatsAllotter(Character c) {
        this.ch = c;

    }

    void baseStatRolls(int atkRoll, int cdRoll, int crRoll, int erRoll, int hpRoll, int defRoll, int emRoll) {
        double maxEm = 23, maxCr = 3.9, maxCd = 7.8, maxEr = 6.5, maxAtk = 19, maxDef = 23, maxHp = 299;

        for (int i = 0; i < atkRoll; i++) {
            this.atk += maxAtk;
        }
        for (int i = 0; i < cdRoll; i++) {
            this.cd += maxCd;
        }
        for (int i = 0; i < crRoll; i++) {
            this.cr += maxCr;
        }
        for (int i = 0; i < erRoll; i++) {
            this.er += maxEr;
        }
        for (int i = 0; i < hpRoll; i++) {
            this.hp += maxHp;
        }
        for (int i = 0; i < defRoll; i++) {
            this.def += maxDef;
        }
        for (int i = 0; i < emRoll; i++) {
            this.atk += maxEm;
        }
    }

    void percentStatRolls(int atkPercentRoll, int hpPercentRoll, int defPercentRoll){
        double maxAtkPercent = 5.8, maxDefPercent = 7.3, maxHpPercent = 5.8;

        for (int i = 0; i < atkPercentRoll; i++) {
            this.atkPercent += maxAtkPercent;
        }
        for (int i = 0; i < defPercentRoll; i++) {
            this.defPercent += maxDefPercent;
        }
        for (int i = 0; i < hpPercentRoll; i++) {
            this.hpPercent += maxHpPercent;
        }
    }
}
