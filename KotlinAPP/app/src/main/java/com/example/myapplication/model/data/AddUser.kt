package com.example.myapplication.model.data


import com.google.gson.annotations.SerializedName

data class AddUser(
    @SerializedName("data")
    val `data`: UserData,
    @SerializedName("success")
    val success: Boolean
)