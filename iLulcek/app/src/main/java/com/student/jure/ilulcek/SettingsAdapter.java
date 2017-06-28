package com.student.jure.ilulcek;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class SettingsAdapter extends BaseAdapter {


    private int numItems;
    private Cursor cursor;
    private Context context;

    public SettingsAdapter(Cursor cur, Context cont) {

        this.context = cont;
        this.cursor = cur;
        this.numItems = 3;
    }

    @Override
    public int getCount() {

        // To je fejkanje, zato da lahko iz ene vrstice iz DB naredim 3 vrstice v listu!
        return numItems;
    }

    @Override
    public Object getItem(int position) {
    //še vedno mi ni jasno kateri je ta "position" row al column?!

        return cursor.getString(0);
    }

    @Override
    public long getItemId(int position) {
        //WHAT IS THIS POSITION, should I move the cursor or what?
        return cursor.getInt(0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the root view
        //use the root view to find other views
        //set the values
        System.out.println("GETVIEW POSITION "+position);
        String[] titles = {"SEX", "AGE", "WEIGHT"};

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        if (convertView == null) {}
//
//        else {
//            convertView = inflater.inflate(R.layout.list_row_settings, parent, false);
//            return  convertView;
//        }

        View row = inflater.inflate(R.layout.list_row_settings, parent, false);

        TextView tv1 = (TextView) row.findViewById(R.id.list_text1);
        TextView tv2 = (TextView) row.findViewById(R.id.list_text2);

        String text1 = titles[position];
        String text2 = cursor.getString(position+1);    //preskoci ID

        System.out.println("TEXT1: "+text1);
        System.out.println("TEXT2: "+text2);

        tv1.setText(text1);
        tv2.setText(text2);

        return row;

    }

}
