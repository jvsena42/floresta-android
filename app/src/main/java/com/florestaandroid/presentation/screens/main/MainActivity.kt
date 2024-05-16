package com.florestaandroid.presentation.screens.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.florestaandroid.presentation.screens.home.ScreenHome
import com.florestaandroid.presentation.screens.importWallet.ScreenImportWallet
import com.florestaandroid.presentation.screens.wallet.ScreenWallet
import com.florestaandroid.presentation.theme.FlorestaAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            FlorestaAndroidTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    FlorestaNavHost(
                        navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    private fun FlorestaNavHost(
        navController: NavHostController,
        modifier: Modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = Home.route,
            modifier = modifier
        ) {
            composable(route = Home.route) {
                ScreenHome(
                    navigateWalletScreen = { walletId ->
                        navController.navigateSingleTopTo("${Wallet.route}/$walletId")
                    },
                    navigateImportWallet = {
                        navController.navigateSingleTopTo(ImportWallet.route)
                    }
                )
            }
            composable(
                route = ImportWallet.route,
            ) {
                ScreenImportWallet(
                    onBackPressed = {
                        navController.popBackStack()
                    }
                )
            }
            composable(
                route = Wallet.routeWithArgs,
                arguments = Wallet.arguments
            ) { navBackStackEntry ->
                val walletIdArg = navBackStackEntry.arguments?.getString(Wallet.walletIdArg)
                ScreenWallet(
                    walletId = walletIdArg,
                    onBackPressed = { navController.popBackStack() }
                )
            }
        }
    }
}

private fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(
        this@navigateSingleTopTo.graph.findStartDestination().id
    ) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = false
}