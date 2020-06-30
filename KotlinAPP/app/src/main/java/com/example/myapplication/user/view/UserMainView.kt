package com.example.myapplication.user.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ConfirmationWebSocket
import com.example.myapplication.R
import com.example.myapplication.ReservationActivity
import com.example.myapplication.UiPlacesWebSocket
import com.example.myapplication.common.SharedPref
import com.example.myapplication.user.viewmodel.UserMainViewModel
import kotlinx.android.synthetic.main.activity_user_main.*


class UserMainView : AppCompatActivity() {
    private var socket: ConfirmationWebSocket? = null
    private var uiplaces: UiPlacesWebSocket? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main)

        //observe view model
        val usermainviewmodel = ViewModelProvider(this).get(UserMainViewModel::class.java)
        usermainviewmodel.platesTokDetails.observe(this, Observer {
            val listItems = arrayOfNulls<String>(it.size)
            for (i in 0 until it.size) {
                val plate = it[i]
                listItems[i] = plate.number
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
            list.adapter = adapter
        })

        val token = SharedPref.getToken()

        usermainviewmodel.getPlatesTok(token)

        //click listeners
        val reserve = findViewById<Button>(R.id.reservePlace)
        reserve.setOnClickListener {
            val intent = Intent(this, ReservationActivity::class.java)
            startActivity(intent)
        }


        socket = ConfirmationWebSocket(this)
        uiplaces = UiPlacesWebSocket(this)
    }
}
