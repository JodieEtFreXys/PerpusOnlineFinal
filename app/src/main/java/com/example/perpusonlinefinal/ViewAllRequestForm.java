package com.example.perpusonlinefinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perpusonlinefinal.Adapter.ViewAllRequestRecyclerAdapter;
import com.example.perpusonlinefinal.BookDatabase.BookDatabaseHelper;
import com.example.perpusonlinefinal.RequestDatabase.RequestDatabaseHelper;
import com.example.perpusonlinefinal.UserDatabase.UserDatabaseHelper;

import java.util.ArrayList;

public class ViewAllRequestForm extends AppCompatActivity {

    private RecyclerView rvViewAllRequest;

    UserDatabaseHelper userDatabaseHelper;
    BookDatabaseHelper bookDatabaseHelper;
    RequestDatabaseHelper requestDatabaseHelper;

    ViewAllRequestRecyclerAdapter viewAllRequestRecyclerAdapter;

    ArrayList<String> id, bookId, requesterId, receiverId, requesterName,
            receiverName, name, author, cover, latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_request_form);

        initUi();
        initControl();
    }

    private void initUi(){

        rvViewAllRequest = findViewById(R.id.rvViewAllRequest);

        userDatabaseHelper = new UserDatabaseHelper(ViewAllRequestForm.this);
        bookDatabaseHelper = new BookDatabaseHelper(ViewAllRequestForm.this);
        requestDatabaseHelper = new RequestDatabaseHelper(ViewAllRequestForm.this);

        id = new ArrayList<>();
        bookId = new ArrayList<>();
        requesterId = new ArrayList<>();
        receiverId = new ArrayList<>();
        requesterName = new ArrayList<>();
        receiverName = new ArrayList<>();
        name = new ArrayList<>();
        author = new ArrayList<>();
        cover = new ArrayList<>();
        latitude = new ArrayList<>();
        longitude = new ArrayList<>();

        storeDataFromRequest();
    }

    private void initControl(){

        viewAllRequestRecyclerAdapter = new ViewAllRequestRecyclerAdapter(ViewAllRequestForm.this,
                id, bookId, requesterId, receiverId, requesterName,
                receiverName, name, author, cover, latitude, longitude);
        rvViewAllRequest.setAdapter(viewAllRequestRecyclerAdapter);
        rvViewAllRequest.setLayoutManager(new LinearLayoutManager(ViewAllRequestForm.this));
    }

    private void storeDataFromRequest(){

        Cursor cursor = requestDatabaseHelper.readAllData();
        if(cursor.getCount() <= 0){
            Toast.makeText(ViewAllRequestForm.this, "No data", Toast.LENGTH_SHORT).show();
        }else{

            cursor.moveToFirst();
            while(cursor.moveToNext()){

                String temp0 = cursor.getString(1);
                String temp1 = cursor.getString(2);
                String temp2 = cursor.getString(3);

                int tempTemp0 = Integer.parseInt(temp0);
                int tempTemp1 = Integer.parseInt(temp1);
                int tempTemp2 = Integer.parseInt(temp2);

                id.add(cursor.getString(0));
                bookId.add(temp0);
                requesterId.add(temp1);
                receiverId.add(temp2);
//                latitude.add(cursor.getString(4));
//                longitude.add(cursor.getString(5));
                latitude.add("tes");
                longitude.add("tes");

                storeDataFromUser(tempTemp1, tempTemp2);
                storeDataFromBook(tempTemp0);
            }
        }
    }

    private void storeDataFromBook(int id){

        Cursor cursor = bookDatabaseHelper.readAllData();
        if(cursor.getCount() <= 0){
            Toast.makeText(ViewAllRequestForm.this, "No data", Toast.LENGTH_SHORT).show();
        }else{

            cursor.moveToPosition(id-1);
            name.add(cursor.getString(1));
            author.add(cursor.getString(2));
            cover.add(cursor.getString(3));
        }
    }

    private void storeDataFromUser(int reqId, int recId){

        Cursor cursor = userDatabaseHelper.readAllData();
        int count = cursor.getCount();
        if(count < 0){
            Toast.makeText(ViewAllRequestForm.this, "No data", Toast.LENGTH_SHORT).show();
        }else{

            int temp = reqId - 1;
            cursor.moveToPosition(temp);
            requesterName.add(cursor.getString(1));

            if(recId == -1){

                receiverName.add("-");
            }else{

                cursor.moveToPosition(recId-1);
                receiverName.add(cursor.getString(1));
            }
        }
    }
}