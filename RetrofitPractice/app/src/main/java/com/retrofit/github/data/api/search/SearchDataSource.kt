package com.retrofit.github.data.api.search

import androidx.lifecycle.LiveData
import com.retrofit.github.data.model.ReposData
import com.retrofit.github.data.model.Search
import com.retrofit.github.data.model.UsersData

interface SearchDataSource {

    suspend fun getSearch() : LiveData<List<Search>>

    suspend fun insert(search : Search)

    suspend fun delete(search : Search)

    suspend fun requestUsers(q : String) : UsersData

    suspend fun requestRepos(q : String) : ReposData
}