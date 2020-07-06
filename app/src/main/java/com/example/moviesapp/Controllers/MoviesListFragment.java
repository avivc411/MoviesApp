package com.example.moviesapp.Controllers;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;

public class MoviesListFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Search search;
    private Sort sort;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_list, container, false);
        if (recyclerView == null) {
            recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

            Button sortButton = view.findViewById(R.id.sort_button);
            sortButton.setOnClickListener(this::sort);

            Button searchButton = view.findViewById(R.id.search_button);
            searchButton.setOnClickListener((v)->search(view.findViewById(R.id.search_box)));
        }

        return view;
    }

    public MoviesListFragment(RecyclerView.LayoutManager layoutManager,
                              RecyclerView.Adapter adapter,
                              Sort sort,
                              Search search) {
        this.layoutManager = layoutManager;
        this.adapter = adapter;
        this.sort = sort;
        this.search = search;
    }

    public void sort(View view) {
        Log.d("List Fragment", "sort: ");
        if (sort != null)
            sort.sort();
    }

    public void search(EditText editText) {
        if (search != null && editText!=null){
            String value = editText.getText().toString();
            search.search(value);
        }
    }

    public interface Sort {
        void sort();
    }

    public interface Search {
        void search(String value);
    }

    private String TAG = "Movies List Frag";

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: checking activity");
        if(getActivity()!=null) {
            Log.d(TAG, "onResume: activity is'nt null");
            recyclerView = getActivity().findViewById(R.id.recyclerView);
            //recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
}