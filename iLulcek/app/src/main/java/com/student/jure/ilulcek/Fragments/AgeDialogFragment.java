package com.student.jure.ilulcek.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.widget.NumberPicker;

import com.student.jure.ilulcek.MyDatabaseHelper;
import com.student.jure.ilulcek.R;


public class AgeDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceBundle) {

        Context contextTheme = new ContextThemeWrapper(getActivity(), R.style.FragmentTheme);
        AlertDialog.Builder builder = new AlertDialog.Builder(contextTheme);


        builder.setTitle("Starost");
        builder.setPositiveButton("POTRDI", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Spremeni parametre v DB!
                // Exit
            }
        });

        builder.setNegativeButton("PREKLIČI", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Exit fragment
            }
        });

        NumberPicker numPicker = new NumberPicker(contextTheme);
        numPicker.setMinValue(1);
        numPicker.setMaxValue(99);
        numPicker.setValue(20);
        builder.setView(numPicker);

        return builder.create();
    }


    /** My database query for SETTINGS entries **/
    public Cursor getSettingsData() {

        Context cont = getActivity();
        MyDatabaseHelper myDBhelper = new MyDatabaseHelper(cont);
        SQLiteDatabase db = myDBhelper.getWritableDatabase();
        Cursor cursor = db.query("nastavitve", null, null, null, null, null, null);

        return cursor;
    }
}
