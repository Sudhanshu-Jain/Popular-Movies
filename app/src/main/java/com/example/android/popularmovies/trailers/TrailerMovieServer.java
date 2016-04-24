package com.example.android.popularmovies.trailers;

import android.content.Context;
import android.util.Log;

import com.example.android.popularmovies.details.MovieServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sudhanshu on 17/4/16.
 */
public class TrailerMovieServer extends MovieServer {
    private static String TAG = "TrailerServer";
    public TrailerMovieServer(Context mContext, int mId) {
        super(mContext, mId);
    }

    @Override
    public Map<String, String> parseElements(JSONArray result) {
        Map<String,String> trailers = new HashMap<>();
        try {
            for (int i = 0; i < result.length(); i++) {
                JSONObject movieJsonObject = result.getJSONObject(i);
                if (movieJsonObject.getString("site").equals("YouTube")) {
                    trailers.put(movieJsonObject.getString("name"), movieJsonObject.getString("key"));
                } else {
                    Log.w(TAG, "Ignored trailer for unknown website: " + movieJsonObject.getString("site"));
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }



        return trailers;

    }

    @Override
    public String setPath() {
        return "videos";
    }
}
