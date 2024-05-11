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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.florestaandroid.data.model.TransactionDTO
import com.florestaandroid.data.model.TransactionType
import com.florestaandroid.data.model.WalletDTO
import com.florestaandroid.presentation.components.TransactionItem
import com.florestaandroid.presentation.components.VerticalSpacer
import com.florestaandroid.presentation.components.WalletCard
import com.florestaandroid.presentation.theme.FlorestaAndroidTheme
import com.florestaandroid.presentation.theme.spacing
import com.testfloresta.R

@Composable
fun ScreenHome() {
    ScreenHome(uiState = HomeUIState()) //TODO GET STATE FROM VIEWMODEL
}

@Composable
private fun ScreenHome(
    uiState: HomeUIState
) {
    FlorestaAndroidTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = MaterialTheme.spacing.spacing16)
        ) {
            VerticalSpacer(value = MaterialTheme.spacing.spacing32)

            Text(
                text = stringResource(R.string.wallets),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            VerticalSpacer(value = MaterialTheme.spacing.spacing32)

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing8)
            ) {
                items(uiState.wallets) { wallet ->
                    WalletCard(
                        name = wallet.name,
                        balance = wallet.balanceBTC.toString(),
                        latestTransaction = wallet.lastTransaction,
                        onClick = {} //TODO IMPLEMENT
                    )
                }
            }

            VerticalSpacer(value = MaterialTheme.spacing.spacing32)

            Text(
                text = stringResource(R.string.all_transactions),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            VerticalSpacer(value = MaterialTheme.spacing.spacing32)

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
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
                WalletDTO(
                    name = "Wallet of satoshi",
                    balanceBTC = 2.3,
                    lastTransaction = "05/11/2024 15:59"
                ),
                WalletDTO(
                    name = "Wallet of satoshi",
                    balanceBTC = 2.3,
                    lastTransaction = "05/11/2024 15:59"
                ),
                WalletDTO(
                    name = "Wallet of satoshi",
                    balanceBTC = 2.3,
                    lastTransaction = "05/11/2024 15:59"
                ),
                WalletDTO(
                    name = "Wallet of satoshi",
                    balanceBTC = 2.3,
                    lastTransaction = "05/11/2024 15:59"
                ),
            ),
            transactions = listOf(
                TransactionDTO(
                    type = TransactionType.SENT,
                    amount = 0.896,
                    date = "05/11/2024 15:59"
                ),
                TransactionDTO(
                    type = TransactionType.WAITING,
                    amount = 0.896,
                    date = "05/11/2024 15:59"
                ),
                TransactionDTO(
                    type = TransactionType.RECEIVED,
                    amount = 0.896,
                    date = "05/11/2024 15:59"
                ),
                TransactionDTO(
                    type = TransactionType.SENT,
                    amount = 0.896,
                    date = "05/11/2024 15:59"
                ),
                TransactionDTO(
                    type = TransactionType.WAITING,
                    amount = 0.896,
                    date = "05/11/2024 15:59"
                ),
                TransactionDTO(
                    type = TransactionType.RECEIVED,
                    amount = 0.896,
                    date = "05/11/2024 15:59"
                ),
                TransactionDTO(
                    type = TransactionType.SENT,
                    amount = 0.896,
                    date = "05/11/2024 15:59"
                ),
                TransactionDTO(
                    type = TransactionType.SENT,
                    amount = 0.896,
                    date = "05/11/2024 15:59"
                ),
            )
        )
    )
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
                WalletDTO(
                    name = "Wallet of satoshi",
                    balanceBTC = 2.3,
                    lastTransaction = "05/11/2024 15:59"
                ),
                WalletDTO(
                    name = "Wallet of satoshi",
                    balanceBTC = 2.3,
                    lastTransaction = "05/11/2024 15:59"
                ),
                WalletDTO(
                    name = "Wallet of satoshi",
                    balanceBTC = 2.3,
                    lastTransaction = "05/11/2024 15:59"
                ),
                WalletDTO(
                    name = "Wallet of satoshi",
                    balanceBTC = 2.3,
                    lastTransaction = "05/11/2024 15:59"
                ),
            ),
            transactions = listOf(
                TransactionDTO(
                    type = TransactionType.SENT,
                    amount = 0.896,
                    date = "05/11/2024 15:59"
                ),
                TransactionDTO(
                    type = TransactionType.WAITING,
                    amount = 0.896,
                    date = "05/11/2024 15:59"
                ),
                TransactionDTO(
                    type = TransactionType.RECEIVED,
                    amount = 0.896,
                    date = "05/11/2024 15:59"
                ),
                TransactionDTO(
                    type = TransactionType.SENT,
                    amount = 0.896,
                    date = "05/11/2024 15:59"
                ),
                TransactionDTO(
                    type = TransactionType.SENT,
                    amount = 0.896,
                    date = "05/11/2024 15:59"
                ),
            )
        )
    )
}