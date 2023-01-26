package com.ryxke.genshin.character;

public abstract class Character {
    double baseAtk;
    double baseDef;
    double baseHp;
    double baseEm;
    double baseEr;
    double baseCd = 50;
    double baseCr = 5;
    double eleDmgBonus;
    double atkPercent;
    double hpPercent;
    double defPercent;

    public double atk;
    public double def;
    public double hp;
    public double em;
    public double er;
    public double cd;
    public double cr;
}
