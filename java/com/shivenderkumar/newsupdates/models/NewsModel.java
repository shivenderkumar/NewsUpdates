package com.shivenderkumar.newsupdates.models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class NewsModel implements Serializable {
    private String source="",author="", title="", description="", urlToImage="", publishedAt="", content="", url="";

    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
    String d, formattedDate;
    Instant instant;
    Date myDate;

    public NewsModel(String source, String author, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.source =  source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;

        d = publishedAt;

        try{
            instant = Instant.parse( d );
            myDate = Date.from(instant);
            formattedDate = outputFormat.format(myDate);
        }
        catch (Exception e){
            System.out.println("EXCPETION DATE :" +e.getMessage());
        }

        this.publishedAt = formattedDate;
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    @NonNull
    @Override
    public String toString() {
        return ""+source+" "+author+" "+title+" "+description+" "+urlToImage+" "+publishedAt+" "+content+" "+url;
    }
}
