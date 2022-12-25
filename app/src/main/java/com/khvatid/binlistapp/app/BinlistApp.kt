package com.khvatid.binlistapp.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.khvatid.binlistapp.ui.components.BinlistTopBar
import com.khvatid.binlistapp.ui.screens.history.HistoryScreen
import com.khvatid.binlistapp.ui.screens.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinlistApp() {
    val state = rememberAppState()
    Scaffold(
        topBar = {
            BinlistTopBar(
                route = state.currentRoute,
                onBackPress = { state.popBack() },
                onHistoryOpen = { state.navigate(UiRoute.HISTORY) })
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = state.navController,
            startDestination = "main"
        ) {
            binlistNavGraph()
        }
    }

}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    viewModelStoreOwner: ViewModelStoreOwner? = LocalViewModelStoreOwner.current
) = remember {
    BinlistAppState(
        navController = navController,
        viewModelStoreOwner = viewModelStoreOwner!!
    )
}

object UiRoute {
    const val HOME = "home_screen"
    const val HISTORY = "history_screen"
}

fun NavGraphBuilder.binlistNavGraph() {
    navigation(startDestination = UiRoute.HOME, builder = { mainGraph() }, route = "main")
}

fun NavGraphBuilder.mainGraph() {
    composable(route = UiRoute.HOME) {
        HomeScreen()
    }
    composable(route = UiRoute.HISTORY) {
        HistoryScreen()
    }
}