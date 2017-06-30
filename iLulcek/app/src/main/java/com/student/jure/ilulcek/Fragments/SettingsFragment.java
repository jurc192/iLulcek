package com.student.jure.ilulcek.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.student.jure.ilulcek.Adapters.SettingsAdapter;
import com.student.jure.ilulcek.MyDatabaseHelper;
import com.student.jure.ilulcek.R;


public class SettingsFragment extends Fragment {

    //Fix duplicating getActivity() for context
    //TODO: create BUNDLE and send it via get/setArguments() instead constructor
    public SettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // A context wrapper that allows you to modify or replace the theme of the wrapped context.
        Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.FragmentTheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        return localInflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView lv = (ListView) getView().findViewById(R.id.listview2);

        Cursor myCursor = getSettingsData();
        myCursor.moveToFirst();

        SettingsAdapter myAdapter = new SettingsAdapter(myCursor, getActivity());
        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();

                switch (position) {

                    // Sex dialog box
                    case 0:
                        SexDialogFragment sexDialog = new SexDialogFragment();
                        sexDialog.show(ft, "sexFragment");
                        break;

                    // Age dialog box
                    case 1:
                        AgeDialogFragment ageDialog = new AgeDialogFragment();
                        ageDialog.show(ft, "ageFragment");
                        break;

                    // Weight dialog box
                    case 2:
                        WeightDialogFragment weightDialog = new WeightDialogFragment();
                        weightDialog.show(ft, "weightFragment");
                        break;

                }
            }

        });


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
