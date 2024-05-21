package com.florestaandroid.data.datasource

import com.florestaandroid.data.dto.request.ElectrumRequest
import com.florestaandroid.data.dto.response.GetBalanceResponse
import com.florestaandroid.data.toHexString
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import javax.inject.Inject

class NodeClient @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) {

    private val xPubMock = "xpub6EGBW6hrQMgfheuZonwAxaYdHMuWwf2uUF2TGCFdbxWN5JjuiZnFt93edgedS1XAqvSh5Ef9Dy28aCsMwGHZWtUhPKZsyNkNqru1yo7cBhL"


    private suspend fun createSocket(): Socket = withContext(dispatcher) {
        return@withContext Socket("127.0.0.1", 50001)
    }

    suspend fun getBalance(
        xPubKey: String = xPubMock // TODO REMOVE
    ): Flow<Result<GetBalanceResponse>> {
        val client = createSocket()
        return flow<Result<GetBalanceResponse>> {
            try {
                client.outputStream.write(
                    Gson().toJson(
                        ElectrumRequest(
                            method = BlockchainMethods.GET_BALANCE.method,
                            params = listOf(
                                xPubKey.toByteArray().toHexString()
                            ) //TODO CHECK IF THIS IS RIGHT
                        )
                    ).toByteArray()
                )

                val text = BufferedReader(InputStreamReader(client.inputStream)).readLine()
                val result = Gson().fromJson(text, GetBalanceResponse::class.java)
                emit(Result.success(result))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }.onCompletion {
            client.close()
        }.flowOn(dispatcher)
    }
}