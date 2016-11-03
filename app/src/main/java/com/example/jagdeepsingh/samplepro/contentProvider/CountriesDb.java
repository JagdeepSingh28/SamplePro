package com.example.jagdeepsingh.samplepro.contentProvider;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by JagdeepSingh on 11-10-2016.
 */

public class CountriesDb {

    public final static String KEY_ROW_ID       = "_id";
    public final static String KEY_CODE         = "code";
    public final static String KEY_NAME         = "name";
    public final static String KEY_CONTINENT    = "continent";

    private final static String LOG_TAG         = CountriesDb.class.getSimpleName();
    public final static String COUNTRY_TABLE    = "Country";

    private final static String CREATE_TABLE_COUNTRY =
            "CREATE TABLE if not exists " + COUNTRY_TABLE + " (" +
                    KEY_ROW_ID      + " integer PRIMARY KEY autoincrement," +
                    KEY_CODE        + "," +
                    KEY_NAME        + "," +
                    KEY_CONTINENT   + "," +
                    " UNIQUE ("     + KEY_CODE + "));";

    public static void onCreate(SQLiteDatabase db){
        Log.i(LOG_TAG, "onCreate: " + CREATE_TABLE_COUNTRY);
        db.execSQL(CREATE_TABLE_COUNTRY);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.i(LOG_TAG, "onUpgrade: oldVersion is " + oldVersion + " and newVersion is " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + COUNTRY_TABLE);
        onCreate(db);
    }
}
