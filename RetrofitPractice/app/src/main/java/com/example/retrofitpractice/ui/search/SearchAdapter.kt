package com.example.retrofitpractice.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpractice.BR
import com.example.retrofitpractice.R
import com.example.retrofitpractice.data.model.Search


class SearchAdapter<B : ViewDataBinding>(private val searchViewModel : SearchViewModel) : RecyclerView.Adapter<SearchAdapter<B>.VHolder<B>>(){
    var search = emptyList<Search>()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int)
            = VHolder<B>(LayoutInflater.from(parent.context).inflate(R.layout.item_search_history, parent, false))

    override fun getItemCount() = search.size

    override fun onBindViewHolder(holder: VHolder<B>, position: Int) {
        holder.bind(search[position])
    }

    internal fun setData(search : List<Search>) {
        this.search = search
        notifyDataSetChanged()
    }

    inner class VHolder<B : ViewDataBinding>(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val binding : B = DataBindingUtil.bind(itemView)!!

        fun bind(search : Search) {
            binding.setVariable(BR.search, search)
            binding.setVariable(BR.searchViewModel, searchViewModel)
        }
    }
}