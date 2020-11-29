package com.retrofit.github.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.retrofit.github.BR
import com.retrofit.github.R
import com.retrofit.github.data.model.Search
import com.retrofit.github.ui.search.SearchDiffUtil
import com.retrofit.github.ui.search.SearchViewModel


class SearchAdapter<B : ViewDataBinding>(private val searchViewModel : SearchViewModel) : RecyclerView.Adapter<SearchAdapter<B>.VHolder<B>>(){
    var search = emptyList<Search>()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int)
            = VHolder<B>(LayoutInflater.from(parent.context).inflate(R.layout.item_search_history, parent, false))

    override fun getItemCount() = search.size

    override fun onBindViewHolder(holder: VHolder<B>, position: Int) {
        holder.bind(search[position])
    }

    fun setData(newList : List<Search>) {
        val diffUtilCallBack = SearchDiffUtil(search, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)
        this.search = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class VHolder<B : ViewDataBinding>(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val binding : B = DataBindingUtil.bind(itemView)!!

        fun bind(search : Search) {
            binding.setVariable(BR.search, search)
            binding.setVariable(BR.searchViewModel, searchViewModel)
        }
    }
}