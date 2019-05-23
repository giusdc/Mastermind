package com.example.riccardochiaretti.mastermind;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Victory_Activity extends AppCompatActivity implements View.OnTouchListener{

    String difficulty;
    int attempts;
    String Name;
    EditText tv;
    MastermindDAO dao;
    Bundle resultBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.victory);

        Intent resultIntent = getIntent();
        resultBundle = resultIntent.getExtras();
        tv = (EditText) findViewById(R.id.editText);
        Button b = (Button) findViewById(R.id.button);
        b.setOnTouchListener(this);  //Set for each button OnTouchListener

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                Button view = (Button) v;
                view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP); //Set background for the button
                v.invalidate();

                dao = new MastermindDAO(getApplicationContext());
                dao.open();
                if (view.getId() == R.id.button) {
                    if(tv.getText().toString().equals("")){ //Name not insert
                        Toast.makeText(getBaseContext(), getString(R.string.toast),
                                Toast.LENGTH_LONG).show();
                        break;
                    }else {
                        //Insert data in DB and go to the MainMenu
                        dao.insert(tv.getText().toString(), resultBundle.getString("Difficulty"), resultBundle.getInt("Attempts"), "Win");
                        Intent intent4 = new Intent(Victory_Activity.this, MainMenu.class);
                        startActivity(intent4);
                        break;
                    }
                }

            }
            //case MotionEvent.ACTION_UP:
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
