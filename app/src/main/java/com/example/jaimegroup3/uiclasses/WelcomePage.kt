package com.example.jaimegroup3.uiclasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.jaimegroup3.R
import com.example.jaimegroup3.UIUpdaterInterface
import com.example.jaimegroup3.mqttmanager.MQTTConnectionParams
import com.example.jaimegroup3.mqttmanager.MQTTManager
import com.example.jaimegroup3.mqttmanager.Moving
import kotlinx.android.synthetic.main.welcome_page.*

class WelcomePage: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.welcome_page, container, false)
    }
}
