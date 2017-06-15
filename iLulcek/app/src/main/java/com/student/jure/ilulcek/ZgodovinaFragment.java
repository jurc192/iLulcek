package com.student.jure.ilulcek;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ZgodovinaFragment extends Fragment {


    public ZgodovinaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // A context wrapper that allows you to modify or replace the theme of the wrapped context.
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.FragmentTheme);

        // LayoutInflater = > Instantiates a layout XML file into its corresponding View objects.
        // Create a copy of the existing LayoutInflater object, with the copy pointing
        // to a different Context than the original. This is used by ContextThemeWrapper to create
        // a new LayoutInflater to go along with the new Context theme.
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        // inflate the layout using the cloned inflater, not default inflater
        return localInflater.inflate(R.layout.fragment_zgodovina, container, false);
    }

}
