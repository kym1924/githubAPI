package com.retrofit.github.ui.user

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.retrofit.github.R
import com.retrofit.github.databinding.FragmentUserBinding
import com.retrofit.github.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {
    private val searchViewModel : SearchViewModel by activityViewModels()

    override fun onAttach(context : Context) {
        super.onAttach(context)
        Log.d("lifeCycle", "user : onAttach()")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("lifeCycle", "user : onCreateView()")
        val binding : FragmentUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        binding.apply{
            viewModel = searchViewModel
            lifecycleOwner = this@UserFragment
        }
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifeCycle", "user : onPause()")
        searchViewModel.resetKeyword()
    }
}