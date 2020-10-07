package com.shivenderkumar.newsupdates.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import com.shivenderkumar.newsupdates.NewsActivity;
import com.shivenderkumar.newsupdates.R;
import com.shivenderkumar.newsupdates.models.NewsModel;
import com.squareup.picasso.Picasso;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsModel> list_news;
    private Context context;

    public NewsAdapter(List<NewsModel> list_news, Context context) {
        this.list_news = list_news;
        this.context = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_news, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        NewsModel news = list_news.get(position);

        holder.textview_title.setText(news.getTitle());
        holder.textview_date.setText(news.getPublishedAt());
        holder.textview_source.setText(news.getSource());

        String url = news.getUrlToImage();
        if(url.length() <10){
            Picasso.get().load(R.drawable.noimageavailable).into(holder.imageView_url);
        }
        else{
            Picasso.get().load(url).into(holder.imageView_url);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsActivity.class);
                NewsModel newsModel = list_news.get(position);
                intent.putExtra("newsModel", newsModel);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_news.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView textview_title, textview_source, textview_date;
        ImageView imageView_url;

        NewsViewHolder(View itemView) {
            super(itemView);
            textview_title = itemView.findViewById(R.id.textview_title);
            textview_date = itemView.findViewById(R.id.textview_date);
            textview_source = itemView.findViewById(R.id.textview_source);
            imageView_url = itemView.findViewById(R.id.imageview_url);
        }

    }

}
