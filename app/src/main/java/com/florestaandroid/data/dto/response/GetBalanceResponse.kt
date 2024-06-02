package com.florestaandroid.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetBalanceResponse(
    @SerialName("confirmed")
    val confirmed: Int,
    @SerialName("unconfirmed")
    val unconfirmed: Int
)