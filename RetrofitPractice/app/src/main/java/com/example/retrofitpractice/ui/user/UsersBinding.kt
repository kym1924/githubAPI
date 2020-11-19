package com.example.retrofitpractice.ui.user

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpractice.data.model.UsersItems
import com.example.retrofitpractice.databinding.ItemSearchUserBinding
import com.example.retrofitpractice.ui.adapter.ResultAdapter

object UsersBinding {
    @BindingAdapter("setUserItem")
    @JvmStatic
    fun setUserItem(recyclerView : RecyclerView, usersList : List<UsersItems>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as ResultAdapter<*, UsersItems>) { usersList?.let{ setData(it) } }
    }

    @BindingAdapter("setUserAdapter")
    @JvmStatic
    fun setUserAdapter(recyclerView: RecyclerView, layout: Int) {
        val adapter = ResultAdapter<ItemSearchUserBinding, UsersItems>(layout)
        recyclerView.adapter = adapter
    }
}