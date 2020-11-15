package com.example.retrofitpractice.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val baseURL = "https://api.github.com/search/"

    private var retrofit = Retrofit.Builder().baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service : RequestInterface = retrofit.create(RequestInterface::class.java)
}