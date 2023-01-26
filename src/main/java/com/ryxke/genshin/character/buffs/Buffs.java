package com.ryxke.genshin.character.buffs;

import com.ryxke.genshin.character.Raiden;
/*
VV
Bennett
sara
kazuha
raiden E
*/
public class Buffs {


  public static double bennettQ(int qLevel, double baseAtk, boolean c1) {
    double atkBonusBennett = 0;
    double[] buff = {
        56, 60.2, 64.4, 70, 74.2, 78.4, 84, 89.6, 95.2, 100.8, 106.4, 112, 119
    };
    if (c1 == true) {
      atkBonusBennett = baseAtk * (buff[qLevel - 1] + 20);
    } else {
      atkBonusBennett = baseAtk * buff[qLevel - 1];
    }
    return atkBonusBennett/100;
  }

  public static double saraE(int skillLevel, double baseAtk) {
    double[] atkBonus = {
        42.96, 46.18, 49.4, 53.7, 56.92, 60.14, 64.44, 68.74, 73.03, 77.33, 81.62, 85.92, 91.29
    };
    return atkBonus[skillLevel - 1] * baseAtk;

  }


  public static double vv(boolean fullSet) {
    double resist = 0;
    if (fullSet == true) {
      resist = 40;
    } else {
      resist = 0;
    }
    return resist;
  }

  public static double kazuhaA4(double em) {
    return em * 0.04;
  }

  public static double raidenEBurstDmgBonus(int skillLevel, double burstCost) {
    Raiden raiden = new Raiden();
    return raiden.skillDmgBonus(skillLevel, burstCost);
  }

}
