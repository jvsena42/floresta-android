package com.florestaandroid.data.model

data class WalletDTO(
    val name: String,
    val balanceBTC: Double,
    val lastTransaction: String
)

fun WalletDTO.getSatoshiBalance(): Double = this.balanceBTC * SATOSHIS_IN_ONE_BITCOIN
