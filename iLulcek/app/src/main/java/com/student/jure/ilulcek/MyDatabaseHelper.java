package com.student.jure.ilulcek;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabaseHelper extends SQLiteOpenHelper{

    // CONSTANTS - ob spremembi sheme lahko preko konstant spremenim vse querije idr. hkrati
    private static final String DATABASE_NAME = "lulcekdb";
    private static final int DATABASE_VERSION = 1;

    // HISTORY - Table name & column names; ID excluded
    private static final String TABLE_ZGODOVINA = "zgodovina";
    private static final String DRINK = "drink";
    private static final String QTY = "qty";    // quantity
    private static final String DATE = "date";
    private static final String TIME = "time";

    // SETTINGS - Table name & column names; ID excluded
    private static final String TABLE_NASTAVITVE = "nastavitve";
    private static final String SEX = "sex";
    private static final String AGE = "age";
    private static final String WEIGHT = "weight";

    private static final String CREATE_TABLE_ZGODOVINA = "CREATE TABLE " + TABLE_ZGODOVINA +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            DRINK + " VARCHAR(255)," +
            QTY  + " FLOAT," +
            DATE + " TEXT," +
            TIME + " TEXT);";

    private static final String CREATE_TABLE_NASTAVITVE = "CREATE TABLE " + TABLE_NASTAVITVE +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            SEX + " VARCHAR(16)," +
            AGE + " SMALLINT," +
            WEIGHT + " SMALLINT);";

    // Drop table statement
    private static final String DROP_TABLE_NASTAVITVE = "DROP TABLE IF EXISTS" + TABLE_NASTAVITVE;
    private static final String DROP_TABLE_ZGODOVINA = "DROP TABLE IF EXISTS" + TABLE_ZGODOVINA;


    // TODO: implement contract class and get rid of SQL inserts
    // TEMPORARY SOLUTION
    private static final String INSERT_ZGODOVINA = "INSERT INTO " + TABLE_ZGODOVINA
            +" ("+DRINK+", "+QTY+", "+DATE+", "+TIME+") VALUES ('voda', 0.33, '22.06.2017', '16:27');";

    private static final String INSERT_ZGODOVINA2 = "INSERT INTO " + TABLE_ZGODOVINA
            +" ("+DRINK+", "+QTY+", "+DATE+", "+TIME+") VALUES ('vino', 0.50, '22.06.2017', '16:30');";

    private static final String INSERT_ZGODOVINA3 = "INSERT INTO " + TABLE_ZGODOVINA
            +" ("+DRINK+", "+QTY+", "+DATE+", "+TIME+") VALUES ('pivo', 0.50, '22.06.2017', '15:30');";

    private static final String INSERT_NASTAVITVE = "INSERT INTO " + TABLE_NASTAVITVE
            +" ("+SEX+", "+AGE+", "+WEIGHT+") VALUES ('m', 20, 85);";


    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL(CREATE_TABLE_NASTAVITVE);
            db.execSQL(CREATE_TABLE_ZGODOVINA);
            db.execSQL(INSERT_NASTAVITVE);
            db.execSQL(INSERT_ZGODOVINA);
            db.execSQL(INSERT_ZGODOVINA2);
            db.execSQL(INSERT_ZGODOVINA3);
            System.out.println("CREATED TABLES!");

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {

            db.execSQL(DROP_TABLE_NASTAVITVE);
            db.execSQL(DROP_TABLE_ZGODOVINA);
            onCreate(db);

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }
}
