package com.example.perpusonlinefinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.perpusonlinefinal.Adapter.MainFormRecyclerAdapter;
import com.example.perpusonlinefinal.BookDatabase.BookDatabaseHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        initData();
    }

    private void initData() {
        if (bookDatabaseHelper.readAllData().getCount() == 0) {
            getBookFromNetwork();
        } else {
            readDataFromDatabase();
        }
    }

    private void initUi(){

        rvMainForm = findViewById(R.id.rvMainForm);

        bookDatabaseHelper = new BookDatabaseHelper(MainForm.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        author = new ArrayList<>();
        cover = new ArrayList<>();
        synopsis = new ArrayList<>();
    }

    private void setRecyclerView(){

        mainFormRecyclerAdapter = new MainFormRecyclerAdapter(MainForm.this, id, name,
                author, cover, synopsis);
        rvMainForm.setAdapter(mainFormRecyclerAdapter);
        rvMainForm.setLayoutManager(new LinearLayoutManager(MainForm.this));
    }

    private void readDataFromDatabase(){

        Cursor cursor = bookDatabaseHelper.readAllData();
        if(cursor.getCount() <= 0){
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

        setRecyclerView();
    }

    private void getBookFromNetwork() {
        Log.d("REQUEST", "Request start");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://isys6203-perpus-online.herokuapp.com/";

        JsonArrayRequest getBookRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("REQUEST", "Request success");
                        insertDataToDatabase(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("REQUEST", "Request error");
                    }
                });

        queue.add(getBookRequest);
    }

    private void insertDataToDatabase(JSONArray jsonArray) {
        for (int i = 0 ; i < jsonArray.length() ; i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String author = jsonObject.getString("author");
                String synopsis = jsonObject.getString("synopsis");
                String cover = jsonObject.getString("cover");

                bookDatabaseHelper.addData(name, author, synopsis, cover);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        readDataFromDatabase();
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
            SharedPreferenceManager.setUserLogout(MainForm.this);
            Intent goToLogin = new Intent(MainForm.this, LoginForm.class);
            startActivity(goToLogin);
        }
        return super.onOptionsItemSelected(item);
    }
}