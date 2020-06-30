package com.example.myapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.common.SharedPref
import com.example.myapplication.services.ServiceController
import com.example.myapplication.services.request.PlateRequest
import com.example.myapplication.services.response.LoginResponse
import com.example.myapplication.user.view.UserMainView
import kotlinx.android.synthetic.main.activity_reservation.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class ReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)


        var reserve = findViewById<EditText>(R.id.selectNrReserve)
        val reserveButton = findViewById<Button>(R.id.buttonMaxTime)

        buttonReservation.setOnClickListener {
            val data = PlateRequest()
            data.plate = reserve.text.toString()
            ServiceController.plate(data)
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
