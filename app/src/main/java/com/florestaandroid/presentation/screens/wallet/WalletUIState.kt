package com.florestaandroid.presentation.screens.wallet

import androidx.compose.runtime.Stable
import com.florestaandroid.data.model.TransactionDTO
import com.florestaandroid.data.model.WalletDTO

@Stable
data class WalletUIState(
    val name: String = "",
    val balanceBTC: String = "",
    val transactions: List<TransactionDTO> = emptyList()
)
