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

public class WeightDialogFragment extends DialogFragment {

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
        final int weight = resultSet.getInt(3);
        final NumberPicker numPicker = new NumberPicker(contextTheme);
        numPicker.setMinValue(1);
        numPicker.setMaxValue(300);
        numPicker.setValue(weight);
        builder.setView(numPicker);

        builder.setTitle("Teža");
        builder.setPositiveButton("POTRDI", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
               int teza = numPicker.getValue();
                Context cont = getActivity();
                MyDatabaseHelper myDBhelper = new MyDatabaseHelper(cont);
                SQLiteDatabase db = myDBhelper.getWritableDatabase();
                myDBhelper.updateWeight(db,teza);
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
