package com.example.myapplication.model.data


import com.google.gson.annotations.SerializedName

data class GetPlateDetails(
    @SerializedName("data")
    val `data`: List<PlateData>,
    @SerializedName("success")
    val success: Boolean
)