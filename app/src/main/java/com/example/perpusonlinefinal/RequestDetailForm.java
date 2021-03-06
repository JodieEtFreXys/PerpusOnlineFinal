package com.example.perpusonlinefinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class RequestDetailForm extends AppCompatActivity {

    private TextView txvRequestDetailName, txvRequestDetailAuthor, txvReqDetailRequester, txvReqDetailReceiver, txvlati;
    private ImageView imvRequestDetailCover;
    private Button btnRequestDetailAccept;
    int PLACE_PICKER_REQ = 1;

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
        txvlati = findViewById(R.id.lati);




    }


    private void initControl(){


        btnRequestDetailAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(RequestDetailForm.this),PLACE_PICKER_REQ);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
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
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                stringBuilder.append("Latitude :");
                stringBuilder.append(latitude);
                stringBuilder.append("/n");
                stringBuilder.append("Longitude :");
                stringBuilder.append(longitude);
                txvlati.setText(stringBuilder.toString());


            }
        }
    }
}