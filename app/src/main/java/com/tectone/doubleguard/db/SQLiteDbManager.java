package com.tectone.doubleguard.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kookai on 2015-06-18.
 */
public class SQLiteDbManager extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "doubleguard_test.db";

    // Contacts table name
    private static final String TABLE_CODE = "contacts1";

    // Contacts Table Columns names
    private static final String KEY_ID = "KEY_ID";
    private static final String KEY_NAME = "KEY_NAME";
    private static final String KEY_VALUE = "KEY_VALUE";

    private static final String CREATE_CODE_TABLE = "CREATE TABLE " + TABLE_CODE
            + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_NAME + " TEXT, "
            + KEY_VALUE + " TEXT "
            + "); ";

    SQLiteDatabase SLD;
//    public SQLiteDbManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    public SQLiteDbManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        SLD = sqLiteDatabase;
        sqLiteDatabase.execSQL(CREATE_CODE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void update(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public String PrintData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from " + TABLE_CODE, null);
        while (cursor.moveToNext()) {
            str += cursor.getInt(0)
                    + " : KEY_NAME "
                    + cursor.getString(1)
                    + ", KEY_VALUE = "
                    + cursor.getString(2)
                    + "\n";
        }

        return str;
    }

    public int getDataCount(String _sql) {
        SQLiteDatabase db = getReadableDatabase();
        int res = 0;

        Cursor cursor = db.rawQuery(_sql, null);

        while (cursor.moveToNext()) {
            res = cursor.getInt(0);

        }

        return res;
    }

    public String getDataValue(String _sql) {
        System.out.println("############## : " + _sql);
        SQLiteDatabase db = getReadableDatabase();
        String res = "";

        Cursor cursor = db.rawQuery(_sql, null);

        if (cursor != null && cursor.moveToNext()) {
            res = cursor.getString(0);

        }

        return res;
    }

    public String getTableCode() {
        return TABLE_CODE;
    }

    public SQLiteDatabase getSld() {
        return this.SLD;
    }

    public String getCreateCodeTable() {

        return CREATE_CODE_TABLE;
    }

    public String getKeyId() {
        return KEY_ID;
    }

    public String getKeyName() {
        return KEY_NAME;
    }

    public String getKeyValue() {
        return KEY_VALUE;
    }

}
