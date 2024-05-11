package com.florestaandroid.data.model

import java.util.Date

data class WalletModel(
    val name: String,
    val balanceBTC: Double,
    val lastTransaction: Date
)

fun WalletModel.getSatoshiBalance(): Double = this.balanceBTC * SATOSHIS_IN_ONE_BITCOIN
