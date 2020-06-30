package com.example.myapplication.model.data


import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("token")
    val token: String
)