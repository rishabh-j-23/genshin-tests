package com.ryxke.genshin.character;

import java.util.HashMap;

public class Raiden extends Character {

    double resistanceShred = 0;
    double mobResist = 10;

    double atk;
    double cd = 50;
    double er = 100;
    double dmgBonus = 0;
    double teamEnergy;

    int qLevel = 1;
    int skillLevel = 1;

    final double burstCost = 90;

    // CONSTRUCTORS AND OTHER VALUES
    public Raiden() {
    }

    public Raiden(double atk, double cd, double er, double dmgBonus) {

        this.atk = atk;
        this.cd = cd;
        this.er = er;
        this.dmgBonus = dmgBonus;
    }

    public void setTeamEnergy(double energy) {
        this.teamEnergy = energy;
    }

    public void setResistances(double resistanceShred, double mobResist) {
        this.resistanceShred = resistanceShred;
        this.mobResist = mobResist;
    }

    public void setQLevel(int qLevel) {
        this.qLevel = qLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    // SKILL DMG LOGIC
    double skillDmg;

    public double skillDmg(boolean skillUsedPreviously) {

        if (skillUsedPreviously == true) {
            skillDmg = this.atk
                    * (1 + this.cd / 100)
                    * (1 + (this.dmgBonus + asc4Passive(this.er) + skillDmgBonus(skillLevel, 90)) / 100)
                    * defenceMitigation(90, 90)
                    * resistanceMultiplier(this.resistanceShred, this.mobResist)
                    * skillMultiplier(this.skillLevel) / 100;
        } else if (skillUsedPreviously == false) {
            skillDmg = this.atk
                    * (1 + this.cd)
                    * (1 + (this.dmgBonus + asc4Passive(er) + skillDmgBonus(this.skillLevel, 90)) / 100)
                    * defenceMitigation(90, 90)
                    * resistanceMultiplier(this.resistanceShred, this.mobResist)
                    * skillMultiplier(this.skillLevel) / 100;
        }
        return this.skillDmg;

    }

    public double dmgMultiplier(int qLevel, int skillLevel, double atk, double cd, double dmgBonus, double er) {

        double dmgMulti = (atk + (337 + 510) * resolveAtkBonus(qLevel, resolveStacks(qLevel, teamEnergy)) / 100)
                * (1 + cd / 100)
                * (1 + (dmgBonus + asc4Passive(er) + skillDmgBonus(skillLevel, 90)) / 100)
                * defenceMitigation(90, 90)
                * resistanceMultiplier(resistanceShred, mobResist);

        return dmgMulti;
    }

    double dmg;

    public double initialQSlash(boolean qUsed) {

        if (qUsed == true) {
            dmg = dmgMultiplier(this.qLevel,
                    this.skillLevel,
                    this.atk,
                    this.cd,
                    this.dmgBonus,
                    this.er)
                    * (initialQSlashMultiplier(this.qLevel)
                            + resolveMultiplierBonus(this.qLevel, resolveStacks(this.qLevel, teamEnergy)))
                    / 100;

        } else if (qUsed == false) {
            dmg = 0;
        }
        return dmg;
    }

    double coordinatedAtk;

    public double skillCoordinatedAtk(boolean skillUsedPreviously) {

        if (skillUsedPreviously == true) {
            coordinatedAtk = this.atk
                    * (1 + this.cd / 100)
                    * (1 + (this.dmgBonus + asc4Passive(er) + skillDmgBonus(this.skillLevel, 90)) / 100)
                    * defenceMitigation(90, 90)
                    * resistanceMultiplier(this.resistanceShred, this.mobResist)
                    * coordinatedAtkMultiplier(this.skillLevel) / 100;
        } else if (skillUsedPreviously == false) {
            coordinatedAtk = this.atk
                    * (1 + this.cd)
                    * (1 + (this.dmgBonus + asc4Passive(er) + skillDmgBonus(this.skillLevel, 90)) / 100)
                    * defenceMitigation(90, 90)
                    * resistanceMultiplier(this.resistanceShred, this.mobResist)
                    * coordinatedAtkMultiplier(this.skillLevel) / 100;
        }
        return this.coordinatedAtk;
    }

    public double coordinatedAtkMultiplier(int skillLevel) {
        double[] multiplier = {
                42.0, 45.15, 48.3, 52.5, 55.65, 58.8, 63.0, 67.2, 71.4, 75.6
        };
        return multiplier[skillLevel - 1];
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
        return this.resist;
    }

    // Defence Mitigation
    public double defenceMitigation(double charLevel, double mobLevel) {
        double defenceMitigation = (charLevel + 100) / (charLevel + mobLevel + 200);
        return defenceMitigation;
    }

    public double skillMultiplier(int level) {

        double[] multiplier = {
                117.2, 125.99, 134.78, 146.5, 155.29, 164.08, 175.08, 187.52, 199.24, 210.96
        };
        return multiplier[level - 1];
    }

    public double initialQSlashMultiplier(int qlevel) {

        double[] multiplier = {
                400.8, 430.86, 460.92, 501.0, 531.06, 561.12, 601.2, 641.28, 681.36, 721.44
        };

        return multiplier[qlevel - 1];
    }

    public double resolveMultiplierBonus(int qLevel, double resolveStks) {

        double[] bonus = {
                3.89, 4.18, 4.47, 4.86, 5.15, 5.44, 5.83, 6.22, 6.61, 7.0
        };
        return bonus[qLevel - 1] * resolveStks;
    }

    public double resolveAtkBonus(int qLevel, double resolveStks) {

        double[] bonus = {
                0.73, 0.78, 0.84, 0.91, 0.96, 1.02, 1.09, 1.16, 1.23, 1.31
        };
        return bonus[qLevel - 1] * resolveStks;
    }

    public double asc4Passive(double energyRecharge) {
        return 0.4 * (energyRecharge - 100);
    }

    public double skillDmgBonus(int skillLevel, double burstCost) {
        double[] dmgBonus = {
                0.22, 0.23, 0.24, 0.25, 0.26, 0.27, 0.28, 0.29, 0.3, 0.3
        };

        return dmgBonus[skillLevel - 1] * burstCost;
    }

    public double resolveStacks(int qLevel, double teamEnergy) {

        double[] stackGain = {
                0.15, 0.16, 0.16, 0.17, 0.17, 0.18, 0.18, 0.19, 0.19, 0.20
        };
        return stackGain[qLevel - 1] * teamEnergy;
    }

    // Q Attack String

    public double ca1Multiplier(int qLevel) {
        double[] multiplier = {
                61.6, 65.8, 70.0, 75.6, 79.8, 84.7, 91.0, 97.3, 103.6, 109.9
        };
        return multiplier[qLevel - 1];
    }

    public double ca2Multiplier(int qLevel) {
        HashMap<Integer, Double> ca2 = new HashMap<>();
        ca2.put(1, 74.36);
        ca2.put(2, 79.43);
        ca2.put(3, 84.5);
        ca2.put(4, 91.26);
        ca2.put(5, 96.33);
        ca2.put(6, 102.25);
        ca2.put(7, 109.85);
        ca2.put(8, 117.46);
        ca2.put(9, 125.06);
        ca2.put(10, 132.67);

        return ca2.get(qLevel);

    }

    public double ca1(int qLevel, int skillLevel) {

        return dmgMultiplier(qLevel, skillLevel, this.atk,
                this.cd, this.dmgBonus, this.er)
                * ca1Multiplier(qLevel) / 100;
    }

    public double ca2(int qLevel, int skillLevel) {

        return dmgMultiplier(qLevel, skillLevel, this.atk,
                this.cd, this.dmgBonus, this.er)
                * ca2Multiplier(qLevel) / 100;
    }

    public double totalCADmg(int qLevel, int skillLevel) {
        return ca1(qLevel, skillLevel) + ca2(qLevel, skillLevel);
    }
}
