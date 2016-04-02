package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.android.popularmovies.models.Movie;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.CallBack {

    final String LOG_TAG = "FirstAPICall";
    private boolean mTwoPane;
    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolBar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        setSupportActionBar(toolBar);
        if (findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, new DetailActivityFragment(),
                                DetailActivityFragment.TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }


    }

    @Override
    public void onItemSelected(Movie movie) {

        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(DetailActivityFragment.DETAIL_MOVIE, movie);

            DetailActivityFragment fragment = new DetailActivityFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment, DetailActivityFragment.TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class)
                    .putExtra(DetailActivityFragment.DETAIL_MOVIE, movie);
            startActivity(intent);
        }
    }
    }


//    public class getMoviesList extends AsyncTask<String,Void,List<Movie>> {
//
//        private List<Movie> getMoviesDataFromJson(String jsonStr) throws JSONException {
//            JSONObject movieJson = new JSONObject(jsonStr);
//            JSONArray movieArray = movieJson.getJSONArray("results");
//
//            List<Movie> results = new ArrayList<>();
//
//            for (int i = 0; i < movieArray.length(); i++) {
//                JSONObject movie = movieArray.getJSONObject(i);
//                Movie movieModel = new Movie(movie);
//                results.add(movieModel);
//            }
//
//            return results;
//        }
//
//        @Override
//        protected List<Movie> doInBackground(String... params) {
//
//            if (params.length == 0) {
//                return null;
//            }
//
//            HttpURLConnection urlConnection = null;
//            BufferedReader reader = null;
//
//            String jsonStr = null;
//
//            try {
//                final String BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
//                final String SORT_BY_PARAM = "sort_by";
//                final String API_KEY_PARAM = "api_key";
//
//                Uri builtUri = Uri.parse(BASE_URL).buildUpon()
//                        .appendQueryParameter(SORT_BY_PARAM, params[0])
//                        .appendQueryParameter(API_KEY_PARAM, getString(R.string.tmdb_api_key))
//                        .build();
//
//                URL url = null;
//                try {
//                    url = new URL(builtUri.toString());
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    urlConnection = (HttpURLConnection) url.openConnection();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                urlConnection.setRequestMethod("GET");
//                urlConnection.connect();
//
//                InputStream inputStream = urlConnection.getInputStream();
//                StringBuffer buffer = new StringBuffer();
//                if (inputStream == null) {
//                    return null;
//                }
//                reader = new BufferedReader(new InputStreamReader(inputStream));
//
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
//                    // But it does make debugging a *lot* easier if you print out the completed
//                    // buffer for debugging.
//                    buffer.append(line + "\n");
//                }
//
//                if (buffer.length() == 0) {
//                    return null;
//                }
//                jsonStr = buffer.toString();
//            } catch (IOException e) {
//                Log.e(LOG_TAG, "Error ", e);
//                return null;
//            } finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (final IOException e) {
//                        Log.e(LOG_TAG, "Error closing stream", e);
//                    }
//                }
//            }
//
//            try {
//                return getMoviesDataFromJson(jsonStr);
//            } catch (JSONException e) {
//                Log.e(LOG_TAG, e.getMessage(), e);
//                e.printStackTrace();
//            }
//
//            // This will only happen if there was an error getting or parsing the forecast.
//            return null;
//
//        }
//
//        @Override
//        protected void onPostExecute(List<Movie> movies) {
//            if (movies != null) {
//                if (adp != null) {
//                    adp.notifyDataSetChanged();
//                }
//                mMovies = new ArrayList<>();
//                mMovies.addAll(movies);
//                adp = new MovieGridAdapter(MainActivity.this,mMovies);
//            }
//        }
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

