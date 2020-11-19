package com.example.retrofitpractice.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "searchDb")
data class Search (
    @PrimaryKey
    val search : String,
    val createdAt : String = System.currentTimeMillis().toString()
) : Parcelable