package com.student.jure.ilulcek.Fragments;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.student.jure.ilulcek.Adapters.SettingsAdapter;
import com.student.jure.ilulcek.AddActivity;
import com.student.jure.ilulcek.R;


public class TodayFragment extends Fragment {


    public TodayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // A context wrapper that allows you to modify or replace the theme of the wrapped context.
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.FragmentTheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        return localInflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // PEE NOW BUTTON STUFF
        Button peeBtn = (Button) getView().findViewById(R.id.peeBtn);
        peeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // LULAJ ZDAJ, reset counterja
            }
        });


        // ADD DRINK BUTTON STUFF
        FloatingActionButton addBtn = (FloatingActionButton) getView().findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });

    }

}
