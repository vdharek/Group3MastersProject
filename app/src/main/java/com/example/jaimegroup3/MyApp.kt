package com.example.jaimegroup3

import android.app.Application
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatDelegate
import com.example.jaimegroup3.database.service.DataService
import com.example.jaimegroup3.model.POIModel
import com.example.jaimegroup3.receivers.TimeChangedReceiver

//Vivek, Akash

/*
    *Store data of all POIs and Name of person of that POIs in local ArrayList.
*/
class MyApp : Application() {

    companion object {
        var poiList: MutableList<POIModel>? = ArrayList()
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //disable dark mode

        setupBroadcastReceiver()

        poiList?.clear()

        val poiModel = POIModel()
        poiModel.poiId = Constant.POI1
        poiModel.poiName = "Lisa"
        poiList?.add(poiModel)

        val poiModel2 = POIModel()
        poiModel2.poiId = Constant.POI2
        poiModel2.poiName = "Mary"
        poiList?.add(poiModel2)

        val poiModel3 = POIModel()
        poiModel3.poiId = Constant.POI3
        poiModel3.poiName = "Adam"
        poiList?.add(poiModel3)

        val poiModel4 = POIModel()
        poiModel4.poiId = Constant.POI4
        poiModel4.poiName = "Boris"
        poiList?.add(poiModel4)

        val poiModel5 = POIModel()
        poiModel5.poiId = Constant.POI5
        poiModel5.poiName = "Sally"
        poiList?.add(poiModel5)

        val poiModel6 = POIModel()
        poiModel6.poiId = Constant.POI6
        poiModel6.poiName = "Frank"
        poiList?.add(poiModel6)

        val poiModel7 = POIModel()
        poiModel7.poiId = Constant.POI7
        poiModel7.poiName = "Kiran"
        poiList?.add(poiModel7)

        val poiModel8 = POIModel()
        poiModel8.poiId = Constant.POI8
        poiModel8.poiName = "Carl"
        poiList?.add(poiModel8)

        val dataService = DataService(this)
        Constant.allEvents = dataService.allEvent
    }

    private fun setupBroadcastReceiver() {
        val timeChangedReceiver = TimeChangedReceiver()
        val filter = IntentFilter()
        filter.addAction("android.intent.action.ACTION_TIME_TICK")
        filter.addAction("android.intent.action.TIME_TICK")
        registerReceiver(timeChangedReceiver, filter)
    }
}