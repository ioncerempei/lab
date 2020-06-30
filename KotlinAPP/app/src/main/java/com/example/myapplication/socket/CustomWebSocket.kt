package com.example.myapplication.socket

import tech.gusavila92.websocketclient.WebSocketClient
import java.lang.Exception
import java.net.URI

open class CustomWebSocket(uri: URI) : WebSocketClient(uri) {
    override fun onOpen() {
    }

    override fun onTextReceived(message: String?) {
    }

    override fun onPongReceived(data: ByteArray?) {
    }

    override fun onException(e: Exception?) {
        println(e?.message)
    }

    override fun onCloseReceived() {
    }

    override fun onBinaryReceived(data: ByteArray?) {
    }

    override fun onPingReceived(data: ByteArray?) {
    }
}