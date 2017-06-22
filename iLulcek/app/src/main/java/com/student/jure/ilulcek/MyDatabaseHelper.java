package com.student.jure.ilulcek;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "lulcekdb";
    private static final int DATABASE_VERSION = 1;

    // HISTORY - Table name & column names
    private static final String ID_ZGODOVINA = "id_zgodovina";
    private static final String TABLE_ZGODOVINA = "zgodovina";
    private static final String DRINK = "drink";
    private static final String QTY = "qty";    // quantity
    private static final String DATE = "date";
    private static final String TIME = "time";

    // SETTINGS - Table name & column names
    private static final String ID_NASTAVITVE = "id_nastavitve";
    private static final String TABLE_NASTAVITVE = "nastavitve";
    private static final String SEX = "sex";
    private static final String AGE = "age";
    private static final String WEIGHT = "weight";

    // Table create SQL statements
    private static final String CREATE_TABLE_ZGODOVINA = "CREATE TABLE "+TABLE_ZGODOVINA
            +"("+ID_ZGODOVINA+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TABLE_ZGODOVINA+" VARCHAR(255))";

    private static final String CREATE_TABLE_NASTAVITVE = "CREATE TABLE "+TABLE_NASTAVITVE
            +"("+ID_NASTAVITVE+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TABLE_NASTAVITVE+" VARCHAR(255))";

    // Drop table statement
    private static final String DROP_TABLE_NASTAVITVE = "DROP TABLE IF EXISTS"+TABLE_NASTAVITVE;
    private static final String DROP_TABLE_ZGODOVINA = "DROP TABLE IF EXISTS"+TABLE_ZGODOVINA;



    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE_NASTAVITVE);
            db.execSQL(CREATE_TABLE_ZGODOVINA);
        } catch (SQLException e) {
            Log.d("DBHelper","ERROR at creating tables");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            db.execSQL(DROP_TABLE_NASTAVITVE);
            db.execSQL(DROP_TABLE_ZGODOVINA);
            onCreate(db);
        } catch (SQLException e) {
            Log.d("DBHelper","ERROR at dropping tables");
        }

    }
}
