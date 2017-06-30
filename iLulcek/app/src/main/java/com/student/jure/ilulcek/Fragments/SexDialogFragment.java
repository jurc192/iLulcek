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

import com.student.jure.ilulcek.MyDatabaseHelper;
import com.student.jure.ilulcek.R;

public class SexDialogFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceBundle) {

        // TODO: polepšaj to, da ne bo podvajanja kode
        Context contextTheme = new ContextThemeWrapper(getActivity(), R.style.FragmentTheme);
        AlertDialog.Builder builder = new AlertDialog.Builder(contextTheme);

        Cursor cursor = getSettingsData();

        builder.setTitle("Spol");
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

        String[] options = {"M", "Ž"};
        builder.setSingleChoiceItems(options, 0, null);


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

}
