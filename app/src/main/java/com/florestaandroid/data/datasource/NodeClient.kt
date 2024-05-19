package com.florestaandroid.data.datasource

import android.app.Application
import com.florestaandroid.data.dto.request.ElectrumRequest
import com.florestaandroid.data.dto.response.GetBalanceResponse
import com.florestaandroid.data.toHexString
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import javax.inject.Inject

class NodeClient @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
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
    ): Flow<GetBalanceResponse> = flow {
        withContext(dispatcher) {
            client.outputStream.write(
                Gson().toJson(
                    ElectrumRequest(
                        method = BlockchainMethods.GET_BALANCE.method,
                        params = listOf(xPubKey.toByteArray().toHexString()) //TODO CHECK IF THIS IS RIGHT
                    )
                ).toByteArray()
            )

            val text = BufferedReader(InputStreamReader(client.inputStream)).readLine()
            val result = Gson().fromJson(text, GetBalanceResponse::class.java)
            emit(result)
        }
    }

    private suspend fun disconnect() = withContext(dispatcher) {
        client.close()
    }
}