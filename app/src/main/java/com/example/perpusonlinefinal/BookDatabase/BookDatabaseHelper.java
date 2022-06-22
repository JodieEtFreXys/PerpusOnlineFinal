package com.example.perpusonlinefinal.BookDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class BookDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public String TABLE_NAME = "Book";
    public String COLUMN_ID = "id";
    public String COLUMN_NAME = "name";
    public String COLUMN_AUTHOR = "author";
    public String COLUMN_COVER = "cover";
    public String COLUMN_SYNOPSIS = "synopsis";

    public BookDatabaseHelper(@Nullable Context context) {
        super(context, "BookDatabase.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " VARCHAR(255), " +
                COLUMN_AUTHOR + " VARCHAR(255), " + COLUMN_COVER + " VARCHAR(255), " +
                COLUMN_SYNOPSIS + " TEXT);";

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

    public void addData(String name, String author, String synopsis, String cover){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_AUTHOR, author);
        contentValues.put(COLUMN_COVER, cover);
        contentValues.put(COLUMN_SYNOPSIS, synopsis);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            Log.d("Database", "add book failed");
        }else{
            Log.d("Database", "add book success: " + name);
        }
    }

}
