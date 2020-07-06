package com.example.moviesapp.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.Model.Movie;
import com.example.moviesapp.R;
import com.example.moviesapp.Utils.MovieSearch;
import com.example.moviesapp.Utils.MoviesSort;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>
        implements MoviesListFragment.Sort, MoviesListFragment.Search {
    private List<Movie> items;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    public MoviesAdapter(Context context, List<Movie> items, ItemClickListener clickListener) {
        this.inflater = LayoutInflater.from(context);
        this.items = items;
        this.clickListener = clickListener;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movie_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = items.get(position);
        holder.movie_name.setText(movie.getName());
        holder.movie_year.setText(String.valueOf(movie.getYear()));
        holder.movie_category.setText(movie.getCategory());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void sort() {
        items = MoviesSort.sort(items);
        notifyDataSetChanged();
    }

    @Override
    public void search(String value) {
        if (value == null || value.equals("")) {
            notifyDataSetChanged();
            return;
        }
        value = value.trim();
        List<Movie> tmpItemsList = items;
        items = MovieSearch.search(value, items);
        notifyDataSetChanged();
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            items = tmpItemsList;
        }).start();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView movie_name, movie_year, movie_category;

        ViewHolder(View itemView) {
            super(itemView);
            movie_category = itemView.findViewById(R.id.movie_category);
            movie_name = itemView.findViewById(R.id.movie_name);
            movie_year = itemView.findViewById(R.id.movie_year);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                Movie movie = items.get(getAdapterPosition());
                clickListener.onItemClick(movie);
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(Movie movie);
    }
}
