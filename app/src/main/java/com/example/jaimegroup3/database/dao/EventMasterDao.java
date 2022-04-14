package com.example.jaimegroup3.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.jaimegroup3.database.EventModel;

import java.util.List;

// Vivek, Akanksha

//all curd operation querries.
@Dao
public interface EventMasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(EventModel eventModel);

    @Delete
    void delete(EventModel eventModel);

    @Query("DELETE FROM event_model")
    void deleteAll();

    @Query("SELECT * from event_model")
    List<EventModel> getAllEvent();

    @Query("SELECT * from event_model WHERE event_date=:date")
    List<EventModel> getAllEventDateWise(String date);

    @Query("SELECT * from event_model WHERE `POI_ID`=:poi")
    List<EventModel> getAllEventPOIise(String poi);

    @Update
    void updateEvent(EventModel eventModel);

}
