package com.example.myapplication.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private static SharedPref instance;
    private static SharedPreferences sharedPreferences;

    private SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences("APP_SHARED_PREF", Context.MODE_PRIVATE);
    }

    public static void initInstance(Context context) {
        if (instance == null) {
            instance = new SharedPref(context);
        }
    }

    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public static String getToken() {
        String token = getString("X-Auth-Token");
        if (token == null)
            return "";
        return token;
    }

    public static void putBoolean(String key, Boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static Boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public static void putToken(String token) {
        putString("X-Auth-Token", token);
    }

}
