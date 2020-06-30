package com.example.myapplication.admin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.data.Token
import com.example.myapplication.model.remote.ApiImpl
import com.example.myapplication.admin.utils.Components
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    val loginDetails = MutableLiveData<Token>()

    fun login(email: String, password: String) {
        val key = arrayListOf<String>("email", "password")
        val value = arrayListOf<String>(email, password)
        val json = Components().createJsonBody(key, value)

        viewModelScope.launch(Dispatchers.Default) {
            kotlin.runCatching {
                ApiImpl().login(json)
            }.onSuccess {
                if(it.success){
                    loginDetails.postValue(it.data)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}