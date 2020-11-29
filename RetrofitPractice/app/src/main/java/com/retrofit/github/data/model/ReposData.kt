package com.retrofit.github.data.model

import com.google.gson.annotations.SerializedName

data class ReposData (
    @SerializedName("total_count")
    val totalCount : Int,
    @SerializedName("incomplete_results")
    val incompleteResults : Boolean,
    val items : List<ReposItems>
)

data class ReposItems (
    @SerializedName("full_name")
    val fullName : String,
    @SerializedName("html_url")
    val htmlUrl : String
)
