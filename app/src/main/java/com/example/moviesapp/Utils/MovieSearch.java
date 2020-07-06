package com.example.moviesapp.Utils;

import com.example.moviesapp.Model.Movie;

import java.util.LinkedList;
import java.util.List;

public class MovieSearch {
    public static List<Movie> search(String value, List<Movie> movies) {
        List<Movie> ans = new LinkedList<>();
        for (Movie movie : movies)
            if (movie.getName().toLowerCase().contains(value.toLowerCase()))
                ans.add(movie);
        return ans;
    }
}
