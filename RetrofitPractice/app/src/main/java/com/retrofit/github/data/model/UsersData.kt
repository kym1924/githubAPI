package com.retrofit.github.data.model

import com.google.gson.annotations.SerializedName

data class UsersData (
    @SerializedName("total_count")
    val totalCount : Int,
    @SerializedName("incomplete_results")
    val incompleteResults : Boolean,
    val items : List<UsersItems>
)

data class UsersItems (
    val login : String,
    @SerializedName("html_url")
    val htmlUrl : String
)
