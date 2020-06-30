package com.example.myapplication.admin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.admin.viewmodel.AddPlateViewModel
import kotlinx.android.synthetic.main.activity_add_plate.*

class AddPlateView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plate)

        //extract data from intent
        val token = if (savedInstanceState == null) {
            val extras = getIntent().extras
            extras?.getString("token")
        } else {
            savedInstanceState.getSerializable("token") as String?
        }

        val userId = if (savedInstanceState == null) {
            val extras = getIntent().extras
            extras?.getString("user_id")
        } else {
            savedInstanceState.getSerializable("user_id") as String?
        }

        //observe view model
        val addplateviewmodel = ViewModelProvider(this).get(AddPlateViewModel::class.java)
        addplateviewmodel.userCarDetails.observe(this, Observer {
            this.finish()
        })

        sendConfirm.setOnClickListener {
            val plateNumber = basicNr.text.toString()
            val brand = details.text.toString()
            val color = details1.text.toString()
            val details = details2.text.toString()

            if (token != null && userId != null) {
                addplateviewmodel.addUserCar(
                    userId,
                    plateNumber,
                    brand,
                    color,
                    details,
                    token
                )
            }
        }
    }
}
