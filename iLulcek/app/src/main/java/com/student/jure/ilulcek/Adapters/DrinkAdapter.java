package com.student.jure.ilulcek.Adapters;


import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.student.jure.ilulcek.AddActivity;
import com.student.jure.ilulcek.R;

public class DrinkAdapter extends BaseAdapter{

    private Context context;
    private int numItems;
    private Cursor cursor;
    private String[] drinks= {"Dr", "Gazirana pijača", "Drink1", "Drink1", "Drink1", "Drink1", "Drink1", "Drink1", "Drink1",};
    private String text;

    public DrinkAdapter(Cursor cur, Context cont) {

        this.context = cont;
        this.cursor = cur;
        this.numItems = 9;

    }


    @Override
    public int getCount() {
        return numItems;
    }

    @Override
    public Object getItem(int position) {
        return cursor.getString(0);
    }

    @Override
    public long getItemId(int position) {
        return cursor.getInt(0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            View item = inflater.inflate(R.layout.grid_item_drinks, parent, false);

            ImageView icon = (ImageView) item.findViewById(R.id.drinkIcon);
            TextView tv = (TextView) item.findViewById(R.id.drinkText);
            icon.setImageResource(R.drawable.ic_local_bar_black_24dp);
            cursor.moveToFirst();
            for (int i=0; i<position; i++) {
                cursor.moveToNext();
            }
           // String text = cursor.getString(position+position*7);  //preskoci ID
            String text = cursor.getString(0);  //preskoci ID

            String pos = Integer.toString(position);
            Log.i("position", pos);
            Log.i("LOG", text);
            //String text = drinks[position];
            tv.setText(text);
            return item;


        } else return convertView;

    }
}
