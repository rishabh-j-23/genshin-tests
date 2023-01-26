package com.ryxke.genshin.character;

public class Xiao extends Character{
    double HP;
    double baseAtk;
    double atkPercent;
    double flatAtk;
    double atk;
    double cd;
    double dmgBonus;
    double resistanceShred;
    double mobResist;

    // Constructors
    public Xiao() {
    }

    // Setters
    public void applyAtkPercent(double percent) {
        this.atkPercent = percent;
    }

    public void setDmgBonus(double dmgBonus) {
        this.dmgBonus = dmgBonus;
    }

    public void setCritDmg(double cd) {
        this.cd = cd;
    }

    public void setBaseAtk(double baseAtk) {
        this.baseAtk = baseAtk;
    }

    public void setFlatAtk(double flatAtk) {
        this.flatAtk = flatAtk;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public void setResistances(double resistanceShred, double mobResist) {
        this.resistanceShred = resistanceShred;
        this.mobResist = mobResist;
    }

    // Weapon PAssives
    public void applyHoma(boolean passive) {
        this.HP = this.HP * 0.2;
        this.atk += this.HP * 0.8 / 100;

        if (passive == true) {
            this.atk += this.HP * 0.8 / 100 + this.HP * 1 / 100;
        } else if (passive == false) {
        }
    }

    // Dmg Calcs
    public double atk() {
        return this.atk = this.baseAtk * (1 + this.atkPercent / 100) + this.flatAtk;
    }

    public double dmgMultiplier(double atk, double cd, double eleDmgBonus) {
        double dmgMultiplier;

        dmgMultiplier = atk * (1 + cd / 100) * (1 + eleDmgBonus / 100) * defenceMitigation(90, 90)
                * resistanceMultiplier(resistanceShred, mobResist);
        return dmgMultiplier;
    }

    // RETURNS SKILL DMG
    public double skillDmg(double a4Stacks) {
        double skillDmg, a4Bonus;

        a4Bonus = 15 * a4Stacks;

        double skillMultiplier = 455;
        skillDmg = dmgMultiplier(atk, cd, (dmgBonus + a4Bonus)) * skillMultiplier / 100;

        return skillDmg;
    }

    // RETURNS PLUNGE DMG IN Q
    public double ultDmg(double a2Stacks) {
        double ultDmg;

        double a2Bonus = 5 * a2Stacks;

        double highPlungeMultiplier = 404;
        double finalDmgBonus = (dmgBonus + 95.2 + a2Bonus);
        ultDmg = dmgMultiplier(atk, cd, finalDmgBonus) * highPlungeMultiplier / 100;

        return ultDmg;
    }

    // RETURNS RESISTANCE MULTIPLIER
    double resist;

    public double resistanceMultiplier(double resistanceShred, double mobResist) {

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

    // Defence Mitigation
    public double defenceMitigation(double charLevel, double mobLevel) {
        double defenceMitigation = (charLevel + 100) / (charLevel + mobLevel + 200);
        return defenceMitigation;
    }

}