package com.student.jure.ilulcek;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class ZgodovinaFragment extends Fragment {

    public ZgodovinaFragment() {
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
        return localInflater.inflate(R.layout.fragment_zgodovina, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView lv = (ListView) getView().findViewById(R.id.listview);

        // Database query
        Cursor myCursor = getHistoryData();
//        String[] from = {"drink", "qty", "time"};
//        int[] to = {R.id.list_text1, R.id.list_text2, R.id.list_text3};
//        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.list_row, myCursor, from, to, 0);

        MyCursorAdapter myAdapter = new MyCursorAdapter(getActivity(), myCursor);
        lv.setAdapter(myAdapter);
    }

    /** My database query for HISTORY entries **/
    public Cursor getHistoryData() {

        MyDatabaseHelper myDBhelper = new MyDatabaseHelper(getActivity());
        SQLiteDatabase db = myDBhelper.getWritableDatabase();
        Cursor cursor = db.query("zgodovina", null, null, null, null, null, null);

        return cursor;
    }
}
