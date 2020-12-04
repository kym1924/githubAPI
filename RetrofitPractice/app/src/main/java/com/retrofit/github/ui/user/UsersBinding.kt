package com.retrofit.github.ui.user

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.retrofit.github.data.model.UsersItems
import com.retrofit.github.ui.adapter.ResultAdapter

object UsersBinding {
    @BindingAdapter("setUserItem")
    @JvmStatic
    fun setUserItem(recyclerView : RecyclerView, usersList : List<UsersItems>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as ResultAdapter<*, UsersItems>) { usersList?.let{ setData(it) } }
    }
}