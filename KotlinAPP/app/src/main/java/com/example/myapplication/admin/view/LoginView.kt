package com.example.myapplication.admin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.admin.viewmodel.LoginViewModel
import com.example.myapplication.common.SharedPref
import com.example.myapplication.services.ServiceController
import com.example.myapplication.services.request.LoginRequest
import com.example.myapplication.services.response.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LoginView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //observe view model
        val loginviewmodel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginviewmodel.loginDetails.observe(this, Observer {
            val token = it.token
            val intent = Intent(this, MainView::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        })

        buttonLog.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPass.text.toString()
            loginviewmodel.login(email, password)
        }
    }
}

