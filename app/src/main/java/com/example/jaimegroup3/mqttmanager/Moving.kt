package com.example.jaimegroup3.mqttmanager

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jaimegroup3.Constant
import com.example.jaimegroup3.R
import com.example.jaimegroup3.UIUpdaterInterface
import com.example.jaimegroup3.uiclasses.WelcomePage
import kotlinx.android.synthetic.main.welcome_page.*
import kotlinx.android.synthetic.main.welcome_page.view.*

//Srikanth, Nisha, Vivek
open class Moving : AppCompatActivity(), UIUpdaterInterface {

    var connection : Boolean = false

    companion object {
        var movingClass: Moving? = null
    }

    var mqttManager: MQTTManager? = null

    override fun resetUIWithConnection(status: Boolean) {

        if (status) {
            updateStatusViewWith("Connected")
        } else {
            updateStatusViewWith("Disconnected")
        }
    }

    override fun updateStatusViewWith(status: String) {

    }

    override fun update(message: String) {
        var text = status.text.toString()
        var newText = """
            $text
            $message
            """

        status.setText(newText)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        /*
            *on click of adapterclick event our robot is moving.
            * Do not remove
        */

        movingClass = this

        // Enable send button and message textfield only after connection
        resetUIWithConnection(false)
        Log.i("success", "connected")
    }

    var poiTopic = "zbos/slam/poi/moveto/uuid" //POI
    var messageTopic = "zbos/dialog/set/message" //message
    var dummyTopic = "topic/dummyTopic"
    var chargingTopic = "zbos/slam/charging/goto" //charging station
    var currentLocation = "zbos/slam/path/remaining/current"

    fun connect(view: View){

        if(ipAddress.text.isNullOrEmpty()){
            var hostname = ipAddress.text.toString().trim()
            var host = "tcp://" + hostname + ":1883"
            var connectionParams = MQTTConnectionParams("MQTTSample", host, chargingTopic, "", "")
            mqttManager = MQTTManager(connectionParams, applicationContext, this)
            mqttManager?.connect()
            connection = true
            statusUpdate(connection)
        }else{
            statusLabl.text = "Please enter corrcet IP address"
        }
    }

    fun statusUpdate(status: Boolean){
        if(status){
            statusLabl.text = "Connected"
        }else{
            statusLabl.text = "Disconnected"
        }
    }

    fun textToSpeech(message: String) {
        mqttManager?.subscribe(messageTopic)
        Log.i("success", "subscribed to " + messageTopic)
        mqttManager?.publish(messageTopic, message)
        Log.i("success", "spoken "+message)
    }

    fun POI1(view: View) {
        mqttManager?.subscribe(poiTopic)
        Log.i("success", "subscribed to " + poiTopic)
        mqttManager?.publish(poiTopic,
            "{\n" +
                    "\"mapName\": \"Master Project\",\n" +
                    "\"uuid\": \"" + Constant.POI1 + "\",\n" +
                    "\"speed\": 50\n" +
                    "}"
        )
    }

    fun POI2(view: View) {
        mqttManager?.subscribe(poiTopic)
        mqttManager?.publish(poiTopic,
            "{\n" +
                    "\"mapName\": \"Master Project\",\n" +
                    "\"uuid\": \"" + Constant.POI2 + "\",\n" +
                    "\"speed\": 50\n" +
                    "}"
        )
        //mqttManager?.subscribe(messageTopic)
        //mqttManager?.publish(messageTopic,"This is POI2")
    }

    fun POI3(view: View) {
        mqttManager?.subscribe(poiTopic)
        mqttManager?.publish(poiTopic,
            "{\n" +
                    "\"mapName\": \"Master Project\",\n" +
                    "\"uuid\": \"" + Constant.POI3 + "\",\n" +
                    "\"speed\": 50\n" +
                    "}"
        )
        //mqttManager?.subscribe(messageTopic)
        //mqttManager?.publish(messageTopic,"This is POI3")
    }

    fun POI4(view: View) {
        mqttManager?.subscribe(poiTopic)
        mqttManager?.publish(poiTopic,
            "{\n" +
                    "\"mapName\": \"Master Project\",\n" +
                    "\"uuid\": \"" + Constant.POI4 + "\",\n" +
                    "\"speed\": 50\n" +
                    "}"
        )
        //mqttManager?.subscribe(messageTopic)
        //mqttManager?.publish(messageTopic,"This is POI4")
    }

