package com.florestaandroid.data.dto.response


import com.google.gson.annotations.SerializedName

data class GetBalanceResponse(
    @SerializedName("confirmed")
    val confirmed: Int,
    @SerializedName("unconfirmed")
    val unconfirmed: Int
)