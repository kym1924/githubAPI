package com.example.retrofitpractice.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpractice.R
import com.example.retrofitpractice.data.api.SearchRepository
import com.example.retrofitpractice.data.model.ReposItems
import com.example.retrofitpractice.data.model.Search
import com.example.retrofitpractice.data.model.UsersItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val tabItems = "USER REPO"
    val search = MutableLiveData<String>("")
    val visibility = MutableLiveData<Boolean>(true)

    val userLayout = MutableLiveData<Int>(R.layout.item_search_user)

    private lateinit var repository : SearchRepository
    lateinit var allSearch : LiveData<List<Search>>

    private val _allUser = MutableLiveData<List<UsersItems>>()
    val allUser : LiveData<List<UsersItems>>
        get() = _allUser

    private val _allRepo = MutableLiveData<List<ReposItems>>()
    val allRepo : LiveData<List<ReposItems>>
        get() = _allRepo

    fun init(searchRepository : SearchRepository) {
        this.repository = searchRepository
        allSearch = repository.getAll()
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

    fun requestUsers(q : String) = viewModelScope.launch(Dispatchers.IO) {
        _allUser.postValue(repository.requestUsers(q).items)
    }

    fun requestRepos(q : String) = viewModelScope.launch(Dispatchers.IO) {
        _allRepo.postValue(repository.requestRepos(q).items)
    }

    fun setSearchText(q : String) {
        search.value = q
    }
}
