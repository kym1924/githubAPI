package com.example.retrofitpractice.ui.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpractice.R
import com.example.retrofitpractice.data.model.UsersItems

class UserAdapter : RecyclerView.Adapter<UserAdapter.VHolder>(){
    private var users = emptyList<UsersItems>()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int)
            = VHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search_user, parent, false))

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(users[position])
    }

    internal fun setData(users: List<UsersItems>) {
        this.users = users
        notifyDataSetChanged()
    }

    inner class VHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data : UsersItems) {
            itemView.findViewById<TextView>(R.id.tv_id).text = data.login
            itemView.findViewById<TextView>(R.id.tv_url).text = data.html_url
        }
    }
}