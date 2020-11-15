package com.example.retrofitpractice.ui.search

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.retrofitpractice.R
import com.example.retrofitpractice.databinding.ActivitySearchBinding
import com.example.retrofitpractice.ui.main.MainActivity
import com.example.retrofitpractice.util.room.SearchDatabase
import com.example.retrofitpractice.util.startActivity
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding
    private val searchViewModel : SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.searchViewModel = searchViewModel
        binding.lifecycleOwner = this

        val searchDao = SearchDatabase.getDatabase(this).searchDao()
        searchViewModel.init(searchDao)
    }

    override fun onStart() {
        super.onStart()
        val adapter = SearchAdapter(this, searchViewModel)

        searchViewModel.allData.observe(this, Observer { allData ->
            allData?.let {
                binding.rvSearch.adapter = adapter
                adapter.setData(allData)
            }
        })
    }
    override fun onResume() {
        super.onResume()
        et_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchViewModel.insert()
                startActivity<MainActivity>()
                finish()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }
}