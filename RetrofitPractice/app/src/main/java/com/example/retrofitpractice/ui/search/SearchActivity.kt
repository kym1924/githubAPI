package com.example.retrofitpractice.ui.search

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.retrofitpractice.R
import com.example.retrofitpractice.data.api.RetrofitBuilder
import com.example.retrofitpractice.data.api.SearchRepository
import com.example.retrofitpractice.databinding.ActivitySearchBinding
import com.example.retrofitpractice.util.room.SearchDatabase

class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding
    private val searchViewModel : SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.apply{
            viewModel = searchViewModel
            fragmentManager = supportFragmentManager
            viewPager = binding.vpMain
            tabLayout = binding.tabMain
            lifecycleOwner = this@SearchActivity
        }
        val searchRepository = SearchRepository(RetrofitBuilder.service, SearchDatabase.getDatabase(this).searchDao())
        searchViewModel.init(searchRepository)
    }

    override fun onResume() {
        super.onResume()
        binding.etSearch.setOnFocusChangeListener{ _, hasFocus ->
            if(hasFocus) binding.searchLayout.visibility = View.VISIBLE
            else binding.searchLayout.visibility = View.INVISIBLE
        }
    }
}