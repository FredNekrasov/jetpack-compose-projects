package com.fredprojects.helloworld.presentation.features.jumps

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.presentation.R
import com.fredprojects.helloworld.presentation.core.*
import com.fredprojects.helloworld.presentation.features.jumps.vm.JDEvents
import com.fredprojects.helloworld.presentation.features.jumps.vm.JDState
import kotlinx.coroutines.launch

@Composable
fun JDListScreen(
    state: JDState,
    onEvent: (JDEvents) -> Unit,
    toJumpingRope: Action
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val deleteMessage = stringResource(R.string.deletedRecord)
    var isShowDialog by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { FredTopBar(true, { onEvent(JDEvents.ToggleSortSection) }, state.isSortingSectionVisible) },
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
                items(state.jds) { jumpData ->
                    JDListItem(
                        jumpData = jumpData,
                        onDeleteClick = {
                            onEvent(JDEvents.DeleteJD(jumpData))
                            scope.launch { snackbarHostState.showSnackbar(deleteMessage) }
                        },
                        Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 2.dp).clickable {
                            onEvent(JDEvents.GetJD(jumpData.id ?: -1))
                            isShowDialog = true
                        }
                    )
                }
            }
        }
    }
    if(isShowDialog) JDDialog(state, closeDialog = { isShowDialog = false }) { onEvent(JDEvents.UpsertJD(it)) }
}