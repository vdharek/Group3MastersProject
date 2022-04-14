package com.example.jaimegroup3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jaimegroup3.R
import com.example.jaimegroup3.database.EventModel
import com.example.jaimegroup3.mqttmanager.Moving

//Vivek, Srikanth

//display data.
class EventAdapter(
    private val mContext: Context,
    private val mList: List<EventModel>?
) :
    RecyclerView.Adapter<EventAdapter.MyViewHolder>() {

    var onItemClick: ((Int) -> Unit)? = null

    //created an sample of view for one particullar entry.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_event_row, parent, false)
        return MyViewHolder(itemView)
    }

    //to get to know how many times, our BindViewHolder will make replica.
    override fun getItemCount(): Int {
        //return number, that will be used for replica creation.
        return mList!!.size
    }

    //to set data in each replica based on position.
    //store data in each replica
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.text = mList!!.get(position).eventName.toString()
        holder.tvDescription.text = mList[position].eventDescription.toString()
        holder.tvDate.text = mList[position].eventDate.toString()
        holder.poiTitle.text = mList[position].poiName.toString()
        holder.tvTime.text =
            mList[position].eventFromTime.toString() + " - " + mList[position].eventToTime

        //item view of event form.
        holder.itemView.setOnClickListener {
            //give the position of replica.
            onItemClick!!.invoke(position)
        }
        // text to speech
        holder.ivMic.setOnClickListener {
            val moving = Moving()
            moving.textToSpeech("You have event named " + mList[position].eventName + " and event description is " + mList[position].eventDescription)
        }
    }

    //hold view form UI
    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvTitle = v.findViewById<AppCompatTextView>(R.id.tvTitle)
        var tvDescription = v.findViewById<AppCompatTextView>(R.id.tvDescription)
        var tvDate = v.findViewById<AppCompatTextView>(R.id.tvDate)
        var tvTime = v.findViewById<AppCompatTextView>(R.id.tvTime)
        var poiTitle = v.findViewById<AppCompatTextView>(R.id.poiTitle)
        var ivMic = v.findViewById<AppCompatImageView>(R.id.ivMic)
    }
}