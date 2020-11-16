package com.example.retrofitpractice.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpractice.R
import com.example.retrofitpractice.data.model.Search

class SearchAdapter(private val searchViewModel : SearchViewModel) : RecyclerView.Adapter<SearchAdapter.VHolder>(){
    var search = emptyList<Search>()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int)
            = VHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search_history, parent, false))

    override fun getItemCount() = search.size

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(search[position])
    }

    internal fun setData(search : List<Search>) {
        this.search = search
        notifyDataSetChanged()
    }

    inner class VHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val search : TextView = itemView.findViewById(R.id.tv_latest_search)
        fun bind(data : Search) {
            search.text = data.search
            search.setOnClickListener { searchViewModel.search.value = search.text.toString() }
            itemView.findViewById<ImageView>(R.id.img_delete).setOnClickListener { searchViewModel.delete(data) }
        }
    }
}