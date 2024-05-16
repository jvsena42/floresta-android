package com.florestaandroid.presentation.screens.home

import androidx.compose.runtime.Stable
import com.florestaandroid.domain.model.TransactionModel
import com.florestaandroid.domain.model.WalletModel

@Stable
data class HomeUIState(
    val wallets: List<WalletModel> = emptyList(),
    val transactions: List<TransactionModel> = emptyList()
)
