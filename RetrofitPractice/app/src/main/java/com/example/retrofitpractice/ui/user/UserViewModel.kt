package com.example.retrofitpractice.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpractice.data.api.SearchRepository
import com.example.retrofitpractice.data.model.UsersData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private lateinit var repository : SearchRepository

    private val _allData = MutableLiveData<UsersData>()
    val allData : LiveData<UsersData>
        get() = _allData

    fun init(searchRepository : SearchRepository) {
        this.repository = searchRepository
    }

    fun requestUsers(q : String) = viewModelScope.launch(Dispatchers.IO) {
        _allData.postValue(repository.requestUsers(q))
    }
}