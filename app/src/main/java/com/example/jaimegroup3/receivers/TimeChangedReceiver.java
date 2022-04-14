package com.example.jaimegroup3.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.jaimegroup3.Constant;
import com.example.jaimegroup3.Utils;
import com.example.jaimegroup3.mqttmanager.Moving;
import com.google.gson.Gson;

import java.util.Calendar;


// Vivek, Srikanth

//Broadcast receiver for reminding entries automatically
public class TimeChangedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0) {
            String currentDateTime = Utils.getDate(System.currentTimeMillis(), Constant.yyyyMMdd_HHmm);

            if (Constant.allEvents != null && Constant.allEvents.size() > 0) {
                for (int i = 0; i < Constant.allEvents.size(); i++) {
                    if (Constant.allEvents.get(i).getReminderTime() != null)
                        if (Constant.allEvents.get(i).getReminderTime().equals(currentDateTime)) {
                            Moving moving = new Moving();
                            moving.redirectToPOI(Constant.allEvents.get(i).getPoiId());
                            Log.d("Receive", "Moving:=> ");
                        }
                }
            }
        }
    }
}