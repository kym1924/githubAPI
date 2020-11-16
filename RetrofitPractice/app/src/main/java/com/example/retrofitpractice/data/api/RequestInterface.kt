package com.example.retrofitpractice.data.api

import com.example.retrofitpractice.data.model.ReposData
import com.example.retrofitpractice.data.model.UsersData
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestInterface {
    @GET("users")
    suspend fun requestUsers(
        @Query("q") q : String
    ) : UsersData

    @GET("repositories")
    suspend fun requestRepos(
        @Query("q") q : String
    ) : ReposData
}