package com.example.moviesapp.Controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.moviesapp.Model.Movie;
import com.example.moviesapp.R;

import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.Objects;

public class MovieFragment extends Fragment {
    private Movie movie;

    public MovieFragment(Movie movie) {
        this.movie = movie;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_movie, container, false);

        if(movie==null)
            return view;

        TextView name = view.findViewById(R.id.movieFragName);
        TextView year = view.findViewById(R.id.movieFragYear);
        TextView category = view.findViewById(R.id.movieFragCategory);
        ImageView img = view.findViewById(R.id.movieFragImg);
        TextView promo = view.findViewById(R.id.movieFragPromo);

        name.setText(movie.getName());
        year.setText(String.valueOf(movie.getYear()));
        category.setText(movie.getCategory());
        promo.setText(movie.getPromoURL());

        new Thread(()->SetImg(img)).start();

        return view;
    }

    private void SetImg(@NotNull ImageView img){
        try {
            String urlPath = movie.getImgURL();
            if(!urlPath.contains("https"))
                urlPath = urlPath.replaceFirst("http", "https");
            URL url = new URL(urlPath);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            Objects.requireNonNull(getActivity()).runOnUiThread(()->img.setImageBitmap(bmp));
        }catch (Exception e){
            img.setImageBitmap(null);
            Log.e("movie frag", "onCreateView: img is null");
            e.printStackTrace();
        }
    }
}