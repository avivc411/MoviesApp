package com.example.moviesapp.Utils;

import com.example.moviesapp.Model.Movie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MoviesSort {
    public static List<Movie> sort(List<Movie> list) {
        if(list == null || list.isEmpty() || list.size()==1)
            return list;
        List<Movie> ans = new ArrayList<>(list);
        ans.sort(Comparator.comparing(Movie::getYear));
        return ans;
    }
}
