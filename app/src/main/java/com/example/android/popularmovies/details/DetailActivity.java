package com.example.android.popularmovies.details;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.popularmovies.BaseActivity;
import com.example.android.popularmovies.details.DetailsActivityFragment;
import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.reviews.ReviewActivity;
import com.example.android.popularmovies.trailers.TrailerActivity;

public class DetailActivity extends BaseActivity implements DetailsActivityFragment.CallBacks {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public void showReviews(int id) {
        Intent intent = new Intent(this, ReviewActivity.class);
        intent.putExtra("extra_movie_id", id);
        startActivity(intent);
    }

    @Override
    public void showTrailers(int id) {
        Intent intent = new Intent(this, TrailerActivity.class);
        intent.putExtra("extra_movie_id", id);
        startActivity(intent);
    }

    @Override
    public Fragment createFragment() {
        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");
        return DetailsActivityFragment.newInstance(movie);
    }
}
