package com.example.riccardochiaretti.mastermind;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class MasterMindHard extends AppCompatActivity implements MasterMindInterface, View.OnClickListener{

    //Variables for the colors,number of slots and pawns
    final static int BLUE = 0, GREEN = 1, RED = 2, WHITE = 3, YELLOW = 4, PURPLE = 5, BLACK = 6, ORANGE = 7, LPURPLE = 8, DBLUE = 9,
            PAWNS_SLOTS = 35, PAWNS = 5;
    //Array of drawable for the pawns
    final int[] pawnsArray = {R.drawable.pegslot_azzurro, R.drawable.pegslot_verde, R.drawable.pegslot_rossa, R.drawable.pegslot_bianco,
            R.drawable.pegslot_giallo, R.drawable.pegslot_viola, R.drawable.pegslot_nero, R.drawable.pegslot_orange, R.drawable.violetto, R.drawable.pdblu};

    final static int[] holePosition = new int[PAWNS_SLOTS]; //Array for the holes
    final int[] smallHolePosition = new int[PAWNS_SLOTS]; //Array for the small holes
    final int[] arrowPosition = {R.id.arrow0, R.id.arrow1, R.id.arrow2, R.id.arrow3, R.id.arrow4,
            R.id.arrow5, R.id.arrow6}; //Array of drawable for the arrows

    int color; ///Select color
    int comboRow[] = {-1, -1, -1, -1, -1}; //User's combination

    AlertDialog a;

    int currentRow = 0;
    Resources res;

    static int clickedhole;

    MasterMindCPU cpu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mastermind_hard);

        //Holes' mapping
        holePosition[0] = R.id.pegslot05;
        holePosition[1] = R.id.pegslot04;
        holePosition[2] = R.id.pegslot03;
        holePosition[3] = R.id.pegslot02;
        holePosition[4] = R.id.pegslot01;
        holePosition[5] = R.id.pegslot15;
        holePosition[6] = R.id.pegslot14;
        holePosition[7] = R.id.pegslot13;
        holePosition[8] = R.id.pegslot12;
        holePosition[9] = R.id.pegslot11;
        holePosition[10] = R.id.pegslot25;
        holePosition[11] = R.id.pegslot24;
        holePosition[12] = R.id.pegslot23;
        holePosition[13] = R.id.pegslot22;
        holePosition[14] = R.id.pegslot21;
        holePosition[15] = R.id.pegslot35;
        holePosition[16] = R.id.pegslot34;
        holePosition[17] = R.id.pegslot33;
        holePosition[18] = R.id.pegslot32;
        holePosition[19] = R.id.pegslot31;
        holePosition[20] = R.id.pegslot45;
        holePosition[21] = R.id.pegslot44;
        holePosition[22] = R.id.pegslot43;
        holePosition[23] = R.id.pegslot42;
        holePosition[24] = R.id.pegslot41;
        holePosition[25] = R.id.pegslot55;
        holePosition[26] = R.id.pegslot54;
        holePosition[27] = R.id.pegslot53;
        holePosition[28] = R.id.pegslot52;
        holePosition[29] = R.id.pegslot51;
        holePosition[30] = R.id.pegslot65;
        holePosition[31] = R.id.pegslot64;
        holePosition[32] = R.id.pegslot63;
        holePosition[33] = R.id.pegslot62;
        holePosition[34] = R.id.pegslot61;

        //Small holes'mapping
        smallHolePosition[0] = R.id.smallp01;
        smallHolePosition[1] = R.id.smallp02;
        smallHolePosition[2] = R.id.smallp05;
        smallHolePosition[3] = R.id.smallp03;
        smallHolePosition[4] = R.id.smallp04;
        smallHolePosition[5] = R.id.smallp11;
        smallHolePosition[6] = R.id.smallp12;
        smallHolePosition[7] = R.id.smallp15;
        smallHolePosition[8] = R.id.smallp13;
        smallHolePosition[9] = R.id.smallp14;
        smallHolePosition[10] = R.id.smallp21;
        smallHolePosition[11] = R.id.smallp22;
        smallHolePosition[12] = R.id.smallp25;
        smallHolePosition[13] = R.id.smallp23;
        smallHolePosition[14] = R.id.smallp24;
        smallHolePosition[15] = R.id.smallp31;
        smallHolePosition[16] = R.id.smallp32;
        smallHolePosition[17] = R.id.smallp35;
        smallHolePosition[18] = R.id.smallp33;
        smallHolePosition[19] = R.id.smallp34;
        smallHolePosition[20] = R.id.smallp41;
        smallHolePosition[21] = R.id.smallp42;
        smallHolePosition[22] = R.id.smallp45;
        smallHolePosition[23] = R.id.smallp43;
        smallHolePosition[24] = R.id.smallp44;
        smallHolePosition[25] = R.id.smallp51;
        smallHolePosition[26] = R.id.smallp52;
        smallHolePosition[27] = R.id.smallp55;
        smallHolePosition[28] = R.id.smallp53;
        smallHolePosition[29] = R.id.smallp54;
        smallHolePosition[30] = R.id.smallp61;
        smallHolePosition[31] = R.id.smallp62;
        smallHolePosition[32] = R.id.smallp65;
        smallHolePosition[33] = R.id.smallp63;
        smallHolePosition[34] = R.id.smallp64;


        AlertDialog.Builder builder = new AlertDialog.Builder(MasterMindHard.this);

        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_normal, null, true));
        builder.setTitle(R.string.col);
        a = builder.create();  //Create dialog
        a.setCanceledOnTouchOutside(false); //Set the dialog fixed on the screen
        cpu = new MasterMindCPU(10, PAWNS); //Call method for generating winner combo
        res = getResources();
        for(int i=5; i<holePosition.length; i++) { //Set not clickable all rows except the first row
            findViewById(holePosition[i]).setClickable(false);
        }
        for (int j=0; j<arrowPosition.length; j++){
            findViewById(arrowPosition[j]).setClickable(false);
        }

    }

    //Check if the row is finished


    @Override
    public boolean checkRow() {
        for(int i=0; i<comboRow.length; i++){
            if(comboRow[i] == -1){
                return false;
            }
        }
        return true;
    }

    @Override
    public void clickHole(View view) {
        a.show(); //Show the dialog when a hole is clicked
        clickedhole = view.getId();
    }

    @Override
    public void setColor(View hole, int color) {
        ((ImageView) hole).setImageDrawable(res.getDrawable(pawnsArray[color], null));  //Set the drawable of the selected color in the hole
        for(int i=0; i<holePosition.length; i++){
            if (holePosition[i] == hole.getId()){
                comboRow[i%5] = color;  //Set the selected color in the user's combo
                break;
            }
        }
    }

    //Called when the arrow is clicked
    @Override
    public void confirmRow(View view) {
        MasterMindControl mc = new MasterMindControl(comboRow, cpu.getCombination(), PAWNS);
        int blacksmallholes = mc.controlCombination(); //controlCombination returns the numbers of black small pawns
        if(blacksmallholes == PAWNS){ //If the number of black small pawns is equals to the number of pawn the user's combo is equals to the winner combo
            for(int i=currentRow*5; i<(currentRow*5)+5; i++){
                findViewById(holePosition[i]).setClickable(false); //Current row is disabled
            }
            //Hai Vinto
            findViewById(arrowPosition[currentRow]).setClickable(false); //Current arrow is disabled

            Intent myIntent = new Intent(MasterMindHard.this, Victory_Activity.class);  //Start Victory_Activity
            Bundle myBundle = new Bundle();

            myBundle.putString("Difficulty", "Hard");
            myBundle.putInt("Attempts",currentRow+1);
            myIntent.putExtras(myBundle);
            startActivity(myIntent);

        }else{
            if(currentRow == 6){ //If currentrow is 6 the rows are finished(YOU LOSE)
                for(int i=currentRow*5; i<(currentRow*5)+5; i++){
                    findViewById(holePosition[i]).setClickable(false); //Current row is disabled
                }
                findViewById(arrowPosition[currentRow]).setClickable(false); //Current arrow is disabled
                //Ha perso

                Intent myIntent = new Intent(MasterMindHard.this, Lose_Activity.class);
                Bundle myBundle = new Bundle();
                //Send datas for DB
                myBundle.putString("Difficulty", "Hard");
                myBundle.putInt("Attempts",currentRow+1);
                //Insert correct combination
                myBundle.putIntArray("Combo",cpu.getCombination());
                myIntent.putExtras(myBundle);
                startActivity(myIntent); //Start LoseActivity

            }else{
                int whitepawns = mc.controlColor(); //Set white small pawns
                for(int i=currentRow*5; i<(currentRow*5)+5; i++) {
                    findViewById(holePosition[i]).setClickable(false); //Current row is disabled
                    findViewById(holePosition[i + 5]).setClickable(true); //Next row is enabled
                }
                //Setting black small pawns
                for(int j=0; j<blacksmallholes; j++){ //
                    ((ImageView) findViewById(smallHolePosition[(currentRow*5)+j])).setImageDrawable(res.getDrawable(R.drawable.smallpegnero, null));
                }
                //Setting white small pawns
                for(int x=0; x<whitepawns; x++){
                    ((ImageView) findViewById(smallHolePosition[(currentRow*5)+x+blacksmallholes])).setImageDrawable(res.getDrawable(R.drawable.smallpegbianco, null));

                }
                findViewById(arrowPosition[currentRow]).setClickable(false); //Current arrow is disabled
                ((ImageView) findViewById(arrowPosition[currentRow+1])).setImageDrawable(res.getDrawable(R.drawable.freccia_selezionata, null)); //Change type of arrow
            }
        }
        currentRow++;
        //Reset combo user
        for(int b=0; b<comboRow.length; b++){
            comboRow[b] = -1;
        }
        mc.resetNumPioli();
    }

    //Called when you click a color
    @Override
    public void onClick(View view) {

        int c = view.getId();
        switch(c){
            //Set the variable color equals to the selected color
            case R.id.blue:
                color = BLUE;
                break;
            case R.id.green:
                color = GREEN;
                break;
            case R.id.red:
                color = RED;
                break;
            case R.id.white:
                color = WHITE;
                break;
            case R.id.yellow:
                color = YELLOW;
                break;
            case R.id.purple:
                color = PURPLE;
                break;
            case R.id.black:
                color = BLACK;
                break;
            case R.id.orange:
                color = ORANGE;
                break;
            case R.id.dblue:
                color = DBLUE;
                break;
            case R.id.lviola:
                color = LPURPLE;
                break;
        }
        a.dismiss();
        if(color != -1){ //Close dialog
            setColor(findViewById(clickedhole), color);
        }
        if(checkRow()){
            ImageView v = (ImageView) findViewById(arrowPosition[currentRow]); //Find image view's id that is equals to currentRow's arrow..
            v.setImageDrawable(res.getDrawable(R.drawable.freccia_verde, null)); //Set green arrow
            v.setClickable(true);// Set the arrow clickable
        }
    }
}
