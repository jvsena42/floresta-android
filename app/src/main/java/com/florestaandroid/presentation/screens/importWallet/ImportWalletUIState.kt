package com.florestaandroid.presentation.screens.importWallet

import androidx.compose.runtime.Stable

@Stable
data class ImportWalletUIState(
    val walletName: String = "",
    val xpubKey: String = ""
)
