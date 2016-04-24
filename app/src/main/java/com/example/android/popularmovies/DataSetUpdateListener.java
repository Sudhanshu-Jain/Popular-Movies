package com.example.android.popularmovies;

import com.example.android.popularmovies.models.Movie;

import java.util.List;

/**
 * Created by sudhanshu on 17/4/16.
 */
public interface DataSetUpdateListener {
    public void onDataSetUpdated(List<Movie> movies);
}
