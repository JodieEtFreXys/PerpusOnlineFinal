package com.example.perpusonlinefinal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.perpusonlinefinal.R;
import com.example.perpusonlinefinal.RequestDetailForm;

import java.util.ArrayList;

public class ViewAllRequestRecyclerAdapter extends RecyclerView.Adapter<ViewAllRequestRecyclerAdapter.ViewHolder>{

    private Context context;
    ArrayList id, bookId, requesterId, receiverId, requesterName, receiverName, name, author, cover, latitude, longitude;

    public ViewAllRequestRecyclerAdapter(Context context, ArrayList id, ArrayList bookId,
                                         ArrayList requesterId, ArrayList receiverId, ArrayList requesterName,
                                         ArrayList receiverName, ArrayList name, ArrayList author, ArrayList cover,
                                         ArrayList latitude, ArrayList longitude) {
        this.context = context;
        this.id = id;
        this.bookId = bookId;
        this.requesterId = requesterId;
        this.receiverId = receiverId;
        this.requesterName = requesterName;
        this.receiverName = receiverName;
        this.name = name;
        this.author = author;
        this.cover = cover;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    @Override
    public ViewAllRequestRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main_form_card, parent, false);

        return new ViewAllRequestRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllRequestRecyclerAdapter.ViewHolder holder, int position) {

        String temp = String.valueOf(id.get(position));
        String temp1 = String.valueOf(bookId.get(position));
        String temp2 = String.valueOf(requesterId.get(position));
        String temp3 = String.valueOf(receiverId.get(position));
        String temp4 = String.valueOf(requesterName.get(position));
        String temp5 = String.valueOf(receiverName.get(position));
        String temp6 = String.valueOf(name.get(position));
        String temp7 = String.valueOf(author.get(position));

        String temp8 = String.valueOf(cover.get(position));
        String tempTemp1 = String.valueOf(latitude.get(position));
        float temp9 = Float.parseFloat(tempTemp1);
        String tempTemp2 = String.valueOf(longitude.get(position));
        float temp0 = Float.parseFloat(tempTemp2);

        Glide.with(context)
                .load("https://isys6203-perpus-online.herokuapp.com/" + temp8)
                .into(holder.imvViewAllRequestBookCover);

        holder.txvViewAllRequestBookName.setText(temp6);
        holder.txvViewAllRequestBookAuthor.setText(temp7);
        holder.txvViewAllRequestBookRequester.setText(temp4);
        holder.txvViewAllRequestBookReceiver.setText(temp5);

        holder.cardViewAllRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToRequestDetail = new Intent(context, RequestDetailForm.class);
                goToRequestDetail.putExtra("id", temp);
                goToRequestDetail.putExtra("bookId", temp1);
                goToRequestDetail.putExtra("requesterId", temp2);
                goToRequestDetail.putExtra("receiverId", temp3);
                goToRequestDetail.putExtra("latitude", temp9);
                goToRequestDetail.putExtra("longitude", temp0);
                context.startActivity(goToRequestDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txvViewAllRequestBookName, txvViewAllRequestBookAuthor,
                txvViewAllRequestBookRequester, txvViewAllRequestBookReceiver;
        ImageView imvViewAllRequestBookCover;
        CardView cardViewAllRequest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txvViewAllRequestBookName = itemView.findViewById(R.id.txvViewAllRequestBookName);
            txvViewAllRequestBookAuthor = itemView.findViewById(R.id.txvViewAllRequestBookAuthor);
            txvViewAllRequestBookRequester = itemView.findViewById(R.id.txvViewAllRequestBookRequester);
            txvViewAllRequestBookReceiver = itemView.findViewById(R.id.txvViewAllRequestBookReceiver);
            imvViewAllRequestBookCover = itemView.findViewById(R.id.imvViewAllRequestBookCover);
            cardViewAllRequest = itemView.findViewById(R.id.cardViewAllRequest);
        }
    }
}
