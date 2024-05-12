package com.florestaandroid.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.florestaandroid.presentation.theme.FlorestaAndroidTheme
import com.florestaandroid.presentation.theme.spacing

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String? = null,
    @DrawableRes imageCenter: Int? = null,
    @DrawableRes leftIcon: Int? = null,
    @DrawableRes rightIcon: Int? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    leftIconColor: Color = MaterialTheme.colorScheme.onSurface,
    rightIconColor: Color = MaterialTheme.colorScheme.onSurface,
    titleColor: Color = MaterialTheme.colorScheme.onSurface,
    onClickLeft: (() -> Unit)? = null,
    onClickRight: (() -> Unit)? = null,
) {
    FlorestaAndroidTheme {
        Box(
            modifier = modifier
                .height(48.dp)
                .background(backgroundColor)
                .fillMaxWidth()
        ) {
            leftIcon?.let {
                Icon(
                    painter = painterResource(it),
                    contentDescription = null,
                    tint = leftIconColor,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable { onClickLeft?.invoke() }
                        .padding(horizontal = MaterialTheme.spacing.spacing16)
                )
            }

            rightIcon?.let {
                Icon(
                    painter = painterResource(it),
                    contentDescription = null,
                    tint = rightIconColor,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable { onClickRight?.invoke() }
                        .padding(horizontal = MaterialTheme.spacing.spacing16)
                )
            }

            title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge,
                    color = titleColor,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = MaterialTheme.spacing.spacing24),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            imageCenter?.let {
                Icon(
                    painter = painterResource(it),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
