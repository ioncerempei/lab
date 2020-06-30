package com.example.myapplication.admin.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.admin.viewmodel.AddAccountViewModel
import com.example.myapplication.admin.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_add_account.*

class AddAccountView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_account)

        //extract data from intent
        val token = if (savedInstanceState == null) {
            val extras = getIntent().extras
            extras?.getString("token")
        } else {
            savedInstanceState.getSerializable("token") as String?
        }

        //observe view model
        val addviewmodel = ViewModelProvider(this).get(AddAccountViewModel::class.java)
        addviewmodel.userDetails.observe(this, Observer {
            textViewCode.text = it.code
        })

        addAccountButton.setOnClickListener {
            val email = emailInput.text.toString()
            if (token != null) {
                addviewmodel.getAccountList(token, email)
            }
        }

    }
}