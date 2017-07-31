package com.student.jure.ilulcek;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.student.jure.ilulcek.Fragments.DrinkNameFragment;

public class AddActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        DrinkNameFragment frag = new DrinkNameFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.add_activity_id, frag, "Add Drink Name Fragment");
        transaction.commit();

    }

    public Cursor getDrinksData() {

        MyDatabaseHelper myDBhelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = myDBhelper.getWritableDatabase();
        Cursor cursor = db.query("pijaca", new String[] {"ime"}, null, null, null, null, null);

        return cursor;
    }
}
