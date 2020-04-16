package com.example.murrayapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ScheduleDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "schedule.db";
    public static final String TABLE_NAME = "setSchedule";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "date";
    public static final String COLUMN_3 = "address";
    public static final String COLUMN_4 = "activity";

    public ScheduleDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE setSchedule (ID INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, address TEXT, activity TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    //Adds Activity into Database
    public long addActivity(String date, String address, String activity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("address", address);
        contentValues.put("activity", activity);
        long res = db.insert("setSchedule", null, contentValues);
        db.close();
        return res;
    }
    //TODO
    //Check Date From Calendar Date String to Date in Database
    //Output values from that date
}