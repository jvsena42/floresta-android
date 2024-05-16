package com.florestaandroid.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florestaandroid.data.model.TransactionType
import com.florestaandroid.domain.model.TransactionModel
import com.florestaandroid.domain.model.WalletModel
import com.florestaandroid.presentation.utils.EventFlow
import com.florestaandroid.presentation.utils.EventFlowImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(),
    EventFlow<HomeViewModel.HomeEvents> by EventFlowImpl() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: HomeActions) {
        when (action) {
            is HomeActions.OnCLickWallet -> viewModelScope.sendEvent(
                HomeEvents.NavigateWalletScreen(
                    action.walletModel.id
                )
            )

            HomeActions.Setup -> setup()
        }
    }

    private fun setup() {
        _uiState.update { //TODO REMOVE MOCK
            it.copy(
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
        }
    }

    sealed interface HomeEvents {
        data class NavigateWalletScreen(val walletId: String) : HomeEvents
    }

    sealed interface HomeActions {
        data object Setup : HomeActions
        data class OnCLickWallet(val walletModel: WalletModel) : HomeActions
    }
}