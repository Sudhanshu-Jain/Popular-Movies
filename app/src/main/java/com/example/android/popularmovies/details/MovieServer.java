package com.example.android.popularmovies.details;

import android.content.Context;
import android.net.Uri;

import com.example.android.popularmovies.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by sudhanshu on 16/4/16.
 */
public abstract class MovieServer {
    public Context mContext;
    public  int mId;
    public static String apiKey  = "57dc2ce682dda520c48d83d5e00c8ba8";
    public static String mPath;

    public MovieServer(Context mContext,int mId) {
        this.mContext = mContext;
        this.mId = mId;
        mPath = setPath();
    }

    public String getData() throws IOException {
        String base_url = mContext.getString(R.string.movie_url);
        Uri uri = Uri.parse(base_url).buildUpon()
                .appendPath(String.valueOf(mId))
                .appendPath(mPath)
                .appendQueryParameter("api_key", this.apiKey).build();

        HttpURLConnection connection = (HttpURLConnection)new URL(uri.toString()).openConnection();
        connection.connect();
        int response = connection.getResponseCode();

                InputStream inputStream = connection.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }
                return stringBuilder.toString();

    }

    public Map<String,String> getElements() throws JSONException, IOException {
        final JSONObject jsonObject = new JSONObject(getData());
        JSONArray results = jsonObject.getJSONArray("results");
        Map<String, String> elements = parseElements(results);
        return elements;

    }

    public abstract Map<String,String> parseElements(JSONArray result);
    public abstract String setPath();

}
