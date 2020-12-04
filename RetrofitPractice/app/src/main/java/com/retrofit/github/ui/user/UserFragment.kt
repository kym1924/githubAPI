package com.retrofit.github.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.retrofit.github.R
import com.retrofit.github.data.model.ReposItems
import com.retrofit.github.databinding.FragmentUserBinding
import com.retrofit.github.databinding.ItemSearchRepoBinding
import com.retrofit.github.ui.adapter.ResultAdapter
import com.retrofit.github.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {
    private val searchViewModel : SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        binding.apply{
            viewModel = searchViewModel
            lifecycleOwner = this@UserFragment
        }

        setUserAdapter(binding)

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        searchViewModel.resetKeyword()
    }

    private fun setUserAdapter(binding : FragmentUserBinding) {
        val adapter = ResultAdapter<ItemSearchRepoBinding, ReposItems>(R.layout.item_search_user)
        binding.rvUserResult.adapter = adapter
    }
}