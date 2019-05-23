package com.example.riccardochiaretti.mastermind;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;



public class ScoresAdapter extends CursorAdapter {

    public ScoresAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.list_layout, viewGroup, false);  //Associated list_layout to the adapter
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        //Set the textview from the DB data
        TextView tvDate = (TextView) view.findViewById(R.id.textView16);
        TextView tvName = (TextView) view.findViewById(R.id.textView15);
        TextView tvDifficulty = (TextView) view.findViewById(R.id.textView5);
        TextView tvAttempts = (TextView) view.findViewById(R.id.textView8);
        TextView tvResult = (TextView) view.findViewById(R.id.textView9);
        tvDate.setText(cursor.getString(cursor.getColumnIndexOrThrow("d")));
        tvName.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
        tvDifficulty.setText(cursor.getString(cursor.getColumnIndexOrThrow("difficulty")));
        tvAttempts.setText(cursor.getString(cursor.getColumnIndexOrThrow("attempts")));
        tvResult.setText(cursor.getString(cursor.getColumnIndexOrThrow("result")));
    }

}
