package com.florestaandroid.data.datasource

import android.util.Log
import com.florestaandroid.data.dto.request.ElectrumRequest
import com.florestaandroid.data.dto.response.GetBalanceResponse
import com.florestaandroid.data.toHexString
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.utils.io.writeStringUtf8
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bitcoindevkit.Address
import org.bitcoindevkit.Network
import javax.inject.Inject

class NodeClient @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) {

    private val selectorManager = SelectorManager(dispatcher)



    @OptIn(ExperimentalStdlibApi::class)
    suspend fun getBalance()= callbackFlow<Result<String>> {
        Log.d(TAG, "getBalance: antes")
        val socket = aSocket(selectorManager).tcp().connect("127.0.0.1", 50001)

        try {
            Log.d(TAG, "getBalance: ")

            val receiveChannel = socket.openReadChannel()
            val sendChannel = socket.openWriteChannel(autoFlush = true)

            val address = Address("tb1qk5238eluqllq2wps67lkxme3x43wll4k282s8q", Network.TESTNET)
            val scriptPubKey = address.scriptPubkey().toBytes().toHexString()

            Log.d(TAG, "scriptPubKey: $scriptPubKey")

            val requestString = Json.encodeToString(ElectrumRequest(
                method = BlockchainMethods.GET_BALANCE.method,
                params = listOf(
                    scriptPubKey
                )
            ))

            Log.d(TAG, "requestString: $requestString")

            sendChannel.writeStringUtf8(requestString)

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