package com.example.jaimegroup3.database;

import android.content.Context;

import androidx.room.Room;

//Vivek, Akanksha

//creating database.
public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private MyDatabase appDatabase;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //DB_NAME is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, MyDatabase.class, "DB_NAME")
                .fallbackToDestructiveMigration().build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public MyDatabase getAppDatabase() {
        return appDatabase;
    }
}
