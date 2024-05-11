package com.florestaandroid.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.florestaandroid.presentation.theme.FlorestaAndroidTheme
import com.florestaandroid.presentation.theme.spacing
import com.testfloresta.R

private val cardHeight = 180.dp
@Composable
fun WalletCard(
    modifier: Modifier = Modifier,
    name: String,
    balance: String,
    latestTransaction: String,
    onClick: () -> Unit,

    ) {
    FlorestaAndroidTheme {
        Card(
            onClick = onClick,
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
            modifier = modifier
                .defaultMinSize(minHeight = cardHeight)
                .aspectRatio(ratio = 1.61f, matchHeightConstraintsFirst = true),
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.spacing.spacing16)
            ) {
                Text(
                    text = name, style = MaterialTheme.typography.titleSmall,
                )

                VerticalSpacer(value = MaterialTheme.spacing.spacing16)

                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = balance, style = MaterialTheme.typography.displayMedium,
                    )
                }

                VerticalSpacer(value = MaterialTheme.spacing.spacing16)

                Text(
                    text = stringResource(R.string.latest_transaction), style = MaterialTheme.typography.bodySmall,
                )

                VerticalSpacer(value = MaterialTheme.spacing.spacing8)

                Text(
                    text = latestTransaction, style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF2C322D,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    name = "Dark"
)
@Composable
private fun Preview1() {
    WalletCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        name = "Wallet 1",
        balance = "0.0000985",
        latestTransaction = "01/02/2009"
    ) {

    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    name = "Light"
)
@Composable
private fun Preview2() {
    WalletCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        name = "Wallet 1",
        balance = "0.0000985",
        latestTransaction = "01/02/2009 15:59"
    ) {

    }
}