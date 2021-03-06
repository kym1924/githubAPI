package com.retrofit.github.ui.repo

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.retrofit.github.data.model.ReposItems
import com.retrofit.github.ui.adapter.ResultAdapter

object ReposBinding {
    @BindingAdapter("setRepoItem")
    @JvmStatic
    fun setRepoItem(recyclerView: RecyclerView, reposList: List<ReposItems>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as ResultAdapter<*, ReposItems>) { reposList?.let { setData(it)} }
    }
}