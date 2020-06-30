package com.example.myapplication.services;

import androidx.annotation.NonNull;

import com.example.myapplication.services.request.ListPlateRequest;
import com.example.myapplication.services.request.LoginRequest;
import com.example.myapplication.services.request.PlateRequest;
import com.example.myapplication.services.response.BaseResponse;
import com.example.myapplication.services.response.ListPlateResponse;
import com.example.myapplication.services.response.LoginResponse;
import com.example.myapplication.services.response.PlateResponse;

import org.greenrobot.eventbus.EventBus;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.myapplication.consts.Consts.HOST;

public class ServiceController {
    private static ServiceController instance;
    private static DataService service;

    private ServiceController() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Retrofit api = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                        new OkHttpClient.Builder()
                                .addInterceptor(new HeaderInterceptor())
                                .addInterceptor(interceptor)
                                .build()
                )
                .build();
        service = api.create(DataService.class);
    }

    private static ServiceController getInstance() {
        if (instance == null) {
            instance = new ServiceController();
        }
        return instance;
    }

    private static DataService getService() {
        return getInstance().service;
    }

    private static <T> void enqueue(Call<BaseResponse<T>> request) {
        request.enqueue(new Callback<BaseResponse<T>>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<T>> call, @NonNull Response<BaseResponse<T>> response) {
                switch (response.code()) {
                    case 200:
                        BaseResponse<T> body = response.body();
                        if (body != null) {
                            EventBus.getDefault().post(body.getData());
                        } else {
//                            EventBus.getDefault().post(new FailureEvent());
                        }
                        break;
//                    case 401:
//                        EventBus.getDefault().post(new SessionInvalidEvent());
//                        break;
//                    case 404:
//                        EventBus.getDefault().post(new NoNetworkEvent());
//                        break;
//                    default:
//                        EventBus.getDefault().post(new FailureEvent());
                }
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse<T>> call, @NonNull Throwable t) {
            }
        });
    }

    public static void login(LoginRequest data) {
        Call<BaseResponse<LoginResponse>> request = getService().login(data);
        enqueue(request);
    }


    public static void plate(PlateRequest data) {
        Call<BaseResponse<PlateResponse>> request = getService().plate(data);
       enqueue(request);
    }

    public static void listPlate(ListPlateRequest data) {
        Call<BaseResponse<ListPlateResponse>> request = getService().listPlate(data);
        enqueue(request);
    }

}
