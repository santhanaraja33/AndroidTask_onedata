package com.example.androidtask_onedata.screens;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtask_onedata.R;
import com.example.androidtask_onedata.adapters.MovieAdapter;
import com.example.androidtask_onedata.model.Movie;
import com.example.androidtask_onedata.view_model.MovieViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListFragment extends Fragment {

    private ArrayList<Movie> movieArrayList = new ArrayList<>();
    public MovieViewModel movieViewModel;

    private RecyclerView recycler_view;
    private LinearLayoutManager layoutManager;
    private MovieAdapter adapter;

    private AppCompatEditText searchBox;
    private ImageView searchBtn;

    private String searchString;

    private NestedScrollView nestedScrollView;

    private ProgressBar loadProgress;
    private int pageNo = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View listView = inflater.inflate(R.layout.fragment_list, container, false);

        intialization(listView);


        searchBtn.setOnClickListener(view -> {

            if (!(movieArrayList.isEmpty())) {
                movieArrayList.clear();
                adapter.notifyDataSetChanged();

            }

            searchString = Objects.requireNonNull(searchBox.getText()).toString();

            int searchLength = searchString.length();
            if (searchLength < 3) {
                loadProgress.setVisibility(View.GONE);

                Toast.makeText(getActivity(), "Movie name empty and enter minimum 3 letters to search movies", Toast.LENGTH_SHORT).show();
            } else {
                getMovieArticles(searchString, pageNo);
            }
        });


        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    pageNo++;
                    loadProgress.setVisibility(View.VISIBLE);
                    getMovieArticles(searchString, pageNo);

                }
            }
        });


        return listView;
    }

    private void intialization(View listView) {
        searchBtn = listView.findViewById(R.id.searchIcon);
        searchBox = listView.findViewById(R.id.searchQuery);

        nestedScrollView = listView.findViewById(R.id.nestedScroll);

        loadProgress = listView.findViewById(R.id.loadProgress);

        recycler_view = (RecyclerView) listView.findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(getActivity());
        recycler_view.setLayoutManager(layoutManager);

        recycler_view.setHasFixedSize(true);

        adapter = new MovieAdapter(getActivity(), movieArrayList);
        recycler_view.setAdapter(adapter);

        movieViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())).get(MovieViewModel.class);


    }

    @SuppressLint("NotifyDataSetChanged")
    private void getMovieArticles(String searchQuery, int page) {

        movieViewModel.getMovies("movie", page, searchQuery).observe(requireActivity(), moviesResponse -> {
            if (moviesResponse != null && moviesResponse.getResponse().equals("True")) {

                List<Movie> articles = moviesResponse.getWholeData();
                movieArrayList.addAll(articles);

                Log.e("TAG", "List : " + movieArrayList);
                loadProgress.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getActivity(), "Movies Not found", Toast.LENGTH_SHORT).show();

            }
        });
    }
}