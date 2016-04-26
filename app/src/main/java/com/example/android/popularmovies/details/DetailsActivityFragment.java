package com.example.android.popularmovies.details;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies.FavouriteMovieManager;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

public class DetailsActivityFragment extends Fragment {

    public static final String ARG_MOVIE = "arg_movie";
    private FavouriteMovieManager mManager;
    private CallBacks mCallBacks;

    public DetailsActivityFragment() {
    }

    public static DetailsActivityFragment newInstance(Movie movie) {
        DetailsActivityFragment fragment = new DetailsActivityFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_MOVIE, movie);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallBacks = (CallBacks) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        mManager = FavouriteMovieManager.create(getActivity());
        final Movie movie = getArguments().getParcelable(ARG_MOVIE);

        TextView moviePlot = (TextView) rootView.findViewById(R.id.movie_plot);
        moviePlot.setText("null".equals(movie.getOverview()) ? "" : movie.getOverview());

        TextView movieTitle = (TextView) rootView.findViewById(R.id.movie_title);
        movieTitle.setText(movie.getTitle());

//
        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        if (mManager.isFavourite(movie)) {
            fab.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            fab.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mManager.isFavourite(movie)) {
                    mManager.remove(movie);
                    fab.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    Toast.makeText(getActivity(), movie.getTitle() + " has been removed from your favorites", Toast.LENGTH_SHORT).show();
                } else {
                    mManager.add(movie);
                    fab.setImageResource(R.drawable.ic_favorite_black_24dp);
                    Toast.makeText(getActivity(), movie.getTitle() + " has been added to your favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView rating = (TextView) rootView.findViewById(R.id.movie_rating);
        rating.setText(String.valueOf("User Rating [ " + movie.getRating() + "/10 ]"));

        TextView releaseDate = (TextView) rootView.findViewById(R.id.movie_release_date);
        releaseDate.setText("Release Date [ " + ("null".equals(movie.getDate()) ? "N/A" : movie.getDate()) + " ]");

        ImageView imageView = (ImageView) rootView.findViewById(R.id.movie_poster);
        Picasso.with(getActivity()).load(movie.getImage()).into(imageView);

        rootView.findViewById(R.id.movie_trailers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBacks.showTrailers(movie.getId());
            }
        });
        rootView.findViewById(R.id.movie_reviews).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBacks.showReviews(movie.getId());
            }
        });

        return rootView;
    }

    public interface CallBacks {
        public void showReviews(int id);

        public void showTrailers(int id);
    }

}


