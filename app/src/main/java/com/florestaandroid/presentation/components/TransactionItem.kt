package com.florestaandroid.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.florestaandroid.data.model.TransactionType
import com.florestaandroid.presentation.theme.FlorestaAndroidTheme

@Composable
fun TransactionItem(
    modifier: Modifier = Modifier,
    title: String,
    amount: String,
    date: String,
    type: TransactionType
) {
    FlorestaAndroidTheme {
        Column(modifier = modifier) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)

                Text(
                    text = amount,
                    style = MaterialTheme.typography.titleMedium,
                    color = type.toBalanceColor()
                )
            }
            Text(text = date, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurface)
        }
    }
}

@Composable
private fun TransactionType.toBalanceColor(): Color {
    return when (this) {
        TransactionType.SENT -> MaterialTheme.colorScheme.error
        TransactionType.RECEIVED -> MaterialTheme.colorScheme.primary
        TransactionType.WAITING -> MaterialTheme.colorScheme.onSurface
    }
}