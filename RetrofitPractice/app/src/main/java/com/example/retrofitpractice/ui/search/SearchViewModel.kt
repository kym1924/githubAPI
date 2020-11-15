package com.example.retrofitpractice.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    val search = MutableLiveData<String>("")

    fun resetSearch() { search.value="" }
}