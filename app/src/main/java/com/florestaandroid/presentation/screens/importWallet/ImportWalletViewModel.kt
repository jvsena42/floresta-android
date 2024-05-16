package com.florestaandroid.presentation.screens.importWallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florestaandroid.presentation.utils.EventFlow
import com.florestaandroid.presentation.utils.EventFlowImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ImportWalletViewModel @Inject constructor() : ViewModel(),
    EventFlow<ImportWalletViewModel.ImportWalletEvents> by EventFlowImpl() {

    private val _uiState = MutableStateFlow(ImportWalletUIState())
    val uiState = _uiState.asStateFlow()
    fun onAction(action: ImportWalletActions) {
        when (action) {
            ImportWalletActions.OnBackPressed -> viewModelScope.sendEvent(ImportWalletEvents.NavigateBack)
            ImportWalletActions.OnClickSave -> viewModelScope.sendEvent(ImportWalletEvents.NavigateBack)
            is ImportWalletActions.OnTextChanged -> handleTextChanged(action)
        }
    }

    private fun handleTextChanged(action: ImportWalletActions.OnTextChanged) {
        when (action.input) {
            is Input.WalletKey -> _uiState.update { it.copy(xpubKey = action.input.text) }
            is Input.WalletName -> _uiState.update { it.copy(walletName = action.input.text) }
        }
    }

    sealed interface ImportWalletEvents {
        data object NavigateBack : ImportWalletEvents
    }

    sealed interface ImportWalletActions {
        data object OnBackPressed : ImportWalletActions
        data object OnClickSave : ImportWalletActions
        data class OnTextChanged(val input: Input) : ImportWalletActions
    }

    sealed interface Input {
        data class WalletName(val text: String) : Input
        data class WalletKey(val text: String) : Input

    }
}