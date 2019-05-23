package com.example.riccardochiaretti.mastermind;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Date;

public class MastermindDAO {

    DataBaseHelper dbh;
    SQLiteDatabase database;
    Context context;


    public MastermindDAO(Context context) {
        this.context = context;
    }

    public void open() {
        dbh = new DataBaseHelper(context);
        database = dbh.getWritableDatabase();  //Set the DB writable
    }

    public void insert(String Name, String Diffulty, int Attempts, String Result){
        open();
        Date d = new Date(System.currentTimeMillis());  //Get the current date
        ContentValues values = new ContentValues();     //Get the values from DB
        values.put(DataBaseHelper.COLUMN_NAME, Name);
        values.put(DataBaseHelper.COLUMN_DATE, d.toString());
        values.put(DataBaseHelper.COLUMN_DIFFICULTY, Diffulty);
        values.put(DataBaseHelper.COLUMN_ATTEMPTS, Attempts);
        values.put(DataBaseHelper.COLUMN_RESULT, Result);
        try
        {
            database.insert(DataBaseHelper.TABLE_NAME, null, values);  //Insert data in the DB
        }
        catch(Exception e)
        {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
        database.close();
    }

    public void close() {
        database.close();
    }

    public Cursor getScore() {
        open();
        //Select data from the DB order by date
        Cursor cursor = database.query(
                DataBaseHelper.TABLE_NAME,
                DataBaseHelper.MATCH_FIELD,
                null, null,
                null, null, "d");

        if (cursor != null) {
            cursor.moveToFirst();  //Set the cursor at the first position
        }
        return cursor;
    }
}


