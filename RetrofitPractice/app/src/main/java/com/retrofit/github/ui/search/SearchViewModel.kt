package com.retrofit.github.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retrofit.github.R
import com.retrofit.github.data.api.search.SearchRepository
import com.retrofit.github.data.model.ReposItems
import com.retrofit.github.data.model.Search
import com.retrofit.github.data.model.UsersItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val searchRepository: SearchRepository
): ViewModel() {

    val tabItems = "USER REPO"
    val keyword = MutableLiveData<String>("")
    val visibility = MutableLiveData<Boolean>(true)

    val userLayout = MutableLiveData<Int>(R.layout.item_search_user)
    val repoLayout = MutableLiveData<Int>(R.layout.item_search_repo)

    lateinit var allSearch : LiveData<List<Search>>

    private val _allUser = MutableLiveData<List<UsersItems>>()
    val allUser : LiveData<List<UsersItems>>
        get() = _allUser

    private val _allRepo = MutableLiveData<List<ReposItems>>()
    val allRepo : LiveData<List<ReposItems>>
        get() = _allRepo

    private val _search = MutableLiveData<Boolean>()
    val search : LiveData<Boolean>
        get() = _search

    fun init() = viewModelScope.launch(Dispatchers.IO) {
        allSearch = searchRepository.getSearch()
    }

    fun resetKeyword() {
        keyword.value=""
    }

    fun insert() = viewModelScope.launch(Dispatchers.IO) {
        val search = Search(search = keyword.value!!)
        searchRepository.insert(search)
    }

    fun delete(search : Search) = viewModelScope.launch(Dispatchers.IO) {
        searchRepository.delete(search)
    }

    fun setVisibility() {
        visibility.value = !visibility.value!!
    }

    fun setSearchText(q : String) {
        keyword.value = q
    }

    fun requestUsers() = viewModelScope.launch(Dispatchers.IO) {
        _allUser.postValue(searchRepository.requestUsers(keyword.value!!).items)
    }

    fun requestRepos() = viewModelScope.launch(Dispatchers.IO) {
        _allRepo.postValue(searchRepository.requestRepos(keyword.value!!).items)
    }

    fun search() {
        _search.value = true
    }
}
