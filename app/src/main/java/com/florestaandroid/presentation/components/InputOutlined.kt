package com.florestaandroid.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InputOutlined(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholderText: String? = null,
    labelText: String? = null,
    errorText: String? = null,
    singleLine: Boolean = true,
    trailingIcon: @Composable() (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onFocusChanged: (Boolean) -> Unit = { }
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value,
            singleLine = singleLine,
            onValueChange = onValueChange,
            placeholder = { placeholderText?.let { Text(text = it) } },
            label = { labelText?.let { Text(text = it) } },
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            supportingText = {
                if (!errorText.isNullOrBlank()) {
                    Text(
                        text = errorText,
                        color = Color.Red
                    )
                }
            },
            modifier = Modifier
                .onFocusChanged { focusState -> onFocusChanged(focusState.isFocused) }
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview1() {
    InputOutlined(value = "", placeholderText = "Texto placeholder", onValueChange = {}) {}
}

@Preview(showBackground = true)
@Composable
private fun Preview2() {
    InputOutlined(
        value = "Uzimaki Naruto",
        placeholderText = "Texto placeholder",
        onValueChange = {}) {}
}

@Preview(showBackground = true)
@Composable
private fun Preview3() {
    InputOutlined(
        value = "Uzimaki Naruto",
        placeholderText = "Texto placeholder",
        errorText = "Mensagem de erro",
        onValueChange = {}) {}
}