package com.example.myapplication.admin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.data.UserCarData
import com.example.myapplication.model.remote.ApiImpl
import com.example.myapplication.admin.utils.Components
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPlateViewModel : ViewModel(){
    val userCarDetails = MutableLiveData<UserCarData>()

    fun addUserCar(userId: String, plateNumber: String, brand: String, color: String, details: String, token:String) {
        val key = arrayListOf<String>("userId", "number", "brand", "color", "details")
        val value = arrayListOf<String>(userId, plateNumber, brand, color, details)
        val json = Components().createJsonBody(key, value)

        viewModelScope.launch(Dispatchers.Default) {
            kotlin.runCatching {
                ApiImpl().addUserCar(token, json)
            }.onSuccess {
                if(it.success){
                    userCarDetails.postValue(it.data)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}