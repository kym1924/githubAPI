package com.example.retrofitpractice.ui.search

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.retrofitpractice.R
import com.example.retrofitpractice.databinding.ActivitySearchBinding
import com.example.retrofitpractice.ui.main.MainActivity
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