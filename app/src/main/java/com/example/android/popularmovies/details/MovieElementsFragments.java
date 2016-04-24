package com.example.android.popularmovies.details;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.details.DataUpdate;
import com.example.android.popularmovies.details.FetchData;
import com.example.android.popularmovies.details.MovieElementAdapter;

import java.util.Map;

/**
 * Created by sudhanshu on 16/4/16.
 */
public abstract class MovieElementsFragments extends Fragment implements DataUpdate {
    public MovieElementAdapter adp;
    public static final String KEY_ID = "id";

    @Override
    public void onDataSetup(Map<String, String> map) {
        adp.updateValues(map);
    }


    protected abstract FetchData setMovieElementsFetcher();


    protected abstract MovieElementAdapter setArrayAdapter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adp = setArrayAdapter();
        FetchData fetchData = setMovieElementsFetcher();
        fetchData.execute(getArguments().getInt(KEY_ID));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ListView gridViewLayout = (ListView) view.findViewById(R.id.trailer_list_view);
        gridViewLayout.setAdapter(adp);
        gridViewLayout.setEmptyView(view.findViewById(R.id.empty));
        gridViewLayout.setOnItemClickListener(setonItemClickListener());
//        view.setBackgroundColor(getResources().getColor(R.color.windowBackground));

        return view;
    }

    protected abstract AdapterView.OnItemClickListener setonItemClickListener();
}
