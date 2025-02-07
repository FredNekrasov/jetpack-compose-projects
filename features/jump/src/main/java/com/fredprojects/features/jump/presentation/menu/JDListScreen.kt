package com.fredprojects.features.jump.presentation.menu

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.jump.presentation.JDSortingSection
import com.fredprojects.features.jump.presentation.menu.vm.JDEvents
import com.fredprojects.features.jump.presentation.menu.vm.JDState
import kotlinx.coroutines.launch

@Composable
fun JDListScreen(
    state: JDState,
    onEvent: (JDEvents) -> Unit,
    goBack: Action,
    toJumpingRope: Action
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val deleteMessage = stringResource(R.string.deletedRecord)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            FredTopBar(goBack) {
                if(!state.isSortingSectionVisible) {
                    FredIconButton({ onEvent(JDEvents.ToggleSortSection) }, Icons.Default.KeyboardArrowDown)
                } else FredIconButton({ onEvent(JDEvents.ToggleSortSection) }, Icons.Default.KeyboardArrowUp)
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = { FredFloatingActionButton(Icons.Default.Add, toJumpingRope) }
    ) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedVisibility(
                state.isSortingSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) { JDSortingSection(state.sortType) { onEvent(JDEvents.Sort(it)) } }
            LazyColumn(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                items(state.jds, key = { it.hashCode() }) { jumpData ->
                    JDListItem(
                        jumpData = jumpData,
                        onDeleteClick = {
                            onEvent(JDEvents.DeleteJD(jumpData))
                            scope.launch { snackbarHostState.showSnackbar(deleteMessage) }
                        },
                        Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 2.dp).clickable {
                            onEvent(JDEvents.GetJD(jumpData))
                            onEvent(JDEvents.SwitchingDialog)
                        }
                    )
                }
            }
        }
    }
}