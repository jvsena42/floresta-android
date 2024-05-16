package com.florestaandroid.presentation.screens.main

import androidx.compose.runtime.Composable
import com.florestaandroid.presentation.screens.home.ScreenHome
import com.florestaandroid.presentation.screens.importWallet.ScreenImportWallet
import com.florestaandroid.presentation.screens.wallet.ScreenWallet

interface Destinations {
    val route: String
    val screen: @Composable () -> Unit
}

data object Home : Destinations {
    override val route: String = "home"
    override val screen: @Composable () -> Unit = { ScreenHome() }
}
data object ImportWallet : Destinations {
    override val route: String = "import_wallet"
    override val screen: @Composable () -> Unit = { ScreenImportWallet() }
}
data object Wallet : Destinations {
    override val route: String = "wallet"
    override val screen: @Composable () -> Unit = { ScreenWallet() }
}
