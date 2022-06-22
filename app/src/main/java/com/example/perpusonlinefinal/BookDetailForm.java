package com.example.perpusonlinefinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.perpusonlinefinal.RequestDatabase.RequestDatabaseHelper;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class BookDetailForm extends AppCompatActivity {

    private TextView txvBookDetailName, txvBookDetailAuthor, txvBookDetailSynopsis;
    private ImageView imvDetailCover;
    private Button btnBookDetailRequest;

    int PLACE_PICKER_REQ = 1;

    private String latitude, longitude, name, author, cover, synopsis;
    private int bookId;

    private RequestDatabaseHelper requestDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail_form);

        initData();
        initUi();
        initControl();
    }

    private void initData(){

        Intent intent = this.getIntent();

        if(intent != null){

            bookId = intent.getIntExtra("bookId", 0);
            name = intent.getStringExtra("name");
            author= intent.getStringExtra("author");
            cover = intent.getStringExtra("cover");
            synopsis = intent.getStringExtra("synopsis");
        }
    }

    private void initUi(){

        txvBookDetailName = findViewById(R.id.txvBookDetailName);
        txvBookDetailAuthor = findViewById(R.id.txvBookDetailAuthor);
        txvBookDetailSynopsis = findViewById(R.id.txvBookDetailSynopsis);
        imvDetailCover = findViewById(R.id.imvDetailCover);
        btnBookDetailRequest = findViewById(R.id.btnBookDetailRequest);

        requestDatabaseHelper = new RequestDatabaseHelper(this);
    }

    private void initControl(){

        btnBookDetailRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Missing function,  map fragment, database input
                float toLat = Float.parseFloat(latitude);
                float toLon = Float.parseFloat(longitude);
                requestDatabaseHelper.addData(bookId, 1, 99, toLat, toLon);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQ) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                StringBuilder stringBuilder = new StringBuilder();
                latitude = String.valueOf(place.getLatLng().latitude);
                longitude = String.valueOf(place.getLatLng().longitude);
                stringBuilder.append("Latitude :");
                stringBuilder.append(latitude);
                stringBuilder.append("/n");
                stringBuilder.append("Longitude :");
                stringBuilder.append(longitude);
                //txvlati.setText(stringBuilder.toString());
            }
        }
    }
}