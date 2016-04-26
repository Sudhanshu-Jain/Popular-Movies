package com.example.android.popularmovies;

/**
 * Created by sudhanshu on 17/4/16.
 */

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.android.popularmovies.models.Movie;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MovieServerConnector {

    private static final String TAG = "ServerConnector";
    private static final int DEFAULT_SERVER_PAGE_SIZE = 20;
    private final String apiKey;
    private Context context;


    public MovieServerConnector(Context context) {
        this.context = context;
        this.apiKey = context.getString(R.string.server_api_key);
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//        this.apikey = sharedPreferences.getString("api-key", context.getString(R.string.server_api_key));
    }

    public String getData() throws IOException {
        String baseUrl = this.context.getString(R.string.server_base_url);
        // TODO: store string constants in resource file(s)
        Uri uri = Uri.parse(baseUrl).buildUpon()
                .appendQueryParameter("sort_by", "popularity.desc")
                .appendQueryParameter("api_key", this.apiKey).build();

        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(uri.toString()).openConnection();
        httpURLConnection.connect();

        int responseCode;
        try {
            responseCode = httpURLConnection.getResponseCode();
        } catch (IOException e) {
            responseCode = httpURLConnection.getResponseCode();
        }

        switch (responseCode) {
            case HttpURLConnection.HTTP_OK:
                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }
                return stringBuilder.toString();

            default:
                throw new IllegalStateException("Connection method is not equipped to handle this case");
        }
    }

    public String getPage(int page, int sortCriteria) throws IOException {
        String baseUrl = this.context.getString(R.string.server_base_url);
        // TODO: store string constants in resource file(s)
        Uri uri = Uri.parse(baseUrl).buildUpon()
                .appendQueryParameter("sort_by", sortCriteria == 0 ? "popularity.desc" : "vote_average.desc")
                .appendQueryParameter("api_key", this.apiKey)
                .appendQueryParameter("page", String.valueOf(page))
                .build();
        Log.d(TAG, "Loading uri: " + uri.toString());
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(uri.toString()).openConnection();
        httpURLConnection.connect();

        int responseCode;
        try {
            responseCode = httpURLConnection.getResponseCode();
        } catch (IOException e) {
            responseCode = httpURLConnection.getResponseCode();
        }

        switch (responseCode) {
            case HttpURLConnection.HTTP_OK:
                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }
                return stringBuilder.toString();

            default:
                Log.e(TAG, "Unknown response code: " + responseCode);
                throw new IllegalStateException("Connection method is not equipped to handle this case");
        }
    }

    public List<Movie> getMovies(int page, int pageSize, int sortCriteria) throws IOException, JSONException {
        Log.d(TAG, "PageSize: " + pageSize);
        int numberOfServerPagesPerResult = pageSize / DEFAULT_SERVER_PAGE_SIZE;
        Log.d(TAG, "Number of server pages per result: " + numberOfServerPagesPerResult);
        int firstRequiredPage = (page - 1) * numberOfServerPagesPerResult + 1;
        int lastRequiredPage = page * numberOfServerPagesPerResult;
        Log.d(TAG, "requesting pages from [" + firstRequiredPage + "] to [" + lastRequiredPage + "]");
        List<Movie> movies = new ArrayList<>();
        for (int i = firstRequiredPage; i <= lastRequiredPage; i++) {
            String pageData = getPage(i, sortCriteria);
            movies.addAll(new DataParser(pageData).getMovies());
        }
        return movies;
    }
}
