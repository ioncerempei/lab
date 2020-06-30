package com.example.myapplication.admin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.data.UserData
import com.example.myapplication.model.remote.ApiImpl
import com.example.myapplication.admin.utils.Components
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddAccountViewModel : ViewModel() {
    val userDetails = MutableLiveData<UserData>()

    fun getAccountList(token:String, email: String) {
        val key = arrayListOf<String>("email")
        val value = arrayListOf<String>(email)
        val json = Components().createJsonBody(key, value)
        viewModelScope.launch(Dispatchers.Default) {
            kotlin.runCatching {
                ApiImpl().addUser(token, json)
            }.onSuccess {
                if(it.success){
                    userDetails.postValue(it.data)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}