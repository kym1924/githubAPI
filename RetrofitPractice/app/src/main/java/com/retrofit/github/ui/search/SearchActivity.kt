package com.retrofit.github.ui.search

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.retrofit.github.R
import com.retrofit.github.data.api.RetrofitBuilder
import com.retrofit.github.data.api.SearchRepository
import com.retrofit.github.data.room.SearchDatabase
import com.retrofit.github.databinding.ActivitySearchBinding

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

    override fun onStart() {
        super.onStart()
        searchViewModel.search.observe(this, Observer { search ->
            search?.let { if(binding.tabMain.selectedTabPosition==0) searchViewModel.requestUsers() else searchViewModel.requestRepos() }
        })
    }

    override fun onResume() {
        super.onResume()
        binding.etSearch.setOnFocusChangeListener{ _, _ -> searchViewModel.setVisibility() }

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if(binding.etSearch.text.toString().isNotEmpty()) {
                    searchViewModel.insert()
                    searchViewModel.search()
                    binding.etSearch.clearFocus()
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }
}