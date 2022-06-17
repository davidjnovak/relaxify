package com.example.relaxify;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "journal_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "data";



    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT)";
        database.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
            database.execSQL(new StringBuilder().append("DROP IF TABLE EXISTS ").append(TABLE_NAME).toString());
            onCreate(database);
        }

    public boolean addData(String item) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

//        database.execSQL("DELETE FROM " + TABLE_NAME);
        //^^ delete everthing, broken data


        Log.d(TAG, "addData: Adding " + item.toString()+ " to " + TABLE_NAME);

        long result = database.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getItemID(String data){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + data + "'";
        Cursor cursorData = database.rawQuery(query, null);
        return cursorData;
    }
    public void deleteData(int id, String text){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + text + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + text + " from database.");
        database.execSQL(query);
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


}


