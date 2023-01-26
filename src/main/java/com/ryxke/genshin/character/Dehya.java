package com.ryxke.genshin.character;

import com.ryxke.genshin.character.multipliers.DefenceMitigation;
import com.ryxke.genshin.character.multipliers.ResistanceMultiplier;

public class Dehya extends Character {

    double baseAtk = 265;
    double baseDef = 628;
    double baseHp = 15675;
    double ascHp = 28.8;
    double atk;
    double cr;
    double cd;
    double def;
    double hp;
    double em;
    double er;
    double atkPercent;
    double hpPercent;
    double defPercent;

    double eleDmgBonus;
    double resistanceShred, mobResist;
    int mobLevel;
    int charLevel;
    int eLevel, qLevel;

    public Dehya(double atk, double cr, double cd, double dmgBonus){
        this.atk = atk;
        this.cd = cd;
        this.cr = cr;
        this.eleDmgBonus = dmgBonus;
    }

    public void setResistances(double resistanceShred, double mobResist){
        this.resistanceShred = resistanceShred;
        this.mobResist = mobResist;
    }

    public void setCharLevel(int level){
        this.charLevel = level;
    }

    public void setMobLevel(int level){
        this.mobLevel = level;
    }

    public void setELevel(int eLevel){
        this.eLevel = eLevel;
    }

    public void setQLevel(int qLevel){
        this.qLevel = qLevel;
    }
    
    // E Multiplier
    double indomidableFlameMultiplier(int eLevel){
        double[] m = {112.9, 121.3, 129.8, 141.1, 149.6, 158, 169.3, 180.6, 191.9, 203.2};
        return m[eLevel-1];
    }
    
    double rangingFlameMultiplier(int eLevel){
        double[] m = {132.8, 142.8, 152.7, 166, 176, 185.9, 199.2, 212.5, 225.8, 239};
        return m[eLevel - 1]; 
    }

    double fieldMultiplier(int eLevel){
        double[] m = {68.8, 74, 79.1, 86, 91.2, 96.3, 103.2, 110.1, 117, 123.8};
        return m[eLevel - 1];
    }

    double mitigation(int eLevel){
        double[] m = {32, 34, 36, 38, 40, 42, 44, 46, 48, 50};
        return m[eLevel - 1];
    }

    double redmanesBloodMaxHp(){
        return 2;
    }

    // Q Multipliers
    double flameManeFistMultiplier(int qLevel){
        double[] m = {127.2, 136.7, 146.3, 159, 168.5, 178.1, 190.8, 203.5, 216.2, 229};
        return m[qLevel -1];
    }

    double incenerationDriveMultiplier(int qLevel){
        double[] m = {156.8, 168.6, 180.3, 196, 207.8, 219.5, 235.2, 250.9, 266.6, 282.2};
        return m[qLevel - 1];
    }

    double modifier(){
        double res = ResistanceMultiplier.resistanceMultiplier(resistanceShred, mobResist);
        double defModi = DefenceMitigation.defenceMitigation(charLevel, mobLevel);
        double mod = res * defModi * (this.atk*(1+cd/100)*(1+eleDmgBonus/100));

        return mod/100;
    }

    //E dmg
    double indomidableFlameDmg(){
        return modifier()*indomidableFlameMultiplier(this.eLevel);
    }

    double rangingFlameDmg(){
        return modifier()*rangingFlameMultiplier(this.eLevel);
    }

    double fieldDmg(){
        return modifier()*fieldMultiplier(this.eLevel);
    }

    //Q dmg
    double flameManeFistDmg(){
        return modifier()*flameManeFistMultiplier(this.qLevel);
    }

    double incenerationDriveDmg(){
        return modifier()*incenerationDriveMultiplier(this.qLevel);
    }

    public void printEDmg(){
        System.out.println("Indomidable Flame Dmg : " + indomidableFlameDmg());
        System.out.println("Ranging Flame Dmg : " + rangingFlameDmg());
        System.out.println("Field Dmg : " + fieldDmg());
    }

    public void printQDmg(){
        System.out.println("Flamemane Fist Dmg : " + flameManeFistDmg());
        System.out.println("Inceneration Drive Dmg : " + incenerationDriveDmg());
    }
}
