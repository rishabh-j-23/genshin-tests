package com.ryxke.genshin;

import com.ryxke.genshin.character.Dehya;
import com.ryxke.genshin.character.buffs.Buffs;

public class Main {
    public static void main(String[] args) {

        Dehya dehya = new Dehya(2000 + Buffs.bennettQ(10, 700, true), 50, 150, 46.6 + Buffs.kazuhaA4(900));
        dehya.setCharLevel(90);
        dehya.setMobLevel(90);
        dehya.setELevel(10);
        dehya.setQLevel(10);
        dehya.setResistances(0 + Buffs.vv(true), 10);

        dehya.printEDmg();
        dehya.printQDmg();
        
    }
}