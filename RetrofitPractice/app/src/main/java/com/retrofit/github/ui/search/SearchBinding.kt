package com.retrofit.github.ui.search

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.retrofit.github.data.model.Search
import com.retrofit.github.ui.adapter.SearchAdapter

object SearchBinding {
    @BindingAdapter("visibleResetButton")
    @JvmStatic
    fun visibleResetButton(view : View, search : String) {
        view.visibility = if (search.isEmpty()) View.INVISIBLE else View.VISIBLE
    }

    @BindingAdapter("setListItem")
    @JvmStatic
    fun setListItem(recyclerView : RecyclerView, searchList : List<Search>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as SearchAdapter<*>) { searchList?.let{ submitList(it) } }
    }
}