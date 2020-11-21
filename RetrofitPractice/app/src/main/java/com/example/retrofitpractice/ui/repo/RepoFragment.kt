package com.example.retrofitpractice.ui.repo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.retrofitpractice.R
import com.example.retrofitpractice.data.api.RetrofitBuilder
import com.example.retrofitpractice.data.api.SearchRepository
import com.example.retrofitpractice.data.room.SearchDatabase
import com.example.retrofitpractice.databinding.FragmentRepoBinding
import com.example.retrofitpractice.ui.search.SearchViewModel

class RepoFragment : Fragment() {
    private val searchViewModel : SearchViewModel by activityViewModels()

    override fun onAttach(context : Context) {
        super.onAttach(context)
        Log.d("lifeCycle", "repo : onAttach()")
        val searchRepository = SearchRepository(RetrofitBuilder.service, SearchDatabase.getDatabase(context).searchDao())
        searchViewModel.init(searchRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("lifeCycle", "repo : onCreateView()")
        val binding : FragmentRepoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_repo, container, false)
        binding.apply{
            viewModel = searchViewModel
            lifecycleOwner = this@RepoFragment
        }
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifeCycle", "repo : onPause()")
        searchViewModel.clearUsers()
    }
}