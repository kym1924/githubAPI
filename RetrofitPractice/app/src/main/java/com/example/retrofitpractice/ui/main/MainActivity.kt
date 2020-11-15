package com.example.retrofitpractice.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.retrofitpractice.R
import com.example.retrofitpractice.databinding.ActivityMainBinding
import com.example.retrofitpractice.ui.search.SearchActivity
import com.example.retrofitpractice.util.addMainPagerListener
import com.example.retrofitpractice.util.addTabLayoutListener
import com.example.retrofitpractice.util.startActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply{
            fragmentManager = supportFragmentManager
            viewPager = binding.vpMain
            tabLayout = binding.tabMain
            tabItems = "USER REPO"
            lifecycleOwner = this@MainActivity
        }
    }

    override fun onResume() {
        super.onResume()
        binding.etSearch.clearFocus()
        binding.etSearch.setOnFocusChangeListener{ _, hasFocus ->
            if(hasFocus) {
                startActivity<SearchActivity>()
                finish()
            }
        }
    }
}