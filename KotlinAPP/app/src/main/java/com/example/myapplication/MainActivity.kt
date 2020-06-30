package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.admin.view.LoginView
import com.example.myapplication.common.SharedPref


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SharedPref.initInstance(this)


        val admin = findViewById<Button>(R.id.admin1)
        admin.setOnClickListener {
            val intent = Intent(this, LoginView::class.java)

            startActivity(intent)
        }

        val user = findViewById<Button>(R.id.user)
        user.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)

            startActivity(intent)
        }

    }

    fun onClick(view: View) {}
}
