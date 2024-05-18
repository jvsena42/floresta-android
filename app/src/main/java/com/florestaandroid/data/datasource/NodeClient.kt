package com.florestaandroid.data.datasource

import android.app.Application
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.utils.io.writeStringUtf8
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uniffi.floresta.Config
import uniffi.floresta.Florestad
import uniffi.floresta.Network
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject
import kotlin.system.exitProcess

class NodeClient @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val application: Application
) {
    private fun setup() {
        val datadir = application.dataDir.toString()
        val config = Config(
            false,
            listOf(),
            true,
            Network.SIGNET,
            datadir,
            electrumAddress = "127.0.0.1:50001",
            logToFile = true
        )
        val daemon = Florestad.fromConfig(config)
        daemon.start()

        CoroutineScope(dispatcher).launch {
            val selectorManager = SelectorManager(Dispatchers.IO)
            val socket = aSocket(selectorManager).tcp().connect("127.0.0.1", 50001)

            val receiveChannel = socket.openReadChannel()
            val sendChannel = socket.openWriteChannel(autoFlush = true)

            launch(Dispatchers.IO) {
                socket.outputStream.write(
                    "{\"jsonrpc\":\"2.0\",\"method\":\"blockchain.headers.subscribe\",\"params\":[],\"id\":1}\n"
                        .toByteArray()
                )

                socket.openWriteChannel().write {
                    "{\"jsonrpc\":\"2.0\",\"method\":\"blockchain.headers.subscribe\",\"params\":[],\"id\":1}\n"
                }

                while (true) {
                    val greeting = receiveChannel.readUTF8Line()
                    if (greeting != null) {
                        println(greeting)
                    } else {
                        println("Server closed a connection")
                        socket.close()
                        selectorManager.close()
                        exitProcess(0)
                    }
                }
            }
        }
    }
}