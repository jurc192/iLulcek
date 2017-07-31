package com.student.jure.ilulcek;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabaseHelper extends SQLiteOpenHelper{

    // CONSTANTS - ob spremembi sheme lahko preko konstant spremenim vse querije idr. hkrati
    private static final String DATABASE_NAME = "lulcekdb";
    private static final int DATABASE_VERSION = 10;

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

    //DRINKS - Table name & column names; ID excluded
    private static final String TABLE_PIJACA= "pijaca";
    private static final String IME = "ime";
    private static final String DIUR = "diur";
    private static final String KOL1 = "kol1";
    private static final String KOL2 = "kol2";
    private static final String KOL3 = "kol3";
    private static final String KOL4 = "kol4";
    private static final String KOL5 = "kol5";


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

    private static final String CREATE_TABLE_PIJACA = "CREATE TABLE " + TABLE_PIJACA +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            IME + " VARCHAR(255)," +
            DIUR + " SMALLINT," +
            KOL1 + " FLOAT," +
            KOL2 + " FLOAT," +
            KOL3 + " FLOAT," +
            KOL4 + " FLOAT," +
            KOL5 + " FLOAT);";

    // Drop table statement
    private static final String DROP_TABLE_NASTAVITVE = "DROP TABLE IF EXISTS" + TABLE_NASTAVITVE;
    private static final String DROP_TABLE_ZGODOVINA = "DROP TABLE IF EXISTS" + TABLE_ZGODOVINA;
    private static final String DROP_TABLE_PIJACA = "DROP TABLE IF EXISTS" + TABLE_PIJACA;


    // TODO: implement contract class and get rid of this SQL inserts
    // TEMPORARY SOLUTION
    private static final String INSERT_ZGODOVINA = "INSERT INTO " + TABLE_ZGODOVINA
            +" ("+DRINK+", "+QTY+", "+DATE+", "+TIME+") VALUES ('voda', 0.33, '22.06.2017', '16:27');";

    private static final String INSERT_ZGODOVINA2 = "INSERT INTO " + TABLE_ZGODOVINA
            +" ("+DRINK+", "+QTY+", "+DATE+", "+TIME+") VALUES ('vino', 0.50, '22.06.2017', '16:30');";

    private static final String INSERT_ZGODOVINA3 = "INSERT INTO " + TABLE_ZGODOVINA
            +" ("+DRINK+", "+QTY+", "+DATE+", "+TIME+") VALUES ('pivo', 0.50, '22.06.2017', '15:30');";

    private static final String INSERT_ZGODOVINA4 = "INSERT INTO " + TABLE_ZGODOVINA
            +" ("+DRINK+", "+QTY+", "+DATE+", "+TIME+") VALUES ('voda', 0.50, '22.06.2017', '17:30');";

    private static final String INSERT_ZGODOVINA5 = "INSERT INTO " + TABLE_ZGODOVINA
            +" ("+DRINK+", "+QTY+", "+DATE+", "+TIME+") VALUES ('kava', 0.10, '22.06.2017', '07:30');";

    private static final String INSERT_NASTAVITVE = "INSERT INTO " + TABLE_NASTAVITVE
            +" ("+SEX+", "+AGE+", "+WEIGHT+") VALUES ('Izberite spol', 20, 50);";

    private static final String INSERT_PIJACA1 = "INSERT INTO " + TABLE_PIJACA
            +" ("+IME+", "+DIUR+", "+KOL1+", "+KOL2+", "+KOL3+",  "+KOL4+",  "+KOL5+") VALUES ('Voda', 0, 0.2 , 0.33 , 0.5 , 0.75 , 1);";

    private static final String INSERT_PIJACA2 = "INSERT INTO " + TABLE_PIJACA
            +" ("+IME+", "+DIUR+", "+KOL1+", "+KOL2+", "+KOL3+",  "+KOL4+",  "+KOL5+") VALUES ('Sok', 0, 0.2 , 0.33 , 0.5, 0.75, 1);";

    private static final String INSERT_PIJACA3 = "INSERT INTO " + TABLE_PIJACA
            +" ("+IME+", "+DIUR+", "+KOL1+", "+KOL2+", "+KOL3+",  "+KOL4+",  "+KOL5+") VALUES ('Gazirana pijača', 1, 0.25, 0.33, 0.5, 0.75, 1);";

    private static final String INSERT_PIJACA4 = "INSERT INTO " + TABLE_PIJACA
            +" ("+IME+", "+DIUR+", "+KOL1+", "+KOL2+", "+KOL3+",  "+KOL4+",  "+KOL5+") VALUES ('Kava', 1, 0.03 , 0.06 , 0.12 , 0.25 , 0.5);";

    private static final String INSERT_PIJACA5 = "INSERT INTO " + TABLE_PIJACA
            +" ("+IME+", "+DIUR+", "+KOL1+", "+KOL2+", "+KOL3+",  "+KOL4+",  "+KOL5+") VALUES ('Čaj', 1, 0.12 , 0.25 , 0.33 , 0.5, 0);";

    private static final String INSERT_PIJACA6 = "INSERT INTO " + TABLE_PIJACA
            +" ("+IME+", "+DIUR+", "+KOL1+", "+KOL2+", "+KOL3+",  "+KOL4+",  "+KOL5+") VALUES ('Mleko', 0, 0.1 , 0.2 , 0.33 , 0.5 , 0.75);";

    private static final String INSERT_PIJACA7 = "INSERT INTO " + TABLE_PIJACA
            +" ("+IME+", "+DIUR+", "+KOL1+", "+KOL2+", "+KOL3+",  "+KOL4+",  "+KOL5+") VALUES ('Pivo', 1, 0.25, 0.33, 0.5, 0.75, 1);";

    private static final String INSERT_PIJACA8 = "INSERT INTO " + TABLE_PIJACA
            +" ("+IME+", "+DIUR+", "+KOL1+", "+KOL2+", "+KOL3+",  "+KOL4+",  "+KOL5+") VALUES ('Vino', 1, 0.12, 0.25, 0.5, 0.75, 1);";

    private static final String INSERT_PIJACA9 = "INSERT INTO " + TABLE_PIJACA
            +" ("+IME+", "+DIUR+",  "+KOL1+", "+KOL2+", "+KOL3+",  "+KOL4+",  "+KOL5+") VALUES ('Žganje', 1, 0.02, 0.03, 0.05, 0, 0);";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL(CREATE_TABLE_NASTAVITVE);
            db.execSQL(CREATE_TABLE_ZGODOVINA);
            db.execSQL(CREATE_TABLE_PIJACA);
            db.execSQL(INSERT_NASTAVITVE);
            db.execSQL(INSERT_ZGODOVINA);
            db.execSQL(INSERT_ZGODOVINA2);
            db.execSQL(INSERT_ZGODOVINA3);
            db.execSQL(INSERT_ZGODOVINA4);
            db.execSQL(INSERT_ZGODOVINA5);
            db.execSQL(INSERT_PIJACA1);
            db.execSQL(INSERT_PIJACA2);
            db.execSQL(INSERT_PIJACA3);
            db.execSQL(INSERT_PIJACA4);
            db.execSQL(INSERT_PIJACA5);
            db.execSQL(INSERT_PIJACA6);
            db.execSQL(INSERT_PIJACA7);
            db.execSQL(INSERT_PIJACA8);
            db.execSQL(INSERT_PIJACA9);
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
            db.execSQL(DROP_TABLE_PIJACA);
            onCreate(db);

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public void updateSex(SQLiteDatabase db,String sex){
        ContentValues values = new ContentValues();
        values.put(SEX,sex);
        db.update(TABLE_NASTAVITVE, values, null,null);
    }

    public void updateAge(SQLiteDatabase db,int age){
        ContentValues values = new ContentValues();
        values.put(AGE,age);
        db.update(TABLE_NASTAVITVE, values, null,null);
    }

    public void updateWeight(SQLiteDatabase db,int weight){
        ContentValues values = new ContentValues();
        values.put(WEIGHT,weight);
        db.update(TABLE_NASTAVITVE, values, null,null);
    }
}
