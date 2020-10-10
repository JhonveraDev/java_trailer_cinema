package com.example.conectando.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.conectando.models.Movie;
import com.example.conectando.adapters.MovieAdapter;
import com.example.conectando.adapters.MovieItemClickListener;
import com.example.conectando.R;
import com.example.conectando.adapters.SliderPagerAdapter;
import com.example.conectando.models.slide;
import com.example.conectando.utils.DataSource;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity implements MovieItemClickListener {

    private List<slide> lstSlides ;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView MoviesRV,moviesRvWeek;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        iniViews();
        iniSlider();
        iniPopularMovies();
        iniWeekMovies();

    }

    private void iniWeekMovies(){

        MovieAdapter weekMovieAdapter = new MovieAdapter(this,DataSource.getWeekMovies(),this);
        moviesRvWeek.setAdapter(weekMovieAdapter);
        moviesRvWeek.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));


    }

    private void iniPopularMovies() {
        //Recycler Setup
        MovieAdapter movieAdapter = new MovieAdapter(this, DataSource.getPopularMovies(),this);
        MoviesRV.setAdapter(movieAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void iniSlider() {
        lstSlides = new ArrayList<>() ;
        lstSlides.add(new slide(R.drawable.rampageend,"Rampage \nDwayne Johnson"));
        lstSlides.add(new slide(R.drawable.trans,"Transformers \nThe last Knight"));
        lstSlides.add(new slide(R.drawable.joe,"Joker \nJoaquin Phoenix"));
        lstSlides.add(new slide(R.drawable.ashe,"Slide Title \nmore text here"));
        SliderPagerAdapter adapter = new SliderPagerAdapter (this,lstSlides);
        sliderpager.setAdapter(adapter);
        // setup Time
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),4000,6000);
        indicator.setupWithViewPager(sliderpager,true);
    }

    private void iniViews() {
        sliderpager= findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        MoviesRV = findViewById(R.id.Rv_movies);
        moviesRvWeek= findViewById(R.id.rv_movies_week);
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {


        Intent intent = new Intent(this,MovieDetailActivity.class);
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());


        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this,
                                                    movieImageView,"sharedName");

        startActivity(intent,options.toBundle());




        Toast.makeText(this,"Elemento Elegido:"+movie.getTitle(),Toast.LENGTH_LONG).show();

    }


    class SliderTimer extends TimerTask{

        @Override
        public void run(){

            HomeActivity.this.runOnUiThread(new Runnable(){
                @Override
                public void run(){
                    if (sliderpager.getCurrentItem()<lstSlides.size()-1){
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);

                    }
                    else
                        sliderpager.setCurrentItem(0);


                }

            });
        }

    }

}
