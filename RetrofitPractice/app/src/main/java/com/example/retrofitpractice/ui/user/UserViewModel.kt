package com.example.retrofitpractice.ui.user

import androidx.lifecycle.ViewModel
import com.example.retrofitpractice.data.api.SearchRepository

class UserViewModel : ViewModel() {

    private lateinit var repository : SearchRepository

    fun init(searchRepository : SearchRepository) {
        this.repository = searchRepository
    }
}