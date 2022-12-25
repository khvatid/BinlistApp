package com.khvatid.binlistapp.ui.screens.home

import android.app.Application
import android.content.Context
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.OffsetMapping
import com.khvatid.binlistapp.domain.model.BinlistModel

data class HomeUiState(
    val binlistModel: BinlistModel? = null,
    val numberBIN: String = ""
)
