package com.student.jure.ilulcek;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MyCursorAdapter extends CursorAdapter {

    public MyCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_row, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView text1 = (TextView) view.findViewById(R.id.list_text1);
        TextView text2 = (TextView) view.findViewById(R.id.list_text2);
        TextView text3 = (TextView) view.findViewById(R.id.list_text3);

        text1.setText(cursor.getString(cursor.getColumnIndexOrThrow("drink")));
        text2.setText(cursor.getString(cursor.getColumnIndexOrThrow("qty")));
        text3.setText(cursor.getString(cursor.getColumnIndexOrThrow("time")));

    }
}
