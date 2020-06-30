package com.example.myapplication.admin.utils

import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.json.JSONObject

class Components : AppCompatActivity() {
    fun createJsonBody(key: ArrayList<String>, value: ArrayList<String>): JsonElement {
        val nrEl = key.size
        val bodyObj = JSONObject()
        for (i in 0 until nrEl){
            bodyObj.put(key[i], value[i])
        }
        val jsonParser = JsonParser()
        return jsonParser.parse(bodyObj.toString())
    }
}