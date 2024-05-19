package com.florestaandroid.data.request


import com.google.gson.annotations.SerializedName

data class ElectrumRequest(
//    @SerializedName("id")
//    val id: Int,
    @SerializedName("jsonrpc")
    val jsonrpc: String = "2.0",
    @SerializedName("method")
    val method: String,
    @SerializedName("params")
    val params: List<Any>
)