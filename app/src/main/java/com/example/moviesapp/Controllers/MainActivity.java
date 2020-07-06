package com.example.moviesapp.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.moviesapp.Model.Movie;
import com.example.moviesapp.R;
import com.example.moviesapp.Utils.DataRetriever;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DataRetriever.DataHandler, MoviesAdapter.ItemClickListener {
    private final String movieFragTag = "movieFragTag", moviesListFragTag = "moviesListFragTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataRetriever.getAllMovies(this, this);
    }

    @Override
    public void handleData(Movie movie) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment movieFragment = fragmentManager.findFragmentById(R.id.movie_frag);
        if (movieFragment == null)
            movieFragment = new MovieFragment(movie);


        fragmentManager.beginTransaction()
                .add(R.id.mainContainer, movieFragment, movieFragTag)
                .hide(fragmentManager.findFragmentByTag(moviesListFragTag))
                .commit();
    }

    @Override
    public void handleData(List<Movie> movies) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment moviesListFragment = fragmentManager.findFragmentByTag(moviesListFragTag);

        if (moviesListFragment == null) {
            MoviesAdapter adapter = new MoviesAdapter(this, movies, this);
            moviesListFragment = new MoviesListFragment(
                    new LinearLayoutManager(this),
                    adapter,
                    adapter,
                    adapter);
        }

        fragmentManager.beginTransaction()
                .add(R.id.mainContainer, moviesListFragment, moviesListFragTag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onItemClick(Movie movie) {
        DataRetriever.getMovie(this, movie.getId());
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment moviesListFragment = fragmentManager.findFragmentByTag(moviesListFragTag);
        Fragment movieFragment = fragmentManager.findFragmentByTag(movieFragTag);
        if (movieFragment != null && moviesListFragment != null) {
            fragmentManager.beginTransaction()
                    .hide(movieFragment)
                    .show(moviesListFragment)
                    .commit();
        }
        else
            super.onBackPressed();
    }
}