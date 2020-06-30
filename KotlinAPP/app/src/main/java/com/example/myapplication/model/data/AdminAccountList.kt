package com.example.myapplication.model.data


import com.google.gson.annotations.SerializedName

data class AdminAccountList(
    @SerializedName("data")
    val `data`: List<ListData>,
    @SerializedName("success")
    val success: Boolean
)