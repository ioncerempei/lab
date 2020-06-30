package com.example.myapplication.services;

import com.example.myapplication.services.request.ListPlateRequest;
import com.example.myapplication.services.request.LoginRequest;
import com.example.myapplication.services.request.PlateRequest;
import com.example.myapplication.services.response.BaseResponse;
import com.example.myapplication.services.response.ListPlateResponse;
import com.example.myapplication.services.response.LoginResponse;
import com.example.myapplication.services.response.PlateResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @POST("/authenticate")
    Call<BaseResponse<LoginResponse>> login(@Body LoginRequest r);

    // get request
//get plates

    @GET("/")
    Call<BaseResponse<PlateResponse>> plate(@Body PlateRequest r);

   @GET("/")
   Call<BaseResponse<ListPlateResponse>> listPlate(@Body ListPlateRequest r);


}
