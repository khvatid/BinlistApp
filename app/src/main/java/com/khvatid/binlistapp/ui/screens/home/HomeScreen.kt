package com.khvatid.binlistapp.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.khvatid.binlistapp.ui.components.BinCard
import com.khvatid.binlistapp.ui.events.HomeEvents
import com.khvatid.binlistapp.ui.events.HomeEvents.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.homeUiState
    val focus = LocalFocusManager.current
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(7f),
                value = uiState!!.numberBIN,
                singleLine = true,
                onValueChange = { viewModel.obtainEvent(ChangeBIN(it)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    viewModel.obtainEvent(GetBinlist)
                    focus.clearFocus()
                }),
                label = {
                    Text(text = "Enter the first digits of a card number (BIN/IIN)")
                }
            )
            IconButton(modifier = Modifier.weight(1f),
                onClick = { viewModel.obtainEvent(GetBinlist) }) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "done")
            }
        }
        AnimatedVisibility(visible = uiState!!.binlistModel != null) {
            if (uiState!!.binlistModel != null) {
                BinCard(
                    modifier = Modifier.padding(10.dp),
                    model = uiState!!.binlistModel!!,
                    onUrlClick = {
                        viewModel.obtainEvent(HomeEvents.OpenUrlActivity(context))
                    },
                    onPhoneClick = {
                        viewModel.obtainEvent(HomeEvents.OpenPhoneActivity(context))
                    },
                    onLocationClick = {
                        viewModel.obtainEvent(HomeEvents.OpenMapActivity(context))
                    }
                )
            }
        }

    }

}