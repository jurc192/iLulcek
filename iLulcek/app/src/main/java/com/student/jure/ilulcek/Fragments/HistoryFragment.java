package com.student.jure.ilulcek.Fragments;


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

import com.student.jure.ilulcek.Adapters.HistoryAdapter;
import com.student.jure.ilulcek.MyDatabaseHelper;
import com.student.jure.ilulcek.R;


public class HistoryFragment extends Fragment {

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // A context wrapper that allows you to modify or replace the theme of the wrapped context.
        // LayoutInflater = > Instantiates a layout XML file into its corresponding View objects.
        // inflate the layout using the cloned inflater, not default inflater

        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.FragmentTheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        return localInflater.inflate(R.layout.fragment_history, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView lv = (ListView) getView().findViewById(R.id.listview);

        // Database query
        Cursor myCursor = getHistoryData();
        myCursor.moveToFirst(); // tega ne rabim, right?

        HistoryAdapter historyAdapter = new HistoryAdapter(getActivity(), myCursor);
        lv.setAdapter(historyAdapter);
    }

    /** My database query for HISTORY entries **/
    public Cursor getHistoryData() {

        MyDatabaseHelper myDBhelper = new MyDatabaseHelper(getActivity());
        SQLiteDatabase db = myDBhelper.getWritableDatabase();
        Cursor cursor = db.query("zgodovina", null, null, null, null, null, null);

        return cursor;
    }
}
