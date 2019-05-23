package com.example.riccardochiaretti.mastermind;

import android.view.View;

/**
 * Created by riccardochiaretti on 08/02/17.
 */

public interface MasterMindInterface {

    public boolean checkRow(); //Check if the row is finished
    public void clickHole(View view); //Called when you click the hole
    public void confirmRow(View view); //Called when you click the arrow
    public void setColor(View view, int color); //Set selected color's drawable
}
