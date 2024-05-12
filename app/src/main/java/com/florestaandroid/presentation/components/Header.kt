package com.florestaandroid.presentation.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.florestaandroid.presentation.theme.FlorestaAndroidTheme
import com.florestaandroid.presentation.theme.spacing
import com.testfloresta.R

@Composable
fun Header(
    modifier: Modifier = Modifier,
    toolbarTitle: String? = null,
    title: String,
    bodyText: String,
    @DrawableRes imageCenter: Int? = null,
    @DrawableRes leftIcon: Int? = null,
    @DrawableRes rightIcon: Int? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    leftIconColor: Color = MaterialTheme.colorScheme.primary,
    rightIconColor: Color = MaterialTheme.colorScheme.primary,
    titleColor: Color = MaterialTheme.colorScheme.onSurface,
    onClickLeft: (() -> Unit)? = null,
    onClickRight: (() -> Unit)? = null,
) {
    FlorestaAndroidTheme {

        Column(modifier = modifier.background(backgroundColor)) {
            Toolbar(
                title = toolbarTitle,
                imageCenter = imageCenter,
                leftIcon = leftIcon,
                rightIcon = rightIcon,
                backgroundColor = backgroundColor,
                leftIconColor = leftIconColor,
                rightIconColor = rightIconColor,
                titleColor = titleColor,
                onClickLeft = onClickLeft,
                onClickRight = onClickRight
            )

            VerticalSpacer(value = MaterialTheme.spacing.spacing32)

            Text(
                text = title, style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.spacing16),
                color = MaterialTheme.colorScheme.primary
            )

            VerticalSpacer(value = MaterialTheme.spacing.spacing8)

            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.spacing16)
            ) {
                Text(
                    text = bodyText,
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(R.string.btc),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(
                        vertical = MaterialTheme.spacing.spacing8,
                        horizontal = MaterialTheme.spacing.spacing4
                    )
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
    FlorestaAndroidTheme {
        Header(
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            leftIcon = R.drawable.ic_chevrom_left,
            rightIcon = R.drawable.ic_create,
            title = "Wallet of satoshi", bodyText = "2.006987"
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    name = "Dark"
)
@Composable
private fun Preview2() {
    FlorestaAndroidTheme {
        Header(
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            leftIcon = R.drawable.ic_chevrom_left,
            rightIcon = R.drawable.ic_create,
            title = "Wallet of satoshi",
            bodyText = "2.006987"
        )
    }
}