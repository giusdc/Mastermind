package com.example.riccardochiaretti.mastermind;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MasterMindEasy extends AppCompatActivity implements MasterMindInterface, View.OnClickListener{

    //Variables for the colors,number of slots and pawns
    final static int BLUE = 0, GREEN = 1, RED = 2, WHITE = 3, YELLOW = 4, PURPLE = 5, BLACK = 6, ORANGE = 7,
            PAWNS_SLOTS = 28, PAWNS = 4;
    //Array of drawable for the pawns
    final int[] pawnsArray = {R.drawable.pegslot_azzurro, R.drawable.pegslot_verde, R.drawable.pegslot_rossa, R.drawable.pegslot_bianco,
                                R.drawable.pegslot_giallo, R.drawable.pegslot_viola, R.drawable.pegslot_nero, R.drawable.pegslot_orange};

    final static int[] holePosition = new int[PAWNS_SLOTS]; //Array for the holes
    final int[] smallHolePosition = new int[PAWNS_SLOTS]; //Array for the small holes
    final int[] arrowPosition = {R.id.arrow0, R.id.arrow1, R.id.arrow2, R.id.arrow3, R.id.arrow4,
                                    R.id.arrow5, R.id.arrow6}; //Array of drawable for the arrows

    int color; //Select color
    int comboRow[] = {-1, -1, -1, -1}; //User's combination

    AlertDialog a;

    int currentRow = 0;
    Resources res;

    static int clickedhole;

    MasterMindCPU cpu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mastermind_main);
        //Holes' mapping
        holePosition[0] = R.id.pegslot04;
        holePosition[1] = R.id.pegslot03;
        holePosition[2] = R.id.pegslot02;
        holePosition[3] = R.id.pegslot01;
        holePosition[4] = R.id.pegslot14;
        holePosition[5] = R.id.pegslot13;
        holePosition[6] = R.id.pegslot12;
        holePosition[7] = R.id.pegslot11;
        holePosition[8] = R.id.pegslot24;
        holePosition[9] = R.id.pegslot23;
        holePosition[10] = R.id.pegslot22;
        holePosition[11] = R.id.pegslot21;
        holePosition[12] = R.id.pegslot34;
        holePosition[13] = R.id.pegslot33;
        holePosition[14] = R.id.pegslot32;
        holePosition[15] = R.id.pegslot31;
        holePosition[16] = R.id.pegslot44;
        holePosition[17] = R.id.pegslot43;
        holePosition[18] = R.id.pegslot42;
        holePosition[19] = R.id.pegslot41;
        holePosition[20] = R.id.pegslot54;
        holePosition[21] = R.id.pegslot53;
        holePosition[22] = R.id.pegslot52;
        holePosition[23] = R.id.pegslot51;
        holePosition[24] = R.id.pegslot64;
        holePosition[25] = R.id.pegslot63;
        holePosition[26] = R.id.pegslot62;
        holePosition[27] = R.id.pegslot61;

        //Small holes'mapping
        smallHolePosition[0] = R.id.smallp01;
        smallHolePosition[1] = R.id.smallp02;
        smallHolePosition[2] = R.id.smallp03;
        smallHolePosition[3] = R.id.smallp04;
        smallHolePosition[4] = R.id.smallp11;
        smallHolePosition[5] = R.id.smallp12;
        smallHolePosition[6] = R.id.smallp13;
        smallHolePosition[7] = R.id.smallp14;
        smallHolePosition[8] = R.id.smallp21;
        smallHolePosition[9] = R.id.smallp22;
        smallHolePosition[10] = R.id.smallp23;
        smallHolePosition[11] = R.id.smallp24;
        smallHolePosition[12] = R.id.smallp31;
        smallHolePosition[13] = R.id.smallp32;
        smallHolePosition[14] = R.id.smallp33;
        smallHolePosition[15] = R.id.smallp34;
        smallHolePosition[16] = R.id.smallp41;
        smallHolePosition[17] = R.id.smallp42;
        smallHolePosition[18] = R.id.smallp43;
        smallHolePosition[19] = R.id.smallp44;
        smallHolePosition[20] = R.id.smallp51;
        smallHolePosition[21] = R.id.smallp52;
        smallHolePosition[22] = R.id.smallp53;
        smallHolePosition[23] = R.id.smallp54;
        smallHolePosition[24] = R.id.smallp61;
        smallHolePosition[25] = R.id.smallp62;
        smallHolePosition[26] = R.id.smallp63;
        smallHolePosition[27] = R.id.smallp64;

        TextView t=(TextView)findViewById(R.id.textView4);
        t.setText(R.string.txtLevelEasy);
        AlertDialog.Builder builder = new AlertDialog.Builder(MasterMindEasy.this);  //Create buider for the dialog

        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.activity_prova, null, true));

        builder.setTitle(R.string.col);
        a = builder.create(); //Create dialog
        a.setCanceledOnTouchOutside(false); //Set the dialog fixed on the screen
        cpu = new MasterMindCPU(8, PAWNS); //Call method for generating winner combo

        res = getResources();
        for(int i=4; i<holePosition.length; i++) { //Set not clickable all rows except the first row
            findViewById(holePosition[i]).setClickable(false);
        }
        for (int j=0; j<arrowPosition.length; j++){ //Set not clickable all arrows
            findViewById(arrowPosition[j]).setClickable(false);
        }
    }




    @Override
    //Check if the row contains four colours
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
        ((ImageView) hole).setImageDrawable(res.getDrawable(pawnsArray[color], null)); //Set the drawable of the selected color in the hole
        for(int i=0; i<holePosition.length; i++){
            if (holePosition[i] == hole.getId()){
                comboRow[i%4] = color; //Set the selected color in the user's combo
                break;
            }
        }
    }

    //Called when the arrow is clicked
    @Override
    public void confirmRow(View view) {
        MasterMindControl mc = new MasterMindControl(comboRow, cpu.getCombination(), PAWNS);//Istance of Mastemind control
        int blacksmallpawns = mc.controlCombination(); //controlCombination returns the numbers of black small pawns
        if(blacksmallpawns == PAWNS){ //If the number of black small pawns is equals to the number of pawn the user's combo is equals to the winner combo
            for(int i=currentRow*4; i<(currentRow*4)+4; i++){ //Current row is disabled
                findViewById(holePosition[i]).setClickable(false);
            }
            findViewById(arrowPosition[currentRow]).setClickable(false); //Current arrow is disabled
            Intent myIntent = new Intent(MasterMindEasy.this, Victory_Activity.class);
            Bundle myBundle = new Bundle();
            myBundle.putString("Difficulty", "Easy");
            myBundle.putInt("Attempts",currentRow+1);  
            myIntent.putExtras(myBundle);
            startActivity(myIntent); //Start Victory_Activity

        }else{
            if(currentRow == 6){ //If currentrow is 6 the rows are finished(YOU LOSE)
                for(int i=currentRow*4; i<(currentRow*4)+4; i++){ //Current row is disabled
                    findViewById(holePosition[i]).setClickable(false);
                }
                findViewById(arrowPosition[currentRow]).setClickable(false);
                Intent myIntent = new Intent(MasterMindEasy.this, Lose_Activity.class);
                Bundle myBundle = new Bundle();
                //Send data for DB
                myBundle.putString("Difficulty", "Easy");
                myBundle.putInt("Attempts",currentRow+1);
                //Insert correct combination
                myBundle.putIntArray("Combo",cpu.getCombination());
                myIntent.putExtras(myBundle);
                startActivity(myIntent); //Start LoseActivity

            }else{
                int smallwhitepawns = mc.controlColor(); //Set white small pawns
                for(int i=currentRow*4; i<(currentRow*4)+4; i++) {
                    findViewById(holePosition[i]).setClickable(false); //Current row is disabled
                    findViewById(holePosition[i + 4]).setClickable(true); //Next row is enabled
                }
                //Setting black small pawns
                for(int j=0; j<blacksmallpawns; j++){
                    ((ImageView) findViewById(smallHolePosition[(currentRow*4)+j])).setImageDrawable(res.getDrawable(R.drawable.smallpegnero, null));
                }
                //Setting white small pawns
                for(int x=0; x<smallwhitepawns; x++){
                    ((ImageView) findViewById(smallHolePosition[(currentRow*4)+x+blacksmallpawns])).setImageDrawable(res.getDrawable(R.drawable.smallpegbianco, null));

                }
                findViewById(arrowPosition[currentRow]).setClickable(false);  //Current arrow is disabled
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
        }
        a.dismiss(); //Close dialog
        if(color != -1){
            setColor(findViewById(clickedhole), color);
        }
        if(checkRow()){ //If the row is finished
            ImageView v = (ImageView) findViewById(arrowPosition[currentRow]); //Find image view's id that is equals to currentRow's arrow..
            v.setImageDrawable(res.getDrawable(R.drawable.freccia_verde, null)); //Set green arrow
            v.setClickable(true);// Set the arrow clickable
        }
    }
}
