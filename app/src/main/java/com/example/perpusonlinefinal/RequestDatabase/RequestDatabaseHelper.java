package com.example.perpusonlinefinal.RequestDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

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

    public void addData(int bookId, int reqId, int recId, String latitude, String longitude){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_BOOK, bookId);
        contentValues.put(COLUMN_REQUESTER, reqId);
        contentValues.put(COLUMN_RECEIVER, recId);
        contentValues.put(COLUMN_LATITUDE, latitude);
        contentValues.put(COLUMN_LONGITUDE, longitude);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            Log.d("Database", "add req failed");
        }else{
            Log.d("Database", "add req success: " + bookId);
        }
    }

    public void updateData(String id, int recId){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_REQUESTER, recId);

        long result = sqLiteDatabase.update(TABLE_NAME, contentValues, "id = ?", new String[]{});
        if(result == -1){
            Log.d("Database", "update req failed");
        }else{
            Log.d("Database", "update req success: " + id);
        }
    }
}
