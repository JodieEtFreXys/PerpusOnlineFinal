package com.example.perpusonlinefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RequestDetailForm extends AppCompatActivity {

    private TextView txvRequestDetailName, txvRequestDetailAuthor, txvReqDetailRequester, txvReqDetailReceiver;
    private ImageView imvRequestDetailCover;
    private Button btnRequestDetailAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail_form);

        initUi();
        initControl();
    }

    private void initUi(){

        txvRequestDetailName = findViewById(R.id.txvRequestDetailName);
        txvRequestDetailAuthor = findViewById(R.id.txvRequestDetailAuthor);
        txvReqDetailRequester = findViewById(R.id.txvReqDetailRequester);
        txvReqDetailReceiver = findViewById(R.id.txvReqDetailReceiver);
        imvRequestDetailCover = findViewById(R.id.imvRequestDetailCover);
        btnRequestDetailAccept = findViewById(R.id.btnRequestDetailAccept);
    }

    private void initControl(){

        btnRequestDetailAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Missing function, database output, map fragment, sms detail form
            }
        });
    }
}