package com.florestaandroid.data.datasource

import android.app.Application
import com.florestaandroid.data.request.ElectrumRequest
import com.google.gson.Gson
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
            Gson().toJson(
                ElectrumRequest(
                    method = BlockchainMethods.SUBSCRIBE_HEADER.method,
                    params = listOf()
                )
            ).toByteArray()
        )
    }

    suspend fun getBalance(
        xPubKey: String
    ) = withContext(dispatcher) {
        client.outputStream.write(
            Gson().toJson(
                ElectrumRequest(
                    method = BlockchainMethods.GET_BALANCE.method,
                    params = listOf(xPubKey)
                )
            ).toByteArray()
        )
    }

    private suspend fun disconnect() = withContext(dispatcher) {
        client.close()
    }
}