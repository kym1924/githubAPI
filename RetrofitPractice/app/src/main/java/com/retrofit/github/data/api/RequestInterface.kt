package com.retrofit.github.data.api

import com.retrofit.github.data.model.ReposData
import com.retrofit.github.data.model.UsersData
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