package com.example.retrofitpractice.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpractice.data.Search
import com.example.retrofitpractice.util.room.SearchDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val search = MutableLiveData<String>("")
    private lateinit var searchDao : SearchDao
    lateinit var allData : LiveData<List<Search>>

    fun resetSearch() { search.value="" }

    fun init(dao : SearchDao) {
        searchDao = dao
        allData = searchDao.getAll()
    }

    fun insert() = viewModelScope.launch(Dispatchers.IO) {
        val search = Search(idx = 0, search = search.value!!)
        searchDao.insert(search)
    }

    fun delete(search : Search) = viewModelScope.launch(Dispatchers.IO) {
        searchDao.delete(search)
    }
}