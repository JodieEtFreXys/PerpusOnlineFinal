package com.example.perpusonlinefinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.perpusonlinefinal.BookDatabase.BookDatabaseHelper;
import com.example.perpusonlinefinal.RequestDatabase.RequestDatabaseHelper;
import com.example.perpusonlinefinal.UserDatabase.UserDatabaseHelper;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class RequestDetailForm extends AppCompatActivity {

    private TextView txvRequestDetailName, txvRequestDetailAuthor, txvReqDetailRequester,
            txvReqDetailReceiver, txvlati, txvRequestDetailSynopsis;
    private ImageView imvRequestDetailCover;
    private Button btnRequestDetailAccept;
    int PLACE_PICKER_REQ = 1;

    private String latitude, longitude, name, author, synopsis, reqName, recName, cover, id,
            lat, lon;
    private RequestDatabaseHelper requestDatabaseHelper;
    private BookDatabaseHelper bookDatabaseHelper;
    private UserDatabaseHelper userDatabaseHelper;

    private int Id, bookId, reqId, recId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail_form);

        initData();
        initUi();
        initControl();
    }

    private void initData(){

        Intent intent = this.getIntent();

        if(intent != null){

            id = intent.getStringExtra("id");
            String tempBookId = intent.getStringExtra("bookId");
            bookId = Integer.parseInt(tempBookId);
            String tempReqId= intent.getStringExtra("requesterId");
            reqId = Integer.parseInt(tempReqId);
            String tempRecId = intent.getStringExtra("receiverId");
            recId = Integer.parseInt(tempRecId);
            lat = intent.getStringExtra("latitude");
            lon = intent.getStringExtra("longitude");
        }
    }

    private void initUi(){

        //Missing SMS detail form
        txvRequestDetailName = findViewById(R.id.txvRequestDetailName);
        txvRequestDetailAuthor = findViewById(R.id.txvRequestDetailAuthor);
        txvReqDetailRequester = findViewById(R.id.txvReqDetailRequester);
        txvReqDetailReceiver = findViewById(R.id.txvReqDetailReceiver);
        txvRequestDetailSynopsis = findViewById(R.id.txvRequestDetailSynopsis);
        imvRequestDetailCover = findViewById(R.id.imvRequestDetailCover);
        btnRequestDetailAccept = findViewById(R.id.btnRequestDetailAccept);
        txvlati = findViewById(R.id.lati);

        requestDatabaseHelper = new RequestDatabaseHelper(this);
        bookDatabaseHelper = new BookDatabaseHelper(this);
        userDatabaseHelper = new UserDatabaseHelper(this);

        getFromUserDatabase(reqId, recId);
        getFromBookDatabase(bookId);
        //Glide to Image
        txvRequestDetailName.setText(name);
        txvRequestDetailAuthor.setText(author);
        txvReqDetailRequester.setText(reqName);
        txvReqDetailReceiver.setText(recName);
        txvRequestDetailSynopsis.setText(synopsis);

        Glide.with(RequestDetailForm.this)
                .load("https://isys6203-perpus-online.herokuapp.com/" + cover)
                .into(imvRequestDetailCover);
    }

    private void getFromUserDatabase(int reqId, int recId){

        Cursor cursor = userDatabaseHelper.readAllData();
        if(cursor.getCount() > 0){

            cursor.moveToPosition(reqId-1);
            reqName = cursor.getString(1);

            if(recId <= 0){

                recName = "-";
            }else{

                cursor.moveToPosition(recId-1);
                recName = cursor.getString(1);
            }
        }
    }

    private void getFromBookDatabase(int bookId){

        Cursor cursor = bookDatabaseHelper.readAllData();
        if(cursor.getCount() > 0){

            cursor.moveToPosition(bookId-1);
            name = cursor.getString(1);
            author = cursor.getString(2);
            cover = cursor.getString(3);
            synopsis = cursor.getString(4);
        }
    }
    private void initControl(){

        if(SharedPreferenceManager.getUserId(RequestDetailForm.this) == reqId){

            btnRequestDetailAccept.setVisibility(View.GONE);
        }else{

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

                    //Insert to Database code here
                    requestDatabaseHelper.updateData(id, recId);
                    Intent goToViewAllRequest = new Intent(RequestDetailForm.this, ViewAllRequestForm.class);
                    startActivity(goToViewAllRequest);
                }
            });
        }
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
                txvlati.setText(stringBuilder.toString());
            }
        }
    }
}