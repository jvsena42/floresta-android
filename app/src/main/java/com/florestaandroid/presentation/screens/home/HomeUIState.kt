package com.florestaandroid.presentation.screens.home

import androidx.compose.runtime.Stable
import com.florestaandroid.data.model.TransactionDTO
import com.florestaandroid.data.model.WalletDTO

@Stable
data class HomeUIState(
    val wallets: List<WalletDTO> = emptyList(),
    val transactions: List<TransactionDTO> = emptyList()
)
