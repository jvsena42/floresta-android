package com.florestaandroid.domain.model

import com.florestaandroid.data.model.TransactionType

data class TransactionModel(
    val type: TransactionType,
    val date: String,
    val amount: String
)
