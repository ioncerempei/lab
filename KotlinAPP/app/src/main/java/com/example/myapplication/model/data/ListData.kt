package com.example.myapplication.model.data


import com.google.gson.annotations.SerializedName

data class ListData(
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: String
)