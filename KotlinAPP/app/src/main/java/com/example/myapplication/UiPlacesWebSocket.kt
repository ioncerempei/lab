package com.example.myapplication

import android.app.Activity
import android.widget.TextView
import com.example.myapplication.common.SharedPref
import com.example.myapplication.consts.Consts
import com.example.myapplication.socket.CustomWebSocket
import tech.gusavila92.websocketclient.WebSocketClient
import java.net.URI
import java.net.URISyntaxException


class UiPlacesWebSocket(private val activity: Activity) {
    private var webSocketClient: WebSocketClient? = null
    private var spaces: TextView? = null;
    init {
        spaces = activity.findViewById(R.id.nrSpaces);
        try {
            val uri = URI(Consts.WS_UI_PLACES_URI)
            webSocketClient = object : CustomWebSocket(uri) {
                override fun onTextReceived(message: String?) {
                    showStatus(message!!)
                }
            }
            webSocketClient?.addHeader("X-Auth-Token", SharedPref.getToken())
            webSocketClient?.enableAutomaticReconnection(500)
            webSocketClient?.connect()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    private fun showStatus(message: String) {
        activity.runOnUiThread {
            val data = message.split(":");
            spaces!!.text = String.format("%s/%s\nSpaces", data[0], data[1])
        }
    }
}