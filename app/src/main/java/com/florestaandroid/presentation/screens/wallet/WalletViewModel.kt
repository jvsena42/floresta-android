package com.florestaandroid.presentation.screens.wallet

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florestaandroid.data.datasource.NodeClient
import com.florestaandroid.data.model.TransactionType
import com.florestaandroid.domain.daemon.FlorestaDaemon
import com.florestaandroid.domain.model.TransactionModel
import com.florestaandroid.presentation.utils.EventFlow
import com.florestaandroid.presentation.utils.EventFlowImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val florestaDaemon: FlorestaDaemon,
    private val nodeClient: NodeClient
) : ViewModel(),
    EventFlow<WalletViewModel.WalletEvents> by EventFlowImpl() {

    private val _uiState = MutableStateFlow(WalletUIState())
    val uiState = _uiState.asStateFlow()

    init {
        florestaDaemon.start()
    }

    fun onAction(action: WalletActions) {
        when (action) {
            WalletActions.OnBackPressed -> viewModelScope.sendEvent(WalletEvents.NavigateBack)
            WalletActions.OnClickDelete -> deleteWallet()
            is WalletActions.Setup -> setup(action.walletId)
        }
    }

    private fun setup(walletId: String?) = viewModelScope.launch(Dispatchers.IO) { // TODO REMOVE MOCK
            nodeClient.getBalance().collect {
                it.onSuccess { response ->
                    _uiState.update { it.copy(
                        balanceBTC = response.confirmed.toString()
                    ) }
                }.onFailure { error ->
                    Log.d("WalletViewModel", "setup: $error")
                }
            }

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