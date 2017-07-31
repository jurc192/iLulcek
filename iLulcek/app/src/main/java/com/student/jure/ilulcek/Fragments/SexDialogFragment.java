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
import android.widget.Toast;

import com.student.jure.ilulcek.MyDatabaseHelper;
import com.student.jure.ilulcek.R;

public class SexDialogFragment extends DialogFragment {

    String selection;
    private DialogInterface.OnDismissListener onDismissListener;

    public Dialog onCreateDialog(Bundle savedInstanceBundle) {

        // TODO: polepšaj to, da ne bo podvajanja kode

        Context contextTheme = new ContextThemeWrapper(getActivity(), R.style.FragmentTheme);
        AlertDialog.Builder builder = new AlertDialog.Builder(contextTheme);
        final String[] options = {"Ženska", "Moški"};
        Context cont = getActivity();
        MyDatabaseHelper myDBhelper = new MyDatabaseHelper(cont);
        SQLiteDatabase db = myDBhelper.getWritableDatabase();
        Cursor resultSet = db.rawQuery("Select * from NASTAVITVE",null);
        resultSet.moveToFirst();
        final String spol = resultSet.getString(1);
        int stSpol;
        if (spol.equals("Izberite spol"))
            stSpol=-1;
         else if (spol.equals("Ženska"))
            stSpol=0;
        else
            stSpol=1;

        builder.setTitle("Spol").setSingleChoiceItems(options, stSpol, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                selection = (String) options[which];
            }
        }).setPositiveButton("POTRDI", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Context cont = getActivity();
                MyDatabaseHelper myDBhelper = new MyDatabaseHelper(cont);
                SQLiteDatabase db = myDBhelper.getWritableDatabase();
                 myDBhelper.updateSex(db,selection);


            }
        });

        builder.setNegativeButton("PREKLIČI", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Exit fragment
            }
        });




        // TODO: add database stuff
        return builder.create();
    }

    /** My database query for SETTINGS entries **/
    // TODO: DUPLICATED METHOD!! Popravi to!
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
