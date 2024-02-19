package com.example.androidtask_onedata.retrofit;

import com.example.androidtask_onedata.response.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("?")
    Call<MoviesResponse> getMovieArticles(@Query("type") String type, @Query("apikey") String apiKey, @Query("page") int page, @Query("s") String query);
}
