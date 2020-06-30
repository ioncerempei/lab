package com.example.myapplication.admin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ConfirmationWebSocket
import com.example.myapplication.R
import com.example.myapplication.admin.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_admin.*

class MainView : AppCompatActivity() {
    private var socket: ConfirmationWebSocket? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        socket = ConfirmationWebSocket(this)

        //extract data from intent
        val token = if (savedInstanceState == null) {
            val extras = getIntent().extras
            extras?.getString("token")
        } else {
            savedInstanceState.getSerializable("token") as String?
        }

        //observe view model
        val mainviewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainviewmodel.accountList.observe(this, Observer {
            val listItems = arrayOfNulls<Int>(it.size)
            for (i in 0 until it.size) {
                val account = it[i]
                listItems[i] = account.id
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
            listView.adapter = adapter

        })

        listView.setOnItemClickListener { parent, view, position, id ->

            val element = listView.getItemAtPosition(position) // The item that was clicked
            val intent = Intent(this, AccountDetailsView::class.java)
            intent.putExtra("token", token)
            intent.putExtra("user_id", element.toString())
            finish()
            startActivity(intent)
        }


        if (token != null) {
            mainviewmodel.getAccountList(token)
        }

        //click listeners
        addAccount.setOnClickListener {
            val intent = Intent(this, AddAccountView::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }




    }


}
