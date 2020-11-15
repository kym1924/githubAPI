package com.example.retrofitpractice.ui.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.example.retrofitpractice.R
import com.example.retrofitpractice.ui.main.MainActivity
import com.example.retrofitpractice.util.startActivity
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }

    override fun onResume() {
        super.onResume()
        et_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                startActivity<MainActivity>()
                finish()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }
}