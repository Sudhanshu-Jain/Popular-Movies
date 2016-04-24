package com.example.android.popularmovies.trailers;

import android.support.v4.app.Fragment;

import com.example.android.popularmovies.BaseActivity;
import com.example.android.popularmovies.reviews.ReviewFragment;

/**
 * Created by sudhanshu on 17/4/16.
 */
public class TrailerActivity extends BaseActivity {
    @Override
    public Fragment createFragment() {
        return TrailerFragment.newInstance(getIntent().getIntExtra("extra_movie_id", 0));
    }
}
