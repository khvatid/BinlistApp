package com.khvatid.binlistapp.ui.screens.history

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.khvatid.binlistapp.ui.components.BinCard
import com.khvatid.binlistapp.ui.events.HistoryEvents

@Composable
fun HistoryScreen(viewModel: HistoryViewModel = hiltViewModel()) {
    LaunchedEffect(viewModel) {
        viewModel.obtainEvent(HistoryEvents.GetBinlistMap)
    }
    val uiState by viewModel.historyUiState
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        uiState?.binlistMap!!.forEach {
            item {
                SelectionContainer() {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        text = it.key, style = MaterialTheme.typography.labelLarge
                    )
                }
            }
            item {
                BinCard(
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 10.dp),
                    model = it.value
                )
            }
        }
        if (uiState?.binlistMap!!.isNotEmpty()) {
            item {
                TextButton(onClick = { viewModel.obtainEvent(HistoryEvents.ClearBinlist) }) {
                    Text(text = "Clear history")
                }
            }
        }
    }
}