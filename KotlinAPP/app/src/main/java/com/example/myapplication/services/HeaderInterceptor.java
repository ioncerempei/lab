package com.example.myapplication.services;

import com.example.myapplication.common.SharedPref;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(Chain chain)
            throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("X-Auth-Token", SharedPref.getToken())
                .build();
        return chain.proceed(request);
    }
}