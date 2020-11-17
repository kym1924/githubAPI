package com.example.retrofitpractice.ui.repo

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpractice.data.model.ReposItems
import com.example.retrofitpractice.databinding.ItemSearchRepoBinding

object ReposBinding {
    @BindingAdapter("setRepoAdapter")
    @JvmStatic
    fun setRepoAdapter(recyclerView : RecyclerView, layout : Int) {
        val adapter = ReposAdapter<ItemSearchRepoBinding>(layout)
        recyclerView.adapter = adapter
    }

    @BindingAdapter("setRepoItem")
    @JvmStatic
    fun setRepoItem(recyclerView : RecyclerView, reposList : List<ReposItems>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as ReposAdapter<*>) { reposList?.let{ setData(it) } }
    }
}