package com.retrofit.github.ui.search

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.retrofit.github.R
import com.retrofit.github.databinding.ActivitySearchBinding
import com.retrofit.github.databinding.ItemSearchHistoryBinding
import com.retrofit.github.ui.adapter.SearchAdapter
import com.retrofit.github.ui.adapter.SearchPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding
    private val searchViewModel : SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.apply{
            viewModel = searchViewModel
            lifecycleOwner = this@SearchActivity
        }

        init()
        setObserver()
        setFocusChangeListener(binding)
        setEditorActionListener(binding)
        addTabLayoutListener(binding)
        addMainPagerListener(binding)
        setAdapter(binding)
    }

    private fun init() {
        searchViewModel.getAllSearch()
    }

    private fun setObserver() {
        searchViewModel.search.observe(this, Observer { search ->
            search?.let { if (binding.tabMain.selectedTabPosition == 0) searchViewModel.requestUsers() else searchViewModel.requestRepos() }
        })
    }

    private fun setFocusChangeListener(binding : ActivitySearchBinding) {
        binding.etSearch.setOnFocusChangeListener { _, _ ->
            searchViewModel.setVisibility()
        }
    }

    private fun setEditorActionListener(binding : ActivitySearchBinding) {
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

    private fun addTabLayoutListener(binding : ActivitySearchBinding){
        binding.tabMain.addTab(binding.tabMain.newTab().setText("USER"))
        binding.tabMain.addTab(binding.tabMain.newTab().setText("REPO"))

        binding.tabMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) { binding.vpMain.currentItem = tab!!.position }
        })
    }

    private fun addMainPagerListener(binding : ActivitySearchBinding){
        binding.vpMain.adapter = SearchPagerAdapter(supportFragmentManager)

        binding.vpMain.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) { binding.tabMain.getTabAt(position)?.select() }
        })
    }

    private fun setAdapter(binding : ActivitySearchBinding) {
        val adapter = SearchAdapter<ItemSearchHistoryBinding>(searchViewModel)
        binding.rvSearchHistory.adapter = adapter
    }
}