    fun POI5(view: View) {
        mqttManager?.subscribe(poiTopic)
        mqttManager?.publish(poiTopic,
            "{\n" +
                    "\"mapName\": \"Master Project\",\n" +
                    "\"uuid\": \"" + Constant.POI5 + "\",\n" +
                    "\"speed\": 50\n" +
                    "}"
        )
        //mqttManager?.subscribe(messageTopic)
        //mqttManager?.publish(messageTopic,"This is POI5")
    }

    fun POI6(view: View) {
        mqttManager?.subscribe(poiTopic)
        mqttManager?.publish(poiTopic,
            "{\n" +
                    "\"mapName\": \"Master Project\",\n" +
                    "\"uuid\": \"" + Constant.POI6 + "\",\n" +
                    "\"speed\": 50\n" +
                    "}"
        )
        //mqttManager?.subscribe(messageTopic)
        //mqttManager?.publish(messageTopic,"This is POI6")
    }

    fun POI7(view: View) {
        mqttManager?.subscribe(poiTopic)
        mqttManager?.publish(poiTopic,
            "{\n" +
                    "\"mapName\": \"Master Project\",\n" +
                    "\"uuid\": \"" + Constant.POI7 + "\",\n" +
                    "\"speed\": 50\n" +
                    "}"
        )
        //mqttManager?.subscribe(messageTopic)
        //mqttManager?.publish(messageTopic,"This is POI7")
    }

    val list: MutableList<String> = ArrayList()
    override fun payloadMes(message: String){
        list.add(message)
        status.setText(list.toString())
    }

    fun POI8(view: View) {
        mqttManager?.subscribe(poiTopic)
        mqttManager?.publish(poiTopic,
            "{\n" +
                    "\"mapName\": \"Master Project\",\n" +
                    "\"uuid\": \"" + Constant.POI8 + "\",\n" +
                    "\"speed\": 50\n" +
                    "}"
        )
        mqttManager?.subscribe(currentLocation)
        //mqttManager?.subscribe(messageTopic)
        //mqttManager?.publish(messageTopic,"Hello POI8")
    }

    fun mute(view: View){
        mqttManager?.subscribe("zbos/audio/volume/set")
        Log.i("success", "subscribed to " + "zbos/audio/volume/set")
        mqttManager?.publish("zbos/audio/volume/set","5")
    }

    fun vol50(view: View){
        mqttManager?.subscribe("zbos/audio/volume/set")
        Log.i("success", "subscribed to " + "zbos/audio/volume/set")
        mqttManager?.publish("zbos/audio/volume/set","50")
    }

    fun vol70(view: View){
        mqttManager?.subscribe("zbos/audio/volume/set")
        Log.i("success", "subscribed to " + "zbos/audio/volume/set")
        mqttManager?.publish("zbos/audio/volume/set","70")
    }

    fun greet(message: String){
        mqttManager?.subscribe(messageTopic)
        mqttManager?.publish(messageTopic,"Hello POI8 " + message)
    }

    //An instance of location of POIs.
    open fun redirectToPOI(poiId: String) {
        mqttManager?.subscribe(poiTopic)
        mqttManager?.publish(poiTopic,
            "{\n" +
                    "\"mapName\": \"Master Project\",\n" +
                    "\"uuid\": \"" + poiId + "\",\n" +
                    "\"speed\": 50\n" +
                    "}"
        )
    }

    fun chargingStation(view: View) {
        mqttManager?.subscribe(chargingTopic)
        Log.i("success", "subscribed to " + chargingTopic)
        mqttManager?.publish(chargingTopic,
            "{\n" +
                    "\"mapName\": \"Master Project\",\n" +
                    "\"uuid\": \"2b2c9353-d851-452a-9091-46749ad216d1\",\n" +
                    "\"speed\": 50\n" +
                    "}"
        )
        mqttManager?.subscribe(messageTopic)
        mqttManager?.publish(messageTopic,"Going to charging station")
    }
}
