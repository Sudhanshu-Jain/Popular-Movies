package com.example.android.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sudhanshu on 12/4/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "movie.db";
    private static int DATABASE_VERSION = 6;
    public static DBHelper instance;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static String MOVIE_TABLE = "movie_table";
    public static String ID ="_id";
    public static String MOVIE_TITLE = "title";
    public static String MOVIE_RELEASE_DATE = "release_date";
    public static String MOVIE_PLOT = "plot";
    public static String MOVIE_RATINGS = "ratings";
    public static String MOVIE_POSTER_URL = "poster_Url";
    public static String COLUMN_ID = "id";

    public static String CREATE_MOVIES_TABLE = "CREATE TABLE "+ MOVIE_TABLE + "(" +ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_ID+ " INTEGER, " + MOVIE_TITLE + " TEXT NOT NULL, "
            + MOVIE_RELEASE_DATE + " TEXT, " + MOVIE_PLOT + " TEXT, " + MOVIE_RATINGS + " INTEGER, "+MOVIE_POSTER_URL +
            " TEXT);";




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ MOVIE_TABLE);
        db.execSQL(CREATE_MOVIES_TABLE);
    }
}
