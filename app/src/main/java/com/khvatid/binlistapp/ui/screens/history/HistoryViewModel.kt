package com.khvatid.binlistapp.ui.screens.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.khvatid.binlistapp.domain.usecase.ClearBinlistUseCase
import com.khvatid.binlistapp.domain.usecase.GetAllBinlistFromDatabaseUseCase
import com.khvatid.binlistapp.ui.events.HistoryEvents
import com.khvatid.binlistapp.ui.screens.BinlistViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getAllBinlistFromDatabaseUseCase: GetAllBinlistFromDatabaseUseCase,
    private val clearBinlistUseCase: ClearBinlistUseCase
) :
    BinlistViewModel<HistoryEvents>() {

    private val _historyUiState: MutableLiveData<HistoryUiState> =
        MutableLiveData(HistoryUiState(emptyMap()))
    val historyUiState: State<HistoryUiState?> @Composable get() = _historyUiState.observeAsState()


    override fun obtainEvent(event: HistoryEvents) {
        when (event) {
            is HistoryEvents.GetBinlistMap -> {
                reduce(event)
            }
            is HistoryEvents.ClearBinlist -> {
                reduce(event)
            }
        }
    }

    private fun reduce(event: HistoryEvents.GetBinlistMap) {
        viewModelScope.launch {
            _historyUiState.value =
                _historyUiState.value?.copy(binlistMap = getAllBinlistFromDatabaseUseCase.execute())
        }
    }

    private fun reduce(event: HistoryEvents.ClearBinlist) {
        viewModelScope.launch {
            _historyUiState.value = _historyUiState.value?.copy(binlistMap = emptyMap())
            clearBinlistUseCase.execute()
        }
    }
}