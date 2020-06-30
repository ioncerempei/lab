package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.common.SharedPref
import com.example.myapplication.services.ServiceController
import com.example.myapplication.services.request.LoginRequest
import com.example.myapplication.services.response.LoginResponse
import com.example.myapplication.user.view.UserMainView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class UserActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        var code = findViewById<EditText>(R.id.code)
        val loginButton = findViewById<Button>(R.id.buttonLogin)

        loginButton.setOnClickListener {
            val data = LoginRequest()
            data.code = code.text.toString()
            ServiceController.login(data)
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }
    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: LoginResponse) {
        SharedPref.putToken(event.token)
        val intent = Intent(this, UserMainView::class.java)

        startActivity(intent)

    }



}
