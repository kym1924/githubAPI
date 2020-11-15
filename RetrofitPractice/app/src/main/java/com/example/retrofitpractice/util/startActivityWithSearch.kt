package com.example.retrofitpractice.util

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T : Activity> Context.startActivityWithSearch(search : String){
    val intent = Intent(this, T ::class.java)
    intent.putExtra("search", search)
    startActivity(intent)
}