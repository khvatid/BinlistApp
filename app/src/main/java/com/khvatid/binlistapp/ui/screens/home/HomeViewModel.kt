package com.khvatid.binlistapp.ui.screens.home

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.khvatid.binlistapp.domain.model.BinlistModel
import com.khvatid.binlistapp.domain.usecase.GetRemoteBinlistModelUseCase
import com.khvatid.binlistapp.domain.usecase.SaveBinlistToDatabaseUseCase
import com.khvatid.binlistapp.ui.events.HomeEvents
import com.khvatid.binlistapp.ui.screens.BinlistViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRemoteBinlistModel: GetRemoteBinlistModelUseCase,
    private val saveBinlistToDatabaseUseCase: SaveBinlistToDatabaseUseCase
) : BinlistViewModel<HomeEvents>() {

    private val _homeUiState: MutableLiveData<HomeUiState> = MutableLiveData(HomeUiState())
    val homeUiState: State<HomeUiState?> @Composable get() = _homeUiState.observeAsState()


    override fun obtainEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.ChangeBIN -> {
                reduce(event)
            }
            is HomeEvents.GetBinlist -> {
                val value = _homeUiState.value!!.numberBIN.toIntOrNull()
                if (value != null) {
                    reduce(event, value)
                }
            }
            is HomeEvents.OpenUrlActivity -> {
                reduce(event)
            }
            is HomeEvents.OpenPhoneActivity -> {
                reduce(event)
            }
            is HomeEvents.OpenMapActivity -> {
                reduce(event)
            }
        }
    }

    private fun reduce(event: HomeEvents.ChangeBIN) {
        _homeUiState.value = _homeUiState.value!!.copy(numberBIN = event.value)
    }

    private fun reduce(event: HomeEvents.GetBinlist, value: Int) {
        viewModelScope.launch {
            _homeUiState.postValue(
                _homeUiState.value!!.copy(
                    binlistModel = getRemoteBinlistModel.execute(
                        value
                    )
                )
            )
            delay(1000)
            saveBinlistToDatabaseUseCase.execute(
                key = _homeUiState.value!!.numberBIN, model = _homeUiState.value!!.binlistModel
            )
        }
    }

    private fun reduce(event: HomeEvents.OpenUrlActivity) {
        val intent: Intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://${_homeUiState.value!!.binlistModel!!.bank!!.url}")
        )
        startActivity(event.context, intent, null)
    }

    private fun reduce(event: HomeEvents.OpenPhoneActivity) {
        val intent: Intent = Intent(
            Intent.ACTION_DIAL, Uri.parse("tel:${_homeUiState.value!!.binlistModel!!.bank!!.phone}")
        )
        startActivity(event.context, intent, null)
    }

    private fun reduce(event: HomeEvents.OpenMapActivity) {
        val intent: Intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("geo:${_homeUiState.value!!.binlistModel!!.country!!.latitude!!},${_homeUiState.value!!.binlistModel!!.country!!.longitude!!}")
        )
        startActivity(event.context, intent, null)
    }
}