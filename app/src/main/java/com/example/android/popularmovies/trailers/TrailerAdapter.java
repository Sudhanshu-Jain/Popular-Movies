package com.example.android.popularmovies.trailers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmovies.details.MovieElementAdapter;

import java.util.HashMap;

/**
 * Created by sudhanshu on 17/4/16.
 */
public class TrailerAdapter extends MovieElementAdapter {
    public TrailerAdapter(Context mContext, HashMap<String, String> objectList, int resource) {
        super(mContext, objectList, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView)convertView;
        if (view == null) {
            view = (TextView) LayoutInflater.from(mContext).inflate(this.resource, parent, false);
        }

        view.setText((String) objectList.keySet().toArray()[position]);
        return view;
    }
}
