package com.example.conectando.adapters;

import android.widget.ImageView;

import com.example.conectando.models.Movie;

public interface MovieItemClickListener {

    void onMovieClick (Movie movie, ImageView movieImageView);

}
