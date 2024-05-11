package com.florestaandroid.data.model

import java.util.Date

data class Transaction(
    val type: TransactionType,
    val date: Date,
    val amount: Double
)
