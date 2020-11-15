package com.example.retrofitpractice.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpractice.R
import com.example.retrofitpractice.data.Search
import com.example.retrofitpractice.ui.main.MainActivity
import com.example.retrofitpractice.util.startActivityWithSearch

class SearchAdapter(private val context : Context, private val searchViewModel : SearchViewModel) : RecyclerView.Adapter<SearchAdapter.VHolder>(){
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
        val search : TextView = itemView.findViewById(R.id.tv_latest_search)

        fun bind(data : Search) {
            search.text = data.search
            search.setOnClickListener { context.startActivityWithSearch<MainActivity>(data.search) }

            itemView.findViewById<ImageView>(R.id.img_delete).setOnClickListener { searchViewModel.delete(data) }
        }
    }
}