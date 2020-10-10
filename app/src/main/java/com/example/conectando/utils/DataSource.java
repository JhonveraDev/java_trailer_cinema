package com.example.conectando.utils;

import com.example.conectando.R;
import com.example.conectando.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class DataSource {


    public static List<Movie>getPopularMovies(){

        List<Movie> lstMovies = new ArrayList<>();
        lstMovies.add(new Movie("Avangers", R.drawable.avengers,R.drawable.avengersmm));
        lstMovies.add(new Movie("John Wick 3",R.drawable.a,R.drawable.wickqwe));
        lstMovies.add(new Movie("Battle Angel",R.drawable.batle,R.drawable.balea));
        lstMovies.add(new Movie("Deadpool",R.drawable.deadpool,R.drawable.deadpoola));
        lstMovies.add(new Movie("Fast & Furius 9",R.drawable.fast,R.drawable.fastda));
        lstMovies.add(new Movie("Final Score",R.drawable.finals,R.drawable.fianala));
        lstMovies.add(new Movie("Joker",R.drawable.joker,R.drawable.jokera));


        return lstMovies;

    }

    public static List <Movie> getWeekMovies(){

        List<Movie> lstMovies = new ArrayList<>();
        lstMovies.add(new Movie("Toy Story 4", R.drawable.toy, R.drawable.toya));
        lstMovies.add(new Movie("Dragon Ball Z",R.drawable.drag,R.drawable.bata));
        lstMovies.add(new Movie("Ralph",R.drawable.ralp,R.drawable.ralpa));
        lstMovies.add(new Movie("Coco",R.drawable.coco,R.drawable.cocoa));
        lstMovies.add(new Movie("Frozen 2",R.drawable.frozen,R.drawable.forezena));
        lstMovies.add(new Movie("Spider-Man",R.drawable.spiderman,R.drawable.spidya));


        return lstMovies;
    }


}
