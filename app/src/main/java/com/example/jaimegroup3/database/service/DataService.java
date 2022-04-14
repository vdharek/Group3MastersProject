package com.example.jaimegroup3.database.service;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jaimegroup3.database.DatabaseClient;
import com.example.jaimegroup3.database.EventModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

//Vivek, Akanksha

//to get data from dataabse
public class DataService {

    private Context context;

    public DataService(Context context) {
        this.context = context;
    }

    public List<EventModel> getAllEvent() {
        try {
            return new GetEventDataAsyncTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class GetEventDataAsyncTask extends AsyncTask<Void, Void, List<EventModel>> {
        @Override
        protected List<EventModel> doInBackground(Void... url) {
            return DatabaseClient.getInstance(context)
                    .getAppDatabase().eventMasterDao().getAllEvent();
        }
    }

    //get user wise event
    public List<EventModel> getPOIWiseEvent(String poi) {
        try {
            return new GetPOIWiseDataAsyncTask(poi).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //AsyncTask.
    private class GetPOIWiseDataAsyncTask extends AsyncTask<Void, Void, List<EventModel>> {
        String poi;

        public GetPOIWiseDataAsyncTask(String poi) {
            this.poi = poi;
        }

        @Override
        protected List<EventModel> doInBackground(Void... url) {
            return DatabaseClient.getInstance(context)
                    .getAppDatabase().eventMasterDao().getAllEventPOIise(poi);
        }
    }

    //get date wise event
    public List<EventModel> getDateWiseEvent(String date) {
        try {
            return new GetDateWiseDataAsyncTask(date).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class GetDateWiseDataAsyncTask extends AsyncTask<Void, Void, List<EventModel>> {
        String date;

        public GetDateWiseDataAsyncTask(String date) {
            this.date = date;
        }

        @Override
        protected List<EventModel> doInBackground(Void... url) {
            return DatabaseClient.getInstance(context)
                    .getAppDatabase().eventMasterDao().getAllEventDateWise(date);
        }
    }
}
