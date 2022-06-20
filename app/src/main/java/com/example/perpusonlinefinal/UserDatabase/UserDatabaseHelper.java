package com.example.perpusonlinefinal.UserDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public String TABLE_NAME = "User";
    public String COLUMN_ID = "id";
    public String COLUMN_EMAIL = "email";
    public String COLUMN_PASSWORD = "password";
    public String COLUMN_PHONE = "phone_number";
    public String COLUMN_DOB = "date_of_birth";

    public UserDatabaseHelper(@Nullable Context context) {
        super(context, "UserDatabase.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_EMAIL + " VARCHAR(255), " +
                COLUMN_PASSWORD + " VARCHAR(255), " + COLUMN_PHONE + " VARCHAR(255), " +
                COLUMN_DOB + " DATE);";

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

    public Cursor validateEmail(String email, String password){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE email = ? and password = ?";

        Cursor cursor = null;
        if(sqLiteDatabase != null){

            cursor = sqLiteDatabase.rawQuery(query, new String[]{email, password});
        }

        return cursor;
    }

    public void addData(String email, String password, String phone, String dob){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_PHONE, phone);
        contentValues.put(COLUMN_DOB, dob);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(result == -1){

            Toast.makeText(context, "Fail to Add", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(context, "Add Success", Toast.LENGTH_SHORT).show();
        }
    }
}
