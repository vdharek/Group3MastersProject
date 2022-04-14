package com.example.jaimegroup3.uiclasses

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jaimegroup3.Constant
import com.example.jaimegroup3.MyApp
import com.example.jaimegroup3.R
import com.example.jaimegroup3.Utils
import com.example.jaimegroup3.adapter.EventAdapter
import com.example.jaimegroup3.database.DatabaseClient
import com.example.jaimegroup3.database.EventModel
import com.example.jaimegroup3.database.service.DataService
import com.example.jaimegroup3.model.POIModel
import com.example.jaimegroup3.mqttmanager.Moving
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import kotlinx.android.synthetic.main.calender_entry.*
import java.util.*
import kotlin.collections.ArrayList

//Vivek, Akash
class calenderEntry : Fragment() {

    private val myCalendar = Calendar.getInstance()

    private var dataService: DataService? = null

    //store event data based on date.
    private var dateWiseEvent: List<EventModel> = ArrayList()

    private var poiIdList: MutableList<String> = ArrayList()
    private var poiNameList: MutableList<String> = ArrayList()

    private var eventAdapter: EventAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.calender_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataService = DataService(requireContext())

        //all events stored in constant
        Constant.allEvents = dataService!!.allEvent

        poiNameList.clear()
        poiIdList.clear()

        for (i in 0 until MyApp.poiList!!.size) {
            MyApp.poiList!![i].poiName?.let{
                poiNameList.add(it)
            }
            MyApp.poiList!![i].poiId?.let { poiIdList.add(it) }
        }

        dateWiseEvent =
            dataService!!.getDateWiseEvent(Utils.getDate(myCalendar.time.time, Constant.yyyyMMdd))

        //to display data from database in our calender_entry.xml file
        rvData.layoutManager = LinearLayoutManager(requireContext())
        eventAdapter = EventAdapter(requireContext(), dateWiseEvent)
        rvData.adapter = eventAdapter
        //position of position of view replica
        eventAdapter!!.onItemClick = {
            getPOIid(it)
        }

        Log.d("TAG", "onViewCreated: " + Gson().toJson(dateWiseEvent))

        //date wise get events in calender
        calenderView.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->

            val calender: Calendar = Calendar.getInstance()
            calender.set(year, month, dayOfMonth)
            dateWiseEvent =
                dataService!!.getDateWiseEvent(Utils.getDate(calender.time.time, Constant.yyyyMMdd))
            rvData.layoutManager = LinearLayoutManager(requireContext())
            eventAdapter = EventAdapter(requireContext(), dateWiseEvent)
            rvData.adapter = eventAdapter
            eventAdapter!!.onItemClick = {
                getPOIid(it)
            }

        }

        addEvent.setOnClickListener {
            openAddEventBottomSheet()
        }
    }

    //get the position of POI by getting replica position
    private fun getPOIid(position: Int) {
        var poiId = dateWiseEvent[position].poiId

        //based on given replica position, we are accessing POIs ID throw redirectToPoi method from Moving class
        Moving.movingClass!!.redirectToPOI(poiId)
    }

    //popup event form
    private fun openAddEventBottomSheet() {
        val view: View = layoutInflater.inflate(R.layout.bottom_sheet_add_event, null)
        val bottomSheetDialog =
            BottomSheetDialog(requireContext(), R.style.bottomSheetDialog) // Style her
        bottomSheetDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bottomSheetDialog.setContentView(view)

        val mBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(view.parent as View)
        bottomSheetDialog.setOnShowListener { dialogInterface: DialogInterface? ->
            mBehavior.setPeekHeight(view.height) //get the height dynamically
        }
        bottomSheetDialog.show()

        val tvEventTitle = bottomSheetDialog.findViewById<AppCompatEditText>(R.id.tvEventTitle)
        val tvEventDescription =
            bottomSheetDialog.findViewById<AppCompatEditText>(R.id.tvEventDescription)
        val tvEventDate = bottomSheetDialog.findViewById<AppCompatEditText>(R.id.tvEventDate)
        val tvEventFromTime =
            bottomSheetDialog.findViewById<AppCompatEditText>(R.id.tvEventFromTime)
        val tvEventToTime = bottomSheetDialog.findViewById<AppCompatEditText>(R.id.tvEventToTime)
        val btnSubmit = bottomSheetDialog.findViewById<MaterialButton>(R.id.btnSubmit)
        val poiSpinner = bottomSheetDialog.findViewById<AppCompatSpinner>(R.id.poiSpinner)

        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.listitems_layout, poiNameList)
        poiSpinner!!.adapter = arrayAdapter

        //button click event, date picker will popup on this click event
        tvEventDate!!.setOnClickListener {
            val date =
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    myCalendar.set(Calendar.YEAR, year)
                    myCalendar.set(Calendar.MONTH, monthOfYear)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    tvEventDate.setText(Utils.getDate(myCalendar.timeInMillis, Constant.yyyyMMdd))
                }

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                date,
                myCalendar
                    .get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.show()
        }

        // time picker code for "from"
        tvEventFromTime!!.setOnClickListener {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
            val minute = mcurrentTime[Calendar.MINUTE]
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(
                requireContext(),
                { timePicker, selectedHour, selectedMinute ->
                    tvEventFromTime.setText("$selectedHour:$selectedMinute")
                },
                hour,
                minute,
                true
            )

            mTimePicker.setTitle("Select Start Time")
            mTimePicker.show()
        }

        // time picker code for "to"
        tvEventToTime!!.setOnClickListener {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
            val minute = mcurrentTime[Calendar.MINUTE]
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(
                requireContext(),
                { timePicker, selectedHour, selectedMinute ->
                    tvEventToTime.setText("$selectedHour:$selectedMinute")
                },
                hour,
                minute,
                true
            )

            mTimePicker.setTitle("Select End Time")
            mTimePicker.show()
        }

        //to store all information of event in database
        btnSubmit!!.setOnClickListener {
            val eventModel = EventModel()
            eventModel.poiId = poiIdList[poiSpinner.selectedItemPosition]
            eventModel.poiName = poiNameList[poiSpinner.selectedItemPosition]
            eventModel.eventName = tvEventTitle!!.text.toString().trim()
            eventModel.eventDescription = tvEventDescription!!.text.toString().trim()
            eventModel.eventDate = tvEventDate.text.toString().trim()
            eventModel.eventFromTime = tvEventFromTime.text.toString().trim()
            eventModel.eventToTime = tvEventToTime.text.toString().trim()
            eventModel.reminderTime = Utils.getTimeBefore15minutes(
                tvEventDate.text.toString().trim() + " " + tvEventFromTime.text.toString().trim()
            )

            //insert data in database
            AsyncTask.execute {
                DatabaseClient.getInstance(context).appDatabase.eventMasterDao()
                    .insert(eventModel)
            }

            bottomSheetDialog.dismiss()

            Constant.allEvents = dataService!!.allEvent
        }
    }
}