package com.example.moviesapp.Utils;

import android.app.Activity;

import com.example.moviesapp.Model.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class DataRetriever {
    public interface DataHandler {
        void handleData(Movie movie);

        void handleData(List<Movie> movies);
    }

    public static void getMovie(DataHandler dataHandler, int movieID) {
        new Thread(() -> {
            try {
                String url = "https://x-mode.co.il/exam/descriptionMovies/" + movieID + ".txt";
                JSONObject movieJson = HttpConnector.getInstance().CallGetRequestForJSON(url);
                List<String> imgsUrls = null;
                if(movieJson.has("imageUrlArr")) {
                    imgsUrls = new LinkedList<>();
                    JSONArray imgsUrlsJsonArray = movieJson.getJSONArray("imageUrlArr");
                    for (int i = 0; i < imgsUrlsJsonArray.length(); i++)
                        imgsUrls.add((String) imgsUrlsJsonArray.get(i));
                }
                final Movie movie = new Movie(
                        movieJson.getString("name"),
                        movieJson.getString("category"),
                        movieJson.getString("description"),
                        movieJson.getString("imageUrl"),
                        movieJson.getString("promoUrl"),
                        movieJson.getString("hour"),
                        movieJson.getInt("id"),
                        movieJson.getInt("year"),
                        movieJson.getDouble("rate"),
                        imgsUrls
                );
                dataHandler.handleData(movie);
            } catch (Exception e) {
            }
        }).start();
    }

    public static void getAllMovies(DataHandler dataHandler, Activity activity) {
        new Thread(() -> {
            try {
                String url = "https://x-mode.co.il/exam/allMovies/allMovies.txt";
                JSONObject jsonObject = HttpConnector.getInstance().CallGetRequestForJSON(url);
                JSONArray moviesJsonArray = jsonObject.getJSONArray("movies");
                List<Movie> moviesList = new LinkedList<>();
                for (int i = 0; i < moviesJsonArray.length(); i++) {
                    JSONObject movieJson = (JSONObject) moviesJsonArray.get(i);
                    moviesList.add(new Movie(
                            movieJson.getString("name"),
                            movieJson.getString("category"),
                            movieJson.getInt("id"),
                            movieJson.getInt("year")
                    ));
                }
                activity.runOnUiThread(() -> dataHandler.handleData(moviesList));
            } catch (Exception e) {
            }
        }).start();
    }
}
