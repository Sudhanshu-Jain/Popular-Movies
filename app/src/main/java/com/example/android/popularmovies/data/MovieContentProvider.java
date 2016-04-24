package com.example.android.popularmovies.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

/**
 * Created by sudhanshu on 12/4/16.
 */
public class MovieContentProvider extends ContentProvider {
    public static  final int CODE_MOVIES = 100;
    public static final int CODE_MOVIE = 101;
    public static String TAG = "Content_PRovider";
    public static SQLiteQueryBuilder sqLiteQueryBuilder;
    static {
        sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(DBHelper.MOVIE_TABLE);
    }

    public DBHelper getDataBaseHelper;
    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MovieCOntract.CONTENT_AUTHORITY,MovieCOntract.PATH, CODE_MOVIES);
        uriMatcher.addURI(MovieCOntract.CONTENT_AUTHORITY, MovieCOntract.PATH+ "/#", CODE_MOVIE);
    }

    @Override
    public boolean onCreate() {
        getDataBaseHelper = new DBHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.v(TAG, "Attempting to query for Uri: " + uri);
        // FIXME: fix the selection parameter
        final String singleMovieQuerySelection = DBHelper.MOVIE_TABLE + "." + DBHelper.COLUMN_ID + " = " + MovieCOntract.extractMovieId(uri);
        int match = uriMatcher.match(uri);
        switch (match) {
            case CODE_MOVIE:
                Log.v(TAG, "matched for a single movie with id: " + extractMovieId(uri));
                return sqLiteQueryBuilder.query(getDataBaseHelper.getReadableDatabase(), null, singleMovieQuerySelection, new String[]{}, null, null, null);
            case CODE_MOVIES:
                Log.v(TAG, "matched for a all movies");
                return sqLiteQueryBuilder.query(getDataBaseHelper.getReadableDatabase(), null, null, null, null, null, null);
            default:
                Log.w(TAG, "No match found for uri: " + uri);
                return null;
        }
    }

    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match){
            case CODE_MOVIE:
                return MovieCOntract.MovieEntry.CONTENT_ITEM_TYPE;

            case CODE_MOVIES:
                return MovieCOntract.MovieEntry.CONTENT_TYPE;

            default:
                return null;

        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        getDataBaseHelper.getWritableDatabase().insert(DBHelper.MOVIE_TABLE,null,values);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return getDataBaseHelper.getWritableDatabase().delete(DBHelper.MOVIE_TABLE,selection,selectionArgs);

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }


    private String extractMovieId(Uri uri) {
        return uri.getLastPathSegment();
    }
}
