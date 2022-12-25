package com.khvatid.binlistapp.ui.screens.history

import com.khvatid.binlistapp.domain.model.BinlistModel

data class HistoryUiState(
    val binlistMap: Map<String, BinlistModel>
)