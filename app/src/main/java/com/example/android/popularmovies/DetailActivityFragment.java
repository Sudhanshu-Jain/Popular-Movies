package com.example.android.popularmovies;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivityFragment extends Fragment {

    public static String TAG = "DetailsActivityFragment";
    TextView title;
    TextView description;
    TextView rating;
    TextView releaseDate;
    ImageView imageView;

    public Movie mMovie;

    static final String DETAIL_MOVIE = "DETAIL_MOVIE";


    public DetailActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mMovie = arguments.getParcelable(DetailActivityFragment.DETAIL_MOVIE);
        }

        View rootView = inflater.inflate(R.layout.activity_movie_details, container, false);

        title = (TextView)rootView.findViewById(R.id.movieTitle);
        description = (TextView)rootView.findViewById(R.id.content);
        rating=(TextView)rootView.findViewById(R.id.ratingsGiven);
        releaseDate = (TextView)rootView.findViewById(R.id.date);
        imageView = (ImageView)rootView.findViewById(R.id.thumbnail);

        title.setText(mMovie.getTitle());
        description.setText(mMovie.getOverview());
        rating.setText(Integer.toString(mMovie.getRating()));
        releaseDate.setText(mMovie.getDate());
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185" + mMovie.getImage()).fit().centerCrop().into(imageView);

        return rootView;
    }


}
