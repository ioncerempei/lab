package com.example.myapplication.admin.view

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.admin.viewmodel.AccountDetailsViewModel
import com.example.myapplication.admin.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_account_details.*
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_account_details.listView

class AccountDetailsView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_details)

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
        val accountdetailsviewmodel = ViewModelProvider(this).get(AccountDetailsViewModel::class.java)
        accountdetailsviewmodel.plateDetails.observe(this, Observer {
            val listItems = arrayOfNulls<String>(it.size)
            for (i in 0 until it.size) {
                val account = it[i]
                listItems[i] = account.number
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
            listView.adapter = adapter
        })

        if (userId != null && token != null) {
            accountdetailsviewmodel.getPlateDetails(token, userId)
        }

        add_plate.setOnClickListener {
            val intent = Intent(this, AddPlateView::class.java)
            intent.putExtra("token", token)
            intent.putExtra("user_id", userId)
            finish()
            startActivity(intent)
        }
    }
}