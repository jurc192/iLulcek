package com.student.jure.ilulcek;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.student.jure.ilulcek.Adapters.DrinkAdapter;

public class AddActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        GridView gridView = (GridView) findViewById(R.id.gridview);

        Cursor myCursor = getDrinksData();
        myCursor.moveToFirst();

        DrinkAdapter drinkAdapter = new DrinkAdapter(myCursor, this);
        gridView.setAdapter(drinkAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), ""Clicked on item: "+position", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public Cursor getDrinksData() {

        MyDatabaseHelper myDBhelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = myDBhelper.getWritableDatabase();
        Cursor cursor = db.query("pijaca", new String[] {"ime"}, null, null, null, null, null);
       /* if (cursor.moveToFirst()) {
            String str = cursor.getString(1);
            Log.i("LOGstr", str);
        }*/
       int col = cursor.getColumnCount();
        int a = cursor.getCount();
        String txt = Integer.toString(col);
        Log.i("count", txt);


        return cursor;
    }
}
