package com.example.jaimegroup3.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.jaimegroup3.database.dao.EventMasterDao;

//Vivek, Akanksha

@Database(entities = {EventModel.class}, version = 4, exportSchema = true)

/*@TypeConverters(ConverterListToString.class)*/

//all curd operation of all entries.
public abstract class MyDatabase extends RoomDatabase {

    //curd operation queries written in DAO class
    public abstract EventMasterDao eventMasterDao();

}

