package com.student.jure.ilulcek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.student.jure.ilulcek.Adapters.DrinkAdapter;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        DrinkAdapter drinkAdapter = new DrinkAdapter(this);
        gridView.setAdapter(drinkAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Clicked on item: "+position);
            }

        });
    }
}
