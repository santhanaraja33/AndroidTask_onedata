package com.example.androidtask_onedata.response;

import com.example.androidtask_onedata.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class MoviesResponse {
    @SerializedName("totalResults")
    int totalResults;

    @SerializedName("Search")
    ArrayList<Movie> wholeData;

    @SerializedName("Response")
    String response;

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Movie> getWholeData() {
        return wholeData;
    }

    public void setWholeData(ArrayList<Movie> wholeData) {
        this.wholeData = wholeData;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
