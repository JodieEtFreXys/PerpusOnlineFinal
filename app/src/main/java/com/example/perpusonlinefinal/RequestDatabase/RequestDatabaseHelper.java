package com.example.perpusonlinefinal.RequestDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RequestDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public String TABLE_NAME = "Request";
    public String COLUMN_ID = "id";
    public String COLUMN_BOOK = "book_id";
    public String COLUMN_REQUESTER = "requester_id";
    public String COLUMN_RECEIVER = "receiver_id";
    public String COLUMN_LATITUDE = "latitude";
    public String COLUMN_LONGITUDE = "longitude";

    public RequestDatabaseHelper(@Nullable Context context) {
        super(context, "RequestDatabase.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_BOOK + " INTEGER, " +
                COLUMN_REQUESTER + " INTEGER, " + COLUMN_RECEIVER + " INTEGER, " +
                COLUMN_LATITUDE + " DECIMAL, " + COLUMN_LONGITUDE + " DECIMAL);";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public Cursor readAllData(){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = null;
        if(sqLiteDatabase != null){

            cursor = sqLiteDatabase.rawQuery(query, null);
        }

        return cursor;
    }
}
