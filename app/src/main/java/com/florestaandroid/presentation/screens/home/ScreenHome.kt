package com.florestaandroid.presentation.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.florestaandroid.data.model.TransactionType
import com.florestaandroid.domain.model.TransactionModel
import com.florestaandroid.domain.model.WalletModel
import com.florestaandroid.presentation.components.HorizontalSpacer
import com.florestaandroid.presentation.components.TransactionItem
import com.florestaandroid.presentation.components.VerticalSpacer
import com.florestaandroid.presentation.components.WalletCard
import com.florestaandroid.presentation.theme.FlorestaAndroidTheme
import com.florestaandroid.presentation.theme.spacing
import com.testfloresta.R

@Composable
fun ScreenHome(
    navigateWalletScreen: (String) -> Unit,
    navigateImportWallet: () -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    ScreenHome(uiState = uiState, viewModel::onAction)

    LaunchedEffect(key1 = Unit) {
        viewModel.onAction(HomeViewModel.HomeActions.Setup)
        viewModel.screenEventFlow.collect { event ->
            when(event) {
                is HomeViewModel.HomeEvents.NavigateWalletScreen -> navigateWalletScreen(event.walletId)
                HomeViewModel.HomeEvents.NavigateImportWallet -> navigateImportWallet()
            }
        }
    }
}

@Composable
private fun ScreenHome(
    uiState: HomeUIState,
    onAction: (HomeViewModel.HomeActions) -> Unit
) {
    FlorestaAndroidTheme {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { onAction(HomeViewModel.HomeActions.OnClickAddWallet) }) {
                    Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = stringResource(
                        R.string.create_a_new_wallet
                    )
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(padding)
            ) {
                VerticalSpacer(value = MaterialTheme.spacing.spacing32)

                Text(
                    text = stringResource(R.string.wallets),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.spacing16)
                )

                VerticalSpacer(value = MaterialTheme.spacing.spacing32)

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing8)
                ) {
                    itemsIndexed(uiState.wallets) { index, wallet ->
                        if (index == 0) {
                            HorizontalSpacer(value = MaterialTheme.spacing.spacing16)
                        }

                        WalletCard(
                            name = wallet.name,
                            balance = wallet.balanceBTC,
                            latestTransaction = wallet.lastTransaction,
                            onClick = { onAction(HomeViewModel.HomeActions.OnCLickWallet(wallet)) }
                        )
                    }
                }

                VerticalSpacer(value = MaterialTheme.spacing.spacing32)

                Text(
                    text = stringResource(R.string.all_transactions),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.spacing16)
                )

                VerticalSpacer(value = MaterialTheme.spacing.spacing32)

                LazyColumn(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.spacing16),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing16)
                ) {

                    items(uiState.transactions) { transaction ->
                        TransactionItem(
                            title = transaction.type.toTitle(),
                            amount = transaction.amount.toString(),
                            date = transaction.date,
                            type = transaction.type
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(top = MaterialTheme.spacing.spacing16),
                        )
                    }
                }
            }
        }
    }

}

@Composable
private fun TransactionType.toTitle() : String{
    return when(this) {
        TransactionType.SENT -> stringResource(R.string.sent)
        TransactionType.RECEIVED -> stringResource(R.string.received)
        TransactionType.WAITING -> stringResource(R.string.waiting)
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    name = "Light", device = "id:pixel_2"
)
@Composable
private fun Preview1() {
    ScreenHome(
        uiState = HomeUIState(
            wallets = listOf(
                WalletModel(
                    name = "Wallet of satoshi",
                    balanceBTC = "2.00003",
                    lastTransaction = "05/11/2024 15:59"
                ),
                WalletModel(
                    name = "Wallet of satoshi",
                    balanceBTC = "2.00003",
                    lastTransaction = "05/11/2024 15:59"
                ),
                WalletModel(
                    name = "Wallet of satoshi",
                    balanceBTC = "2.00003",
                    lastTransaction = "05/11/2024 15:59"
                ),
                WalletModel(
                    name = "Wallet of satoshi",
                    balanceBTC = "2.00003",
                    lastTransaction = "05/11/2024 15:59"
                ),
            ),
            transactions = listOf(
                TransactionModel(
                    type = TransactionType.SENT,
                    amount = "0.896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.WAITING,
                    amount = "0.896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.RECEIVED,
                    amount = "0.896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.SENT,
                    amount = "0.896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.WAITING,
                    amount = "0.896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.RECEIVED,
                    amount = "0.896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.SENT,
                    amount = "0.896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.SENT,
                    amount = "0.896",
                    date = "05/11/2024 15:59"
                ),
            )
        )
    ){ }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "id:pixel_2",
    name = "Dark"
)
@Composable
private fun Preview2() {
    ScreenHome(
        uiState = HomeUIState(
            wallets = listOf(
                WalletModel(
                    name = "Wallet of satoshi",
                    balanceBTC = "2.00003",
                    lastTransaction = "05/11/2024 15:59"
                ),
                WalletModel(
                    name = "Wallet of satoshi",
                    balanceBTC = "2.00003",
                    lastTransaction = "05/11/2024 15:59"
                ),
                WalletModel(
                    name = "Wallet of satoshi",
                    balanceBTC = "2.00003",
                    lastTransaction = "05/11/2024 15:59"
                ),
                WalletModel(
                    name = "Wallet of satoshi",
                    balanceBTC = "2.00003",
                    lastTransaction = "05/11/2024 15:59"
                ),
            ),
            transactions = listOf(
                TransactionModel(
                    type = TransactionType.SENT,
                    amount = "0.896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.WAITING,
                    amount = "0.896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.RECEIVED,
                    amount = "0.896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.SENT,
                    amount = "0.896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.SENT,
                    amount = "0.896",
                    date = "05/11/2024 15:59"
                ),
            )
        )
    ) {

    }
}