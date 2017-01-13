package com.example.jagdeepsingh.samplepro.dagger2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jagdeep.Singh on 11-01-2017.
 */

public class DbHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "user_info";
    private static final int DB_VERSION = 1;
    public static final String TABLE_USER   = "userTable";
    public static final String USER_ID      = "user_id";
    public static final String NAME         = "name";
    public static final String PHONE        = "phone";
    public static final String ADDRESS      = "addess";
    public static final String EMAIL        = "email";

    public static final String CREATE_USER_TABLE = "create table if not exists "+ TABLE_USER + "("
            + USER_ID + " integer primary key autoincrement, "
            +NAME+ " text not null, "
            +PHONE+ " text not null, "
            +ADDRESS+ " text not null, "
            +EMAIL+ " text not null);";


    public DbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_USER);
        onCreate(db);
    }
}
