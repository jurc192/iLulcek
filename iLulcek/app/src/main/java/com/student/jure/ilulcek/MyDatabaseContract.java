package com.student.jure.ilulcek;

import android.provider.BaseColumns;

public final class MyDatabaseContract {

    private static final String DATABASE_NAME = "lulcekdb";
    private static final int DATABASE_VERSION = 1;

    // To prevent someone from instantiating the class -> empty & private constructor
    private MyDatabaseContract() {}

    // Definition of ZGODOVINA table
    public static final class Zgodovina implements BaseColumns {

        public static final String ID_ZGODOVINA = "id_zgodovina";
        public static final String TABLE_NAME = "zgodovina";
        public static final String DRINK = "drink";
        public static final String QTY = "qty";    // quantity
        public static final String DATE = "date";
        public static final String TIME = "time";

    }

}
