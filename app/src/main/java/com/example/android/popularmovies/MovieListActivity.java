package com.example.android.popularmovies;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.android.popularmovies.data.DBHelper;
import com.example.android.popularmovies.details.DetailsActivityFragment;
import com.example.android.popularmovies.reviews.ReviewFragment;
import com.example.android.popularmovies.trailers.TrailerFragment;

/**
 * Created by sudhanshu on 17/4/16.
 */
public class MovieListActivity extends BaseActivity implements DetailsActivityFragment.CallBacks {
    @Override
    public Fragment createFragment() {

        return new MovieListFragment();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_movie_lists;
    }

    @Override
    public void showReviews(int id) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_details, ReviewFragment.newInstance(id)).addToBackStack(null).commit();
    }

    @Override
    public void showTrailers(int id) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_details, TrailerFragment.newInstance(id)).addToBackStack(null).commit();
    }
}
