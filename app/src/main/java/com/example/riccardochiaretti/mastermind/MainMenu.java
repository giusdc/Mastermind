package com.example.riccardochiaretti.mastermind;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity implements View.OnTouchListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNewGame = (Button) findViewById(R.id.btnNewGame);
        Button btnInstr = (Button) findViewById(R.id.btnIstr);
        Button btnHigh = (Button) findViewById(R.id.btnHigh);
        //Set for each button OnTouchListener
        btnNewGame.setOnTouchListener(this);
        btnInstr.setOnTouchListener( this);
        btnHigh.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: { //Simulate user's click
                Button view = (Button) v;
                view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);  //Set background for the button
                v.invalidate();
                //Go to the InstructionActivity
                if (view.getId() == R.id.btnIstr) {
                    Intent intent1 = new Intent(MainMenu.this, InstructionActivity.class);
                    startActivity(intent1);
                    break;
                }
                //Go to the HighScoreActivity
                if (view.getId() == R.id.btnHigh) {
                    Intent intent2 = new Intent(MainMenu.this, HighScoresActivity.class);
                    startActivity(intent2);
                    break;
                }
                //Go to the MainMenuDifficulty
                if (view.getId() == R.id.btnNewGame) {
                    Intent intent3 = new Intent(MainMenu.this, MainMenuDifficulty.class);
                    startActivity(intent3);
                    break;
                }
            }
            case MotionEvent.ACTION_UP:
                // Your action here on button click
            case MotionEvent.ACTION_CANCEL: {
                Button view = (Button) v;
                view.getBackground().clearColorFilter();  //Clear color when push up the button
                view.invalidate();
                break;
            }
        }
        return true;

    }//onTouch
}
