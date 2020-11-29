package com.retrofit.github.ui.search

import androidx.recyclerview.widget.DiffUtil
import com.retrofit.github.data.model.Search

class SearchDiffUtil (private val oldList : List<Search>, private val newList : List<Search>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].search == newList[newItemPosition].search
}