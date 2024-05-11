package com.florestaandroid.presentation.screens.home

import androidx.compose.runtime.Stable
import com.florestaandroid.data.model.Transaction
import com.florestaandroid.data.model.WalletModel

@Stable
data class HomeUIState(
    val wallets: List<WalletModel> = emptyList(),
    val transactions: List<Transaction> = emptyList()
)
