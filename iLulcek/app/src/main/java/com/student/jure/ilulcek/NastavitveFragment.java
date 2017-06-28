package com.student.jure.ilulcek;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class NastavitveFragment extends Fragment {

    //Fix duplicating getActivity() for context
    //TODO: create BUNDLE and send it via get/setArguments() instead constructor
    public NastavitveFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // A context wrapper that allows you to modify or replace the theme of the wrapped context.
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.FragmentTheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        return localInflater.inflate(R.layout.fragment_nastavitve, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView lv = (ListView) getView().findViewById(R.id.listview2);

        // Database query
        Cursor myCursor = getSettingsData();

        //Preveri če kurzor dela in dobi podatke
        System.out.println("ROW COUNT: "+myCursor.getCount());
        myCursor.moveToFirst();
        System.out.println("SEX COL INDEX: "+myCursor.getColumnIndex("sex"));
        System.out.println("AGE COL INDEX: "+myCursor.getColumnIndex("age"));
        System.out.println("WEIGHT COL INDEX: "+myCursor.getColumnIndex("weight"));

        System.out.println("SEX COL VALUE: "+myCursor.getString(1));
        System.out.println("AGE COL VALUE: "+myCursor.getString(2));
        System.out.println("WEIGHT COL VALUE: "+myCursor.getString(3));

        SettingsAdapter myAdapter = new SettingsAdapter(myCursor, getActivity());

        lv.setAdapter(myAdapter);

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
