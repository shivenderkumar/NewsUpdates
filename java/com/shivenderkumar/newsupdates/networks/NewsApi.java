package com.shivenderkumar.newsupdates.networks;

import com.google.gson.JsonObject;

import org.json.JSONObject;


import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {
    String BASE_URL = "http://newsapi.org/v2/";
    String yourAPIKEY ="";

    @GET("top-headlines?country=in&apiKey="+yourAPIKEY)
    Call<JsonObject> getNews();

}
