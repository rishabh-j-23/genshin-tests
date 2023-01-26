package com.ryxke.genshin.artifacts;

public class Artifacts {

    public double vh(double atk, double stacks) {
        return atk * (10 * stacks / 100);
    }

    public double esf(int pieces, double er){
        if(pieces == 2){
            return 0.20;
        } else if (pieces == 4){
            return 0.75*(er - 1);
        }
        return 0;
    }
}
