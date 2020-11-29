package com.retrofit.github.data.model

import com.google.gson.annotations.SerializedName

data class UsersData (
    val total_count : Int,
    val incomplete_results : Boolean,
    val items : List<UsersItems>
)

data class UsersItems (
    val login : String,
    @SerializedName("html_url")
    val htmlUrl : String
)
