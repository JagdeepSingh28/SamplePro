package com.example.jagdeepsingh.samplepro.contentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JagdeepSingh on 11-10-2016.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME   = "TheWorld";
    private final static int    VERSION         = 1;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CountriesDb.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        CountriesDb.onUpgrade(db,oldVersion,newVersion);
    }
}
