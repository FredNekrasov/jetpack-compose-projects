package com.fredprojects.helloworld.ui.navigation

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.fredprojects.helloworld.presentation.core.FredNavigationDrawerItem
import kotlinx.coroutines.launch

@Composable
fun MainEntryPoint(
    onTakePicture: () -> Uri,
    fibonacciScreen: @Composable () -> Unit
) {
    val controller = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    ModalNavigationDrawer(
        drawerContent = {
            FredModalSheet(selectedItemIndex) { index, route ->
                controller.navigate(route)
                selectedItemIndex = index
                scope.launch { drawerState.close() }
            }
        },
        modifier = Modifier.fillMaxSize(),
        drawerState = drawerState
    ) {
        MainScaffold(controller, openDrawer = { scope.launch { drawerState.open() } }) { paddingValues, snackbarHostState ->
            MainNavHost(controller, snackbarHostState, fibonacciScreen, onTakePicture, Modifier.fillMaxSize().padding(paddingValues))
        }
    }
}
@Composable
private fun FredModalSheet(selectedItemIndex: Int, navigateTo: (Int, String) -> Unit) {
    ModalDrawerSheet {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()), Arrangement.Center) {
            Routes.navItems.forEachIndexed { index, route ->
                FredNavigationDrawerItem(route, selected = index == selectedItemIndex) { navigateTo(index, route) }
            }
        }
    }
}