package com.example.retrofitpractice.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpractice.data.api.SearchRepository
import com.example.retrofitpractice.data.model.Search
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val tabItems = "USER REPO"
    val search = MutableLiveData<String>("")
    val visibility = MutableLiveData<Boolean>(true)

    private lateinit var repository : SearchRepository
    lateinit var allData : LiveData<List<Search>>

    fun init(searchRepository : SearchRepository) {
        this.repository = searchRepository
        allData = repository.getAll()
    }

    fun resetSearch() {
        search.value=""
    }

    fun insert() = viewModelScope.launch(Dispatchers.IO) {
        val search = Search(search = search.value!!)
        repository.insert(search)
    }

    fun delete(search : Search) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(search)
    }

    fun setVisibility() {
        visibility.value = !visibility.value!!
    }
}
