package com.example.retrofitpractice.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpractice.BR

class ResultAdapter<B : ViewDataBinding, I>(private val layout : Int) : RecyclerView.Adapter<ResultAdapter<B, I>.VHolder<B, I>>(){
    private var users = emptyList<I>()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int)
            = VHolder<B, I>(LayoutInflater.from(parent.context).inflate(layout, parent, false))

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: VHolder<B, I>, position: Int) {
        holder.bind(users[position])
    }

    internal fun setData(users : List<I>) {
        this.users = users
        notifyDataSetChanged()
    }

    inner class VHolder<B : ViewDataBinding, I>(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val binding : B = DataBindingUtil.bind(itemView)!!

        fun bind(result : I) {
            binding.setVariable(BR.result, result)
        }
    }
}