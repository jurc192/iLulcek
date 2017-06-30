package com.student.jure.ilulcek.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.student.jure.ilulcek.R;

public class HistoryAdapter extends BaseAdapter {

    private Cursor cursor;
    private Context context;

    public HistoryAdapter(Context cont, Cursor cur) {

        this.context = cont;
        this.cursor = cur;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.moveToPosition(position);
    }

    @Override
    public long getItemId(int position) {
        cursor.moveToPosition(position);
        return cursor.getInt(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            // Get the root view (=> list_row_settings.xml => LinearLayout)
            View row = inflater.inflate(R.layout.list_row_history, parent, false);

            // Use the root view to find other views
            ImageView iv1 = (ImageView) row.findViewById(R.id.history_icon);
            TextView tv1 = (TextView) row.findViewById(R.id.list_text1);
            TextView tv2 = (TextView) row.findViewById(R.id.list_text2);
            TextView tv3 = (TextView) row.findViewById(R.id.list_text3);

            // Set the values for each view
            cursor.moveToPosition(position);
            String text1 = cursor.getString(1);
            String text2 = cursor.getString(2);
            String text3 = cursor.getString(4);


            tv1.setText(text1);
            tv2.setText(text2);
            tv3.setText(text3);
            iv1.setImageResource(R.drawable.ic_local_bar_black_24dp);
            // TODO: spremeni barvo ikonc, uporabi raje setDrawableResource

            return row;

        } else {

            // View recycling, optimization
            return  convertView;
        }

    }
}
