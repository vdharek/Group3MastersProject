package com.example.jaimegroup3.uiclasses

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jaimegroup3.Constant
import com.example.jaimegroup3.MyApp
import com.example.jaimegroup3.R
import com.example.jaimegroup3.adapter.EventAdapter
import com.example.jaimegroup3.database.EventModel
import com.example.jaimegroup3.database.service.DataService
import com.example.jaimegroup3.mqttmanager.Moving
import kotlinx.android.synthetic.main.calender_entry.*
import kotlinx.android.synthetic.main.drive_around.*

//Srikanth, Vivek

class driveAround : Fragment() {

    //to store UUID of pois
    private var poiIdList: MutableList<String> = ArrayList()

    //to store name of a person for certain POIs
    private var poiNameList: MutableList<String> = ArrayList()

    //store all events from database to an arraylist, POI wise.
    private var poiWiseEvent: MutableList<EventModel> = ArrayList()

    //to display all events
    private var eventAdapter: EventAdapter? = null

    //get data from database
    private var dataService: DataService? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.drive_around, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataService = DataService(requireContext())

        Constant.allEvents = dataService!!.allEvent

        poiNameList.clear()
        poiIdList.clear()

        //All data from databse.
        poiNameList.add("All")
        poiIdList.add("0")

        // POIs' UUID and POIs' name being stored separately
        for (i in 0 until MyApp.poiList!!.size) {
            MyApp.poiList!![i].poiName?.let { poiNameList.add(it) }
            MyApp.poiList!![i].poiId?.let { poiIdList.add(it) }
        }

        // store data of spinner using an array adepter
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.listitems_layout, poiNameList)
        poiSpinner!!.adapter = arrayAdapter

        poiSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // for all entries
                //"ALL" in spinner
                if (position == 0) {
                    poiWiseEvent.clear()
                    //getting all data, no matter which POI location
                    poiWiseEvent.addAll(dataService!!.allEvent)

                    setupAdapter(poiWiseEvent)
                }
                // for individual entries.
                else {
                    poiWiseEvent.clear()
                    //to store data of database to our local ArrayList in order to access in project.
                    poiWiseEvent.addAll(dataService!!.getPOIWiseEvent(poiIdList[position]))
                    if (poiWiseEvent != null && poiWiseEvent.size > 0) {
                        getPOIid(poiIdList[position])
                    }
                    setupAdapter(poiWiseEvent)
                }
            }
        }
    }

    //POI vice data.
    private fun setupAdapter(poiWiseEvent: MutableList<EventModel>) {
        rvEventList.layoutManager = LinearLayoutManager(requireContext())
        eventAdapter = EventAdapter(requireContext(), poiWiseEvent)
        rvEventList.adapter = eventAdapter
        eventAdapter!!.onItemClick = {
            getPOIid(poiWiseEvent[it].poiId)
        }
    }

    private fun getPOIid(poiId: String) {
        Moving.movingClass!!.redirectToPOI(poiId)
    }
}