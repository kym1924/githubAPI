package com.example.retrofitpractice.data.model

data class UsersData (
    val total_count : Int,
    val incomplete_results : Boolean,
    val items : List<Items>
)

data class Items (
    val login : String,
    val html_url : String
)
