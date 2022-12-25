package com.khvatid.binlistapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.khvatid.binlistapp.app.UiRoute

@Composable
fun BinlistTopBar(route: String?, onBackPress: () -> Unit, onHistoryOpen: () -> Unit) {
    when (route) {
        UiRoute.HOME -> {
            HomeTopBar(onHistoryOpen = onHistoryOpen)
        }
        UiRoute.HISTORY -> {
            HistoryTopBar(onBackPress = onBackPress)
        }
        else -> {
            HomeTopBar(onHistoryOpen = onHistoryOpen)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HistoryTopBar(onBackPress: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackPress) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "go back")
            }
        }, title = {
            Text(text = "Binlist App")
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar(onHistoryOpen: () -> Unit) {
    TopAppBar(actions = {
        IconButton(onClick = onHistoryOpen) {
            Icon(imageVector = Icons.Default.List, contentDescription = "history")
        }
    }, title = {
        Text(text = "Binlist App")
    })
}