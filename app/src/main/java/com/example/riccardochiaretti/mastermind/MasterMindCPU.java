package com.example.riccardochiaretti.mastermind;

/**
 * Created by riccardochiaretti on 08/02/17.
 */

public class MasterMindCPU {
    private int NUMBER_PAWNS;
    private int HOLES_NUMBER;
    /*Winning CPU-generated combination*/
    private int[] CPUcombination;

    public MasterMindCPU(int NUMBER_PAWNS, int HOLES_NUMBER) {
        this.NUMBER_PAWNS = NUMBER_PAWNS;
        this.HOLES_NUMBER = HOLES_NUMBER;
        CPUcombination = new int[this.HOLES_NUMBER];

        for (int i = 0; i < CPUcombination.length; i++) {
            this.CPUcombination[i] = (int) ((this.NUMBER_PAWNS) * Math.random());  //Each element of combination is a random number between 0 and the number of colors
            System.out.println(CPUcombination[i]);
        }
    }

    public int[] getCombination(){
        return this.CPUcombination;
    }
}
