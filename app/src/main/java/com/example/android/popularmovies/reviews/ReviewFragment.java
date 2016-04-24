package com.example.android.popularmovies.reviews;

import android.os.Bundle;
import android.widget.AdapterView;

import com.example.android.popularmovies.details.FetchData;
import com.example.android.popularmovies.details.MovieElementAdapter;
import com.example.android.popularmovies.details.MovieElementsFragments;

import java.util.HashMap;

/**
 * Created by sudhanshu on 16/4/16.
 */
public class ReviewFragment extends MovieElementsFragments {

    public static ReviewFragment newInstance(int id) {
        ReviewFragment fragment = new ReviewFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(KEY_ID, id);
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    protected FetchData setMovieElementsFetcher() {
        return new FetchMovieReviews(getActivity(),this);
    }

    @Override
    protected MovieElementAdapter setArrayAdapter() {
        return new ReviewAdapter(getActivity(),new HashMap<String,String>(),android.R.layout.simple_list_item_2);
    }

    @Override
    protected AdapterView.OnItemClickListener setonItemClickListener() {
        return null;
    }


}
