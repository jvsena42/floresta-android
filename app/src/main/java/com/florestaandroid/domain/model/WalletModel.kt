package com.florestaandroid.domain.model

import java.util.UUID

data class WalletModel(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val balanceBTC: String,
    val lastTransaction: String
)