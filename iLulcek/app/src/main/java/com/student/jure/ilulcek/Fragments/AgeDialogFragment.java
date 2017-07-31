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

    private DialogInterface.OnDismissListener onDismissListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceBundle) {

        Context contextTheme = new ContextThemeWrapper(getActivity(), R.style.FragmentTheme);
        AlertDialog.Builder builder = new AlertDialog.Builder(contextTheme);
        Context cont = getActivity();
        MyDatabaseHelper myDBhelper = new MyDatabaseHelper(cont);
        SQLiteDatabase db = myDBhelper.getWritableDatabase();
        Cursor resultSet = db.rawQuery("Select * from NASTAVITVE",null);
        resultSet.moveToFirst();
        final int age = resultSet.getInt(2);
        final NumberPicker numPicker = new NumberPicker(contextTheme);
        numPicker.setMinValue(1);
        numPicker.setMaxValue(99);
        numPicker.setValue(age);
        builder.setView(numPicker);

        builder.setTitle("Starost");
        builder.setPositiveButton("POTRDI", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                int starost = numPicker.getValue();
                Context cont = getActivity();
                MyDatabaseHelper myDBhelper = new MyDatabaseHelper(cont);
                SQLiteDatabase db = myDBhelper.getWritableDatabase();
                myDBhelper.updateAge(db,starost);
            }
        });

        builder.setNegativeButton("PREKLIČI", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Exit fragment
            }
        });



        return builder.create();
    }


    /** My database query for SETTINGS entries **/
    // Copy-paste metoda, popravi!
    public Cursor getSettingsData() {

        Context cont = getActivity();
        MyDatabaseHelper myDBhelper = new MyDatabaseHelper(cont);
        SQLiteDatabase db = myDBhelper.getWritableDatabase();
        Cursor cursor = db.query("nastavitve", null, null, null, null, null, null);

        return cursor;
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener){
        this.onDismissListener = onDismissListener;
    }


    public void onDismiss(DialogInterface dialog){
        super.onDismiss(dialog);
        if (onDismissListener !=null){
            onDismissListener.onDismiss(dialog);
        }
    }
}
