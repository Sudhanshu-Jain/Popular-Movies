package com.example.android.popularmovies;

/**
 * Created by sudhanshu on 17/4/16.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.EditText;


import com.example.android.popularmovies.models.Movie;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FetchMoviesData extends AsyncTask<Void, Void, List<Movie>> {

    private static final int PAGE_NUMBER_1 = 1;
    private Activity mActivity;
    private ProgressDialog pd;
    private boolean unauthorizedExceptionOccured = false;
    private DataSetUpdateListener listener;
    private int mSortCriteria = 0;

    public FetchMoviesData(Activity activity, DataSetUpdateListener listener, int sortCriteria) {
        mActivity = activity;
        this.mSortCriteria = sortCriteria;
        this.listener = listener;
    }

    public FetchMoviesData(Activity activity, DataSetUpdateListener listener) {
        mActivity = activity;
        this.mSortCriteria = 0;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(mActivity);
        pd.setTitle(mActivity.getString(R.string.dialog_progress_title));
        pd.setMessage(mActivity.getString(R.string.dialog_progress_message));
        pd.setCancelable(false);
        pd.setIndeterminate(true);
        pd.show();
    }

    @Override
    protected List<Movie> doInBackground(Void... params) {
        MovieServerConnector connector = new MovieServerConnector(mActivity.getApplicationContext());
        List<Movie> movies;
        try {
            movies = connector.getMovies(PAGE_NUMBER_1,200, mSortCriteria);
        } catch (IOException | JSONException e) {

            Log.e("", "Error occurred while parsing movies data...: " + e.toString());
            return new ArrayList<>();
        } catch (Exception e) {
            unauthorizedExceptionOccured = true;
            return new ArrayList<>();
        }

        return movies;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {

        listener.onDataSetUpdated(movies);
        if (pd != null) {
            pd.dismiss();
        }
    }
}
