package com.example.riccardochiaretti.mastermind;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class Lose_Activity extends AppCompatActivity implements View.OnTouchListener{
    MastermindDAO dao;
    int attempts;
    String difficulty;
    int arr[];
    EditText et;
    Bundle resultBundle;
    //Array of color's drawable
    final int[] pawnsArray = {R.drawable.pegslot_azzurro, R.drawable.pegslot_verde, R.drawable.pegslot_rossa, R.drawable.pegslot_bianco,
            R.drawable.pegslot_giallo, R.drawable.pegslot_viola, R.drawable.pegslot_nero, R.drawable.pegslot_orange, R.drawable.violetto, R.drawable.pdblu};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lose);
        Button btnReTry = (Button) findViewById(R.id.btnTry);
        Button btnMainMenu = (Button) findViewById(R.id.btnBack);
        btnReTry.setOnTouchListener(this);
        btnMainMenu.setOnTouchListener(this);
        //Get data from the intent
        Intent resultIntent = getIntent();
        resultBundle = resultIntent.getExtras();
        attempts = resultBundle.getInt("Attempts");
        difficulty = resultBundle.getString("Difficulty");
        arr=resultBundle.getIntArray("Combo");
        et = (EditText) findViewById(R.id.editText2);
        //Setting the images equals to the winner combination
        switch(difficulty) {
            //If the difficult is easy or normal set only four imageview
            case "Easy":
                ((ImageView) findViewById(R.id.imageView)).setImageDrawable(getResources().getDrawable(pawnsArray[arr[0]], null));
                ((ImageView) findViewById(R.id.imageView2)).setImageDrawable(getResources().getDrawable(pawnsArray[arr[1]], null));
                ((ImageView) findViewById(R.id.imageView3)).setImageDrawable(getResources().getDrawable(pawnsArray[arr[2]], null));
                ((ImageView) findViewById(R.id.imageView4)).setImageDrawable(getResources().getDrawable(pawnsArray[arr[3]], null));
                break;
            case "Normal":
                ((ImageView) findViewById(R.id.imageView)).setImageDrawable(getResources().getDrawable(pawnsArray[arr[0]], null));
                ((ImageView) findViewById(R.id.imageView2)).setImageDrawable(getResources().getDrawable(pawnsArray[arr[1]], null));
                ((ImageView) findViewById(R.id.imageView3)).setImageDrawable(getResources().getDrawable(pawnsArray[arr[2]], null));
                ((ImageView) findViewById(R.id.imageView4)).setImageDrawable(getResources().getDrawable(pawnsArray[arr[3]], null));
                break;
            //If the difficult is hard set five imageview
            case "Hard":
                ((ImageView) findViewById(R.id.imageView)).setImageDrawable(getResources().getDrawable(pawnsArray[arr[0]], null));
                ((ImageView) findViewById(R.id.imageView2)).setImageDrawable(getResources().getDrawable(pawnsArray[arr[1]], null));
                ((ImageView) findViewById(R.id.imageView3)).setImageDrawable(getResources().getDrawable(pawnsArray[arr[2]], null));
                ((ImageView) findViewById(R.id.imageView4)).setImageDrawable(getResources().getDrawable(pawnsArray[arr[3]], null));
                ((ImageView) findViewById(R.id.imageView5)).setVisibility(View.VISIBLE); //Set visible the last imageview
                ((ImageView) findViewById(R.id.imageView5)).setImageDrawable(getResources().getDrawable(pawnsArray[arr[4]], null));
                break;


        }
        //pippo(arr);


    }

    public void pippo(int[] arr)
    {
        System.out.print(arr[0]+arr[1]+arr[2]+arr[3]);
        //System.out.print("pippo");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                Button view = (Button) v;
                view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                v.invalidate();
                if (view.getId() == R.id.btnTry) {
                    switch (difficulty){
                        case "Easy":
                            Intent intent1 = new Intent(Lose_Activity.this, MasterMindEasy.class);
                            startActivity(intent1);
                            break;
                        case "Normal":
                            Intent intent2 = new Intent(Lose_Activity.this, MasterMindNormal.class);
                            startActivity(intent2);
                            break;
                        case "Hard":
                            Intent intent3 = new Intent(Lose_Activity.this, MasterMindHard.class);
                            startActivity(intent3);
                            break;
                    }

                }

                if (view.getId() == R.id.btnBack) {
                    dao = new MastermindDAO(getApplicationContext());
                    dao.open();
                    if(et.getText().toString().equals("")){
                        Toast.makeText(getBaseContext(), getString(R.string.toast),
                                Toast.LENGTH_LONG).show();
                        break;
                    }else {
                        dao.insert(et.getText().toString(), resultBundle.getString("Difficulty"), resultBundle.getInt("Attempts"), "Lose");
                        Intent intent4 = new Intent(Lose_Activity.this, MainMenu.class);
                        startActivity(intent4);
                        break;
                    }
                }

            }
            case MotionEvent.ACTION_UP:
                // Your action here on button click
            case MotionEvent.ACTION_CANCEL: {
                Button view = (Button) v;
                view.getBackground().clearColorFilter();
                view.invalidate();
                break;
            }
        }
        return true;
    }
}
