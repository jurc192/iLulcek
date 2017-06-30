package com.student.jure.ilulcek;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;

import com.student.jure.ilulcek.Adapters.MyPagerAdapter;


public class MainActivity extends AppCompatActivity {

    MyDatabaseHelper myDBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        ViewPager myViewPager = (ViewPager) findViewById(R.id.container);
        myViewPager.setAdapter(myPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        TabLayout.Tab tab1 = tabLayout.newTab();
        TabLayout.Tab tab2 = tabLayout.newTab();
        TabLayout.Tab tab3 = tabLayout.newTab();

        tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.addTab(tab3);

        tabLayout.setupWithViewPager(myViewPager);
        myViewPager.setCurrentItem(1);


        // database stuff
        myDBhelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = myDBhelper.getWritableDatabase();

        //get all data
//        Cursor query (
//                String table,
//                String[] columns,
//                String selection,
//                String[] selectionArgs,
//                String groupBy,
//                String having,
//                String orderBy
//                )

        Cursor cursor = db.query("zgodovina", null, null, null, null, null, null);
        StringBuffer buf = new StringBuffer();

        while(cursor.moveToNext()) {
            String drink = cursor.getString(1);
            String qty = cursor.getString(2);
            String time = cursor.getString(4);

            buf.append(drink+" "+qty+" "+time+"\n");
        }

        System.out.println(buf.toString());
    }
}
