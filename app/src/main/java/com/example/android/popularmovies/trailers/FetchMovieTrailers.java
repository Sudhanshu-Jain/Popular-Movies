package com.example.android.popularmovies.trailers;

import android.app.Activity;

import com.example.android.popularmovies.details.DataUpdate;
import com.example.android.popularmovies.details.FetchData;
import com.example.android.popularmovies.details.MovieServer;

/**
 * Created by sudhanshu on 16/4/16.
 */
public class FetchMovieTrailers extends FetchData {
    public FetchMovieTrailers(Activity mActivity, DataUpdate listener) {
        super(mActivity, listener);
    }

    @Override
    public MovieServer setServerConnector(Integer[] params) {
        return new TrailerMovieServer(mActivity,params[0]);
    }
}
