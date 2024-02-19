package com.example.androidtask_onedata.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtask_onedata.R;
import com.example.androidtask_onedata.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    Context context;
    ArrayList<Movie> movieArrayList;

    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MovieViewHolder(movieItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie = movieArrayList.get(position);

        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getYear());
        holder.imdbID.setText(movie.getImdbID());
        holder.type.setText(movie.getType());
        Picasso.get().load(movie.getPoster()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        if (movieArrayList.size() == 0) {
            return 0;

        }
        return movieArrayList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView title, year, imdbID, type;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title);
            year = itemView.findViewById(R.id.year);
            imdbID = itemView.findViewById(R.id.imdbID);
            type = itemView.findViewById(R.id.type);
        }
    }
}
