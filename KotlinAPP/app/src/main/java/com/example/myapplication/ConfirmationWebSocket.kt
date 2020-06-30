package com.example.myapplication

import android.app.Activity
import android.app.AlertDialog
import com.example.myapplication.common.SharedPref
import com.example.myapplication.consts.Consts
import com.example.myapplication.socket.CustomWebSocket
import tech.gusavila92.websocketclient.WebSocketClient
import java.net.URI
import java.net.URISyntaxException


class ConfirmationWebSocket(private val activity: Activity) {
    private var webSocketClient: WebSocketClient? = null

    init {
        try {
            val uri = URI(Consts.WS_CONFIRMATION_URI)
            webSocketClient = object : CustomWebSocket(uri) {
                override fun onTextReceived(message: String?) {
                    println("text received")
                    println(message)
                    showDialog(message!!)
                }
            }
            webSocketClient?.addHeader("X-Auth-Token", SharedPref.getToken())
            webSocketClient?.enableAutomaticReconnection(500)
            webSocketClient?.connect()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    private fun showDialog(message: String) {
        activity.runOnUiThread {
            AlertDialog.Builder(activity)
                .setTitle("Title")
                .setMessage("Is the car at the barrier?")
                .setPositiveButton("Yes") { dialog, whichButton ->

                    webSocketClient?.send(message)

                }
                .setNegativeButton("No") { dialog, whichButton ->

                    webSocketClient?.send("NO")

                }.show()
        }
    }
}