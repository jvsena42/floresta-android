package com.florestaandroid.data.datasource

import android.util.Log
import com.florestaandroid.data.dto.response.GetBalanceResponse
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.json.Json
import javax.inject.Inject

class NodeClient @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) {

    private val xPubMock = "xpub6EGBW6hrQMgfheuZonwAxaYdHMuWwf2uUF2TGCFdbxWN5JjuiZnFt93edgedS1XAqvSh5Ef9Dy28aCsMwGHZWtUhPKZsyNkNqru1yo7cBhL"

    val selectorManager = SelectorManager(dispatcher)
    val serverSocket = aSocket(selectorManager).tcp().bind("127.0.0.1", 50001)


    suspend fun getBalance(
        xPubKey: String = xPubMock // TODO REMOVE
    )= callbackFlow<Result<GetBalanceResponse>> {
        val socket = serverSocket.accept()

        try {
            val receiveChannel = socket.openReadChannel()

            receiveChannel.readUTF8Line(10000)?.let { json ->
                Json.decodeFromString<GetBalanceResponse>(json)
            }?.let { balance ->
                trySend(Result.success(balance))
            }
        }catch (e: Exception) {
            Log.d(TAG, "getBalance: ${e.stackTraceToString()}")
            trySend(Result.failure(e))
        }

       /* return flow<Result<GetBalanceResponse>> {
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
        }.flowOn(dispatcher)*/
    }

    companion object {
        private const val TAG = "NodeClient"
    }
}