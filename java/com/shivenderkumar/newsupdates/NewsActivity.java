package com.shivenderkumar.newsupdates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.shivenderkumar.newsupdates.models.NewsModel;
import com.squareup.picasso.Picasso;

public class NewsActivity extends AppCompatActivity {

    TextView textview2_source, textview2_publishedAt, textview2_author, textview2_title, textview2_description, textview2_content;
    ImageView imageview2_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        imageview2_url = findViewById(R.id.imageview2_url);
        textview2_source = findViewById(R.id.textview2_source);
        textview2_publishedAt = findViewById(R.id.textview2_publishedAt);
        textview2_author = findViewById(R.id.textview2_author);
        textview2_title = findViewById(R.id.textview2_title);
        textview2_description = findViewById(R.id.textview2_description);
        textview2_content = findViewById(R.id.textview2_content);

        if(getIntent().hasExtra("newsModel") ){
            NewsModel newsmodel = (NewsModel) getIntent().getSerializableExtra("newsModel");
            loadData(newsmodel);
        }
    }

    private void loadData(NewsModel newsModel){
        if(newsModel.getUrlToImage().length() <10){
            Picasso.get().load(R.drawable.noimageavailable).into(imageview2_url);
        }
        else{
            Picasso.get().load(newsModel.getUrlToImage()).into(imageview2_url);
        }

        textview2_source.setText(newsModel.getSource());
        textview2_publishedAt.setText(newsModel.getPublishedAt());
        textview2_author.setText(newsModel.getAuthor());
        textview2_title.setText(newsModel.getTitle());
        textview2_description.setText(newsModel.getDescription());
        textview2_content.setText(newsModel.getContent());

    }

}