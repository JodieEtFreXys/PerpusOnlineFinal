package com.example.perpusonlinefinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.perpusonlinefinal.Adapter.MainFormRecyclerAdapter;
import com.example.perpusonlinefinal.BookDatabase.BookDatabaseHelper;

import java.util.ArrayList;

public class MainForm extends AppCompatActivity {

    private RecyclerView rvMainForm;

    BookDatabaseHelper bookDatabaseHelper;
    ArrayList<String> id, name, author, cover, synopsis;

    MainFormRecyclerAdapter mainFormRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);

        initUi();
        initControl();
    }

    private void initUi(){

        rvMainForm = findViewById(R.id.rvMainForm);

        bookDatabaseHelper = new BookDatabaseHelper(MainForm.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        author = new ArrayList<>();
        cover = new ArrayList<>();
        synopsis = new ArrayList<>();

        storeData();
    }

    private void initControl(){

        mainFormRecyclerAdapter = new MainFormRecyclerAdapter(MainForm.this, id, name,
                author, cover, synopsis);
        rvMainForm.setAdapter(mainFormRecyclerAdapter);
        rvMainForm.setLayoutManager(new LinearLayoutManager(MainForm.this));
    }

    private void storeData(){

        Cursor cursor = bookDatabaseHelper.readAllData();
        if(cursor.getCount() > 0){
            Toast.makeText(MainForm.this, "No data", Toast.LENGTH_SHORT).show();
        }else{

            while(cursor.moveToNext()){

                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                author.add(cursor.getString(2));
                cover.add(cursor.getString(3));
                synopsis.add(cursor.getString(4));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_drop_down, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menuViewAllRequest){

            Intent goToViewAllRequest = new Intent(MainForm.this, ViewAllRequestForm.class);
            startActivity(goToViewAllRequest);
        }else if(item.getItemId() == R.id.menuLogout){
            //missing Shared preference
            Intent goToLogin = new Intent(MainForm.this, LoginForm.class);
            startActivity(goToLogin);
        }
        return super.onOptionsItemSelected(item);
    }
}