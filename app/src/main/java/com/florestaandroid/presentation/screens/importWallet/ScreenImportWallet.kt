package com.florestaandroid.presentation.screens.importWallet

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.florestaandroid.presentation.components.InputOutlined
import com.florestaandroid.presentation.components.Toolbar
import com.florestaandroid.presentation.components.VerticalSpacer
import com.florestaandroid.presentation.theme.FlorestaAndroidTheme
import com.florestaandroid.presentation.theme.spacing
import com.testfloresta.R

@Composable
fun ScreenImportWallet() {
    ScreenImportWallet(ImportWalletUIState()) // TODO INJECT
}

@Composable
private fun ScreenImportWallet(uiState: ImportWalletUIState) {
    FlorestaAndroidTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Toolbar(
                backgroundColor = MaterialTheme.colorScheme.background,
                leftIcon = R.drawable.ic_chevrom_left,
                leftIconColor = MaterialTheme.colorScheme.onSurface,
                titleColor = MaterialTheme.colorScheme.onSurface,
                title = stringResource(R.string.import_wallet),
            )

            VerticalSpacer(value = MaterialTheme.spacing.spacing32)

            Text(
                text = stringResource(R.string.choose_the_wallet_name),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.spacing16)
            )

            InputOutlined(
                value = uiState.walletName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.spacing16),
                onValueChange = {
                    //TODO IMPLEMENT
                })

            VerticalSpacer(value = MaterialTheme.spacing.spacing16)

            Text(
                text = stringResource(R.string.set_the_xpub_key),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.spacing16)
            )

            InputOutlined(
                value = uiState.xpubKey,
                singleLine = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = MaterialTheme.spacing.spacing16),
                onValueChange = {
                    //TODO IMPLEMENT
                })

            VerticalSpacer(value = MaterialTheme.spacing.spacing32)

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.spacing16),
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(R.string.import_),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            VerticalSpacer(value = MaterialTheme.spacing.spacing32)
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    name = "Light"
)
@Composable
private fun Preview1() {
    ScreenImportWallet(ImportWalletUIState())
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    name = "Dark"
)
@Composable
private fun Preview2() {
    ScreenImportWallet(
        ImportWalletUIState(
            walletName = "Wallet of satoshi",
            xpubKey = "12345-689cs-fs6d5f1-fsdfaserfsfdvf-6584-12345-689cs-fs6d5f1-fsdfaserfsfdvf-6584-12345-689cs-fs6d5f1-fsdfaserfsfdvf-6584",
        )
    )
}