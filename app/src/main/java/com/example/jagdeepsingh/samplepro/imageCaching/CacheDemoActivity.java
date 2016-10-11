package com.example.jagdeepsingh.samplepro.imageCaching;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.jagdeepsingh.samplepro.R;

import java.util.ArrayList;
import java.util.List;

public class CacheDemoActivity extends AppCompatActivity {

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MovieAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    private void prepareMovieData() {

        Movie movie;
        movie = new Movie("http://theopentutorials.com/totwp331/wp-content/uploads/totlogo.png","Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);

        movie = new Movie("http://api.androidhive.info/images/glide/small/deadpool.jpg","Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);

        movie = new Movie("http://www.sketchappsources.com/resources/source-image/android-n-logo-kunalbodke.jpg","Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);

        movie = new Movie("https://image.freepik.com/free-icon/android-hand-drawn-logo-outline_318-51834.png","Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);

        movie = new Movie("http://www.2embedcom.com/blog/wp-content/uploads/2013/05/Android-logo2.png","The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);
//
//        movie = new Movie("https://image.freepik.com/free-icon/android-logo_318-53348.png","Mission: Impossible Rogue Nation", "Action", "2015");
//        movieList.add(movie);
//
//        movie = new Movie("http://famouslogos.net/images/android-logo.jpg","Up", "Animation", "2009");
//        movieList.add(movie);
//
//        movie = new Movie("https://image.freepik.com/free-vector/android-boot-logo_634639.jpg","Star Trek", "Science Fiction", "2009");
//        movieList.add(movie);
//
//        movie = new Movie("https://www.digitalreins.com/wp-content/uploads/2015/03/ANDROID.png","The LEGO Movie", "Animation", "2014");
//        movieList.add(movie);
//
//        movie = new Movie("http://downloadicons.net/sites/default/files/android-logo-icon-64976.png","Iron Man", "Action & Adventure", "2008");
//        movieList.add(movie);
//
//        movie = new Movie("http://www.logoopenstock.com/media/users/693/64839/raw/aa71e3b81f378c7e71e4191b89f5edae-android-vector-logo.jpg  ","Aliens", "Science Fiction", "1986");
//        movieList.add(movie);
//
//        movie = new Movie("http://img.time2draw.com/2015/05/Learn-to-Draw-Android-Logo-final-step-215x382.png","Chicken Run", "Animation", "2000");
//        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }
}
