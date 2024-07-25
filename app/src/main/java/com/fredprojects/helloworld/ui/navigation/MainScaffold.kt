package com.fredprojects.helloworld.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fredprojects.helloworld.presentation.core.Action
import com.fredprojects.helloworld.presentation.core.FredTopBar

@Composable
fun MainScaffold(
    controller: NavHostController,
    openDrawer: Action,
    content: @Composable (PaddingValues, SnackbarHostState) -> Unit
) {
    val destination = controller.currentBackStackEntryAsState().value?.destination
    val snackbarHostState by rememberSaveable { mutableStateOf(SnackbarHostState()) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            FredTopBar(
                condition = (destination?.route != null) && (destination.parent?.startDestinationRoute != destination.route),
                openDrawer
            ) { controller.navigateUp() }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { content(it, snackbarHostState) }
}