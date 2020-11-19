package com.example.retrofitpractice.ui.repo

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpractice.data.model.ReposItems
import com.example.retrofitpractice.databinding.ItemSearchRepoBinding
import com.example.retrofitpractice.ui.adapter.ResultAdapter

object ReposBinding {
    @BindingAdapter("setRepoItem")
    @JvmStatic
    fun setRepoItem(recyclerView: RecyclerView, reposList: List<ReposItems>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as ResultAdapter<*, ReposItems>) { reposList?.let { setData(it)} }
    }

    @BindingAdapter("setRepoAdapter")
    @JvmStatic
    fun setRepoAdapter(recyclerView: RecyclerView, layout: Int) {
        val adapter = ResultAdapter<ItemSearchRepoBinding, ReposItems>(layout)
        recyclerView.adapter = adapter
    }
}