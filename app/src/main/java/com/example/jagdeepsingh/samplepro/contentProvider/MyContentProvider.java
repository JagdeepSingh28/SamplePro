package com.example.jagdeepsingh.samplepro.contentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.net.URI;
import java.sql.SQLData;

/**
 * Created by JagdeepSingh on 11-10-2016.
 */

public class MyContentProvider extends ContentProvider {

    private MyDatabaseHelper dbHelper;

    private static final int ALL_COUNTRIES  = 1;
    private static final int SINGLE_COUNTRY = 2;

    private static final String AUTHORITY   = "com.example.jagdeepsingh.samplepro.contentProvider";

    public static final Uri CONTENT_URI     = Uri.parse("content://" + AUTHORITY + "/countries");

    private static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"countries",ALL_COUNTRIES);
        uriMatcher.addURI(AUTHORITY,"countries/#",SINGLE_COUNTRY);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext());
        return false;
    }

    @Override
    public String getType(Uri uri) {

        switch (uriMatcher.match(uri)){
            case ALL_COUNTRIES:
                return "vnd.android.cursor.dir/vnd.com.example.jagdeepsingh.samplepro.contentProvider.countries";
            case SINGLE_COUNTRY:
                return "vnd.android.cursor.item/vnd.com.example.jagdeepsingh.samplepro.contentProvider.countries";
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);

        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case ALL_COUNTRIES:
                // Do Nothing
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " +uri);
        }

        long id = db.insert(CountriesDb.COUNTRY_TABLE, null, values);
        getContext().getContentResolver().notifyChange(uri,null);
        return Uri.parse(CONTENT_URI + "/" + id);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db                       = dbHelper.getWritableDatabase();
        SQLiteQueryBuilder queryBuilder         = new SQLiteQueryBuilder();
        queryBuilder.setTables(CountriesDb.COUNTRY_TABLE);

        switch (uriMatcher.match(uri)){
            case ALL_COUNTRIES:
                //Do nothing
                break;
            case SINGLE_COUNTRY:
                String id = uri.getPathSegments().get(1);
                queryBuilder.appendWhere(CountriesDb.KEY_ROW_ID + "=" + id);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI" + uri);
        }

        Cursor cursor = queryBuilder.query(db,projection,selection,selectionArgs,null,null,sortOrder);
        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)){
            case ALL_COUNTRIES:
                //do nothing
                break;
            case SINGLE_COUNTRY:
                String id = uri.getPathSegments().get(1);
                selection = CountriesDb.KEY_ROW_ID + "=" + id + (!TextUtils.isEmpty(selection) ? "AND (" + selection + ')' : "");
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI" + uri);
        }

        int deleteCount = db.delete(CountriesDb.COUNTRY_TABLE,selection,selectionArgs);
        getContext().getContentResolver().notifyChange(uri,null);
        return deleteCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)){
            case ALL_COUNTRIES:
                //do nothing
                break;
            case SINGLE_COUNTRY:
                String id = uri.getPathSegments().get(1);
                selection = CountriesDb.KEY_ROW_ID + "=" + id + (!TextUtils.isEmpty(selection) ? "AND (" + selection + ')' : "");
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI" + uri);
        }

        int updateCount = db.update(CountriesDb.COUNTRY_TABLE,values,selection,selectionArgs);
        getContext().getContentResolver().notifyChange(uri,null);
        return updateCount;
    }
}
