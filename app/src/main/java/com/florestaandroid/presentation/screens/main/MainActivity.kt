package com.florestaandroid.presentation.screens.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
                    NavHost(
                        navController = navController,
                        startDestination = Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Home.route) {
                            ScreenHome(
                                onClickSelectWallet = {
                                    navController.navigateSingleTopTo(Wallet.route)
                                }
                            )
                        }
                        composable(route = ImportWallet.route) {
                            ScreenImportWallet()
                        }
                        composable(route = Wallet.route) {
                            ScreenWallet()
                        }
                    }
                }
            }
        }
    }
}

private fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = false
    }