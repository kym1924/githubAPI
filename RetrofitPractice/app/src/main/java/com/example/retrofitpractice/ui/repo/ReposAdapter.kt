package com.example.retrofitpractice.ui.repo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpractice.BR
import com.example.retrofitpractice.data.model.ReposItems

class ReposAdapter<B : ViewDataBinding>(private val layout : Int) : RecyclerView.Adapter<ReposAdapter<B>.VHolder<B>>(){
    private var users = emptyList<ReposItems>()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int)
            = VHolder<B>(LayoutInflater.from(parent.context).inflate(layout, parent, false))

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: VHolder<B>, position: Int) {
        holder.bind(users[position])
    }

    internal fun setData(users : List<ReposItems>) {
        this.users = users
        notifyDataSetChanged()
    }

    inner class VHolder<B : ViewDataBinding>(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val binding : B = DataBindingUtil.bind(itemView)!!

        fun bind(repo : ReposItems) {
            binding.setVariable(BR.repo, repo)
        }
    }
}