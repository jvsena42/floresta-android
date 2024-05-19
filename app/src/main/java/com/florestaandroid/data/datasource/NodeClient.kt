package com.florestaandroid.data.datasource

import android.app.Application
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import javax.inject.Inject

class NodeClient @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val application: Application
) {
    private lateinit var client: Socket
    private suspend fun connect() = withContext(dispatcher) {
        client = Socket("127.0.0.1", 50001)
    }

    suspend fun subscribeHeader() = withContext(dispatcher) {
        client.outputStream.write(
            """
                {
                    "jsonrpc": "2.0",
                    "method": "blockchain.headers.subscribe",
                    "params": [],
                    "id": 1
                }
                """.toByteArray()
        )

        val resultJson = BufferedReader(InputStreamReader(client.inputStream)).readLine()
    }

    private suspend fun disconnect() = withContext(dispatcher) {
        client.close()
    }
}