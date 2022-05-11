package com.example.newsapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class newsArticlesAdapter extends RecyclerView.Adapter<newsArticlesAdapter.Viewholder> {

    ArrayList<Articles> articlesArrayList;
    private Context context;

    public newsArticlesAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public newsArticlesAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list,parent,false);
       return new newsArticlesAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull newsArticlesAdapter.Viewholder holder, int position) {
        Articles articles = articlesArrayList.get(position);
        holder.title.setText(articles.getTitle());
        holder.subtitle.setText(articles.getDescription());
        Picasso.get().load(articles.getUrlToImage()).into(holder.newsImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,newsDetailsActivity.class);
                intent.putExtra("title",articles.getTitle());
                intent.putExtra("content",articles.getContent());
                intent.putExtra("description",articles.getDescription());
                intent.putExtra("urlToImage",articles.getUrlToImage());
                intent.putExtra("url",articles.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private TextView title, subtitle;
        private ImageView newsImage;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.newsHeading);
            subtitle = itemView.findViewById(R.id.newsSubtitle);
            newsImage = itemView.findViewById(R.id.newsImageItem);
        }
    }
}
