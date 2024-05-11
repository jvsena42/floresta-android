package com.florestaandroid.data.model

import java.util.Date

data class TransactionDTO(
    val type: TransactionType,
    val date: String,
    val amount: Double
)
