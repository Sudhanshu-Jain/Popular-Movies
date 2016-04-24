package com.example.android.popularmovies.reviews;

import android.content.Context;

import com.example.android.popularmovies.details.MovieServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sudhanshu on 16/4/16.
 */
public class ReviewMovieServer extends MovieServer {
    public ReviewMovieServer(Context mContext, int mId) {
        super(mContext, mId);
    }

    @Override
    public Map<String, String> parseElements(JSONArray result)  {
        Map<String,String> reviews = new HashMap<>();
        try {
            for (int i = 0; i < result.length(); i++) {
                JSONObject jsonObject = result.getJSONObject(i);
                reviews.put(jsonObject.getString("author"), jsonObject.getString("content"));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }



        return reviews;
    }

    @Override
    public String setPath() {
        return "reviews";
    }
}
