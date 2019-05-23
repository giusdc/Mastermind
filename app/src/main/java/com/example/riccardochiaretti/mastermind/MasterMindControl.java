package com.example.riccardochiaretti.mastermind;



public class MasterMindControl {

    private MasterMindInterface mi;
    private int[] userArray;
    private int[] cpuArray;
    private int pioliBianchi=0, pioliNeri=0;
    private boolean[] colour;
    private boolean[] bianco;

    public MasterMindControl(int[] userArray, int[] cpuArray, int pawns){
        this.userArray = userArray;
        this.cpuArray = cpuArray;
        this.colour = new boolean[pawns];
        this.bianco = new boolean[pawns];
    }

    //Check position and color
    public int controlCombination(){

        for (int i=0; i<cpuArray.length; i++){
            if(cpuArray[i] == userArray[i]){
                this.pioliNeri++;
                colour[i] = bianco[i] = false;
            } else{
                colour[i] = bianco[i] = true;
                }

            }
        return pioliNeri;
        }

    //Check color and wrong position
    public int controlColor(){
        for (int i=0; i<userArray.length; i++){
            if(colour[i]){
                e:for(int j=0; j<userArray.length; j++){
                    if(bianco[j]){ //
                        if(userArray[i] == cpuArray[j]){
                            pioliBianchi++;
                            bianco[j]= false;
                            break e;
                        }
                    }
                }
            }
        }

        return pioliBianchi;
    }

    public void resetNumPioli(){
        this.pioliBianchi=this.pioliNeri=0;
    }
}
