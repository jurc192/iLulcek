package com.student.jure.ilulcek.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.student.jure.ilulcek.R;

public class DrinkAdapter extends BaseAdapter{

    private Context context;
    private int numItems;
    private String[] drinks= {"Drink1", "Drink2", "Drink1", "Drink1", "Drink1", "Drink1", "Drink1", "Drink1", "Drink1",};

    public DrinkAdapter(Context cont) {

        this.context = cont;
        this.numItems = 9;

    }

    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            View item = inflater.inflate(R.layout.grid_item_drinks, parent, false);

            ImageView icon = (ImageView) item.findViewById(R.id.drinkIcon);
            TextView tv = (TextView) item.findViewById(R.id.drinkText);

            icon.setImageResource(R.drawable.ic_local_bar_black_24dp);
            tv.setText(drinks[position]);

            return item;


        } else return convertView;

    }
}
