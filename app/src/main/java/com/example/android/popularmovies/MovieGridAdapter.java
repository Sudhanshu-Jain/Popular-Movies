package com.example.android.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sudhanshu on 1/4/16.
 */
public class MovieGridAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Movie> movieArrayList;
    LayoutInflater mInflater;
    public MovieGridAdapter(Context mContext, ArrayList<Movie> movieArrayList) {
        this.mContext = mContext;
        this.movieArrayList = movieArrayList;

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return movieArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            view = mInflater.inflate(R.layout.grid_single, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        Movie movie = movieArrayList.get(position);

        String image_url = "http://image.tmdb.org/t/p/w185" + movie.getImage();

        viewHolder = (ViewHolder) view.getTag();

        Picasso.with(mContext).load(image_url).fit().centerCrop().into(viewHolder.imageView);
       // viewHolder.titleView.setText(movie.getTitle());

        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    public static class ViewHolder {
        public final ImageView imageView;
        //public final TextView titleView;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.grid_image);
            //titleView = (TextView) view.findViewById(R.id.grid_text);
        }
    }
}
