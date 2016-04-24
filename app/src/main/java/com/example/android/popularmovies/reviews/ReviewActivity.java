package com.example.android.popularmovies.reviews;

import android.support.v4.app.Fragment;

import com.example.android.popularmovies.BaseActivity;

/**
 * Created by sudhanshu on 16/4/16.
 */
public class ReviewActivity extends BaseActivity {
    @Override
    public Fragment createFragment() {
        return ReviewFragment.newInstance(getIntent().getIntExtra("extra_movie_id", 0));
    }
}
