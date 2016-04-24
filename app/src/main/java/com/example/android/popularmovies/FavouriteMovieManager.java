package com.example.android.popularmovies;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.v4.content.ContextCompat;

import com.example.android.popularmovies.data.DBHelper;
import com.example.android.popularmovies.data.MovieCOntract;
import com.example.android.popularmovies.models.Movie;

/**
 * Created by sudhanshu on 17/4/16.
 */
public class FavouriteMovieManager {
    private static SQLiteQueryBuilder sqLiteQueryBuilder;
    private static  FavouriteMovieManager favouriteMovieManager;

    static {
        sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(DBHelper.MOVIE_TABLE);
    }
    private final ContentResolver mContentResolver;

    public FavouriteMovieManager(Context context) {
        mContentResolver = context.getContentResolver();
    }
    public static FavouriteMovieManager create(Context context){
        if(favouriteMovieManager==null){
            favouriteMovieManager = new FavouriteMovieManager(context);
        }
        return favouriteMovieManager;
    }

    public void add(Movie movie){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COLUMN_ID,movie.getId());
        contentValues.put(DBHelper.MOVIE_TITLE,movie.getTitle());
        contentValues.put(DBHelper.MOVIE_PLOT,movie.getOverview());
        contentValues.put(DBHelper.MOVIE_POSTER_URL,movie.getImage());
        contentValues.put(DBHelper.MOVIE_RATINGS,movie.getRating());
        contentValues.put(DBHelper.MOVIE_RELEASE_DATE,movie.getDate());
        mContentResolver.insert(MovieCOntract.MovieEntry.CONTENT_URI,contentValues);

    }

    public void remove(Movie movie){
        mContentResolver.delete(MovieCOntract.BASE_CONTENT_URI, DBHelper.COLUMN_ID + " = " + movie.getId(), null);
    }

    public boolean isFavourite(Movie movie){
        Cursor cursor = mContentResolver.query(MovieCOntract.MovieEntry.CONTENT_URI.buildUpon().appendPath(String.valueOf(movie.getId())).build(), null, null, null, null);
        if(cursor!=null) {
            if (cursor.getCount() == 1) {
                cursor.close();
                return true;
            }
            cursor.close();
        }

        return false;
    }

}
