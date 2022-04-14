package com.example.jaimegroup3.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//Vivek, Akanksha

//new table creation in database.
@Entity(tableName = "event_model")
public class EventModel implements Serializable {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "POI_ID")
    private String poiId;

    @ColumnInfo(name = "POI_NAME")
    private String poiName;

    @ColumnInfo(name = "event_name")
    private String eventName;

    @ColumnInfo(name = "event_description")
    private String eventDescription;

    @ColumnInfo(name = "event_date")
    private String eventDate;

    @ColumnInfo(name = "event_from")
    private String eventFromTime;

    @ColumnInfo(name = "event_to")
    private String eventToTime;

    @ColumnInfo(name = "reminder_time")
    private String reminderTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventFromTime() {
        return eventFromTime;
    }

    public void setEventFromTime(String eventFromTime) {
        this.eventFromTime = eventFromTime;
    }

    public String getEventToTime() {
        return eventToTime;
    }

    public void setEventToTime(String eventToTime) {
        this.eventToTime = eventToTime;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }
}
