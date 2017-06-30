package com.student.jure.ilulcek.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.widget.NumberPicker;

import com.student.jure.ilulcek.R;

public class WeightDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceBundle) {

        Context contextTheme = new ContextThemeWrapper(getActivity(), R.style.FragmentTheme);
        AlertDialog.Builder builder = new AlertDialog.Builder(contextTheme);


        builder.setTitle("Teža");
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
        numPicker.setMaxValue(300);
        numPicker.setValue(85);
        builder.setView(numPicker);

        return builder.create();
    }
}
