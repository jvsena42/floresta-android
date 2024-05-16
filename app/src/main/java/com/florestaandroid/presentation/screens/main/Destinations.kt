package com.florestaandroid.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.florestaandroid.presentation.screens.home.ScreenHome
import com.florestaandroid.presentation.screens.importWallet.ScreenImportWallet
import com.florestaandroid.presentation.screens.wallet.ScreenWallet

interface Destinations {
    val route: String
}

data object Home : Destinations {
    override val route: String = "home"
}

data object ImportWallet : Destinations {
    override val route: String = "import_wallet"
    const val walletIdArg = "wallet_id"
    val routeWithArgs = "$route/{$walletIdArg}"
    val arguments = listOf(
        navArgument(walletIdArg) { type = NavType.StringType }
    )
}

data object Wallet : Destinations {
    override val route: String = "wallet"
    const val walletIdArg = "wallet_id"
    val routeWithArgs = "${route}/{$walletIdArg}"
    val arguments = listOf(
        navArgument(walletIdArg) { type = NavType.StringType }
    )
}
