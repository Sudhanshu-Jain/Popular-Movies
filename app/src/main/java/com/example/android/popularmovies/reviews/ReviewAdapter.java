package com.example.android.popularmovies.reviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmovies.details.MovieElementAdapter;

import org.w3c.dom.Text;

import java.util.HashMap;

/**
 * Created by sudhanshu on 16/4/16.
 */
public class ReviewAdapter extends MovieElementAdapter {
    public ReviewAdapter(Context mContext, HashMap<String, String> objectList, int resource) {
        super(mContext, objectList, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView==null) {
            view = LayoutInflater.from(mContext).inflate(this.resource, parent, false);
        }
        ((TextView) view.findViewById(android.R.id.text1)).setText((String) objectList.keySet().toArray()[position]);
        ((TextView) view.findViewById(android.R.id.text2)).setText((String) objectList.values().toArray()[position]);

        return view;
    }
}
