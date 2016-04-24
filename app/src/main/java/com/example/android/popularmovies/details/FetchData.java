package com.example.android.popularmovies.details;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sudhanshu on 16/4/16.
 */
public abstract class FetchData extends AsyncTask<Integer,Void,Map<String,String>> {
    ProgressDialog pd;
    public Activity mActivity;
    DataUpdate listener;

    public FetchData(Activity mActivity,DataUpdate listener) {
        this.mActivity = mActivity;
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(Map<String, String> stringStringMap) {
        listener.onDataSetup(stringStringMap);
        if(pd!=null)
            pd.dismiss();
    }

    @Override
    protected Map<String, String> doInBackground(Integer... params) {
        MovieServer movieServer = setServerConnector(params);
        Map<String ,String > stringMap;
        try {
            stringMap = movieServer.getElements();
        } catch (JSONException e) {
            e.printStackTrace();
            return new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
        return stringMap;


    }

    public abstract MovieServer setServerConnector(Integer[] params);


    @Override
    protected void onPreExecute() {
        pd=new ProgressDialog(mActivity);
        pd.setTitle("Loading elements..");
        pd.setIndeterminate(true);
        pd.show();
    }
}
