package com.example.android.popularmovies.details;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sudhanshu on 16/4/16.
 */
public abstract class MovieElementAdapter extends BaseAdapter {

    public Context mContext;
    public Map<String ,String > objectList;
    public int resource;
    public final Object object = new Object();

    public MovieElementAdapter(Context mContext, HashMap<String,String > objectList, int resource) {
        this.mContext = mContext;
        this.objectList = objectList;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return objectList.size();
    }

    @Override
    public Object getItem(int position) {
        return (String)objectList.values().toArray()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    abstract public View getView(int position, View convertView, ViewGroup parent);

    public void updateValues(Map<String, String> objectList) {
        synchronized (object) {
            this.objectList = objectList;
        }
        notifyDataSetChanged();
    }


}
