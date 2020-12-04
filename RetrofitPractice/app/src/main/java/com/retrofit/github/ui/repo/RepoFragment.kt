package com.retrofit.github.ui.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.retrofit.github.R
import com.retrofit.github.data.model.ReposItems
import com.retrofit.github.databinding.FragmentRepoBinding
import com.retrofit.github.databinding.ItemSearchRepoBinding
import com.retrofit.github.ui.adapter.ResultAdapter
import com.retrofit.github.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoFragment : Fragment() {
    private val searchViewModel : SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentRepoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_repo, container, false)
        binding.apply{
            viewModel = searchViewModel
            lifecycleOwner = this@RepoFragment
        }

        setRepoAdapter(binding)

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        searchViewModel.resetKeyword()
    }

    private fun setRepoAdapter(binding : FragmentRepoBinding) {
        val adapter = ResultAdapter<ItemSearchRepoBinding, ReposItems>(R.layout.item_search_repo)
        binding.rvRepoResult.adapter = adapter
    }
}