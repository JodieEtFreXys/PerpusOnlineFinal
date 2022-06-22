package com.example.perpusonlinefinal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.perpusonlinefinal.BookDetailForm;
import com.example.perpusonlinefinal.R;

import java.util.ArrayList;

public class MainFormRecyclerAdapter extends RecyclerView.Adapter<MainFormRecyclerAdapter.ViewHolder>{

    private Context context;
    ArrayList id, name, author, cover, synopsis;

    public MainFormRecyclerAdapter(Context context, ArrayList id, ArrayList name, ArrayList author,
                                   ArrayList cover, ArrayList synopsis) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.author = author;
        this.cover = cover;
        this.synopsis = synopsis;
    }

    @NonNull
    @Override
    public MainFormRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main_form_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainFormRecyclerAdapter.ViewHolder holder, int position) {

        String temp = String.valueOf(name.get(position));
        String temp1 = String.valueOf(author.get(position));
        String temp3 = String.valueOf(synopsis.get(position));
        String temp4 = String.valueOf(cover.get(position));
        String temp0 = String.valueOf(id.get(position));
        int tempId = Integer.parseInt(temp0);

        Glide.with(context)
                .load("https://isys6203-perpus-online.herokuapp.com/" + temp4)
                .into(holder.imvMainFormBookCover);

        holder.txvMainFormBookName.setText(temp);
        holder.txvMainFormBookAuthor.setText(temp1);
        holder.txvMainFormBookSynopsis.setText(temp3);
        holder.cardMainForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToBookDetail = new Intent(context, BookDetailForm.class);
                goToBookDetail.putExtra("bookId", tempId);
                goToBookDetail.putExtra("name", temp);
                goToBookDetail.putExtra("author", temp1);
                goToBookDetail.putExtra("cover", temp4);
                goToBookDetail.putExtra("synopsis", temp3);
                context.startActivity(goToBookDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imvMainFormBookCover;
        TextView txvMainFormBookName, txvMainFormBookAuthor, txvMainFormBookSynopsis;
        CardView cardMainForm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imvMainFormBookCover = itemView.findViewById(R.id.imvMainFormBookCover);
            txvMainFormBookName = itemView.findViewById(R.id.txvMainFormBookName);
            txvMainFormBookAuthor = itemView.findViewById(R.id.txvMainFormBookAuthor);
            txvMainFormBookSynopsis = itemView.findViewById(R.id.txvMainFormBookSynopsis);
            cardMainForm = itemView.findViewById(R.id.cardMainForm);
        }
    }
}
