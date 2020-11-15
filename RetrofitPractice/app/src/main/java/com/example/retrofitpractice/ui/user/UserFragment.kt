package com.example.retrofitpractice.ui.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.retrofitpractice.R
import com.example.retrofitpractice.data.api.RetrofitBuilder
import com.example.retrofitpractice.data.api.SearchRepository
import com.example.retrofitpractice.databinding.FragmentUserBinding
import com.example.retrofitpractice.util.room.SearchDatabase

class UserFragment : Fragment() {
    private val userViewModel : UserViewModel by activityViewModels()
    lateinit var binding : FragmentUserBinding

    override fun onAttach(context : Context){
        super.onAttach(context)

        val searchRepository = SearchRepository(RetrofitBuilder.service, SearchDatabase.getDatabase(context).searchDao())
        userViewModel.init(searchRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        binding.userViewModel = userViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}