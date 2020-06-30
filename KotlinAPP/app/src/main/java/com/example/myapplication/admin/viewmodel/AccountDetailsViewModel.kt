package com.example.myapplication.admin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.data.PlateData
import com.example.myapplication.model.remote.ApiImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountDetailsViewModel : ViewModel() {
    val plateDetails = MutableLiveData<List<PlateData>>()

    fun getPlateDetails(token:String, id: String) {
        viewModelScope.launch(Dispatchers.Default) {
            kotlin.runCatching {
                ApiImpl().getPlateDetails(token, id)
            }.onSuccess {
                if(it.success){
                    plateDetails.postValue(it.data)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}