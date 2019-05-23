package com.example.riccardochiaretti.mastermind;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class HighScoresActivity extends AppCompatActivity {

    MastermindDAO dao;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        getItem();  //Call method for select data
    }

    public void getItem(){
        dao = new MastermindDAO(getApplicationContext());
        Cursor c = dao.getScore();  //The cursor cointain the data of the table
        ScoresAdapter score = new ScoresAdapter(this, c); //Set the textview with the data
        lv = (ListView) findViewById(R.id.lv) ;
        lv.setAdapter(score);
        while (c.moveToNext()) { //Slide all cursor's results
            score.changeCursor(c);
        }
    }
}
