package com.example.android.popularmovies.trailers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.android.popularmovies.details.FetchData;
import com.example.android.popularmovies.details.MovieElementAdapter;
import com.example.android.popularmovies.details.MovieElementsFragments;

import java.util.HashMap;

/**
 * Created by sudhanshu on 17/4/16.
 */
public class TrailerFragment extends MovieElementsFragments{

    @Override
    protected FetchData setMovieElementsFetcher() {
        return new FetchMovieTrailers(getActivity(),this);
    }

    @Override
    protected MovieElementAdapter setArrayAdapter() {
        return new TrailerAdapter(getActivity(),new HashMap<String,String>(),android.R.layout.simple_list_item_1);
    }

    @Override
    protected AdapterView.OnItemClickListener setonItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + adp.getItem(position))));
            }
        };
    }

    public static TrailerFragment newInstance(int id) {
        TrailerFragment fragment = new TrailerFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(KEY_ID, id);
        fragment.setArguments(arguments);
        return fragment;
    }
}
