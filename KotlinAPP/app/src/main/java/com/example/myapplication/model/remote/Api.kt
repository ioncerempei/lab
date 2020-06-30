package com.example.myapplication.model.remote

import com.example.myapplication.model.data.*
import com.google.gson.JsonElement
import retrofit2.http.*

interface Api{
    @GET("/userList")
    suspend fun getAccountList(
        @Header("X-Auth-Token") token: String
    ): AdminAccountList

    @POST("/authenticate")
    suspend fun login(
        @Body body: JsonElement
    ): Login

    @POST("/addUser")
    suspend fun addUser(
        @Header("X-Auth-Token") token: String,
        @Body body: JsonElement
    ): AddUser

    @POST("/addUserCar")
    suspend fun addUserCar(
        @Header("X-Auth-Token") token: String,
        @Body body: JsonElement
    ): AddUserCar

    @GET("/cars/{id}")
    suspend fun getPlateDetails(
        @Header("X-Auth-Token") token: String,
        @Path("id") id: String
    ): GetPlateDetails

    @GET("/cars")
    suspend fun getPlateDetailsTok(
        @Header("X-Auth-Token") token: String
    ): GetPlateDetails
}