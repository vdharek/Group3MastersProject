package com.example.jaimegroup3.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.jaimegroup3.uiclasses.WelcomePage
import com.example.jaimegroup3.uiclasses.calenderEntry
import com.example.jaimegroup3.uiclasses.driveAround

// Srikanth, Nisha

//UI classes for fragments
@Suppress("DEPRECATION")
class MyAdapter (
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                WelcomePage()
            }
            1 -> {
                calenderEntry()
            }
            2 -> {
                driveAround()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}