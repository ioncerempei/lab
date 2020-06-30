package com.example.myapplication.user.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.data.PlateData
import com.example.myapplication.model.remote.ApiImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserMainViewModel : ViewModel() {
    val platesTokDetails = MutableLiveData<List<PlateData>>()

    fun getPlatesTok(token: String) {
        viewModelScope.launch(Dispatchers.Default) {
            kotlin.runCatching {
                ApiImpl().getPlateDetailsTok(token)
            }.onSuccess {
                if(it.success){
                    platesTokDetails.postValue(it.data)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}