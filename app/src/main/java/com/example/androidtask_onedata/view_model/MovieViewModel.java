package com.example.androidtask_onedata.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidtask_onedata.constants.AppConstant;
import com.example.androidtask_onedata.repository.MovieRepository;
import com.example.androidtask_onedata.response.MoviesResponse;


public class MovieViewModel extends AndroidViewModel {

    public MovieRepository movieRepository;

    public LiveData<MoviesResponse> moviesResponseLiveData;

    public MovieViewModel(@NonNull Application application) {
        super(application);

        movieRepository = new MovieRepository();
    }

//    public void getMovieFromAPI(String type, int page, String searchQuery) {
//    }

    public LiveData<MoviesResponse> getMovies(String type, int page, String searchQuery) {

        moviesResponseLiveData = movieRepository.getMovieArticles(type, AppConstant.API_KEY, page, searchQuery);

        return moviesResponseLiveData;
    }
}
