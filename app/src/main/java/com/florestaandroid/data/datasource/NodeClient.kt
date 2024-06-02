package com.florestaandroid.data.datasource

import android.util.Log
import com.florestaandroid.data.dto.request.ElectrumRequest
import com.florestaandroid.data.dto.response.GetBalanceResponse
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.utils.io.writeStringUtf8
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class NodeClient @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) {

    private val xPubMock = "xpub6EGBW6hrQMgfheuZonwAxaYdHMuWwf2uUF2TGCFdbxWN5JjuiZnFt93edgedS1XAqvSh5Ef9Dy28aCsMwGHZWtUhPKZsyNkNqru1yo7cBhL"

    val selectorManager = SelectorManager(dispatcher)



    @OptIn(ExperimentalStdlibApi::class)
    suspend fun getBalance(
        xPubKey: String = xPubMock // TODO REMOVE
    )= callbackFlow<Result<String>> {
        Log.d(TAG, "getBalance: antes")
        val socket = aSocket(selectorManager).tcp().connect("127.0.0.1", 50001)

        try {
            Log.d(TAG, "getBalance: ")

            val receiveChannel = socket.openReadChannel()
            val sendChannel = socket.openWriteChannel(autoFlush = true)

            val request = ElectrumRequest(
                method = BlockchainMethods.GET_BALANCE.method,
                params = listOf(
                    xPubKey.toByteArray().toHexString()
                )
            )

            sendChannel.writeStringUtf8("{\"jsonrpc\":\"2.0\",\"method\":\"blockchain.headers.subscribe\",\"params\":[],\"id\":1}\n")

            receiveChannel.readUTF8Line(10000)?.let { json ->
                Log.d(TAG, "getBalance readUTF8Line: $json")
//                Json.decodeFromString<GetBalanceResponse>(json)
            }?.let { balance ->
//                trySend(Result.success(balance))
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
                            )
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