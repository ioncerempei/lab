package com.example.myapplication.model.data


import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("data")
    val `data`: Token,
    @SerializedName("success")
    val success: Boolean
)