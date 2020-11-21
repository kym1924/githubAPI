package com.retrofit.github.data.model

data class UsersData (
    val total_count : Int,
    val incomplete_results : Boolean,
    val items : List<UsersItems>
)

data class UsersItems (
    val login : String,
    val html_url : String
)
