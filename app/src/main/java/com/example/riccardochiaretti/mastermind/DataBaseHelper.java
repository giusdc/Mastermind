package com.example.riccardochiaretti.mastermind;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db = null;
    final static String TABLE_NAME = "scores";
    final static String DATABASE_NAME = "mastermind.db";
    final static String[] MATCH_FIELD = {"_id", "name", "d", "difficulty", "attempts", "result"};
    public static final
    String COLUMN_DATE= "d";
    public static final
    String COLUMN_NAME = "name";
    public static final
    String COLUMN_DIFFICULTY = "difficulty";
    public static
    String COLUMN_ATTEMPTS = "attempts";
    public static
    String COLUMN_RESULT ="result";
    private static final
    int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Execute query for creating table
    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CITY_TABLE_SQL = "CREATE TABLE scores (" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " name TEXT NOT NULL, " +
                "d TEXT NOT NULL, " +
                "difficulty TEXT NOT NULL, " +
                "attempts INTEGER NOT NULL, " +
                "result TEXT NOT NULL " +
                ");";
        db.execSQL(CITY_TABLE_SQL);
    }

    //Use only if the DB version change
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {

    }

}
