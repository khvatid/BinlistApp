package com.khvatid.binlistapp.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class BinlistAppState(
    val navController: NavHostController,
    val viewModelStoreOwner: ViewModelStoreOwner,
) {


    // -------------------- Navigation block -------------------------
    val currentRoute: String?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route

    private val route: String?
        get() = navController.currentDestination?.route


    fun navigate(route: String) {
        navController.navigate(route)
    }

    fun popBack() {
        navController.popBackStack()
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) {
                inclusive = true
            }
        }
    }
    // -------------------- Global UI --------------------------------
}