package com.example.retrofitpractice.ui.user

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpractice.data.model.UsersItems
import com.example.retrofitpractice.databinding.ItemSearchUserBinding

object UsersBinding {
    @BindingAdapter("setUserAdapter")
    @JvmStatic
    fun setUserAdapter(recyclerView : RecyclerView, layout : Int) {
        val adapter = UsersAdapter<ItemSearchUserBinding>(layout)
        recyclerView.adapter = adapter
    }

    @BindingAdapter("setUserItem")
    @JvmStatic
    fun setUserItem(recyclerView : RecyclerView, usersList : List<UsersItems>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as UsersAdapter<*>) { usersList?.let{ setData(it) } }
    }
}