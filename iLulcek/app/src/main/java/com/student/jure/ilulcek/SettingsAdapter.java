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
        // Zmeraj imam samo en "item" pri nastavitvah
        return cursor.getString(0);
    }

    @Override
    public long getItemId(int position) {
        // Tega ne rabim zares, zmeraj imam samo en "item" pri nastavitvah
        return cursor.getInt(0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String[] titles = {"SEX", "AGE", "WEIGHT"};
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            // Get the root view (=> list_row_settings.xml => LinearLayout)
            View row = inflater.inflate(R.layout.list_row_settings, parent, false);

            // Use the root view to find other views
            TextView tv1 = (TextView) row.findViewById(R.id.list_text1);
            TextView tv2 = (TextView) row.findViewById(R.id.list_text2);

            // Set the values for each view
            String text1 = titles[position];
            String text2 = cursor.getString(position+1);    //preskoci ID

            tv1.setText(text1);
            tv2.setText(text2);

            return row;

        } else {

            // View recycling, optimization
            return  convertView;
        }

    }

}
