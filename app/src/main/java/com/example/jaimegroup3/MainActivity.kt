package com.example.jaimegroup3

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.jaimegroup3.adapter.MyAdapter
import com.google.android.material.tabs.TabLayout
import com.example.jaimegroup3.mqttmanager.Moving

// Srikanth, Nisha

class MainActivity : Moving() {

    private lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Gekko & Co"
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("Welcome"))
        tabLayout.addTab(tabLayout.newTab().setText("Calender"))
        tabLayout.addTab(tabLayout.newTab().setText("Go & Notify"))

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = MyAdapter(this, supportFragmentManager,
            tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }
}