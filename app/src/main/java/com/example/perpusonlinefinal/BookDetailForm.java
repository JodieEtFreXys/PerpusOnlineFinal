package com.example.perpusonlinefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BookDetailForm extends AppCompatActivity {

    private TextView txvBookDetailName, txvBookDetailAuthor, txvBookDetailSynopsis;
    private ImageView imvDetailCover;
    private Button btnBookDetailRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail_form);

        initUi();
        initControl();
    }

    private void initUi(){

        txvBookDetailName = findViewById(R.id.txvBookDetailName);
        txvBookDetailAuthor = findViewById(R.id.txvBookDetailAuthor);
        txvBookDetailSynopsis = findViewById(R.id.txvBookDetailSynopsis);
        imvDetailCover = findViewById(R.id.imvDetailCover);
        btnBookDetailRequest = findViewById(R.id.btnBookDetailRequest);
    }

    private void initControl(){

        btnBookDetailRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Missing function,  map fragment, database input
            }
        });
    }
}