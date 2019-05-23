package com.example.riccardochiaretti.mastermind;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainMenuDifficulty extends AppCompatActivity implements View.OnTouchListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_difficulty);
        Button btnEasy = (Button) findViewById(R.id.btnEasy);
        Button btnMed = (Button) findViewById(R.id.btnMedium);
        Button btnHard = (Button) findViewById(R.id.btnHard);
        //Set for each button OnTouchListener
        btnEasy.setOnTouchListener(this);
        btnMed.setOnTouchListener(this);
        btnHard.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                Button view = (Button) v;
                view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);  //Set background for the button
                v.invalidate();
                //Go the MastermindEasy
                if (view.getId() == R.id.btnEasy) {
                    Intent intent1 = new Intent(MainMenuDifficulty.this, MasterMindEasy.class);
                    startActivity(intent1);
                    break;
                }
                //Go the MastermindMedium
                if (view.getId() == R.id.btnMedium) {
                    Intent intent2 = new Intent(MainMenuDifficulty.this, MasterMindNormal.class);
                    startActivity(intent2);
                    break;
                }
                //Go the MastermindHard
                if (view.getId() == R.id.btnHard) {
                    Intent intent3 = new Intent(MainMenuDifficulty.this, MasterMindHard.class);
                    startActivity(intent3);
                    break;
                }

            }
            case MotionEvent.ACTION_UP:
                // Your action here on button click
            case MotionEvent.ACTION_CANCEL: {
                Button view = (Button) v;
                view.getBackground().clearColorFilter(); //Clear color when push up the button
                view.invalidate();
                break;
            }
        }
        return true;

    }
}
