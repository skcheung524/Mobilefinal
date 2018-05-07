package com.skc.mobilefinal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Tracker";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String MONEY = "money";
    public static final String NOTES = "notes";

    // Database creation sql statement
    private static final String TABLE_NAME = "Budget";
    private static final String DATABASE_CREATE = ("create TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL," + MONEY + " INTEGER NOT NULL," + NOTES + " TEXT)");


    public MySQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String money, String notes) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // change if it's not empty because empty string is consider NOTNULL
        if(!name.isEmpty()){
            values.put(NAME, name);
        }
        // change if it's not empty because empty string is consider NOTNULL
        if(!money.isEmpty()){
            values.put(MONEY, money);
        }
        // don't care if notes is empty because it's an optional field
        values.put(NOTES, notes);
        long result = database.insert(TABLE_NAME, null, values);

        // the database will return -1 if insert query was unsuccessful
        return (result != -1);
    }

    public Cursor getAllData() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }

    public boolean updateData(String id, String name, String money, String notes) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(MONEY, money);
        values.put(NOTES, notes);
        database.update(TABLE_NAME, values, "ID = ?",new String[] {id});
        return true;
    }

    public int deleteData(String id) {
        SQLiteDatabase database = this.getWritableDatabase();

        //returns the number of deleted rows
        return database.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }


}
