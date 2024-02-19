package com.example.androidtask_onedata.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidtask_onedata.response.MoviesResponse;
import com.example.androidtask_onedata.retrofit.ApiRequest;
import com.example.androidtask_onedata.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieRepository {
    static final String TAG = MovieRepository.class.getSimpleName();
    ApiRequest apiRequest;

    public MovieRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<MoviesResponse> getMovieArticles(String type, String key, int page, String search) {
        final MutableLiveData<MoviesResponse> data = new MutableLiveData<>();
        apiRequest.getMovieArticles(type, key, page, search).enqueue(new Callback<MoviesResponse>() {


            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                Log.d(TAG, "onResponse response:: " + response);


                if (response.body() != null) {
                    data.setValue(response.body());

                    if (response.body().getResponse().equals("True")) {
                        Log.d(TAG, "Movies total result:: " + response.body().getTotalResults());
                        Log.d(TAG, "Movies size:: " + response.body().getWholeData().size());
                    } else {
                        Log.d(TAG, "Movies get Status:: " + "Not Found in Database");


                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
