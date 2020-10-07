package com.shivenderkumar.newsupdates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.shivenderkumar.newsupdates.adapters.NewsAdapter;
import com.shivenderkumar.newsupdates.models.NewsModel;
import com.shivenderkumar.newsupdates.networks.NewsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<NewsModel> list_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list_news = new ArrayList<>();
        loadNews();
    }

    private void loadNews() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi api = retrofit.create(NewsApi.class);
        Call<JsonObject> call = api.getNews();
        call.enqueue(new Callback<JsonObject>(){

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        System.out.println("JSONOBJECT XXX : "+jsonObject.toString());

                        JSONArray articles = jsonObject.getJSONArray("articles");
                        System.out.println("JSON ARRAY ARTICLES :: " +articles.toString());

                        System.out.println("SIZE OF ARTICLES : "+articles.length());

                        for(int i=0; i< articles.length(); i++){

                            JSONObject obj = articles.getJSONObject(i);

                            String source = "";
                            if( ! obj.getJSONObject("source").getString("name").equals(null)){
                                source = obj.getJSONObject("source").getString("name");
                            }

                            String author = "";
                            author = obj.optString("author");

                            String title = "";
                            title = obj.optString("title");

                            String description = "";
                            description = obj.optString("description");

                            String urlToImage = "";
                            urlToImage = obj.optString("urlToImage");

                            String publishedAt = "";
                            publishedAt = obj.optString("publishedAt");

                            String content = "";
                            content = obj.optString("content");

                            String url = "";
                            url = obj.optString("url");

                            NewsModel newsModel = new NewsModel(source,author, title, description, url,urlToImage, publishedAt, content );
                            list_news.add(newsModel);
                            System.out.println("NEWS MODEL i:"+i+" OBJ :"+newsModel.toString());

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Error...", Toast.LENGTH_LONG).show();
                }

                newsAdapter = new NewsAdapter(list_news ,getApplicationContext());
                recyclerView.setAdapter(newsAdapter);

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error in loading News: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}