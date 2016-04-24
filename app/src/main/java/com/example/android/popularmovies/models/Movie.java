package com.example.android.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sudhanshu on 1/4/16.
 */
public class Movie implements Parcelable{

    private int id;

    protected Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        image = in.readString();
        overview = in.readString();
        rating = in.readInt();
        date = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie(int rating, int id, String title, String image, String overview, String date) {
        this.rating = rating;
        this.id = id;
        this.title = title;
        this.image =    "http://image.tmdb.org/t/p/w185" + image;
        this.overview = overview;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String title; // original_title



    private String image; // poster_path
    private String overview;
    private int rating; // vote_average
    private String date; // release_date

    public Movie() {
    }

    public Movie(JSONObject movie){
        try {
            this.id = movie.getInt("id");
            this.title = movie.getString("original_title");
            this.image = movie.getString("poster_path");
            this.overview = movie.getString("overview");
            this.rating = movie.getInt("vote_average");
            this.date = movie.getString("release_date");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(overview);
        dest.writeInt(rating);
        dest.writeString(date);
    }
}
