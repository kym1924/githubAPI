package com.example.retrofitpractice.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitpractice.R
import com.example.retrofitpractice.util.addMainPagerListener
import com.example.retrofitpractice.util.addTabLayoutListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vp_main.addMainPagerListener(tab_main)
        tab_main.addTabLayoutListener(vp_main)
        tab_main.addTab(tab_main.newTab().setText("Users"))
        tab_main.addTab(tab_main.newTab().setText("Repo"))
    }
}