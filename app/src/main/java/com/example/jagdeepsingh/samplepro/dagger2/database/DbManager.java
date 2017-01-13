package com.example.jagdeepsingh.samplepro.dagger2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jagdeepsingh.samplepro.dagger2.model.User;

/**
 * Created by Jagdeep.Singh on 11-01-2017.
 */

public class DbManager implements DataService {

    private static DbManager dbManager;
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    private DbManager(){}

    private void init(Context context){
        DbHelper dbHelper = new DbHelper(context);
        if(db == null)
            db = dbHelper.getWritableDatabase();
    }

    public static DbManager getInstance(Context context){
        if(dbManager == null)
            dbManager = new DbManager();
        dbManager.init(context);
        return dbManager;
    }

    public void closeDatabase(){
        if(db!=null){
            db.close();
        }
        db = null;
    }

    @Override
    public User getUser() {
        Cursor cursor = getSingleCursorUser();
        User user = null;
        if (cursor.getCount() > 0) {
            user = new User();
            cursor.moveToNext();
            user.name = cursor.getString(cursor.getColumnIndex(DbHelper.NAME));
            user.email = cursor.getString(cursor.getColumnIndex(DbHelper.EMAIL));
            user.address = cursor.getString(cursor.getColumnIndex(DbHelper.ADDRESS));
            user.phone = cursor.getString(cursor.getColumnIndex(DbHelper.PHONE));
        }
        cursor.close();
        return user;
    }

    @Override
    public void storeUser(User user) {
        Cursor cursor = getSingleCursorUser();
        boolean isUpdate = cursor.getCount() > 0;
        cursor.close();
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.USER_ID, user.getUserId());
        cv.put(DbHelper.ADDRESS, user.address);
        cv.put(DbHelper.EMAIL, user.email);
        cv.put(DbHelper.PHONE, user.phone);
        cv.put(DbHelper.NAME, user.name);
        if (isUpdate) {
            db.update(DbHelper.TABLE_USER, cv, DbHelper.USER_ID + "=?",
                    new String[] { String.valueOf(user.getUserId()) });
        } else {
            db.insert(DbHelper.TABLE_USER, null, cv);
        }
    }

    private Cursor getSingleCursorUser(){
        return db.rawQuery("select * from "
        + DbHelper.TABLE_USER
        + " where "
        + DbHelper.USER_ID
        + " = "
        + User.getUniqueUserId(),null);
    }
}
