package com.florestaandroid.presentation.screens.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florestaandroid.data.model.TransactionType
import com.florestaandroid.domain.model.TransactionModel
import com.florestaandroid.presentation.utils.EventFlow
import com.florestaandroid.presentation.utils.EventFlowImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor() : ViewModel(),
    EventFlow<WalletViewModel.WalletEvents> by EventFlowImpl() {

    private val _uiState = MutableStateFlow(WalletUIState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: WalletActions) {
        when (action) {
            WalletActions.OnBackPressed -> viewModelScope.sendEvent(WalletEvents.NavigateBack)
            WalletActions.OnClickDelete -> deleteWallet()
            is WalletActions.Setup -> setup(action.walletId)
        }
    }

    private fun setup(walletId: String?) { // TODO REMOVE MOCK
        _uiState.update { it.copy(
            name = "Wallet of satoshi",
            balanceBTC = "2.000987",
            transactions = listOf(
                TransactionModel(
                    type = TransactionType.SENT,
                    amount = "8.00098896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.WAITING,
                    amount = "8.00098896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.RECEIVED,
                    amount = "8.00098896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.SENT,
                    amount = "8.00098896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.WAITING,
                    amount = "8.00098896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.RECEIVED,
                    amount = "8.00098896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.SENT,
                    amount = "8.00098896",
                    date = "05/11/2024 15:59"
                ),
                TransactionModel(
                    type = TransactionType.SENT,
                    amount = "8.00098896",
                    date = "05/11/2024 15:59"
                ),
            )
        ) }
    }

    private fun deleteWallet() {

    }

    sealed interface WalletEvents {
        data object NavigateBack : WalletEvents
    }

    sealed interface WalletActions {
        data class Setup(val walletId: String?) : WalletActions
        data object OnBackPressed : WalletActions
        data object OnClickDelete : WalletActions
    }
}